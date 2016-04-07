package com.mot.dm.common.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


public class FileLocker
{
    private  File flagFile;
    private  FileLock backupLock = null; 
    private  RandomAccessFile backupRaf = null;
    private  FileChannel backupFc = null;
    
    public FileLocker(String filePath)
    {
        this.flagFile =  new File(filePath);
    }
    public FileLocker(String dirName, String fileName)
    {        
        this.flagFile = new File(dirName, fileName);
    }
    
    public boolean isLocked()
    {
        boolean running = true;
    
        RandomAccessFile raf = null;
        FileChannel fc = null;
        FileLock fl = null; 
        
        try
        {
            if(!flagFile.exists())
            {       
                flagFile.createNewFile();       
            }
    
            raf = new RandomAccessFile(flagFile, "rws");
            fc = raf.getChannel();
            fl = fc.tryLock();
                
            if(fl != null && fl.isValid())
            {
                running = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            running = true;
        }
        finally
        {
            try
            {
                if(fl != null)
                    fl.close();
                if(fc != null)
                    fc.close();
                if(raf != null)
                    raf.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
         
        return running;
    }
    
    public boolean createLock()
    {
        boolean checkResult = false;
        try
        {
            if(!flagFile.exists())
            {       
                flagFile.createNewFile();       
            }

            backupRaf = new RandomAccessFile(flagFile, "rws");
            backupFc = backupRaf.getChannel();
            backupLock = backupFc.tryLock();
            
            if(backupLock != null && backupLock.isValid())
            {
                checkResult = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            checkResult = false;
        }
        return checkResult;
    }
    
    private  void releaseLock()
    {
        // Release backup lock
        try
        {
            if(backupLock != null)
            {
                backupLock.release();
                backupLock = null;
            }
            if(backupFc != null)
            {
                backupFc.close();
                backupFc = null;
            }
            if(backupRaf != null)
            {
                backupRaf.close();
                backupRaf = null;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
  
    }
    
    protected void finalize()
    {
        releaseLock();
    }
    
}
