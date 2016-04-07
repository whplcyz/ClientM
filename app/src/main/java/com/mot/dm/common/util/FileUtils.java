/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2011-2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
12/01/08   chad001               CCMPD00962248 Export codeplug, move device and folder
12/03/08   chad001               CCMPD01003009 Throw exception is source file
                                               not exist 
03/04/08  chad001                CCMPD01010519 add cleanup method  
24/04/08  chad001               CCMPD01027315 Handle runtime exception
17/06/08 chad001                CCMPD01051225 Compress codeplug. All methods about
                                              repository operations are taken from
                                              repository service, repository manager
                                              and FlatFileProxy class. 
08/08/08  Hai Dong              CCMPD01078628  Fix export codeplug error. Remove 
                                               the renameFile() method
27/01/09  Hai Dong               CCMPD01125953 Fix Klocwork findings                                               
20/Nov/09  Kim mortensen         CCMPD01287398 Add Notification message to 
                                               JobInfo
07/Dec/09  Michael Leismann      CCMPD01287031 Jobs on memory stick feature:
                                               Added method that removes a
                                               dir path from a file path                                               
10/May/11  Michael Leismann      CCMPD01505986 iTM 5.0: Proxy lib part of Panda
                                               integration - off-line and
                                               stand-alone part
                                               Clean-up: Removed methods that
                                               were not being used
26/May/11  Michael Leismann      CCMPD01505987 iTM 5.0: ICD changes 
                                               Removed methods
21/Jun/11  cjh102                CCMPD01518983 Added and updated methods                                                      
8/Jul/11   cjh102                CCMPD01525915 Added and updated methods                                                      
21/Jul/11  Michael Leismann      CCMPD01534934 iTM 5.0: Moved getTmpPath method
                                               functionality to this class from
                                               client.dmImpl.util.FileUtil
28/Feb/13  tqfn38                CCMPD01748338 The warning icon for Access code shall be hidden if user does not select collect DMS
20/Aug/13  mjr638                CCMPD01803292 Enhance client has breakpoint resume ability, during downloading RPK file
28/3/14    wch378               CCMPD01889533 develop flexera license.            
25/11/14   qngv36               CMPD01948694  Collect log for iTM Client                                   
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.exception.FlexeraLicenseException;
import com.mot.dm.common.util.exception.ServiceException;

/**
 * @Version 1.0 2007 July 5
 * @author w23021
 */
public final class FileUtils
{
    private FileUtils()
    {
        
    }
    // Logger
    private static Logger logger = Logger.getLogger("com.mot.dm");
    private static final String CLASS_NAME = "FileUtils";

    public static final String ZIP_FILE_EXTENSION = ".zip";
    private static final String HELP_ZIP_FILE_EXTENSION = ".ihlp";

    public static final int BUFFER = 2048;

    // The temporary path; see getTmpPath method for details
    private static String tmpPath = null;
    
    private static List<String> supporttedLanguageList;
    
    static
    {
        supporttedLanguageList = new ArrayList<String>();
        supporttedLanguageList.add(CommonConstants.LANG_ENGLISH);
        supporttedLanguageList.add(CommonConstants.LANG_GERMAN);
        supporttedLanguageList.add(CommonConstants.LANG_PORTUGUESE);
        supporttedLanguageList.add(CommonConstants.LANG_SPANISH);        
    }

    public static void copyCodeplug(String sourcePath, String targetPath) throws ServiceException
    {
        InputStream inStream = null;
        FileOutputStream fs = null;

        try
        {
            int byteread = 0;

            inStream = new FileInputStream(sourcePath);
            fs = new FileOutputStream(targetPath);

            File sourceFile = new File(sourcePath);
            if (!sourceFile.exists())
            {
                closeOutputStream(fs);
                closeInputStream(inStream);
                throw new ServiceException("the source file not exist");
            }

            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1)
            {
                fs.write(buffer, 0, byteread);
            }

            closeOutputStream(fs);
            closeInputStream(inStream);
        }
        catch (IOException e)
        {
            closeOutputStream(fs);
            closeInputStream(inStream);
            throw new ServiceException("IOException", e);
        }
    }

    /**
     * Delete the given file, can't delete a not empty folder
     * !!! Caution: do not use this method to delete file in server repository (except for temporary file). 
     * !!! Use FileManager.deleteInRepository instead
     * 
     * @param filepath
     */
    public static boolean deleteFile(String filepath)
    {
        File file = new File(filepath);

        if (file.exists())
        {
            if(file.delete())
            {
                logger.info(FileUtils.class.getClass().getName() + " - delete file successful: " + filepath);
                return true;
            }
            else
                return false;
        }

        return false;
    }

    public static byte[] getBytesFromFile(String fileFullPath) throws IOException
    {
        File file = new File(fileFullPath);

        InputStream is = new FileInputStream(file);

        try
        {
            // Get the size of the file
            long length = file.length();

            if (length > Integer.MAX_VALUE)
            {

                throw new IOException("File is too large to process:" + file.getName());
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = is.read(bytes, offset, bytes.length - offset);
            while ((offset < bytes.length) && (numRead >= 0))
            {
                offset += numRead;
                numRead = is.read(bytes, offset, bytes.length - offset);
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length)
            {
                throw new IOException("Could not completely read file:" + file.getName());
            }

            is.close();
            is = null;

            return bytes;
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage());
        }
        finally
        {
            if (is != null)
            {
                is.close();
            }
        }
    }

    /**
     * Create the file's folder path if it's not existed
     * 
     * @param fileFullPath
     */
    public static void CreateOwnFolder(String fileFullPath)
    {
        String sFileFullPath = fileFullPath.replace('\\', '/');

        if (sFileFullPath.indexOf("/") != -1)
        {
            String folderFullPath = sFileFullPath.substring(0, sFileFullPath.lastIndexOf("/"));

            File folder = new File(folderFullPath);

            if (!folder.exists())
            {
                folder.mkdirs();
            }
        }
    }

    /**
     * 
     * @param folderFullPath
     *            the folder full path
     */
    public static void CreateFolder(String folderFullPath)
    {
        File newFile = new File(folderFullPath);

        if (!newFile.exists())
        {
            newFile.mkdirs();
        }
    }

    /**
     * 
     * @param fileOldPath
     *            the file full path
     * @param fileNewPath
     *            the file full path
     */
    public static void CopyFile(String fileOldPath, String fileNewPath) throws IOException
    {
        int byteread = 0;
        File oldfile = new File(fileOldPath);
        InputStream inStream = null;
        FileOutputStream fs = null;
        try
        {
            inStream = new FileInputStream(fileOldPath);
        }
        catch (IOException e)
        {
            throw new IOException("Got exception during instantiate IO stream");
        }

        try
        {
            fs = new FileOutputStream(fileNewPath);
        }
        catch (IOException e)
        {
            closeInputStream(inStream);
            throw new IOException("Got exception during instantiate IO stream");
        }

        if (oldfile.exists())
        {
            byte[] buffer = new byte[1444];
            try
            {
                while ((byteread = inStream.read(buffer)) != -1)
                {
                    fs.write(buffer, 0, byteread);
                }
            }
            catch (Exception e)
            {
                closeOutputStream(fs);
                closeInputStream(inStream);
            }

            closeOutputStream(fs);
            closeInputStream(inStream);
        }
        else
        {
            closeOutputStream(fs);
            closeInputStream(inStream);
            throw new FileNotFoundException("File not found: " + fileOldPath);
        }
    }

    /**
     * Return a list filenames in the folder
     * 
     * @param folderPath
     * @return Returns null if the folder is empty, otherwise it returns the
     *         list of files/folders in the folder.
     */
    public static String[] getFolderContent(String folderPath)
    {
        return getFolderContent(folderPath, null);
    }

    /**
     * Return a list filenames in the folder which satisfy the constraints in
     * the filename filter
     * 
     * @param folderPath
     * @param filenameFilter
     *            A FilnameFilter object, which defines the pathern the files
     *            must satisfy. If set to null all files in the directory will
     *            be returned
     * @return Returns null if the folder is empty, otherwise it returns the
     *         list of files/folders in the folder.
     */
    public static String[] getFolderContent(String folderPath, FilenameFilter filenameFilter)
    {
        File myFolder = new File(folderPath);

        String[] files = myFolder.list(filenameFilter);
        if (files != null && files.length > 0)
            return files;
        else
            return null;
    }

    /**
     * Delete a folder and all it's files
     * !!! Caution: do not use this function to delete folder in server repository (except for temporary folder). 
     * !!! Use FolderManager.deleteInRepository instead
     * 
     * @param folderPath
     */
    public static void DeleteFolder(String folderPath)
    {
        DeleteAllFileUnderFolder(folderPath);

        File myFolder = new File(folderPath);

        if (myFolder.exists())
        {
            myFolder.delete();
        }
    }

    /**
     * Delete all files under a folder
     * 
     * @param folderFullPath
     * @return boolean
     */
    public static boolean DeleteAllFileUnderFolder(String folderFullPath)
    {
        return DeleteAllFileUnderFolder(folderFullPath, null);
    }

    /**
     * Delete all files under a folder filter by file suffix.
     * 
     * @param folderFullPath
     * @param fileSuffix
     * @return boolean
     */
    public static boolean DeleteAllFileUnderFolder(String folderFullPath, String fileSuffix)
    {
        boolean bea = false;

        try
        {
            File folder = new File(folderFullPath);
    
            if (!folder.exists())
            {
                return bea;
            }
    
            if (!folder.isDirectory())
            {
                return bea;
            }
    
            File[] fileList = folder.listFiles();
            // No files under the folder, then it's fine.
            if (fileList == null || fileList.length == 0)
                return true;
    
            for (File file : fileList)
            {
                if(file.isFile())
                {
                    if (fileSuffix == null || fileSuffix.length()==0)
                    {
                        file.delete();
                    }
                    else if (file.getName().endsWith(fileSuffix))
                    {
                        file.delete();
                    }
                }
                else
                if (file.isDirectory())
                {
                    DeleteAllFileUnderFolder(file.getPath(), fileSuffix);                                
                    if (file.list() == null || file.list().length == 0)
                    {
                        DeleteFolder(file.getPath());
                    }
                    bea = true;
                }
            }
        }
        catch(NullPointerException ex)
        {
            logger.error(ex);
            return false;
        }

        return bea;
    }

    public static void wrtieFile(String fileFullPath, byte[] fileContents) throws IOException
    {
        if ((fileFullPath == null))
        {
            throw new IOException("File Path is null");
        }
        else if ((fileContents == null))
        {
            throw new IOException("File content is null");
        }

        String folderPath;

        if (fileFullPath.indexOf("/") != -1)
        {
            folderPath = fileFullPath.substring(0, fileFullPath.lastIndexOf("/"));
        }
        else
        {
            folderPath = fileFullPath.substring(0, fileFullPath.lastIndexOf("\\"));
        }

        File folder = new File(folderPath);

        if (!folder.exists())
        {
            folder.mkdirs();
        }

        FileOutputStream output = new FileOutputStream(fileFullPath);
        try
        {
            output.write(fileContents);
            output.flush();
        }
        catch (Exception e)
        {
            closeOutputStream(output);
            throw new IOException("File write fail");
        }
        closeOutputStream(output);

    }

    /**
     * Method for renaming files.
     * 
     * @param originalFile
     *            , Source file that needs to be renamed
     * @param newFile
     *            , destination filename.
     */
    public static boolean renameFile(String originalFile, String newFile)
    {
        if (originalFile == null || newFile == null)
        {
            return false;
        }
        
        File origin = new File(originalFile);
        File destination = new File(newFile);

        // Rename file (or directory)
        boolean success = origin.renameTo(destination);
        if (!success)
        {
            return false;
        }
        return true;
    }

    /**
     * Move all files in the srcFolder to the destinationFolder
     * 
     * @param srcFolder
     * @param destinationFolder
     */
    public static void moveFilesFromTo(String srcFolder, String destinationFolder, String fileSuffix)
    {
        String[] fileNames = null;

        if (fileSuffix == null || fileSuffix.length()==0)
        {
            fileNames = FileUtils.getFolderContent(srcFolder);
        }
        else
        {
            FilenameFilter filenameFilter = new ItmFilenameFilter(fileSuffix);
            fileNames = FileUtils.getFolderContent(srcFolder, filenameFilter);
        }
        if (fileNames != null && fileNames.length > 0)
        {
            for (String file : fileNames)
            {
                File f = new File(file);
                String oldPath = srcFolder + f.getName();
                String newPath = destinationFolder + f.getName();
                newPath = newPath.replace('\\', '/');
                oldPath = oldPath.replace('\\', '/');
                FileUtils.CreateOwnFolder(destinationFolder);
                FileUtils.renameFile(oldPath, newPath);
            }
        }
    }

    /**
     * Method to close output stream with try-catch to reduce duplicate code
     * 
     * @param output
     *            FileOutputStream
     */
    public static void closeOutputStream(FileOutputStream output)
    {
        try
        {
            if (output != null)
                output.close();
        }
        catch (Exception e)
        {
            return;
        }
    }

    /*
     * Method to close input stream with try-catch to reduce duplicate code
     * 
     * @param input InputStream
     */
    public static void closeInputStream(InputStream input)
    {
        try
        {
            if (input != null)
                input.close();
        }
        catch (Exception e)
        {
            return;
        }
    }

    /*
     * Delete temporary files created during edit, import codeplug, etc. we
     * don't care about if it fails
     */
    public static void cleanup(String filePath)
    {
        try
        {
            deleteFile(filePath);
        }
        catch (Exception e)
        {
            // should log
        }

    }

    /*
     * This method is used for getting a string type stack trace
     */
    public static void logStackTrace(Exception exception, Logger fgLogger)
    {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        fgLogger.error("Caught exception : " + exception.toString());
        for (int ii = 0; ii < stackTrace.length; ii++)
        {
            // we don't need whole page exception messages in error log
            // so only first 10 lines are directly related to the error
            if (ii == 10)
                break;
            fgLogger.error(" at " + stackTrace[ii].toString());
        }
    }
    
    /*
     * compress a file/directory to a ZIP format file
     * 
     * */
    public static void compress(String sourcePath, String outputZipFileName)  throws Exception 
    {
        List<String> sourcePathList = new ArrayList<String>();
        sourcePathList.add(sourcePath);
        
        compress(sourcePathList, outputZipFileName);
    }
    
    
    /**
     * 
     * Compress a collection of directories, 
     * such as we can put path A, path B into one zip file outputZipFileName
     * 
     * */
    public static void compress(List<String> sourcePathList, String outputZipFileName)  throws Exception 
    {
        if(sourcePathList == null || outputZipFileName == null)
        {
            throw new Exception("Invalid parameter: sourcePathList or outputZipFileName is null");
        }

        
        File outputFile = new File(outputZipFileName);
        if(outputFile.exists())
        {
            outputFile.delete();
        }
        
        FileOutputStream fileOutputStream = new FileOutputStream(outputZipFileName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
        
        try
        {
            for(String sourcePath: sourcePathList)
            {
                File sourceFile = new File(sourcePath);
                if(!sourceFile.exists())
                {
                    continue;
                }
                
                compress(sourceFile, out, "");
            }
        }
        catch(Exception ex)
        {
            if(null != out)
            {
                out.close(); 
            }
            
            if(null != fileOutputStream)
            {
                fileOutputStream.close();
            }
            
            throw ex;
        }
        
        if(null != out)
        {
            out.close(); 
        }
        
        if(null != fileOutputStream)
        {
            fileOutputStream.close();
        }
    }
    
    
    private static void compress(File file, ZipOutputStream out, String basePathName) throws Exception
    {
        if(file.isDirectory())
        {
            compressDir(file, out, basePathName);
        }
        else
        {
            compressFile(file, out, basePathName);
        }
    }
    
    
    private static void compressDir(File dir, ZipOutputStream out, String basePathName) throws Exception 
    {
        if (dir != null)
        {
            if (!dir.exists())
            {
                return;
            }

            File[] files = dir.listFiles();
            if(files != null && files.length > 0)
            {
                for (File file : files)
                {
                    compress(file, out, basePathName + dir.getName() + File.separator);
                }
            }
        }
    }
    
    private static void compressFile( File file,  ZipOutputStream out, String basePathName) throws Exception
    {
        if(!file.exists())
        {
            return;
        }
        
        FileInputStream fileInputStream;
        try
        {
            fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferInputStream = new BufferedInputStream(fileInputStream, BUFFER);
            
            ZipEntry entry = new ZipEntry(basePathName + file.getName());
            out.putNextEntry(entry);
            
            int count;
            byte[] data = new byte[BUFFER];
            while((count = bufferInputStream.read(data, 0, BUFFER)) != -1)
            {
                out.write(data, 0, count);
            }
            
            
            bufferInputStream.close();
        }
        catch (FileNotFoundException e)
        {
            throw e;
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    /*
     * Compress the file in fromFilePath into ZIP format to toFilePath
     */
    public static void zip(String fromFilePath, String toFilePath) throws Exception
    {
        FileInputStream fi = null;
        FileOutputStream dest = null;
        try
        {
            fi = new FileInputStream(fromFilePath);
            BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(fromFilePath);
            dest = new FileOutputStream(toFilePath);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            out.putNextEntry(entry);
            int count;
            byte [] data = new byte[BUFFER];

            while ((count = origin.read(data, 0, BUFFER)) != -1)
            {
                out.write(data, 0, count);
            }

            origin.close();
            out.close();
            dest.close();
        }
        catch (Exception e)
        {
            closeOutputStream(dest);
            closeInputStream(fi);
            throw new Exception(e);
        }
    }
    
    /**
     * Unzip files from a .zip file to a directory structure
     * 
     * @param String
     *            fromFile, the source zip file
     * @param String
     *            toDir, the target directory
     */
    // TODO: Maybe this code should be moved to a common code library
    public static boolean unzipToPath(String fromFile, String toDir)
    {
        // Code from jobs on memory stick, unzip function
        // Maybe move to a common code library
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ZipFile zipFile = null;
        
        if (toDir.endsWith("\\") == false && toDir.endsWith("/") == false)
        {
            toDir = toDir + System.getProperty("file.separator");
        }
        
        try
        {
            // Open the zip file
            zipFile = new ZipFile(fromFile);
            Enumeration<?> entries;
            entries = zipFile.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String zipEntryFileName = zipEntry.getName();

                // Don't extract folders
                if (zipEntryFileName.endsWith("\\") == true || zipEntryFileName.endsWith("/") == true)
                {
                    continue;
                }

                zipEntryFileName = toDir + zipEntryFileName;

                // Create any required folder
                FileUtils.CreateOwnFolder(zipEntryFileName);                

                bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));

                byte[] buffer = new byte[FileUtils.BUFFER];

                // Klocwork finding should be ignored
                fileOutputStream = new FileOutputStream(zipEntryFileName);
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream, buffer.length);

                int size;
                while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) != -1)
                {
                    bufferedOutputStream.write(buffer, 0, size);
                }

                // Close streams
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
            }
        }
        catch (Exception e)
        {
            // An error occurred when extracting
            logger.error("Failed to unzip file: " + fromFile + " to directory: " + toDir, e);            
            return false;
        }
        finally
        {
            // Close all files and streams
            try
            {
                if (zipFile != null)
                {
                    zipFile.close();
                }
            }
            catch (Exception e)
            {
            }

            try
            {
                if (bufferedInputStream != null)
                {
                    bufferedInputStream.close();
                }
            }
            catch (Exception e)
            {
            }

            try
            {
                if (fileOutputStream != null)
                {
                    fileOutputStream.close();
                }
            }
            catch (Exception e)
            {
                // Do nothing
            }

            try
            {
                if (bufferedOutputStream != null)
                {
                    bufferedOutputStream.close();
                }
            }
            catch (Exception e)
            {
                // Do nothing
            }
        }
        return true;
    }

    /*
     * Unzip the compressed file in fromZipPath to toFilePath
     */
    public static void unzipToFile(String fromZipPath, String toFilePath) throws Exception
    {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(fromZipPath);
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }

        try
        {
            fos = new FileOutputStream(toFilePath);
        }
        catch (Exception e)
        {
            closeInputStream(fis);
            throw new Exception(e);
        }

        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));

        try
        {
            int count;
            byte [] data = new byte[BUFFER];
            @SuppressWarnings("unused")
            ZipEntry entry = zis.getNextEntry();
            while ((count = zis.read(data, 0, BUFFER)) != -1)
            {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
            zis.close();
            fis.close();
        }
        catch (Exception e)
        {
            closeOutputStream(fos);
            closeInputStream(fis);
            throw new Exception(e);
        }
    }
    
    /*
     * Unzip a zip and extract the file content
     * return Map, key is file full name and value is file content
     */
    public static Map<String, byte[]> unzipAndExtractContent(String fromZipPath, String desFolderPath) throws Exception
    {
        FileUtils.CreateFolder(desFolderPath);
        
        Map<String, byte[]> returnList = new HashMap<String, byte[]>();

        ZipInputStream Zin = new ZipInputStream(new FileInputStream(fromZipPath));
        BufferedInputStream Bin = new BufferedInputStream(Zin);
        ZipEntry entry = null;
        try
        {
            entry = Zin.getNextEntry();
            do
            {               
                if (entry == null || entry.isDirectory())
                {
                    // empty Zip or containing sub folder
                    throw new FlexeraLicenseException("Validator.invalidFileContent");
                }
                String fileName = entry.getName();
                if (!fileName.endsWith(CommonConstants.DOT_BIN))
                {
                    throw new FlexeraLicenseException("Validator.invalidFileType");
                }
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int buf_size = 1024;
                byte[] buffer = new byte[buf_size];
                int len = 0;
                while (-1 != (len = Bin.read(buffer, 0, buf_size)))
                {
                    bos.write(buffer, 0, len);
                }
                
                if (!CheckDiskSpaceIsEnough(bos.size(), desFolderPath))
                {
                    throw new IOException("General.DiskSpaceIsNotEnough");
                }
                
                String desFileFullPath = desFolderPath.concat("\\").concat(fileName);
                returnList.put(desFileFullPath, bos.toByteArray());
                wrtieFile(desFileFullPath, bos.toByteArray());
                bos.close();
                entry = Zin.getNextEntry();
            }
            while (entry != null);
        }
        finally
        {
            Bin.close();
            Zin.close();
        }
        return returnList;
    }
    
    public static boolean CheckDiskSpaceIsEnough(int requestSize, String dirName)
    {
        long freeSpace = OsUtils.getFreeDiskSpace(dirName);
        long dValue = freeSpace - requestSize;
        if (dValue < 50000000) //add log when free space is about 50M 
        {
            logger.warn("CLASS_NAME + free disk space is very litte");
        }

        if (dValue < 0)
        {             
            return false;
        }
        return true;
    }

    /**
     * This method removes a dir path from a file path E.g.: file path:
     * c:\\temp\\itm\stand-alone\\upload\\333333333333333.cpd file Path:
     * c:\\temp\\itm\\stand-alone\\ returns: upload/333333333333333.cpd
     * 
     * @param p_filePath
     * @param p_dirPath
     * @return the new file path
     * @throws Exception
     */
    public static String removeDirPath(String p_filePath, String p_dirPath) throws Exception
    {
        try
        {
            // First replace \ with / for the two paths
            // Then remove the dir path from the file path
            String filePath = p_filePath.replace('\\', '/');
            String dirPath = p_dirPath.replace('\\', '/');
            String newFilePath = filePath.replaceAll(dirPath, "");
            return newFilePath;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    /**
     * Returns a directory which can be used for temporary files. The sequence
     * is as follows: 1. Get the temporary directory from
     * getProperty("java.io.tmpdir") 2. If the path from 1 does not exist, then
     * it is created 3. If the path cannot be created in 2., then temporary
     * location is defaulted to c:\\
     * 
     * Note: the value of the temporary directory is cached inside this class.
     * 
     * @return the path
     */
    public static String getTmpPath()
    {
        final String defaultTmp = "c:\\";

        // We only need to se the value once, so if it is already set,
        // then use that value
        if (tmpPath != null)
        {
            return tmpPath;
        }

        // If we get here tmpPath has not been set, hence we need to find a
        // value for it
        String tmpDir = System.getProperty("java.io.tmpdir");

        // Check if the property returned null
        if (tmpDir == null)
        {
            logger.error(CLASS_NAME + " - tmpDir is null - defaulting to " + defaultTmp);
            tmpPath = defaultTmp;
            return tmpPath;
        }

        // Check if the path exists - if not create it
        File file = new File(tmpDir);
        if (!file.exists())
        {
            logger.info(CLASS_NAME + " - The path: " + tmpDir
                    + " does not exist - will attempt to create it");
            if (!file.mkdirs())
            {
                logger.error(CLASS_NAME + " - The path: " + tmpDir
                        + " could not be created. Defaulting to " + defaultTmp);
                tmpPath = defaultTmp;
                return tmpPath;
            }
        }

        logger.info(CLASS_NAME + " - Setting the tmpPath to: " + tmpDir);
        tmpPath = tmpDir;
        return tmpPath;
    }
    
    public static String getFileNameWithOutExtension(String originFileName)
    {
        if (originFileName == null || !originFileName.contains("."))
        {
            return "";
        }
        String [] resultArray = originFileName.split("\\.");
        return resultArray[0];
    }
    
    /**
     * @param file target directory instance
     * @return total size of given directory(in bytes)
     */
    public static long getSize(File file) 
    {     
        if (file != null && file.exists()) 
        {      
            if (file.isDirectory()) 
            {     
                File[] children = file.listFiles();                
                long size = 0;     
                
                if(children != null && children.length > 0)
                {
                    for (File f : children)     
                        size += getSize(f);     
                }
                return size;     
            } 
            else 
            {       
                return file.length();     
            }     
        } 
        else 
        {     
            return -1;     
        }     
    }     
    
    public static void deleteDirectoryAndFile(File recordPathFile)
    {
        if(!recordPathFile.exists())
        {
            return;
        }
        
        if(recordPathFile.isFile())
        {
            recordPathFile.delete();
        }
        else if(recordPathFile.isDirectory())
        {
            File[] fileList = recordPathFile.listFiles();
            if(fileList != null)
            {
                for(File file: fileList)
                {
                    deleteDirectoryAndFile(file);
                }
            }
            
            recordPathFile.delete();
        }
    }

    public static void copyFolder(File src, File dest, boolean toScreen, ProgressReporter pgReporter, ScheduleExcutionRecord pJob) throws IOException
    {
    	if(pJob != null && pJob.isCancelling())
    	{
    		return;
    	}
    	
    	if (src.isDirectory())
        {
            // if directory not exists, create it
            if (!dest.exists())
            {
                dest.mkdir();
            }

            String content = "Copy directory:" + dest.getName();
            if (toScreen)
            {
            	System.out.println(content);
            }
            if(pgReporter != null)
            {
            	pgReporter.ReportProgress(content);
            }
            
            // list all the directory contents
            String[] files = src.list();

            if (files != null)
            {
                for (String file : files)
                {
                    // construct the src and dest file structure
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    // recursive copy
                    copyFolder(srcFile, destFile, toScreen, pgReporter, pJob);
                }
            }
        }
        else
        {
        	FileChannel source = null;
            FileChannel destination = null;
            try
            {
            	source = new FileInputStream(src).getChannel();
            	destination = new FileOutputStream(dest).getChannel();
            	destination.transferFrom(source, 0, source.size());
            	//report copied size
            	if(pgReporter != null)
            	{
            		pgReporter.AddCopiedSize(source.size());
            	}
            }
            catch(Exception e)
            {
            	throw new IOException(e.getMessage());
            }
            finally
            {
            	if(source != null)
            	{
            		source.close();
            	}
            	if(destination != null)
            	{
            		destination.close();
            	}
            	
            }
        }
    }
    
    public static void copyFolder(File src, File dest, boolean toScreen) throws IOException
    {
    	copyFolder(src, dest, toScreen, null, null);
    }
    
    public static String PathCombine(String path, String subPart)
    {
        File file = new File(path, subPart);
        return file.getPath();
    }   
    
    public static void unzipHelpFile()
    {
        for(String language  : supporttedLanguageList)
        {
            String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + 
                    CommonConstants.BASE_PATH + System.getProperty("file.separator") + CommonConstants.PRE_HELP_PATH + language + HELP_ZIP_FILE_EXTENSION;
            File file = new File(fileName);
            if (file.exists())
            {                
                try
                {
                    if(FileUtils.unzipToPath(fileName, System.getProperty("user.dir") + System.getProperty("file.separator") + CommonConstants.BASE_PATH))
                    {
                    	FileUtils.deleteFile(fileName);
                    }
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
