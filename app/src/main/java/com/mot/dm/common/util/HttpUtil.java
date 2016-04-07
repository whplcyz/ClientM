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

PRODUCT NAME:  ITM     
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/06/08 chad001                CCMPD01051225 Compress codeplug.                    
09/07/08 chad001                CCMPD01061654 Change repository location
22/07/08 cjh102                 CCMPD01067239 Removed mkdir functionality.
                                              Moved to ProcessFileUpload.jsp
29/07/08 chad001                CCMPD01074913 Add logging. Change error check for 
                                              get file from repository. 
08/08/08 Hai Dong               CCMPD01078628 Fix export codeplug error
22/08/08 thmt001                CCMPD01084470 Files has to end with .cpd                                                 
09/Sep/08  Michael Leismann      CCMPD01087804 Modified text strings to be able
                                               to handle a German client
15/09/08   ckfm01                CCMPD01092340 Removing codeplugVersion table in DB.
                                               & Converting CPversion to Integer. 
22/09/08   chad001               CCMPD01090964 Codeplug is determined by fullpath
                                               and no need to pass version   
20/11/08   cmtr001               CCMPD01128099 Retry on file download missing. 
05/Jan/09  Hai Dong              CCMPD01145535 Take CM5000 feature.                                                                                             
13/Jan/09   Kim Mortesnen        CCMPD01146563 We need to have the correct filename in repository.                                                                                            
20/11/08   cmtr001               CCMPD01128099 Retry on file download missing.
20/Jan/09  chad001               CCMPD01125953 Fix KW finding
02/Apr/09  chad001               CCMPD01191346 Fix KW finding                                                                                              
20/Apr/09 Kim Mortesnen          CCMPD01198547 Making sure we have correct checking 
                                               for file extentions.
30/07/09  Kalyan Pushpala        CCMPD01237099 Basic Authentication header added for 
                                               FileUopload/Download services
21/Sep/09  Hai Dong              CCMPD01259651 Supports the new radio type                                               
23/09/09  Kalyan Pushpala        CCMPD01266263 Proxy anonymous login support
12/10/09  Kalyan Pushpala        CCMPD01274569 Encoding the credentials using UTF-8
19/Oct/09  Jens Hansen           CCMPD01277190 Updated getSoftware method
05/Jan/10  Michael Leismann      CCMPD01295648 Added log statements
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1
11/Aug/10  Kalyan Pushpala      CCMPD01357138 Socket timeout set to 3 mins instead
                                              1 min
24/Aug/10  cjh102                CCMPD01385457 Updates for CPE codeplugs                                               
20/09/10   chad001               ccmpd01393090 4.0 licensing feature                                           
24/Aug/10  cjh102                CCMPD01385457 Updates for CPE codeplugs
28/Oct/10  cjh102                CCMPD01399192 Added client ID to request
                                               header                                                
27/Oct/10  Michael Leismann      CCMPD01387289 iTM 4.0: Network Logging
22/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
30/Mar/11  Michael Leismann      CCMPD01488491 iTM 5.0: Merged getCodeplug,
                                               getSoftware and getNwLoggingFile
                                               into a getFile method
04/May/11  Michael Leismann      CCMPD01502974 iTM 5.0: Proxy lib part of Panda
                                               integration - polling
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions
10/Jun/11  Michael Leismann      CCMPD01516767 iTM 5.0: New release package
                                               structure
10/Jun/11  Michael Leismann      CCMPD01516805 iTM 5.0: Enhanced API -
                                               Upload/download of UserData file
24/06/11   cjh102                CCMPD01523954 Added support for release package                                               
21/Jul/11  Michael Leismann      CCMPD01534934 iTM 5.0: Remove temporary files
                                               after committing to DB 
18/July/11 cjh102                CCMPD01537918 Upload release package to temporary
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature
19/July/12 tqfn38	        	 CCMPD01665910 proxy back compatibility
4/Dec/12  tqfn38 				 CCMPD01721424 iTM6.1 Picture Pull & Push
27/Feb/13 wch378          CCMPD01747688 iTM6.1 export policy result
07/April/13 wch378          CCMPD01761631 strange file name raises when exporting policy repeatedly
20/Aug/13  mjr638                CCMPD01803292 Enhance client has breakpoint resume ability, during downloading RPK file
23/Dec/13  qngv36                CCMPD01848243 [iTM6.2] New radio with password unknown job will keep  pending
28/3/14    wch378               CCMPD01889533 develop flexera license. 
10/10/14   cmdg37               CCMPD01930966  Enhancement for adding release packages,multi-selection is supported.
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFilter;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mot.dm.common.delegation.ServiceDelegationWrapper;
import com.mot.dm.common.model.Codeplug;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.FlexeraLicenseSource;
import com.mot.dm.common.model.Software;
import com.mot.dm.common.model.StatusConstants;
import com.mot.dm.common.service.CodeplugService;
import com.mot.dm.common.service.DeviceService;
import com.mot.dm.common.service.SoftwareService;
import com.mot.dm.common.util.CommonConstants.DownloadType;
import com.mot.dm.common.util.exception.LicenseException;
import com.mot.dm.common.util.exception.RepositoryException;
import com.mot.dm.common.util.exception.ServiceException;


/**
 * @Version 1.0 2007 July 5
 * @author w23021
 */
@SuppressWarnings("deprecation")
public class HttpUtil
{
	private int currentProgressValue = 0;
	private int needProgressValue = 0;
	private int currentTimes = 0;
	private int totalTimes = 0;
	private int speed = 0;
	
    private static final String PROTOCOL = "https://";

    public static final String FILE_REPOSITORY_SERVICE_EXCEPTION = "FileService.getFile";

    private static final String PROPERTY_FILE_UPLOAD = "dms/ProcessFileUpload.jsp";
    private static final String PROPERTY_FILE_DOWNLOAD = "dms/ProcessFileDownload.jsp";

    private static Logger fgLogger = Logger.getLogger("com.mot.dm");
    private static final String CLASS = HttpUtil.class.getName();
    
    private HttpClient httpClient;
    private String url;
    private String anonymousUsername;
    private String anonymousSessionKey;
    private String credentialsCharset = "UTF-8"; // Default Charset as per
                                                 // server

    private DownloadingProcessEvent eventHandler = null;
    // Counter how many times we have retried
    private int retryCounter = 0;

    // Specifies how many times to retry
    private static final int MAX_RETRY = 5;

    // Specifies how long time to wait before doing the next retry
    private static final int SLEEP_VALUE = 30000;

    // Use to "interrupt" the transfer
    private static boolean stopTransfer = false;

    public HttpUtil()
    {
        fgLogger.info(CLASS + " - HTTP connection will be started.");
    }

    public HttpUtil(String anonymousUsername, String anonymousSessionKey)
    {
        this.anonymousUsername = anonymousUsername;
        this.anonymousSessionKey = anonymousSessionKey;

        fgLogger.info(CLASS + " - HTTP connection will be started.");
    }
    
    public void RegisterProcessEventHandler(DownloadingProcessEvent eventHandler)
    {
    	this.eventHandler = eventHandler;
    }



    /**
     * Uploads a codeplug to the server.
     * 
     * @param serverUrl
     *            - The server address/URL.
     * @param codeplug
     *            - A reference to the codeplug that should be uploaded.
     * @param localCodeplugPath
     *            - The local path from where the codeplug is uploaded.
     * @return A reference to the codeplug that was added to the server.
     */
    public Codeplug addCodeplug(String serverUrl, Codeplug codeplug,
            String localCodeplugPath) throws ServiceException
    {
        if (codeplug == null || codeplug.getDevice() == null)
            throw new ServiceException("CodeplugFileService.addCodeplug");

        CodeplugService codeplugService = (CodeplugService) ServiceDelegationWrapper
                .getService("codeplugService");
        codeplug.setCodeplugFullPath(localCodeplugPath);

        // Set the initial version to "0" as its completely new, and
        // havent been assigned the correct version number that have
        // to be BIGGER than 0. If not setting the version, the version
        // will be null.
        codeplug.setCodeplugRepositoryVersion(0);
        Codeplug newCodeplug = codeplugService.addCodeplug(codeplug);

        String uuid = newCodeplug.getCodeplugUuid();
        String newFullPath = "";
        // we need to handle the file extensions correctly
        String fileExtension = "";
        if (localCodeplugPath.toLowerCase().endsWith(
                Codeplug.CPE_FILE_EXTENSION))
        {
            fileExtension = Codeplug.CPE_FILE_EXTENSION;
        }
        else if(localCodeplugPath.toLowerCase().endsWith(
                Codeplug.PROFILE_FILE_EXTENSION))
        {
        	fileExtension = Codeplug.PROFILE_FILE_EXTENSION;
        }
        else
        {
            fileExtension = Codeplug.CODEPLUG_FILE_EXTENSION;
        }


        newFullPath = newCodeplug.getDevice().getDeviceFullPath() + "/" + uuid
                + fileExtension;

        newCodeplug.setCodeplugFullPath(newFullPath);
        codeplugService.updateCodeplug(newCodeplug);

        // Create a temporary local path
        String tempPath = FileUtils.getTmpPath();
        String newLocalPath = tempPath + "/" + uuid + fileExtension;

        try
        {
            // Copy the file to the temporary location
            FileUtils.CopyFile(localCodeplugPath, newLocalPath);
        }
        catch (Exception e)
        {
            /*
             * If this copy operation fails a ServiceException is thrown. The
             * exception is handled by the caller (proxy or client) and used to
             * log an error or display an error message to the user. The update
             * of the repository is not performed because addOrUpdateToRepostory
             * is not called. The transaction is therefore aborted correctly.
             */
            fgLogger.error(CLASS + " - File could not be copied from "
                    + localCodeplugPath + " to " + newLocalPath, e);
            throw new ServiceException(
                    "CodeplugFileService.addOrUpdateToRepostory_2");
        }

        addOrUpdateToRepostory(serverUrl, newCodeplug, newLocalPath);

        FileUtils.cleanup(newLocalPath);

        return newCodeplug;

    }

    /**
     * This method is used to get a file from repository Will use next getFile
     * method with the additional DownloadType parameter
     */
    public void getFile(String serverUrl, String fullPath, String localpath)
            throws ServiceException
    {
        getFile(serverUrl, fullPath, localpath,
                DownloadType.DOWNLOAD_TYPE_ON_DEMAND);
    }

    /**
     * This method is used to get a file from repository
     * 
     * @param fullPath
     *            the full path to the file in the repository
     * @param localpath
     *            the local path where the file should be stored
     * @param downloadType
     *            the download type
     * @throws ServiceException
     *             if there is an error the method will throw the
     *             ServiceException
     */
    public void getFile(String serverUrl
            , String fullPath
            , String localpath
            , DownloadType downloadType) throws ServiceException
    {
        String urlPath = PROTOCOL + serverUrl;
        try
        {
            createConnection(urlPath);
            if (getFileFromRepostory(fullPath, localpath, downloadType) == false)
            {
                fgLogger.error(CLASS + " - Could not get file: " + fullPath);
                throw new ServiceException(FILE_REPOSITORY_SERVICE_EXCEPTION);
            }
        }
        catch (RepositoryException e)
        {
            if (isMSMobilityLogFile(fullPath))
            {
                fgLogger.error(CLASS + " - Could not get NwLogging file.", e);
                throw new ServiceException("Export.GetFileFromRepostoryFailed",
                        e);
            }
            else if (Codeplug.isCodeplugFile(fullPath))
            {
                fgLogger.error(CLASS + " - Could not get codeplug.", e);
                throw new ServiceException(
                        "CodeplugFileManagerImpl.getCodeplugFromRepostory_2", e);
            }
            else if (isUserDataFile(fullPath))
            {
                fgLogger.error(CLASS + " - Could not get user data file.", e);
                throw new ServiceException("Export.GetFileFromRepostoryFailed",
                        e);
            }
            else
            {
                fgLogger.error(CLASS
                        + " - Could not get software from repository.", e);
                throw new ServiceException(
                        "SoftwareFileManagerImpl.getSoftwareFromRepostory_2", e);
            }
        }
        finally
        {
            releaseConnection();
        }
    }

    public Software addSoftware(String serverUrl, Software software,
            String localSoftware) throws ServiceException
    {
        if (software == null)
            return null;

        String returnMessage = commitToRepostory(serverUrl, software,
                localSoftware);
        if (returnMessage != null)
            throw new ServiceException(returnMessage);

        SoftwareService softwareService = (SoftwareService) ServiceDelegationWrapper
                .getService("softwareService");
        return softwareService.addSoftware(software);
    }

    /**
     * Used to add a UserData file It is assumed that the caller of this method
     * has validated if the file has the correct format
     * 
     * @param serverUrl
     * @param localPath
     * @param uuid
     * @return the path to the repository
     * @throws ServiceException
     */
    public String addUserData(String serverUrl, String localPath, String uuid)
            throws ServiceException
    {
        if (uuid == null || uuid.length() == 0)
        {
            return null;
        }

        // Create a temporary local path
        String tempPath = FileUtils.getTmpPath();
        String newLocalPath = tempPath + "/" + uuid
                + CommonConstants.USER_DATA_EXTENSION;

        try
        {
            // Copy the file to the temporary location
            FileUtils.CopyFile(localPath, newLocalPath);
        }
        catch (IOException e)
        {
            fgLogger.error(CLASS
                    + " - addUserData() - File could not be copied from "
                    + localPath + " to " + newLocalPath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }

        String repositoryDirPath = CommonConstants.USER_DATA_REP_DIR;
        String repositoryFilePath = generateFilePathInRepostory(
                repositoryDirPath, newLocalPath);

        try
        {
            String urlPath = PROTOCOL + serverUrl;
            createConnection(urlPath);

            String returnMessage = commitFileToRepository(repositoryFilePath,
                    newLocalPath);
            if (returnMessage != null)
            {
                fgLogger.error(CLASS
                        + " - Could not commit Userdata file to repository: "
                        + repositoryFilePath);
                throw new ServiceException(returnMessage);
            }
            else
            {
                fgLogger.info(CLASS + " - Committed Userdata to repository: "
                        + repositoryFilePath);
                return repositoryFilePath;
            }
        }
        catch (RepositoryException e)
        {
            fgLogger.error(CLASS
                    + " - Could not commit Userdata to repository: "
                    + repositoryFilePath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }
        finally
        {
            releaseConnection();
            FileUtils.cleanup(newLocalPath);
        }
    }

    /**
     * Used to add a HotFix file It is assumed that the caller of this method
     * has validated if the file has the correct format
     * 
     * @param serverUrl
     * @param localPath
     * @param uuid
     * @return the path to the repository
     * @throws ServiceException
     */
    public String addHotFix(String serverUrl, String localPath, String uuid)
            throws ServiceException
    {
        if (uuid == null || uuid.length() == 0)
        {
            return null;
        }

        // Create a temporary local path
        String tempPath = FileUtils.getTmpPath();
        String newLocalPath = tempPath + "/" + uuid
                + CommonConstants.HOT_FIX_EXTENSION;

        try
        {
            // Copy the file to the temporary location
            FileUtils.CopyFile(localPath, newLocalPath);
        }
        catch (IOException e)
        {
            fgLogger.error(CLASS
                    + " - addHotFix() - File could not be copied from "
                    + localPath + " to " + newLocalPath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }

        String repositoryDirPath = CommonConstants.HOT_FIX_REP_DIR;
        String repositoryFilePath = generateFilePathInRepostory(
                repositoryDirPath, newLocalPath);

        try
        {
            String urlPath = PROTOCOL + serverUrl;
            createConnection(urlPath);

            String returnMessage = commitFileToRepository(repositoryFilePath,
                    newLocalPath);
            if (returnMessage != null)
            {
                fgLogger.error(CLASS
                        + " - Could not commit Hotfix file to repository: "
                        + repositoryFilePath);
                throw new ServiceException(returnMessage);
            }
            else
            {
                fgLogger.info(CLASS + " - Committed Hotfix to repository: "
                        + repositoryFilePath);
                return repositoryFilePath;
            }
        }
        catch (RepositoryException e)
        {
            fgLogger.error(CLASS + " - Could not commit Hotfix to repository: "
                    + repositoryFilePath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }
        finally
        {
            releaseConnection();
            FileUtils.cleanup(newLocalPath);
        }
    }

    /**
     * Used to add a HotFix file It is assumed that the caller of this method
     * has validated if the file has the correct format
     * 
     * @param serverUrl
     * @param localPath
     * @param uuid
     * @return the path to the repository
     * @throws ServiceException
     */
    public String addPicturePushFile(String serverUrl, String localPath,
            String uuid, String updateCount) throws ServiceException
    {
        if (uuid == null || uuid.length() == 0)
        {
            return null;
        }

        // Create a temporary local path
        String tempPath = FileUtils.getTmpPath();
        String newLocalPath = tempPath + "/" + uuid + "_" + updateCount
                + CommonConstants.PICTURE_FILE_FIX_EXTENSION;

        try
        {
            // Copy the file to the temporary location
            FileUtils.CopyFile(localPath, newLocalPath);
        }
        catch (IOException e)
        {
            fgLogger.error(
                    CLASS
                            + " - addPicturePushFile() - File could not be copied from "
                            + localPath + " to " + newLocalPath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }

        String repositoryDirPath = CommonConstants.PICTURE_PUSH_FILE_FIX_REP_DIR;
        String repositoryFilePath = generateFilePathInRepostory(
                repositoryDirPath, newLocalPath);

        try
        {
            String urlPath = PROTOCOL + serverUrl;
            createConnection(urlPath);

            String returnMessage = commitFileToRepository(repositoryFilePath,
                    newLocalPath);
            if (returnMessage != null)
            {
                fgLogger.error(CLASS
                        + " - Could not commit picture push file to repository: "
                        + repositoryFilePath);
                throw new ServiceException(returnMessage);
            }
            else
            {
                fgLogger.info(CLASS
                        + " - Committed picture push to repository: "
                        + repositoryFilePath);
                return repositoryFilePath;
            }
        }
        catch (RepositoryException e)
        {
            fgLogger.error(CLASS
                    + " - Could not commit picture push to repository: "
                    + repositoryFilePath, e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }
        finally
        {
            releaseConnection();
            FileUtils.cleanup(newLocalPath);
        }
    }

    /**
     * The method is used to add a codeplug file to repostory
     * 
     * @param codeplug
     *            the codeplug which will be accessed to repostory file system
     *            and database.
     * @param localCodeplug
     *            the local path the codeplug be exist.
     * @throws ServiceException
     *             if there is error to add to repostory or update in database
     *             the method will throws the ServiceException
     */
    private void addOrUpdateToRepostory(String serverUrl, Codeplug codeplug,
            String localCodeplug) throws ServiceException
    {
        DeviceService deviceService = (DeviceService) ServiceDelegationWrapper
                .getService("deviceService");
        Device device = deviceService.getDeviceById(codeplug.getDevice()
                .getDeviceId());
        CodeplugService codeplugService = (CodeplugService) ServiceDelegationWrapper
                .getService("codeplugService");
        if (device == null)
        {
            fgLogger.error(CLASS + " - device is null");
            throw new ServiceException(
                    "CodeplugFileService.addOrUpdateToRepostory_1");
        }

        int nextVersionNumber;
        Codeplug codeplugVersion = codeplugService.getLatestCodeplug(device);
        if (codeplugVersion == null)
        {
            fgLogger.error(CLASS + " - codeplug is null");
            throw new ServiceException(
                    "CodeplugFileService.addOrUpdateToRepostory_2");
        }

        nextVersionNumber = codeplugVersion.getCodeplugRepositoryVersion() + 1;

        codeplug.setCodeplugRepositoryVersion(nextVersionNumber);

        codeplugService.updateCodeplug(codeplug);

        String returnMessage = commitToRepostory(serverUrl, codeplug,
                device.getDeviceFullPath(), localCodeplug);
        if (returnMessage != null)
            throw new ServiceException(returnMessage);
    }

    /**
     * 
     * @param serverUrl
     * @param codeplug
     * @param deviceFullPath
     * @param localCodeplug
     * @return
     * @throws ServiceException
     */
    private String commitToRepostory(String serverUrl, Codeplug codeplug,
            String deviceFullPath, String localCodeplug)
            throws ServiceException
    {
        if (codeplug == null || codeplug.getCodeplugRepositoryVersion() == null)
        {
            fgLogger.error("Codeplug is null");
            return "RepositoryException";
        }

        if (new File(localCodeplug).length() > 100000000)
            throw new ServiceException(
                    "CodeplugFileManagerImpl.commitToRepostory_1");

        String urlPath = PROTOCOL + serverUrl;

        String codeplugFilePath = generateFilePathInRepostory(deviceFullPath,
                localCodeplug);
        codeplug.setCodeplugFullPath(codeplugFilePath);

        try
        {
            createConnection(urlPath);
            return commitFileToRepository(codeplugFilePath, localCodeplug);
        }
        catch (RepositoryException e)
        {
            fgLogger.error(CLASS
                    + " - Could not commit codeplug to repository.", e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }
        finally
        {
            releaseConnection();
        }
    }

    private static String generateFilePathInRepostory(String repostoryPath,
            String localPath)
    {
        String filename = "";
        if (localPath == null)
            return null;
        String sLocalPath = localPath.replace('\\', '/');
        String[] strings = sLocalPath.split("/");
        filename = strings[strings.length - 1];

        return repostoryPath + "/" + filename;
    }

    private String commitToRepostory(String serverUrl, Software software,
            String localSoftware) throws ServiceException
    {
        if (software == null)
        {
            fgLogger.error("software object null");
            return "SoftwareFileService.addOrUpdateToRepostory_2";
        }

        if (new File(localSoftware).length() > 100000000)
            throw new ServiceException(
                    "SoftwareFileManagerImpl.commitToRepostory_1");

        String repostoryPath = software.getSwVersionBuildType()
                + software.getSwVersionBuildId() + software.getEncryptionMode()
                + software.getSwVersionRelease();

        String repostoryFile = generateFilePathInRepostory(repostoryPath,
                localSoftware);

        software.setSoftwareFullPath(repostoryFile);

        try
        {
            String urlPath = PROTOCOL + serverUrl;

            createConnection(urlPath);

            return commitFileToRepository(repostoryFile, localSoftware);
        }
        catch (RepositoryException e)
        {
            fgLogger.error(CLASS
                    + " - Could not commit software to repository.", e);
            throw new ServiceException("FileManager.CommitToRepostoryFailed", e);
        }
        finally
        {
            releaseConnection();
        }
    }

    /**
     * This method is used to create connection to repostory server.
     */
    private void createConnection(String p_urlPath)
    {
        if (p_urlPath == null)
            return;

        url = p_urlPath;

        fgLogger.info(CLASS + " - creating connection");

        httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(60000);

        // Setting socket timeout to ensure that the socket doesn't block
        // forever
        httpClient.getHttpConnectionManager().getParams()
                .setSoTimeout(60000 * 3);
        
        httpClient.getParams().setVirtualHost("iTM" + VersionNumber.INTERFACE_VERSION);

        fgLogger.info(CLASS + " - connection created");
    }

    /**
     * This method is used to upload file to repostory
     */
    private void releaseConnection()
    {
        fgLogger.info(CLASS + " - Releasing connection");
        httpClient = null;
        fgLogger.info(CLASS + " - Connection released");
    }

    /**
     * This method is used to upload file to repository
     * 
     * @param repostoryFileName
     *            the file name which will exist in repository
     * @param localFileName
     *            the local file's name
     * @return String If the transfer is successfull, then null is returned,
     *         otherwise the String contains a localized message, that the
     *         caller can use in an exception
     * @throws RepositoryException
     *             if there is error in upload file, the method will throw
     *             RepostoryException
     */
    private String commitFileToRepository(String repostoryFileName,
            String localFileName) throws RepositoryException
    {
        PostMethod filePost = null;
        String uploadFileName = localFileName;
        String zipFileName = localFileName;

        // --------------------------------------------------------------------//
        // we only compress codeplugs when they are stored in server. The //
        // entry for full path of the codeplug still shows file name as cpd //
        // but actually in repository they are stored as *.cpd.zip //
        // Notice that we only compress codeplugs, not software. so the flag //
        // compressCodeplug is used for checking if we need to compress the //
        // file //
        // specific file extension //
        // MS mobility log and UserData xml files should also be compressed //
        // --------------------------------------------------------------------//
        boolean compressfile = Codeplug.isCodeplugFile(localFileName)
                || isMSMobilityLogFile(localFileName)
                || isUserDataFile(localFileName) || isHotFixFile(localFileName);

        try
        {
            String path = repostoryFileName;
            if (compressfile)
            {
                zipFileName = localFileName + FileUtils.ZIP_FILE_EXTENSION;
                FileUtils.zip(localFileName, zipFileName);

                uploadFileName = zipFileName;
                path = path + FileUtils.ZIP_FILE_EXTENSION;
            }

            File f1 = new File(uploadFileName);

            Part[] parts =
            { new StringPart("path", path),
                    new FilePart(f1.getName(), f1) };

            String requestAddress = url;
            if (!url.endsWith("/"))
                requestAddress += "/";

            requestAddress = requestAddress + PROPERTY_FILE_UPLOAD;

            filePost = new PostMethod(requestAddress);

            filePost.setRequestEntity(new MultipartRequestEntity(parts,
                    filePost.getParams()));

            insertBasicAuthHeader(filePost);

            return httpClientExecuteMethod(filePost);
        }
        catch (Exception e)
        {
            // Release the connection
            fgLogger.error(CLASS
                    + " - Could not commit a file to the repository.", e);

            // If max retries have been reached then throw an exception
            if (retryCounter == MAX_RETRY)
            {
                fgLogger.error(CLASS + " - Max retry on commit reached");

                throw new RepositoryException("commitFileToRepository_failed",
                        e);
            }
            // otherwise sleep for some time and then try again
            try
            {
                Thread.sleep(SLEEP_VALUE);
            }
            catch (Exception ex)
            {
                /*
                 * The Thread.sleep() method requires a try-catch for the
                 * interrupted exception. This is used to wake up the thread
                 * from it's sleep. The exception should be ignored since it is
                 * not expected that the sleep is interrupted. If it should
                 * occur a service exception should not be thrown since this
                 * would prevent the retry mechanism to work.
                 */
                fgLogger.error(CLASS + " - Exception in sleep for commit.", ex);
            }

            retryCounter = retryCounter + 1;
            createConnection(url);

            return commitFileToRepository(repostoryFileName, localFileName);
        }
        finally
        {
            // Remove the temporary zip file
            if (compressfile)
            {
                FileUtils.cleanup(zipFileName);
            }
        }
    }

    /**
     * add license file
     * 
     * @param repostoryFileName
     * @param localFileName
     * @return boolean
     * @throws RepositoryException
     */
    private String commitLicenseFileToRepository(String repostoryFileName,
            String localFileName) throws RepositoryException
    {
        PostMethod filePost = null;
        String uploadFileName = localFileName;
        try
        {
            String path = repostoryFileName;
            File f1 = new File(uploadFileName);

            Part[] parts =
            { new StringPart("path", path),
                    new FilePart(f1.getName(), f1) };

            String requestAddress = url;
            if (!url.endsWith("/"))
                requestAddress += "/";

            requestAddress = requestAddress + PROPERTY_FILE_UPLOAD;

            filePost = new PostMethod(requestAddress);

            filePost.setRequestEntity(new MultipartRequestEntity(parts,
                    filePost.getParams()));
            insertBasicAuthHeader(filePost);

            return httpClientExecuteMethod(filePost);
        }
        catch (Exception e)
        {
            // Release the connection
            fgLogger.error(CLASS
                    + " - Could not commit a file to the repository.", e);

            // If max retries have been reached then throw an exception
            if (retryCounter == MAX_RETRY)
            {
                fgLogger.error(CLASS + " - Max retry on commit reached");
                throw new RepositoryException("commitFileToRepository_failed",
                        e);
            }
            // otherwise sleep for some time and then try again
            try
            {
                Thread.sleep(SLEEP_VALUE);
            }
            catch (Exception ex)
            {
                /*
                 * The Thread.sleep() method requires a try-catch for the
                 * interrupted exception. This is used to wake up the thread
                 * from it's sleep. The exception should be ignored since it is
                 * not expected that the sleep is interrupted. If it should
                 * occur a service exception should not be thrown since this
                 * would prevent the retry mechanism to work.
                 */
                fgLogger.error(CLASS + " - Exception in sleep for commit.", ex);
            }

            retryCounter = retryCounter + 1;
            createConnection(url);

            return commitFileToRepository(repostoryFileName, localFileName);
        }
    }

    /**
     * This method is used to get file from repository Will use next
     * getFileFromRepostory method with the additional DownloadType parameter
     */
    private boolean getFileFromRepostory(String repostoryFileName,
            String localFileName) throws RepositoryException
    {
        return getFileFromRepostory(repostoryFileName, localFileName,
                DownloadType.DOWNLOAD_TYPE_ON_DEMAND);
    }
    
    /**
     * This method is used to get file from repository
     * 
     * @param repostoryFileName
     *            the file's path and it's name in the repository which will be
     *            downloaded
     * @param localFileName
     *            the local path where the file should be downloaded to
     * @param downloadType
     *            the download type
     * @return boolean return true if the file was downloaded successfully, else
     *         return false.
     * @throws RepositoryException
     *             if there is an error while downloading the file, the method
     *             will throw RepostoryException
     */
    // CHECKSTYLE IGNORE MethodLength FOR NEXT 500 LINES
    private boolean getFileFromRepostory(String repostoryFileName
            , String localFileName
            , DownloadType downloadType) throws RepositoryException
    {
        fgLogger.info(CLASS + " - getFileFromRepostory invoked, retryCounter: "
                + retryCounter);

        int statusCode = 0;
        String requestFile = repostoryFileName;
        requestFile = requestFile.replaceAll(" ", "%20");

        String requestAddress = url;
        if (!url.endsWith("/"))
            requestAddress += "/";

        requestAddress = requestAddress + PROPERTY_FILE_DOWNLOAD;

        // --------------------------------------------------------------------//
        // we use the compressed flag to see if the repository contains a //
        // compressed codeplug file. as in database, the full path points //
        // to a file named as *.cpd but actually it is stored as *.cpd.zip //
        // in repository. //
        // A MS mobility log file is also compressed //
        // --------------------------------------------------------------------//
        boolean compressed = true;
        boolean isMSMobilityLogFile = isMSMobilityLogFile(requestFile);
        boolean isUserDataFile = isUserDataFile(requestFile);
        boolean isHotFixFile = isHotFixFile(requestFile);
        boolean getSoftwareFromRepository = Software
                .isSoftwareFile(requestFile);
        boolean getReleasePackageRepository = Software
                .isReleasePackage(requestFile);
        boolean isPicturePushFile = isPicturePushFile(localFileName);
        boolean isGetPolicyResult = repostoryFileName
                .contains(CommonConstants.POLICY_REPOSITORY_PATH);
        PostMethod postMethod = new PostMethod(requestAddress);

        List<File> filePathList = genFileList(localFileName);

        InputStream in = null;
        FileLock outputFileLock = null;
        FileOutputStream out = null;
        String tempLocalPath = "";
        long breakPoint = 0;

        int i = 0;
        while (outputFileLock == null && filePathList.size() > i)
        {
            // If break point greater than zero, append stream to existed file,
            // file breaking point resume.
            try
            {
                breakPoint = filePathList.get(i).length();
                tempLocalPath = filePathList.get(i).getAbsolutePath();
                out = new FileOutputStream(tempLocalPath, breakPoint > 0);
                outputFileLock = out.getChannel().tryLock();
            }
            catch (OverlappingFileLockException e)
            {
                fgLogger.info("File lock exception: OverlappingFileLockException. Path"
                        + tempLocalPath);
            }
            catch (FileNotFoundException e)
            {
                fgLogger.info("File lock exception: FileNotFoundException. Path"
                        + tempLocalPath);
            }
            catch (IOException e)
            {
                fgLogger.info("File lock exception: IOException. Path"
                        + tempLocalPath);
            }
            catch (Exception e)
            {
                fgLogger.info("File lock exception: " + e.getMessage() + ". Path"
                        + tempLocalPath);
            }
            i++;
        }

        try
        {
            // ----------------------------------------------------------------//
            // we try ZIPed file first. if it fails, we will try the file //
            // withoout .zip extension. //
            // notice that a codeplug can be stored as *.cpd in repository //
            // if server is restored from 1.9 release //
            // ----------------------------------------------------------------//
            if (getSoftwareFromRepository || getReleasePackageRepository)
            {
                fgLogger.info("Get the software/release package file: "
                        + repostoryFileName
                        + " from the repository - downloadType: "
                        + downloadType.toString() + " download starts from: "
                        + breakPoint);

                compressed = false;
                NameValuePair[] nvp =
                {
                        new NameValuePair("path", requestFile),
                        new NameValuePair("downloadType",
                                downloadType.toString()),
                        new NameValuePair("RANGE", "bytes=" + breakPoint + "-") 
                };

                postMethod.setRequestBody(nvp);
                insertBasicAuthHeader(postMethod);
                statusCode = httpClient.executeMethod(postMethod);
                Header[] headers = postMethod.getResponseHeaders();
                fgLogger.info(Arrays.toString(headers));
                if (statusCode < 200 || statusCode >= 300)
                {
                    if (statusCode == HttpServletResponse.SC_SERVICE_UNAVAILABLE)
                    {
                        fgLogger.error("Failed to get the file from the server: "
                                + repostoryFileName
                                + " as no ticket was available - statusCode = "
                                + statusCode);
                    }
                    else
                    {
                        fgLogger.error("Failed to get the file from the server: "
                                + repostoryFileName
                                + " statusCode = "
                                + statusCode);
                    }
                    // bad request, no need to continue
                    return false;
                }
            }
            else
            {
                // Log whether it is a MS mobility log file, release package,
                // user data or a codeplug file
                if (isMSMobilityLogFile)
                {
                    fgLogger.info("Get the MS mobility log file: "
                            + repostoryFileName + " from the repository.");
                }
                else if (isUserDataFile)
                {
                    fgLogger.info("Get the user data file: "
                            + repostoryFileName + " from the repository.");
                }
                else if (isHotFixFile)
                {
                    fgLogger.info("Get the hot fix file: " + repostoryFileName
                            + " from the repository.");
                }
                else if (isPicturePushFile)
                {
                    fgLogger.info("Get the picture push file: "
                            + repostoryFileName + " from the repository.");
                }
                else if (isGetPolicyResult)
                {
                    fgLogger.info("Get the policy result file: "
                            + repostoryFileName + " from the repository.");
                }
                else
                {
                    fgLogger.info("Get the codeplug file: " + repostoryFileName
                            + " from the repository.");
                }

                if (isPicturePushFile
                        || (isGetPolicyResult && !isMSMobilityLogFile))
                {
                    compressed = false;
                    NameValuePair[] nvp =
                    {
                            new NameValuePair("path", requestFile),
                            new NameValuePair("downloadType",
                                    downloadType.toString()),
                            new NameValuePair("RANGE", "bytes=" + breakPoint + "-") 
                    };
                    postMethod.setRequestBody(nvp);
                }
                else
                {
                    // ------------------------------------------------------------//
                    // for codeplug, we try ZIPed file on server first //
                    // ------------------------------------------------------------//
                    NameValuePair[] nvp =
                    {
                            new NameValuePair("path", requestFile
                                    + FileUtils.ZIP_FILE_EXTENSION),
                            new NameValuePair("downloadType", downloadType.toString()),
                            new NameValuePair("RANGE", "bytes=" + breakPoint + "-")
                    };

                    postMethod.setRequestBody(nvp);
                }
                insertBasicAuthHeader(postMethod);
                statusCode = httpClient.executeMethod(postMethod);
                if (statusCode < 200 || statusCode >= 300)
                {
                    // bad request, no need to continue
                    fgLogger.error("Failed to get the file from the server: "
                            + repostoryFileName + " statusCode = " + statusCode);
                    callback(tempLocalPath, 0, CommonConstants.ERROR);
                    return false;
                }
            }

            in = postMethod.getResponseBodyAsStream();

            // Create the folder
            FileUtils.CreateOwnFolder(localFileName);

            fgLogger.info("GetFileFromRepository, destination path = " + localFileName);
       
            long nTotalSize = Long.parseLong(postMethod.getResponseHeader("Content-Length").getValue());
            callback(tempLocalPath, nTotalSize, CommonConstants.DOWNLOADING);
           
            byte[] b = new byte[1024];
            int len = in.read(b);
            // Also check if the transfer should be interrupted
            while ((len != -1) && stopTransfer == false)
            {
                out.write(b, 0, len);
                len = in.read(b);
            }
            out.flush();
            outputFileLock.release();
            out.close();

            // If the transfer stopped due to the fact that the proxy
            // is being closed then release the connection
            if (stopTransfer == true)
            {
                // Close the stream and release the connection
                in.close();
                postMethod.releaseConnection();

                fgLogger.info("Transfer was interrupted as the proxy was closed. "
                        + "So the file "
                        + repostoryFileName
                        + " was NOT fetched from the repository");
                callback(tempLocalPath, nTotalSize, CommonConstants.ERROR);
                return false;
            }
            else
            {
                if (compressed)
                {
                    FileUtils.unzipToFile(tempLocalPath, localFileName);
                    FileUtils.cleanup(tempLocalPath);
                }
                else
                {
                    FileUtils.cleanup(localFileName);
                    FileUtils.renameFile(tempLocalPath, localFileName);
                }
            }

            in.close();
            postMethod.releaseConnection();

            callback(tempLocalPath, nTotalSize, CommonConstants.DOWNLOADED);
            fgLogger.info("The file " + repostoryFileName
                    + " has been fetched from the repository.");
            return true;
        }
        catch (Exception e)
        {
            fgLogger.error(CLASS + " - Get file from repository failed.", e);

            // Release file lock
            try
            {
                if (outputFileLock != null)
                    outputFileLock.release();
            }
            catch (Exception outRelease)
            {
                fgLogger.error(CLASS + " - out could not be released", outRelease);
            }

            // Close the streams
            try
            {
                if (out != null)
                    out.close();
            }
            catch (Exception inout)
            {
            	callback(tempLocalPath, 0, CommonConstants.ERROR);
            	fgLogger.error(CLASS + " - out could not be closed", inout);
                throw new RepositoryException(
                        "commitFileToRepository_close_outstream_failed", e);
            }

            try
            {
                if (in != null)
                    in.close();
            }
            catch (Exception inout)
            {
                /*
                 * If the input stream cannot be closed an exception should not
                 * be thrown. The input stream is used for a network connection
                 * and the reason for the failure could be that the connection
                 * has been closed. Closing a network socket involves a request
                 * to the server (TCP/IP FIN packet) being sent over the network
                 * and waiting for the other end to acknowledge this request. If
                 * the server has closed the connection, no response will be
                 * received from the server and the close method will fail even
                 * if the socket is released. Under any circumstances the system
                 * resources are released when the instance of this class is
                 * garbage collected.
                 * 
                 * Notice also that an exception being thrown here would prevent
                 * the retry mechanism from working.
                 */
                fgLogger.error("in could not be closed", inout);
            }

            // Release the connection
            postMethod.releaseConnection();

            FileUtils.cleanup(tempLocalPath);

            // If max retries have been reached then throw an exception
            if (retryCounter == MAX_RETRY)
            {
            	callback(tempLocalPath, 0, CommonConstants.ERROR);
            	fgLogger.error(CLASS
                        + " - Max retires has been reaced!!, retrycounter: "
                        + retryCounter);
                throw new RepositoryException("getFileFromRepostory_failed", e);
            }
            // otherwise sleep for some time and then try again
            try
            {
                fgLogger.info(CLASS
                        + " - Sleeping before trying to get software again");
                Thread.sleep(SLEEP_VALUE);
            }
            catch (Exception ex)
            {
                /*
                 * The Thread.sleep() method requires a try-catch for the
                 * interrupted exception. This is used to wake up the thread
                 * from it's sleep. The exception should be ignored since it is
                 * not expected that the sleep is interrupted. If it should
                 * occur a service exception should not be thrown since this
                 * would prevent the retry mechanism from working.
                 */
                fgLogger.error(CLASS
                        + " - Exception in sleep for getFileFromRepostory.", ex);
            }
            retryCounter = retryCounter + 1;
            fgLogger.info(CLASS + " - retryConter: " + retryCounter);
            createConnection(url);
            return getFileFromRepostory(repostoryFileName, localFileName);
        }
    }

    
    /**
     * Callback method
     * 
     * @param errorCode
     */
    public void callback(String strFileName, long nTotalSize, int statusCode)
    {
        try
        {
        	if(eventHandler != null)
        	{
        	   eventHandler.DownloadingEvent(strFileName, nTotalSize, statusCode);
        	}
        }
        catch (Exception e)
        {
        	fgLogger.error("callback() - Exception: " + e.getMessage());
        }
        catch (Throwable e)
        {
        	fgLogger.error("callback() - Exception: " + e.getMessage());
        }
    }
    
    private List<File> genFileList(String localFileName)
    {
        List<File> filePathList = new ArrayList<File>();

        File localDir = new File(localFileName);
        String fileName = localDir.getName();
        String filePath = localDir.getParent();        
        localDir = new File(filePath);
        
        FileFilter fileFilter = new WildcardFilter(fileName + ".*");
        File[] searchResults = localDir.listFiles(fileFilter);

        if(searchResults != null && searchResults.length > 0)
        {
            for (File file : searchResults)
            {
                if (fileName.equals(FilenameUtils.getBaseName(file.getName())))
                {
                    filePathList.add(file);
                }
            }
        }

        // Add new file name at last of array, make it having at least 1 available
        // file name in array. This is used for:
        // 1. If it is new file downloading, this is default file name.
        // 2. If all searched file are locked, it will download as new file,
        // and won't block any further logic.
        String tempLocalPath = localFileName + "." + System.currentTimeMillis();
        File tempFile = new File(tempLocalPath);

        filePathList.add(tempFile);

        return filePathList;
    }

    /**
     * Checks - based on the extension - if the file is a MS mobility log file
     * 
     * @param fileName
     * @return
     */
    private boolean isMSMobilityLogFile(String fileName)
    {
        return fileName.endsWith(CommonConstants.S19_EXTENSION);
    }

    /**
     * Checks - based on the extension - if the file is a user data file
     * 
     * @param fileName
     * @return
     */
    private boolean isUserDataFile(String fileName)
    {
        return fileName.endsWith(CommonConstants.USER_DATA_EXTENSION);
    }

    /**
     * Checks - based on the extension - if the file is a hot fix file
     * 
     * @param fileName
     * @return
     */
    private boolean isHotFixFile(String fileName)
    {
        return fileName.endsWith(CommonConstants.HOT_FIX_EXTENSION);
    }

    /**
     * Checks - based on the content - if the file is a picture push file
     * 
     * @param fileName
     * @return
     */
    private boolean isPicturePushFile(String fileName)
    {
        return fileName.contains(CommonConstants.PICTURE_PUSH_FILE_FIX_REP_DIR);
    }

    /**
     * This method is insert basic auth header for FileUpload/Download requests
     * 
     * @param postMethod
     *            used by HttpClient
     * @throws UnsupportedEncodingException
     */
    private void insertBasicAuthHeader(PostMethod postMethod)
            throws UnsupportedEncodingException
    {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        String basicAuthHeader = null;

        if ((auth != null) && (auth.getName() != null)
                && (auth.getCredentials() != null))
        {
            fgLogger.info(CLASS + " - Using normal login for Upload/Download");
            basicAuthHeader = auth.getName() + ":"
                    + auth.getCredentials().toString();
        }
        else if (this.anonymousUsername != null
                && this.anonymousUsername.length() > 0
                && this.anonymousSessionKey != null
                && this.anonymousSessionKey.length() > 0)
        {
            fgLogger.info(CLASS
                    + " - Using Anonymous Session keys for Upload/Download");
            basicAuthHeader = this.anonymousUsername + ":"
                    + this.anonymousSessionKey;
            postMethod.addRequestHeader(CommonConstants.ANONYMOUS_LOGIN_FLAG, String.valueOf(true));
        }
        if (null != basicAuthHeader)
        {
            postMethod.setRequestHeader(
                    "Authorization",
                    "Basic "
                            + new String(Base64.encodeBase64(basicAuthHeader
                                    .getBytes(credentialsCharset))));
        }
        else
        {
            fgLogger.error(CLASS + " - No credentials for Upload/Download");
        }

        // Adding the client ID to the request header
        postMethod.addRequestHeader(
                AuthCommonsHttpInvokerRequestExecutor.CLIENT_ID_HEADER_KEY,
                VersionNumber.FILE_TRANSMITTER_ID);
    }

    /**
     * Add the policy result file to server
     * 
     * @param serverUrl
     * @param licenseFile
     * @param localPath
     * @return LicenseFile
     * @throws ServiceException
     */
    public String addPolicyFile(String serverUrl, String fileName,
            String localPath, int policyType) throws ServiceException
    {
        try
        {
            String urlPath = PROTOCOL + serverUrl;
            createConnection(urlPath);

            String repositoryFile = "/"
                    + CommonConstants.POLICY_REPOSITORY_PATH + "/" + fileName;

            if (policyType == StatusConstants.POLICY_TYPE_MS_MOBILITY_LOGGING)
            {
                repositoryFile = "/"
                        + CommonConstants.MS_MOBILITY_LOGGING_FILE_FIX_REP_DIR
                        + "/" + fileName;
            }
            else if (policyType == StatusConstants.POLICY_TYPE_PICTURE_COLLECTION)
            {
                repositoryFile = "/"
                        + CommonConstants.PICTURE_COLLECTION_FILE_FIX_REP_DIR
                        + "/" + fileName;
            }

            fgLogger.info("Adding policy file from local PC " + localPath
                    + " to " + repositoryFile);

            String returnMessage = commitFileToRepository(repositoryFile,
                    localPath);
            if (returnMessage != null)
            {
                throw new ServiceException(returnMessage);
            }

            return repositoryFile;
        }
        catch (RepositoryException re)
        {
            throw new ServiceException("RepositoryException");
        }
    }


    /**
     * Will interrupt the transfer by setting the boolean to true
     */
    public static void stopTransfer()
    {
        stopTransfer = true;
    }

    private String httpClientExecuteMethod(PostMethod filePost)
            throws HttpException, IOException
    {
        int statusCode = httpClient.executeMethod(filePost);

        if (statusCode == HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE)
        {
            fgLogger.info("The status code indicates that the file "
                    + "to upload is too large for the server: "
                    + filePost.getStatusLine());
            return "RepositoryException_fileTooLarge";
        }
        else if (statusCode >= 200 && statusCode < 300)
        {
            return null;
        }
        else
        {
            fgLogger.error("Transfer of file failed - error message: "
                    + filePost.getStatusLine());
            return "RepositoryException_transferFailed";
        }
    }
}
