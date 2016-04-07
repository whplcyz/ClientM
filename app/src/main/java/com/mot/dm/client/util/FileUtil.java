/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                  Copyright 2011-2015 Motorola Solutions Inc.                 |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  iTM Client   
 CODED     BY:  chad001    2008.01.11

 ------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
 12/01/08   chad001               CCMPD00968807 Put codeplug open/parse/
                                                validation here so that they can 
                                                be used anywhere
 04/03/08   cnij001               CCMPD00996858 Software version is set to unknown 
                                                for a template if software has not 
                                                been loaded         
 12/03/08   chad001               CCMPD01004802 Cleanup dead code
 26/03/08   chad001               CCMPD01010431 Update software version if they 
                                                are changed due to add codeplug 
                                                for template                            
 24/Mar/08  Tommy Thomadsen       CCMPD01006519 Updated job status.
 01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database.
 01/May/08  Hai Dong              CCMPD01030725 Remove TEI check as all codeplugs 
                                                for a given radio have same TEI
 17/06/08   chad001               CCMPD01051225 Use FileUtils for codeplug
 15/09/08   ckfm01                CCMPD01092340 Removing codeplugVersion table in DB.
                                                & Converting CPversion to Integer.
 17/06/08   chad001               CCMPD01051225 Use FileUtils for codeplug 
 22/09/08   chad001               CCMPD01090964 Codeplug is determined by fullpath
                                                and no need to pass version                                              
 09/Oct/08  Michael Leismann      CCMPD01087804 Modified import to use common
                                                StatusConstants
 05/Jan/09  Hai Dong              CCMPD01145535 Take CM5000 feature.                                               
 26/Jan/09  Jens Hansen           CCMPD01139373 Updated importCodeplug, so code
                                                plugModified is always 0 (false)
                                                for templates.                                               
 17/Nov/08  jnss008               CCMPD01125953 Fix Klocwork findings
 20/Jan/09  chad001               CCMPD01125953 Fix KW finding
 23/Feb/09  Kim Mortensen         CCMPD01154300 Import codeplug on CM5000 
 12/Mar/09  Hai Dong              CCMPD01178702 Avoid using raw exception message                                          
 30/Mar/09  Sigurdur Jonsson      CCMPD01188992 Import Templates via XML.
 20/Apr/09  Kim Mortensen         CCMPD01198547 Making sure we have correct checking 
                                                for file extentions.                                                   
 20/Apr/09  Kim Mortensen         CCMPD01198547 Correct Arguments for starting Native
                                                Programmer from command line.                                                   
 20/May/09  Sigurdur Jonsson      CCMPD01211485 Import of template (cpd file) via xml
                                                file overwrites xml file if it has
                                                the same name.
 21/Jul/09  Jens Hansen           CCMPD01240884 Create getTempPath which returns
                                                getProperty(java.io.tmpdir) 
 21/Sep/09  Hai Dong              CCMPD01259651 Supports the new radio type
 01/Oct/09  Jens Hansen           CCMPD01269158 Changed getCodeplugFromXml 
                                                to use device type service instead
                                                of the codeplug parser
 15/Oct/09  Kalyan Pushpala       CCMPD01275462 importing a codeplug with empty 
                                               software version is restricted
20/Oct/09  Hai Dong               CCMPD01277716 Allow import with different TEA mode                                               
20/Oct/09  Michael Leismann       CCMPD01277660 Showing iTM model type name
                                                instead of id if types don't
                                                match
20/Oct/09  Hai Dong               CCMPD01278175 Correct message
31/Oct/08  Hai Dong               CCMPD01282666 Check cli exists 
10/Nov/09  Kim Mortensen          CCMPD01284501 Moving Codeplug_Version from 
                                                Device to Codeplug.
24/Aug/10  cjh102                 CCMPD01385457 Updates for CPE codeplugs 
20/09/10   chad001               ccmpd01393090 4.0 licensing feature  
30/Mar/11  Michael Leismann      CCMPD01488491 iTM 5.0: Merged getCodeplug,
                                               getSoftware and getNwLoggingFile
                                               into a getFile method in HttpUtil
08/05/11   chad001               ccmpd01502972 support for Multiple Codeplug 
                                               Edit/Views   
05/May/11  Hai Dong              CCMPD01502972 Panda integration   
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions                                                                                            
05/May/11  Hai Dong              CCMPD01502972 Panda integration
16/Jun/11  cvkh36                CCMPD01521711 iTM 5.0: Enhanced API
29/Jun/11  chad001               CCMPD01526562 Template SW change
21/Jul/11  Michael Leismann      CCMPD01534934 iTM 5.0: Moved getTmpPath method
                                               functionality to
                                               common.util.FileUtils
28/jul/11  Kim Mortensen         CCMPD01537287 Improving our filename for codeplugs.
08/Aug/11  chad001               CCMPD01541784 Space makes codeplug cannot be modified
10/Oct/11  Kim Mortensen         CCMPD01565747 Add logging in case of unsuccessfull, 
                                               and bail out correctly with service
                                               exception.
22/Nov/11  jnss008               CCMPD01582468 Fix for, Import a template with
                                               no application image fails
27/Feb/12  wch378                CCMPD01618679 prevent some banned characters being put into the file name  
22/Mar/12  gjmv74                CCMPD01617113 import different templates,notes upgrades
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature 
22/Oct/12  wch378                CCMPD01709286 If the codeplug has been upgraded by Panda, the software version will
                                               be removed. Modify the logic for handling the case, because the logic 
                                               before make a bad effect(prevent to execute the logic after that).
4/Dec/12   tqfn38 		 		 CCMPD01721424 iTM6.1 Picture Pull & Push  
28/Jan/13  wch378                CCMPD01738482 When you add  a template and type some notes 
                                               at the same time.The notes are not stored.    
30/Jan/13  wch378                CCMPD01739363 add codeplug feature enhancement 
16/Sep/13  wch378                CCMPD01813562 update notes        
06/Jan/14  qngv36                CCMPD01849547 Should check extended model type when import cp/profile to existing template/profile 
28/3/14    wch378                CCMPD01889533 develop flexera license.                                                            
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mot.dm.client.delegation.ServerIpAccessor;
import com.mot.dm.client.dmImpl.control.Controller;
import com.mot.dm.client.dmImpl.gui.DmsContext;
import com.mot.dm.common.model.Codeplug;import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.FlexeraLicenseSource;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.Software;
import com.mot.dm.common.model.StatusConstants;
import com.mot.dm.common.model.SupportSoftware;
import com.mot.dm.common.service.CodeplugService;
import com.mot.dm.common.service.DeviceService;
import com.mot.dm.common.service.SoftwareService;
import com.mot.dm.common.util.FileUtils;
import com.mot.dm.common.util.HttpUtil;
import com.mot.dm.common.util.exception.LicenseException;
import com.mot.dm.common.util.exception.ServiceException;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @Version 1.0 Jul 11, 2007
 * @author q19266 Jack Qiu
 */
public class FileUtil
{
    private static Logger fgLogger = LoggerController.createLogger(FileUtil.class);

    // The none encryption SW has format xxx000xxxx.
    private static final String NONE_ENCRYTION_SW_PATTERN = "000";

    // The none encryption SW has format xxx000xxxx.
    private static final int PATTERN_START_INDEX = 3;

    // The none encryption SW has format xxx000xxxx.
    private static final int PATTERN_END_INDEX = 6;

    public static enum SwCompatible
    {
        COMPATIBLE,
        WARNING,
        ERROR
    };

    /**
     * Used to generate a uniformly formatted filename for codeplugs when they
     * are to be compared/exported, viewed.
     * 
     * @param codeplugToUse
     * @param fileExtension
     * @return copdeplug filename.
     */
    public static String generateCodeplugFileName(Codeplug codeplugToUse,
            boolean appendFileExtention)
    {
        String cpName = "";
        String fileExtension = "";
        Device deviceForCodeplug = codeplugToUse.getDevice();
        if (appendFileExtention)
        {
        	int extensionStartPos = codeplugToUse.getCodeplugFullPath().lastIndexOf('.');
            fileExtension = codeplugToUse.getCodeplugFullPath().toLowerCase().substring(extensionStartPos);
        }

        if (deviceForCodeplug != null)
        {
            // We have a template
            if (deviceForCodeplug.isAProfile())
            {
                cpName = deviceForCodeplug.getDeviceName() + "_Profile" + "_"
                        + codeplugToUse.getCodeplugRepositoryVersion() + "_"
                        + UuIdGenerator.generate() + fileExtension;
            }
            else if (deviceForCodeplug.isATemplate())
            {
            	cpName = deviceForCodeplug.getDeviceName() + "_Template" + "_"
                + codeplugToUse.getCodeplugRepositoryVersion() + "_"
                + UuIdGenerator.generate() + fileExtension;
            }
            // We have a normal radio
            else
            {
                cpName = deviceForCodeplug.getDeviceName() + "_" + deviceForCodeplug.getDeviceId()
                        + "_" + codeplugToUse.getCodeplugRepositoryVersion() + "_"
                        + UuIdGenerator.generate() + fileExtension;
            }
        }
        else
        {
            cpName = codeplugToUse.getCodeplugUuid() + "_"
                    + codeplugToUse.getCodeplugRepositoryVersion() + fileExtension;
        }

        cpName = FileUtil.getLegalFileName(cpName);
        return cpName;
    }

    public static String getLegalFileName(String preFileName)
    {
        Pattern standardpattern = Pattern.compile("\\\\|/|:|\\*|\\?|\"|<|>|\\||\\s+");
        Matcher matcher = standardpattern.matcher(preFileName);
        String postFileName = matcher.replaceAll("_");
        return postFileName;
    }

    public static boolean isInvalidFolderPath(String pathName)
    {
        return (pathName.length() > 250 // ����MaxPath
                || pathName.startsWith(".") // ���ļ���
                || pathName.contains(":")
                || pathName.contains("*")
                || pathName.contains("?") 
                || pathName.contains("\"") 
                || pathName.contains(">") 
                || pathName.contains("<") 
                || pathName.contains("|"));
    }
      
    public static String downLoadCp(Codeplug codeplug) throws ServiceException
    {
        String localCpPath = null;
        File file;
        try
        {
            // Build filename based on codeplug/device information
            String tempPath = getTmpPath();
            file = new File(tempPath, generateCodeplugFileName(codeplug, true));

            localCpPath = file.getAbsolutePath();
            String remoteCpPath = codeplug.getCodeplugFullPath();
            String serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address"); //$NON-NLS-1$
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.getFile(serverUrl, remoteCpPath, localCpPath);
            return localCpPath;
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());
            throw new ServiceException(DmsContext.getString("FileUtil.downloadCodeplugFail"));
        }
    }
    

    /*
     * The method will add a codeplug to the file repository system. This method
     * is used for import codeplug for template.
     */
    public static boolean importCodeplug(Codeplug codeplug, String codeplugPath,
            Device updatedDevice) throws ServiceException
    {
        codeplugPath = codeplugPath.replace('\\', '/');
        String serverUrl = null;
        try
        {
            serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address");
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());
            throw new ServiceException(DmsContext.getString("FileUtil.importCodeplugFail"));
        }
        
        ClientUtil.addCodeplug(serverUrl, codeplug, codeplugPath);

        CodeplugService codeplugService = Controller.getInstance().getCodeplugService();
        codeplug = codeplugService.getLatestCodeplug(codeplug.getDevice());

        // --------------------------------------------------------------------//
        // import codeplug is different to edit codeplug. for edit codeplug, //
        // the device codeplug version is not changed as the working copy of //
        // the codeplug can be different to the one in database. the import //
        // codeplug will make the new codeplug to be the one in database as //
        // current codeplug. Therefore we need to use services to set the //
        // import codeplug to the current codeplug of the template //
        // --------------------------------------------------------------------//
        DeviceService deviceService = Controller.getInstance().getDeviceService();

        try
        {
            Device device = codeplug.getDevice();
            device.setLogicalId(updatedDevice.getLogicalId());
            device.setItmModelType(updatedDevice.getItmModelType());
            device.setExtModelType(updatedDevice.getExtModelType());
            // If customer add new notes,we use new notes. Else we use the notes from template.
            if (updatedDevice.getNotes() != null && !updatedDevice.getNotes().isEmpty())
            {
                device.setNotes(updatedDevice.getNotes());
            }
            // If its a template, we would like to do special processing that
            // makes the template "eat" the codeplug immediately and not go
            // through "codeplug modified", but directly to "ready" with the
            // new codeplug attached.
            if (device.isATemplateOrProfile())
            {
                // Set the codeplug
                device.setCurrentCodeplug(codeplug);
                // Set it to ready state
                device.setGuiRadioState(StatusConstants.DEVICE_READY);
                // Clear the codeplug modified flag.
                device.setCodeplugModified(0);
            }
            else
            {
                device.setSoftwareVersion(updatedDevice.getSoftwareVersion());
            }
            deviceService.updateDevice(device);
        }
        catch (Exception ed)
        {
            fgLogger.error(ed.getMessage());
            throw new ServiceException(DmsContext.getString("FileUtil.importCodeplugFail"));
        }

        return true;
    }

    /*
     * The method will add a codeplug to the file repository system.
     */
    public static void addCodeplug(Codeplug codeplug, String codeplugPath) throws ServiceException
    {
        codeplugPath = codeplugPath.replace('\\', '/');
        String serverUrl = null;
        try
        {
            serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address"); //$NON-NLS-1$
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());
            throw new ServiceException(DmsContext.getString("FileUtil.addCodeplugFail"));
        }
        
        ClientUtil.addCodeplug(serverUrl, codeplug, codeplugPath);
    }





    /**
     * Compare two code-plugs to determine if they are compatible
     * 
     * @param oldCP
     *            , the first of the two code-plugs to compare
     * @param newCP
     *            , the second of the two code-plugs to compare
     * @param outStrBuff
     *            , a warning or error string returned
     * @param gui
     *            , choose if error and warning texts are for GUI or log file
     * @return enumeration value = compatible, warning or error
     */
    // CHECKSTYLE IGNORE MethodLength FOR NEXT 500 LINES
    public static SwCompatible compareCodeplugParameters(Codeplug oldCP, Codeplug newCP,
            StringBuffer outStrBuff, boolean gui)
    {
        SwCompatible result = SwCompatible.COMPATIBLE;

        // If the new code-plug has a different radio type than the radio type
        // of the old code-plug
        // then return an ERROR.
        // If the new code-plug has a different TEA mode than the TEA mode of
        // the old code-plug
        // then return an WARNING.
        // If the new code-plug has a different version than the version of the
        // old code-plug
        // then return a WARNING.
        // If the new code-plug has a different E2E encryption than the E2E
        // encryption of the old code-plug
        // then return a WARNING.

        Software newCPsw = new Software();
        Software oldCPsw = new Software();

        /**
         * if the codeplug has been upgraded by Panda, the software version will
         * be removed. In that case we say no checks will be done for
         * compatibility. This is dsicussed with S�ren
         */
        if (newCP.getSoftwareVersion() == null || newCP.getSoftwareVersion().length() == 0)
        {
            newCP.setSoftwareVersion(getSwVersionFromCodeplugCPversion(newCP));
        }
        if (oldCP.getSoftwareVersion() == null || oldCP.getSoftwareVersion().length() == 0)
        {
            oldCP.setSoftwareVersion(getSwVersionFromCodeplugCPversion(oldCP));
        }
        newCPsw.setSoftwareVersion(newCP.getSoftwareVersion());
        oldCPsw.setSoftwareVersion(oldCP.getSoftwareVersion());

        String newCpRadioType = newCP.getDevice().getItmModelType().getItmModelTypeName();
        String existingTemplRadioType = oldCP.getDevice().getItmModelType().getItmModelTypeName();
        String newCpTeaMode = newCPsw.getTeaMode();
        String existingTemplTeaMode = oldCPsw.getTeaMode();
        String deviceName = oldCP.getDevice().getDeviceName();

        String newCpReleaseVersion = newCPsw.getVersion();
        String existingTemplateSoftwareReleaseVersion = oldCPsw.getVersion();
        String existingTemplE2EMode = oldCPsw.getEnd2end();
        String newCpE2EMode = newCPsw.getEnd2end();
        SoftwareComparator swComp = new SoftwareComparator();

        String prefix = "";
        String suffix = "";
        String suffix2 = "";
        if (gui == true)
        {
            suffix = DmsContext.getString("ImportCodeplugAction.wantToContinueImport");
        }
        else
        {
            prefix = DmsContext.getString("ImportRadiosAndJobsFromFile.prefix");
            prefix = String.format(prefix, deviceName);
            suffix = DmsContext.getString("ImportRadiosAndJobsFromFile.willImportAnyway");
            suffix2 = DmsContext.getString("ImportRadiosAndJobsFromFile.abortImport");
        }
        if (newCP.getSoftwareVersion() == null || newCP.getSoftwareVersion().length() == 0)
        {

            String text = DmsContext
                    .getString("ImportRadiosAndJobsFromFile.EmptySoftwareVersionCodeplugMsg");
            String theText = prefix;
            theText = theText.concat(text);
            theText = theText.concat(suffix2);

            outStrBuff.append(theText);
            result = SwCompatible.ERROR;

        }
        else if (!newCpRadioType.equals(existingTemplRadioType))
        {
        	// The radio type of the new code-plug does not match the radio
            // type
            // of the old code-plug.
            String text = DmsContext
                    .getString("ImportRadiosAndJobsFromFile.RadioTypeNotMatchMsg");
            // Replace %s with values
            text = String.format(text, newCpRadioType, existingTemplRadioType);
            String theText = prefix;
            theText = theText.concat(text);
            theText = theText.concat(suffix2);

            outStrBuff.append(theText);
            result = SwCompatible.ERROR;
        }
        else if(!ClientUtil.isSameCopyGrp(newCP.getDevice().getExtModelType(), oldCP.getDevice().getExtModelType()))
        {
        	// The radio type of the new code-plug does not match the radio
            // type
            // of the old code-plug.
            String text = DmsContext
                    .getString("ImportRadiosAndJobsFromFile.ModelTypeNotMatchMsg");
            // Replace %s with values
            text = String.format(text, newCP.getDevice().getExtModelTypeDescription(), oldCP.getDevice().getExtModelTypeDescription());
            String theText = prefix;
            theText = theText.concat(text);
            theText = theText.concat(suffix2);

            outStrBuff.append(theText);
            result = SwCompatible.ERROR;
        }
        //for warning....
        else if (!existingTemplTeaMode.equals("") && !newCpTeaMode.equals("")
                && !newCpTeaMode.equals(existingTemplTeaMode))
        {
        	// The TEA mode of the new code-plug does not match the TEA mode
            // of the old code-plug.
            String text = DmsContext
                    .getString("ImportRadiosAndJobsFromFile.TeaEncrModeNotMatchMsg");
            // Replace %s with values
            text = String.format(text, newCpTeaMode, existingTemplTeaMode);
            String theText = prefix;
            theText = theText.concat(text);
            theText = theText.concat(suffix);

            outStrBuff.append(theText);
            result = SwCompatible.WARNING;
        }
        else if (swComp.compareIgnoreEncryption(oldCPsw, newCPsw) != 0
                || !newCpE2EMode.equals(existingTemplE2EMode))
        {
            if (swComp.compareIgnoreEncryption(oldCPsw, newCPsw) != 0)
            {
                // The software release version of the new code-plug does not
                // match
                // the software release version of the old code-plug.
                String text = DmsContext
                        .getString("ImportRadiosAndJobsFromFile.ReleaseVersionNotMatchMsg");
                // Replace %s with values
                text = String.format(text, newCpReleaseVersion,
                        existingTemplateSoftwareReleaseVersion);
                String theText = prefix;
                theText = theText.concat(text);
                theText = theText.concat(suffix);

                outStrBuff.append(theText);
                result = SwCompatible.WARNING;
            }
            if (!existingTemplE2EMode.equals("") && !newCpTeaMode.equals("")
                    && !newCpE2EMode.equals(existingTemplE2EMode))
            {
                // The End2End encryption mode of the new code-plug does not
                // match
                // the End2End encryption mode of the old code-plug.
                String text = DmsContext
                        .getString("ImportRadiosAndJobsFromFile.E2EEncrModeNotMatchMsg");
                // Replace %s with values
                text = String.format(text, newCpE2EMode, existingTemplE2EMode);
                String theText = prefix;
                theText = theText.concat(text);
                theText = theText.concat(suffix);

                outStrBuff.append(theText);
                result = SwCompatible.WARNING;
            }
        }
        return result;
    }

    /**
     * Checks if a specific file exists
     * 
     * @param p_filePath
     *            the path to the file
     * @return true, if the file exists and is a regular file, otherwise false
     */
    public static boolean fileExists(String p_filePath)
    {
        File file = new File(p_filePath);
        if (file.isFile())
            return true;
        else
            return false;
    }

    /**
     * See com.mot.dm.common.util.getTmpPath()
     * 
     * @return the temp path
     */
    public static String getTmpPath()
    {
        return FileUtils.getTmpPath();
    }

    /**
     * Get the SW version if the codeplug itself doesn't contain that
     * information
     * 
     * @param cp
     * @return String
     */
    private static String getSwVersionFromCodeplugCPversion(Codeplug cp)
    {
        String release = "";
        Device dev = cp.getDevice();
        if (dev == null)
            return release;
        String extType = dev.getExtModelType();
        String cpStructureVersion = cp.getCodeplugVersion();

        Software soft = new Software();
        soft.setSoftwareVersion(dev.getSoftwareVersion());
        String encMode = soft.getEncryptionMode();

        SoftwareService swService = Controller.getInstance().getSoftwareService();
        try
        {
            SupportSoftware ss = swService.getSwByExtModelTypeAndCodepluigStructureVersion(extType,
                    cpStructureVersion);
            if (ss == null)
            {
                // English logging statement, with argument details.
                fgLogger.error("Unable to find combination of Ext. Model type:" + extType
                        + " and CPStructVersion:" + cpStructureVersion + " In metadata.");

                // Localized Exception statement.
                throw new ServiceException(
                        DmsContext.getString("AddTemplateImpl.EmptySoftwareVersionMsg"));
            }
            else
            {
                release = soft.getSwVersionBuildType() + ss.getSwVersionBuildId() + encMode
                        + ss.getSwVersionRelease();
            }
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        return release;
    }

    /*
     * The method will add a user data to the file repository system.
     */
    public static String addUserData(String userDataPath, String uuid) throws ServiceException
    {
        String userDataServerRef = "";

        String serverUrl = null;
        try
        {
            serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address");
            HttpUtil httpUtil = new HttpUtil();
            userDataServerRef = httpUtil.addUserData(serverUrl, userDataPath, uuid);
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());

            throw new ServiceException("FileUtil.addUserDataFail");
        }
        catch (ServiceException ex)
        {
            throw new ServiceException("FileUtil.addUserDataFail");
        }

        return userDataServerRef;
    }

    /*
     * The method will add a hot fix to the file repository system.
     */
    public static String addHotFix(String hotFixPath, String uuid) throws ServiceException
    {
        String hotFixServerRef = "";

        String serverUrl = null;
        try
        {
            serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address");
            HttpUtil httpUtil = new HttpUtil();
            hotFixServerRef = httpUtil.addHotFix(serverUrl, hotFixPath, uuid);
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());

            throw new ServiceException("FileUtil.addHotFixFail");
        }
        catch (ServiceException ex)
        {
            throw new ServiceException("FileUtil.addHotFixFail");
        }

        return hotFixServerRef;
    }

    /*
     * The method will add a hot fix to the file repository system.
     */
    public static String addPicturePushFile(String picturePushFileFrom, String uuid,
            String updateCount) throws ServiceException
    {
        String picturePushFileInServer = "";

        String serverUrl = null;
        try
        {
            serverUrl = ServerIpAccessor.getSelf().getProperties().getProperty("address");
            HttpUtil httpUtil = new HttpUtil();
            picturePushFileInServer = httpUtil.addPicturePushFile(serverUrl, picturePushFileFrom,
                    uuid, updateCount);
        }
        catch (IOException ex)
        {
            fgLogger.error(ex.getMessage());

            throw new ServiceException("FileUtil.addPicturePushFile.Fail");
        }
        catch (ServiceException ex)
        {
            throw new ServiceException("FileUtil.addPicturePushFile.Fail");
        }

        return picturePushFileInServer;
    }
    
}
