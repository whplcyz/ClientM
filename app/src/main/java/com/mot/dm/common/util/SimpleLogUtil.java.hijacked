/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2015 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
------------------------------------------------------------------------------*/

package com.mot.dm.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogUtil
{
    Logger fgLogger = null;
    FileHandler fh = null;
    static SimpleLogUtil instantce = null;
    
    private SimpleLogUtil()
    {
        String logPath = getLogPath();
        
        try
        {
            //Delete the lock flag before create new log file.
            //Actually, the lock file will be removed when finalize() is called, however, it does not works in Tomcat...
            //So, I need to delete it manually.
            File lockFile = new File(logPath + ".lck");
            if(lockFile.exists()) lockFile.delete();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        try
        {
            fh = new FileHandler(logPath, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            fgLogger = Logger.getLogger("Cll_Load_Log");
            fgLogger.addHandler(fh); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static synchronized  Logger getLogger()
    {
        if (null == instantce)
        {
            instantce = new SimpleLogUtil(); 
        }
        
        return instantce.fgLogger;
    }
    
    protected synchronized void finalize() throws java.lang.Throwable
    {
        if(instantce != null)
        {
            instantce.fh.close();
            instantce = null;
        }
        
        super.finalize();
    }
   

    private static String getLogPath()
    {
        String workDirctory = System.getProperty("user.dir");
        if (workDirctory.lastIndexOf("Tomcat") != -1) // server
        {
            return getServerLogPath(workDirctory);
        }
        else // default
        {
            return getDefaultLogPath(workDirctory);
        }

    }

    private static String getServerLogPath(String workDirctory)
    {
        try
        {
            // try to get the full path of "../iTMServer/iTMServer.property"
            String rootPath = workDirctory.substring(0, workDirctory.lastIndexOf("Tomcat"));

            // get iTM Data folder path
            File propertyFile = new File(rootPath, "iTMServer.property");
            Properties iTMServerProps = new Properties();
            FileInputStream iTMServerFis = new FileInputStream(propertyFile);
            iTMServerProps.load(iTMServerFis);
            iTMServerFis.close();

            String iTMDataFolder = "";
            iTMDataFolder = iTMServerProps.getProperty("REPOSITORY_DIR");

            File cllLogPath = new File(iTMDataFolder, "logs\\cll_lib_load.log");
            return cllLogPath.getAbsolutePath();
        }
        catch (Exception ex)
        {
            return getDefaultLogPath(workDirctory);
        }
    }


    private static String getDefaultLogPath(String workDirctory)
    {
        File cllLogPath = new File(workDirctory, "logs\\cll_lib_load.log");
        return cllLogPath.getAbsolutePath();
    }
}
