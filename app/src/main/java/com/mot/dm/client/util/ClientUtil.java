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
 CODED     BY:  chad001    2010.07.04

------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
 04/07/10   chad001               CCMPD01365714 Get radio types from server
 06/07/10   chad001               ccmpd01365714 Personal data   
 19/08/10   chad001               ccmpd01383963 Show sub-menu in Vista    
 20/09/10   chad001               ccmpd01393090 4.0 licensing feature 
 14/12/10   cjh102                CCMPD01453332 License expiry   
 21/Sep/10  chad001               ccmpd01394081 add time mapping
 21/Sep/10  chad001               ccmpd01394081 Add policies manager 
 03/01/11   chad001               ccmpd01456430 Paging for TEI table 
 06/jan/11  Hai Dong              ccmpd01460066 Using id instead of name   
 31/Jan/11  chad001               CCMPD01468421 exclude frodo and CH  
 22/Jun/11  Hai Dong              CCMPD01524144 Cross group copy: use ext model types 
                                               instead of ItmModelType  
 29/Jun/11  chad001               CCMPD01526562 Template SW change
 8/Jul/11   cjh102                CCMPD01525915 Adding method for getting paths
                                                in the client
 04/Jul/11  chad001               CCMPD01529282 fix Personal data and special 
                                                parameter
 19/Jul/11  Michael Leismann      CCMPD01533374 iTM 5.0: Multiple lines and
                                                scrollbar for SW versions
                                                Code cleanup
 27/Jul/11  Michael Leismann      CCMPD01535660 iTM 5.0: Check if CPS is running
 11/Nov/11  Kim Mortensen         CCMPD01565747 We now support Mobility logging 
                                                for Frodo.
12/Jul/12   gjmv74                CCMPD01675886 searching feature both through extmodeltype and itmmodeltypeId
15/Aug/12   a23126                CCMPD01686323 [iTM R6.0] Feature of German fire - upgrade limitation
26/Nov/12   ZhaoWei wch378        CCMPD01713576 Convert Radio Model
21/Mar/13  wch378                 CCMPD01756334 detach ip and port on client 
4/Dec/12  tqfn38 		          CCMPD01721424 iTM6.1 Picture Pull & Push
27/Feb/13 wch378                  CCMPD01747688 iTM6.1 export policy result
26/Mar/13  wch378                 CCMPD01750151 Prevent convert ext-model across itm-ModelType 
09/May/13  wch378                 CCMPD01770362 iTM6.1 Judge personal data or special parameter is supported 
                                               by SwVersion(a new factor)
19/06/13   wch378                 CCMPD01784068 refactor language setting                                                 
24/June/13 wch378                 CCMPD01785776 rebuild pd and sp when target softerware or model change
31/July/13 qngv36                 CCMPD01790940 Support datalight upgrade in iTM6.1      
16/Aug/13  wch378                 CCMPD01805101 enhance checking logic for apply template   
21/Aug/13  qngv36                 CCMPD01804786 recover flash pack when apply codeplug change
11/Sep/13  wch378                 CCMPD01813562 replace ISSI Tag      
16/Sep/13  wch378                 CCMPD01813562 enhance cache mechanism                    
19-Dec-13  QNGV36                 CCMPD01846352 [iTM 6.2] XML should support create job for new radio if the agency has enforce template/profile flag         
23/Jan/14  qngv36                 CCMPD01847926 iTM will enforce wrong profile for multi-selection
17/June/14 tqfn38		          CCMPD01902641 Flexera license feature
28/3/14    wch378                CCMPD01889533 develop flexera license.
03/07/13   tqfn38                CCMPD01907396 Flexera license development
13/10/14   wch378                CCMPD01937887 iTM will put "iTM model type" and "radio current software version" into the request file.
24/10/14   qngv36                 CCMPD01937810 Profile, template, enforced profile will apply wrong when select multi radio programming.
11/03/14   a21944                 CCMPD01943650 [iTM7.0]Performace issue of fresh client folder  
10/10/14   cmdg37                 CCMPD01930966  Enhancement for loading release packages,
											   multi-selection is supported.   
27/11/14   qngv36                 CCMPD01950160 [iTM 7.0]Client can choose wrong profile/template in cross model case
04/01/15   tqfn38                CCMPD01956628 The refactor of radio license page.
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import com.mot.dm.client.dmImpl.control.Controller;
import com.mot.dm.common.model.Agency;
import com.mot.dm.common.model.Codeplug;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.EnforcedMandatoryFiles;
import com.mot.dm.common.model.FolderPermissions;
import com.mot.dm.common.model.ItmLanguage;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.MandatoryFile;
import com.mot.dm.common.model.ModelNumber;
import com.mot.dm.common.model.ModelType;
import com.mot.dm.common.model.Parameter;
import com.mot.dm.common.model.ParameterItem;
import com.mot.dm.common.model.Software;
import com.mot.dm.common.model.StatusConstants;
import com.mot.dm.common.model.SuppPolTypeForItmModelType;
import com.mot.dm.common.model.SupportSoftware;
import com.mot.dm.common.service.AgencyService;
import com.mot.dm.common.service.DeviceTypeService;
import com.mot.dm.common.service.PolicyService;
import com.mot.dm.common.service.SoftwareService;
import com.mot.dm.common.util.CommonConstants;
import com.mot.dm.common.util.HttpUtil;
import com.mot.dm.common.util.LanguageUtil;
import com.mot.dm.common.util.SecurityUtils;
import com.mot.dm.common.util.exception.ServiceException;
import com.mot.dm.common.util.log.impl.LoggerController;
import com.mot.dm.common.util.FileUtils;


/*
 * Utility class for client 
 */
public final class ClientUtil
{
    private ClientUtil()
    {
    }

    private static Logger fgLogger = LoggerController.createLogger(ClientUtil.class);
    private static String configurationPath = null;
    private static String clientRootPath = null;

    private static HashMap<String, String> extNamesMap = new HashMap<String, String>();
    private static Map<String, List<String>> crossModelGroupsMap = null;
    private static Object obj = new Object();

    /**
     * notice: this text is used for resource map to find localized text. in
     * resource map, entry has key like "General.5MINUTES", which has text
     * "5 Minutes" in english. be aware of that this mapping while adding or
     * modifying the array.
     */
    public static String[] TIME_INTERVAL_TEXT =
    { "5MINUTES", "10MINUTES", "30MINUTES", "1HOUR", "2HOURS", "6HOURS",
            "12HOURS", "1DAY", "2DAYS", "1WEEK" };

    /**
     * integer value for time frame
     */
    public static int[] TIME_INTERVAL_INT =
    { 5, 10, 30, 60, 120, 360, 720, 1440, 2880, 10080 };

    private static HashMap<String, String> softwareMap = new HashMap<String, String>();

    // Separator used for multiple software versions
    public static final String SOFTWARE_SEPARATOR = ":";


    /*
     * At the moment, only Boromior radio supports dalight file system. So, only
     * Boromior needs to check if SW is compatibility with radio. The radio type
     * and file system is hard code.
     * 
     * In the feature, Frodo may also support datalight, the function needs to
     * change.
     */
    public static boolean isFWAndDeviceFileSystemApplicable(Device device, Software sw)
    {
        if (!device.getItmModelTypeId().equalsIgnoreCase(ItmModelType.MTP3250_ITM_MODEL_TYPE_ID))
        {
            return true;
        }

        String BOROMIOR_FW_START_SUPPORT_DATALIGHT = "7672";
        Software deviceSW = new Software();
        deviceSW.setSoftwareVersion(device.getSoftwareVersion());

        // if current device SW version is no less than the start version of
        // boromior datalight start version
        // then, only SW files whose version are also no less than the version
        // is applicable.
        if (deviceSW.getVersion().compareToIgnoreCase(BOROMIOR_FW_START_SUPPORT_DATALIGHT) >= 0)
        {
            if (sw.getVersion().compareToIgnoreCase(BOROMIOR_FW_START_SUPPORT_DATALIGHT) >= 0)
            {
                return true;
            }
        }
        // Otherwise, we do not limit the SW range as current device is still
        // YAFAS file system.
        else
        {
            return true;
        }

        return false;
    }

    /**
     * The limitation in MR10.6.3:
     * 
     * Due to transceiver kernel limitation (flash pack support), the upgrade
     * path of Frodo (transceiver) cannot across MR10.6.3. That means if a
     * transceiver is older than MR10.6.3, it cannot be upgraded to a version
     * that is higher than MR10.6.3 directly. It must be upgraded to MR10.6.3
     * firstly and then to the target version.
     * 
     * To keep alignment with transceiver, the upgrade path of
     * "Mobile Control-Head" will also has this limitation - cannot across
     * MR10.6.3.
     * 
     * MR10.6.3 NGCH - start cp version 7345 MR10.6.3 TRAN - start cp version
     * 220
     * 
     * MR10.6.5 NGCH - start cp version 7380 MR10.6.5 TRAN - start cp version
     * 300
     * 
     * @return
     */
    public static boolean isLimitedByMR10_6_3(Device currentDevice, Software targetSoftware)
    {
        String typeId = null;
        int iCurrCpVersion = 0;
        int iTargetCpVersion = 0;

        try
        {
            typeId = currentDevice.getItmModelTypeId();
            iCurrCpVersion =
                    Integer.parseInt(currentDevice.getCurrentCodeplug().getCodeplugVersion());
            iTargetCpVersion = Integer.parseInt(targetSoftware.getCodeplugVersion());
        }
        catch (Exception e)
        {
            fgLogger.error("getItmModelTypeId() or convert codeplug version error.", e);
            // If we cannot get the cp version, just skip the check.
            return false;
        }

        // TRAN
        if (typeId.equalsIgnoreCase(ItmModelType.MTM5400_ITM_MODEL_TYPE_ID))
        {
            // upgrade - from lower version across MR10.6.3
            if ((iCurrCpVersion < 220) && (iTargetCpVersion >= 300))
            {
                return true;
            }
        }

        // NGCH
        if (typeId.equalsIgnoreCase(ItmModelType.MTM800E_CONTROL_HEAD_ITM_MODEL_TYPE_ID))
        {
            // upgrade - from lower version across MR10.6.3
            if ((iCurrCpVersion < 7345) && (iTargetCpVersion >= 7380))
            {
                return true;
            }
        }

        return false;
    }

    /*
     * Get the software version for Judgement of personal data and special
     * parameter support
     * 
     * @param RadioParameters m_radioParameters contains target software version
     * 
     * 
     * @return String
     */
    public static String GetSwVersionForJudgement(RadioParameters m_radioParameters)
    {
        /*
         * Target software version is first choice. Then source software
         * version. Then regard it as a new radio case return null.
         */
        String swVersionVariable = m_radioParameters.getSW();
        if (swVersionVariable == null || swVersionVariable.equals("")
                || swVersionVariable.equals(CommonConstants.UNKNOWN))
        {
            swVersionVariable = m_radioParameters.getDeviceList().get(0).getSoftwareVersion();
            if (swVersionVariable == null || swVersionVariable.equals("")
                    || swVersionVariable.equals(CommonConstants.UNKNOWN))
            {
                swVersionVariable = null;
            }
        }
        return swVersionVariable;
    }

    /*
     * Get the ext model for Judgement of personal data and special parameter
     * support
     * 
     * @param RadioParameters m_radioParameters contains target software version
     * 
     * 
     * @return String
     */
    public static String GetExtModelForJudgement(RadioParameters m_radioParameters)
    {
        /*
         * Target ext model version is first choice. Then source ext model
         * version. Then ext model got from template Then regard it as a new
         * radio case return null.
         */
        String extModel = m_radioParameters.getExtModelType(); // Now it is
        // Target ext
        // model or
        // source ext
        // model
        if (extModel == null || extModel.equals(""))
        {
            Codeplug cp = m_radioParameters.getTemplate();
            if (cp != null)
            {
                extModel = cp.getDevice().getExtModelType();
            }
            else
            {
                List<Codeplug> profileList = m_radioParameters.getProfileList();
                if (profileList.size() > 0)
                {
                    extModel = profileList.get(0).getDevice().getExtModelType();
                }
            }
        }
        return extModel;
    }

    
    /*
     * Get the ext model for Judgement of personal data and special parameter
     * support
     * 
     * @param RadioParameters m_radioParameters contains target software version
     * 
     * 
     * @return String
     */
    public static List<String> GetExtModelListByParameter(RadioParameters m_radioParameters)
    {
        /*
         * Target ext model version is first choice. Then source ext model
         * version. Then ext model got from template Then regard it as a new
         * radio case return null.
         */
        List<String> extModelTypes = new ArrayList<String>();
        for (Device d : m_radioParameters.getDeviceList())
        {
            String extModel = d.getExtModelType();
            if (extModel != null && extModel.length() > 0 &&!extModelTypes.contains(extModel))
            {
                extModelTypes.add(extModel);
            }
        }
        //non-new radios case
        if(extModelTypes.size() > 0)
        {
            return extModelTypes;
        }
        
        
        /*
         * Get model type from template, profile 
         * */
        String modelType = GetExtModelForJudgement(m_radioParameters);
        if(modelType != null && modelType.length() > 0)
        {
            extModelTypes.add(modelType);
            return extModelTypes;
        }
        
        
        /*
         * Get model type from SW version if sw is selected
         * */
        if(m_radioParameters.getSW() != null && m_radioParameters.getSW().length() > 0)
        {
            DeviceTypeService deviceTypeSevice = Controller.getInstance().getDevceTypeService();
            try
            {
                extModelTypes = deviceTypeSevice.getExtModelTypesByFirmWareVersion(m_radioParameters.getSW());
                return extModelTypes;
            }
            catch(Exception ex)
            {
                fgLogger.error(ex.getMessage());
            }
        }
        
        
        /*
         * Get model type from iTM model type
         * */
        return m_radioParameters.getItmModelType().getExtModelTypes();
    }
    
    
    public static List<String> getExtModelRangeWhenSelectTemplate(RadioParameters m_radioParameters)
    {
        List<String> extModelRangeList = new ArrayList<String>();
        if(m_radioParameters.getDeviceList().size() <= 0)
        {
            return extModelRangeList;
        }
        
        boolean isModelTypeUnknown = false;
        String deviceExtModelType = ClientUtil.GetExtModelForJudgement(m_radioParameters);
        Device dev = m_radioParameters.getDeviceList().get(0);
        //if its extended model types size are greater than 1, it should be new radio
        if ((dev.getExtModelTypes() != null) && (dev.getExtModelTypes().size() > 1)
                && (deviceExtModelType == null || deviceExtModelType.isEmpty()))
        {
            isModelTypeUnknown = true;
        }
        
        if(isModelTypeUnknown)
        {
            return getExtModelRangeWhenSelectTemplate4ExtModelTypeUnknown(m_radioParameters);
        }
        else
        {
            return getExtModelRangeWhenSelectTemplate4ExtModelTypeKnown(m_radioParameters);
        }
    }
    
    
    /*
     * For ext model type known  programming, if the template/profile's model can apply to all of the selected radio(s), then it is candidate template/profile.
     * */
    private static List<String> getExtModelRangeWhenSelectTemplate4ExtModelTypeKnown(RadioParameters m_radioParameters)
    {
        List<String> toExtModelRangeList = new ArrayList<String>();
        List<String> applicableFromExtTypes = new ArrayList<String>();
        String deviceExtModelType = ClientUtil.GetExtModelForJudgement(m_radioParameters);
        
        if (deviceExtModelType != null && deviceExtModelType.length() != 0)  //for extended model type is known, such as single radio, or template/profile is selected, or convert model is specified. 
        // If extension model type is known, just add it.
        {
            toExtModelRangeList.add(deviceExtModelType);
            
        }
        else// for extended mode type is unknown, then just add all model types to the list, it shall be multi-selection case
        {
            for (int i = 0; i < m_radioParameters.getDeviceList().size(); i++)
            {
                Device dev = m_radioParameters.getDeviceList().get(i);
                if ((dev.getExtModelTypes() != null) && (dev.getExtModelTypes().size() == 1)  //known radio scenario, we should check its model type one by one
                        && !toExtModelRangeList.contains(dev.getExtModelTypes().get(0)))
                {
                    toExtModelRangeList.add(dev.getExtModelTypes().get(0));
                }
                else if((dev.getExtModelTypes() != null) && (dev.getExtModelTypes().size() > 1  //new radio scenario
                        && deviceExtModelType != null && deviceExtModelType.length() != 0 ))
                {
                    toExtModelRangeList.clear();
                    toExtModelRangeList.add(deviceExtModelType);
                    break;
                }
            }
        }
            

        //if the "To" model type is only one model, then "From" model will include itself at least.
        if(toExtModelRangeList.size() == 1)
        {
            applicableFromExtTypes.addAll(toExtModelRangeList);
        }
        
        /**
         * we need check which ext model type can apply to all of them
         */
        try
        {
            Map<String, List<String>> crossMap = ClientUtil.getCrossGroupMap();
            Set<String> key = crossMap.keySet();
            
            for (Iterator<String> it = key.iterator(); it.hasNext();) 
            {
                String candidateExtType = it.next();
                List<String> extInMap = getSuppotedCrossCopyModels(crossMap, candidateExtType);
                 
                //the radio type can apply to the device, then add to applicable list
                if(extInMap.containsAll(toExtModelRangeList) && !applicableFromExtTypes.contains(candidateExtType))
                {
                    applicableFromExtTypes.add(candidateExtType);
                }
            }
            
        }
        catch (ServiceException e)
        {
            fgLogger.error("Fail to get the cross group map.");
            fgLogger.error(e);
            e.printStackTrace();
        }
        
        return applicableFromExtTypes;
    }

    
    /*
     * For model type unknown programming(radio is a new radio and it does not apply any template, covert model...), if the profile/template's model type can apply to any model in the iTM model type group, 
     * then it is candidate template/profile.
     * */
    private static List<String> getExtModelRangeWhenSelectTemplate4ExtModelTypeUnknown(RadioParameters m_radioParameters)
    {
        List<String> candidateExtModelRangeList = new ArrayList<String>();
        List<String> extModelRangeList = new ArrayList<String>();
        String swVer = m_radioParameters.getSW();
        if(swVer != null && !swVer.isEmpty())
        {
            SupportSoftware software;
            try
            {
                software = Controller.getInstance().getSoftwareService().getSupportedSoftwareByVersion(m_radioParameters.getSW());
                if(software != null)
                {
                    candidateExtModelRangeList = software.getExtModelTypes();
                }
                
            }
            catch (ServiceException e)
            {
                fgLogger.error("Fail to get supported software with sw version: "  + swVer, e);
            }
        }
        
        List<String> allAvaiExtTypesInGroup =  m_radioParameters.getItmModelType().getExtModelTypes();
        if(candidateExtModelRangeList == null ||candidateExtModelRangeList.size() == 0)
        {
            fgLogger.info("use the whole itm model types for the programming");
            extModelRangeList = allAvaiExtTypesInGroup;
        }
        else
        {
            for(String extModel: candidateExtModelRangeList)
            {
                if(allAvaiExtTypesInGroup.contains(extModel)
                        && !extModelRangeList.contains(extModel))
                {
                    extModelRangeList.add(extModel);
                }
            }
        }
        
        List<String> applicableExtTypes = new ArrayList<String>();
        /**
         * we need collect all ext model types in copy group
         */
        try
        {
            Map<String, List<String>> crossMap = ClientUtil.getCrossGroupMap();
            Set<String> key = crossMap.keySet();
            
            for (Iterator<String> it = key.iterator(); it.hasNext();) 
            {
                String candidateExtType = it.next();
                List<String> extGroup = getSuppotedCrossCopyModels(crossMap, candidateExtType);
                
                for(String extModeInRange: extModelRangeList)
                {
                    if(!extGroup.contains(extModeInRange))
                    {
                        continue;
                    }
                    
                    //the radio type can apply to one of the model type, then it is candidate model type
                    if(!applicableExtTypes.contains(candidateExtType))
                    {
                        applicableExtTypes.add(candidateExtType);
                    }
                    
                    break;
                }
            }

        }
        catch (ServiceException e)
        {
            fgLogger.error("Fail to get the cross group map.");
            fgLogger.error(e);
            e.printStackTrace();
        }
        
        return extModelRangeList;
    }
    
    //it will include from model type it self
    private static List<String> getSuppotedCrossCopyModels(Map<String, List<String>>crossMap, String fromModeTypeExt)
    {
        List<String> modelList = new ArrayList<String>();
        modelList.add(fromModeTypeExt);
        
        List<String> extInMap = crossMap.get(fromModeTypeExt);
        if (extInMap == null || extInMap.isEmpty())
            return modelList;
        
        
        for (String extString : extInMap)
        {
            if (!modelList.contains(extString))
            {
                modelList.add(extString);
            }
        }
        
        return modelList;
    }
    

    public static boolean isSameCopyGrp(String oneExtModelType, String anotherExtModelType)
    {
        List<String> compabilityModelList = null;
        if (null == oneExtModelType)
        {
            return anotherExtModelType == null;
        }

        if (oneExtModelType.equals(anotherExtModelType))
        {
            return true;
        }

        try
        {
            compabilityModelList = ClientUtil.getCrossGroupMap().get(oneExtModelType);
        }
        catch (ServiceException e)
        {
            return false;
        }

        if (null == compabilityModelList || compabilityModelList.size() == 0)
        {
            return false;
        }

        for (int i = 0; i < compabilityModelList.size(); i++)
        {
            if (compabilityModelList.get(i).equals(anotherExtModelType))
            {
                return true;
            }
        }

        return false;
    }

    public static String getISSIForJudgement(RadioParameters m_radioParameters)
    {
        /*
         * Target ISSI is first choice. Then source ISSI. Then regard it as a
         * new radio case return null.
         */
        String iSSI = m_radioParameters.getISSI(); // Now it is Target ISSI
        
        if (iSSI == null || iSSI.equals(""))
        {
            iSSI = m_radioParameters.getDeviceList().get(0).getLogicalId();// source ISSI
        }
        return iSSI;
    }
    
    public static String getNotificationIDForJudgement(RadioParameters m_radioParameters)
    {
        /*
         * Target NotificationID is first choice. Then source NotificationID. Then regard it as a
         * new radio case return null.
         */
        String notificationID = m_radioParameters.getNotificationId(); // Now it is Target notificationID
        
        if (notificationID == null || notificationID.equals(""))
        {
            notificationID = m_radioParameters.getDeviceList().get(0).getNotificationID(); // source notificationID
        }
        return notificationID;
    }

    /**
     * Get the installed languages for the radio
     * 
     * @param device
     * @return
     */
    public static List<ItmLanguage> getLanguages(Device device)
    {
        List<ItmLanguage> itmLanguages = new ArrayList<ItmLanguage>();
        Codeplug codeplug = device.getCurrentCodeplug();
        if (codeplug == null)
            return null;
        List<String> list = codeplug.getFlashPackLanguages();
        if (list != null && !list.isEmpty())
        {
            for (String ll : list)
            {
                ItmLanguage lang = new ItmLanguage();
                lang.setLanguageName(ll);
                itmLanguages.add(lang);
            }
        }
        return itmLanguages;
    }


    
    public static boolean isSupported(String p_name, RadioParameters m_radioParameters)
    {
        return getSupported(p_name, m_radioParameters).getIsSupport();
    }

    /*
     * check if a model type id and personal data combination is supported
     * 
     * @param p_itmModelTypeId String
     * 
     * @param p_name String
     * 
     * @return boolean
     */
    public static ParameterItem getSupported(String p_name, RadioParameters m_radioParameters)
    {
        ParameterItem parameterItem = new ParameterItem();
        ItmModelType itmModelType = m_radioParameters.getDeviceList().get(0).getItmModelType();
        String p_itmModelTypeId = itmModelType.getItmModelTypeId();
        String extModelType = GetExtModelForJudgement(m_radioParameters);
        String fwVersion = GetSwVersionForJudgement(m_radioParameters);

        if (p_name.equals(CommonConstants.PARAMETER_NETWORK_LOGGING))
        {
            PolicyService ps = Controller.getInstance().getPolicyService();
            List<SuppPolTypeForItmModelType> supps =
                    ps.getSuppPolTypeByItmModelTypeId(p_itmModelTypeId);
            for (SuppPolTypeForItmModelType supp : supps)
            {
                if (supp.getPolicyTypeId().equals(
                        Integer.toString(StatusConstants.POLICY_TYPE_MS_MOBILITY_LOGGING)))
                {
                    parameterItem.setIsSupport(true);
                    return parameterItem;
                }
            }

            parameterItem.setIsSupport(false);
            return parameterItem;
        }

        DeviceTypeService deviceTypeService = Controller.getInstance().getDevceTypeService();
        try
        {
            if ((extModelType == null) || extModelType.isEmpty())
            {
                // check by iTM model type
                parameterItem =
                        deviceTypeService
                                .getExtModelSupportParameter(p_name,
                                        itmModelType.getExtModelTypes(), fwVersion);
            }
            else
            {
                // check by ext model type
                List<String> extModelTypeList = new ArrayList<String>();
                extModelTypeList.add(extModelType);
                parameterItem =
                        deviceTypeService.getExtModelSupportParameter(p_name, extModelTypeList,
                                fwVersion);
            }
        }
        catch (ServiceException e)
        {
            fgLogger.error("Get service exception while trying to get personal data setting");
        }

        return parameterItem;
    }
    
    public static List<Boolean> isSupportedList(List<String> nameList,
            RadioParameters m_radioParameters)
    {
        List<Boolean> returnList = new ArrayList<Boolean>();
        List<ParameterItem> parameterItemList = getSupportedList(nameList, m_radioParameters);
        for(ParameterItem parameterItem:parameterItemList)
        {
            returnList.add(parameterItem.getIsSupport());
        }
        return returnList;
    }

    public static List<ParameterItem> getSupportedList(List<String> nameList,
            RadioParameters m_radioParameters)
    {
        ItmModelType itmModelType = m_radioParameters.getDeviceList().get(0).getItmModelType();
        String extModelType = GetExtModelForJudgement(m_radioParameters);
        String fwVersion = GetSwVersionForJudgement(m_radioParameters);

        DeviceTypeService deviceTypeService = Controller.getInstance().getDevceTypeService();
        List<ParameterItem> resultValue = new ArrayList<ParameterItem>();
        try
        {
            if ((extModelType == null) || extModelType.isEmpty())
            {
                // check by iTM model type
                resultValue = deviceTypeService.getExtModelSupportParameterList(nameList,
                        itmModelType.getExtModelTypes(), fwVersion);
            }
            else
            {
                // check by ext model type
                List<String> extModelTypeList = new ArrayList<String>();
                extModelTypeList.add(extModelType);
                resultValue =
                        deviceTypeService.getExtModelSupportParameterList(nameList,
                                extModelTypeList, fwVersion);
            }
        }
        catch (ServiceException e)
        {
            fgLogger.error("Get service exception while trying to get personal data setting");
        }

        return resultValue;
    }

    
    /**
     * Validate if a number is a valid hex number
     * 
     * @param hex
     * @return
     */
    public static boolean validHex(String hex)
    {
        try
        {
            Long.parseLong(hex, 16);
        }
        catch (NumberFormatException ne)
        {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param extModelId
     * @return
     * @throws ServiceException
     */
    public static String getExtModelNameById(String extModelId) throws ServiceException
    {
        if (extNamesMap.get(extModelId) != null)
        {
            return extNamesMap.get(extModelId);
        }

        List<ItmModelType> itmModelTypes = ClientCacheUtil.Instance().getItmModelTypeList(); 

        if (itmModelTypes == null || itmModelTypes.isEmpty())
            return "";
        
        for (ItmModelType itmType : itmModelTypes)
        {
            List<ModelType> list = itmType.getModelTypes();
            if (list == null || list.isEmpty())
                continue;
            for (ModelType mt : list)
            {
                if (mt.getExtModelTypeId().equals(extModelId))
                {
                    extNamesMap.put(extModelId, mt.getModelDescription());
                    return mt.getModelDescription();
                }
            }
        }

        return "";
    }

    /**
     * Get the cross group map
     * 
     * @return Map
     * @throws ServiceException
     */
    public static Map<String, List<String>> getCrossGroupMap() throws ServiceException
    {
        synchronized (fgLogger)
        {
            if (crossModelGroupsMap == null)
            {
                DeviceTypeService deviceTypeService =
                        Controller.getInstance().getDevceTypeService();
                crossModelGroupsMap =
                        deviceTypeService.getCrossModelCopyGroupInformation()
                                .getCrossModelGroupsMap();
            }
        }
        return crossModelGroupsMap;
    }

    /**
     * Get the mobile control head name by id
     * 
     * @return String
     * @throws ServiceException
     */
    public static String getMobileControlHeadName() throws ServiceException
    {
        return ClientCacheUtil.Instance().GetITMModelTypeName(ItmModelType.MTM800E_CONTROL_HEAD_ITM_MODEL_TYPE_ID);
    }

    /**
     * Get the Ethernet control head name by id
     * 
     * @return String
     * @throws ServiceException
     */
    public static String getEthernetControlHeadName() throws ServiceException
    {
        return ClientCacheUtil.Instance().GetITMModelTypeName(ItmModelType.Ethernet_CONTROL_HEAD_ITM_MODEL_TYPE_ID);
    }

    /**
     * Get the name by id
     * 
     * @return String
     * @throws ServiceException
     */
    public static String getMTP850Name() throws ServiceException
    {
        return ClientCacheUtil.Instance().GetITMModelTypeName(ItmModelType.MTP850_ITM_MODEL_TYPE_ID);
    }

    /**
     * Get the name by id
     * 
     * @return String
     * @throws ServiceException
     */
    public static String getAtexName() throws ServiceException
    {
        return ClientCacheUtil.Instance().GetITMModelTypeName(ItmModelType.ATEX_ITM_MODEL_TYPE_ID);
    }

    /**
     * 
     * @param modelId
     * @return
     */
    public static boolean modelSupportPolicy(String modelId)
    {
        if (modelId.equals(ItmModelType.MTM800E_CONTROL_HEAD_ITM_MODEL_TYPE_ID) || modelId.equals(ItmModelType.Ethernet_CONTROL_HEAD_ITM_MODEL_TYPE_ID))
        {
            return false;
        }
        
        return true;
    }

    /**
     * Get the softwares matching to the codeplug structure version
     * 
     * @param codeplugStructureVersion
     * @param extModel
     * @return
     */
    public static String getCompatibleSoftwares(String codeplugStructureVersion, String extModel)
    {
        String mapKey = codeplugStructureVersion + "." + extModel;
        /**
         * if it's already cached in map, use it
         */
        String value = softwareMap.get(mapKey);
        if (value != null && value.length() != 0)
        {
            return value; // found value in map
        }

        StringBuffer softwareVersions = new StringBuffer();

        try
        {
            List<String> corssGroupModels = getCrossGroupMap().get(extModel);
            if (corssGroupModels == null)
            {
                corssGroupModels = new ArrayList<String>();
            }
            corssGroupModels.add(extModel);
            
            List<SupportSoftware> supportSoftwareList = ClientCacheUtil.Instance().getSupportSoftwareList();
            if(supportSoftwareList != null)
            {
                for (SupportSoftware support : supportSoftwareList)
                {
                    String cpdVersion = support.getCodeplugVersion();
                    if (cpdVersion == null)
                        continue;
                    if (cpdVersion.equals(codeplugStructureVersion))
                    {
                        List<String> swExtModels = support.getExtModelTypes();
                        /**
                         * we need to check if the swExtModels contains the entry in
                         * cross copy group
                         */
                        for (String swExtModel : swExtModels)
                        {
                            if (corssGroupModels.contains(swExtModel))
                            {
                                /**
                                 * the SW supports the combi of cpd version and ext
                                 * model type
                                 */
                                if (softwareVersions.length() != 0)
                                {
                                    softwareVersions.append(SOFTWARE_SEPARATOR);
                                }
                                softwareVersions.append(support.getName());
                                break;
                            }
                        }
                    }
                }
                
            }
            
            softwareMap.put(mapKey, softwareVersions.toString());
        }
        catch (ServiceException e)
        {
            /**
             * this should never happen, otherwise nothing works
             */
            fgLogger.error("Got exception during getCompatibleSoftwares()");
            e.printStackTrace();
        }
        return softwareVersions.toString();
    }



    /**
     * This method returns the full path to the configuration folder
     * 
     * @return
     */
    public static String getConfigurationPath()
    {
        if (configurationPath == null)
        {
            File file = new File(Constants.CONFIG_DIR);
            configurationPath = file.getAbsolutePath();

        }
        return configurationPath;
    }

    /**
     * This method returns the full path to the root of the client installation,
     * i.e. ...\itmClient
     * 
     * @return
     */
    public static String getClientRoot()
    {
        if (clientRootPath != null)
            return clientRootPath;

        String sConfigurationPath = getConfigurationPath();
        if (sConfigurationPath == null)
            return null;

        clientRootPath =
                sConfigurationPath.substring(0,
                        sConfigurationPath.indexOf(Constants.CONFIG_DIR) - 1);

        return clientRootPath;
    }

    /**
     * Returns the full path to the logs directory on the cleint, i.e.
     * ...\itmClient\logs
     * 
     * @return
     */
    public static String getLogsFolderPath()
    {
        if (getClientRoot() == null)
            return null;

        return getClientRoot() + Constants.LOG_DIR;
    }


    public static String getFlexeraLicenseServerUrl() throws ServiceException
    {
        Parameter parameter = Controller.getInstance().getParameterService()
                .getParameter(Constants.FLEXERA_LICENSE_SERVER_URL);
        if (parameter != null)
        {
            return parameter.getParaValue();
        }
        else
        {
            return "";
        }
    }
    
    public static boolean isFLServerUrlCanBeModified() throws ServiceException
    {
	Parameter parameter = Controller.getInstance().getParameterService().getParameter(Constants.FLEXERA_LICENSE_SERVER_URL_MODIFY_FLAG);
        if (parameter != null && parameter.getParaValue().equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isFlexeraLicenseOnline() throws ServiceException
    {
        Parameter parameter = Controller.getInstance().getParameterService().getParameter(Constants.FLEXERA_LICENSE_ONLINE);
        if (parameter != null && parameter.getParaValue().equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * create a random password
     * 
     * @param length
     *            the password length
     * @return List<ViewObject>
     */
    public static String createRandomPassword(int length)
    {
        /*
         * Random password is composed by the characters a-z, A-Z, 0-9 to make
         * the passwords easy to record and type into stand-alone CPS if needed.
         * The length shall be 12 characters
         */
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++)
        {
            // random create a char or a number
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if (charOrNum.equalsIgnoreCase("char"))
            {
                // random create a upper case or a down case
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            }
            else if ("num".equalsIgnoreCase(charOrNum))
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    public static List<MandatoryFile> getEnforceProfilesByRadioParameters(Agency deviceAgency,
            String swVersion,
            List<String> modelRangeList) throws ServiceException
    {
        List<MandatoryFile> profiles = new ArrayList<MandatoryFile>();
        if (swVersion == null)
        {
            return profiles;
        }

        if (!deviceAgency.getEnforceProfiles())
        {
            // if enforce template is disabled, no need to show them
            return profiles;
        }

        AgencyService agencyService = Controller.getInstance().getAgencyService();
        SoftwareService softwareService = Controller.getInstance().getSoftwareService();
        String cpStructureVersion = "";
        SupportSoftware software = softwareService.getSupportedSoftwareByVersion(swVersion);
        if (software != null)
        {
            cpStructureVersion = software.getCodeplugVersion();
        }

        EnforcedMandatoryFiles enforcedMandatoryProfiles =
                agencyService.getMandatoryFiles(deviceAgency,
                        cpStructureVersion, modelRangeList);
        if (enforcedMandatoryProfiles != null)
        {
            profiles = enforcedMandatoryProfiles.getMandatoryProfiles();
        }

        return profiles;
    }

    public static boolean splitIpAndPort(StringBuffer sb_sIpAndPort, boolean needAppendDefaultPort)
    {
        String sIpAndPort = sb_sIpAndPort.toString();
        boolean isSplitIpAndPortSuccess = false;
        if (sIpAndPort.equals(""))
        {
            return isSplitIpAndPortSuccess;
        }
        if (sIpAndPort.contains(":"))
        {
            String[] arrayIpAndPort = sIpAndPort.split(":");
            if (arrayIpAndPort.length == 2)
            {
                String sIp = arrayIpAndPort[0];
                String sPort = arrayIpAndPort[1];
                isSplitIpAndPortSuccess = checkIpformat(sIp) && checkPortFormat(sPort);
                if (isSplitIpAndPortSuccess)
                {
                    sIpAndPort = sIp + ":" + sPort;
                }
            }
        }
        else
        {
            isSplitIpAndPortSuccess = checkIpformat(sIpAndPort);
            if (isSplitIpAndPortSuccess)
            {
                if (needAppendDefaultPort)
                {
                    sIpAndPort = sIpAndPort + ":" + CommonConstants.defaultPort;
                }
            }
        }
        sb_sIpAndPort.delete(0, sb_sIpAndPort.length());
        sb_sIpAndPort.append(sIpAndPort);

        return isSplitIpAndPortSuccess;
    }

    public static boolean checkIpformat(String p_ip)
    {
        InetAddress address = null;
        try
        {
            address = InetAddress.getByName(p_ip);
        }
        catch (Exception e)
        {
            fgLogger.error("Unable to resolve the IP address of the server: " + p_ip);
        }

        if (address == null)
        {
            return false;
        }

        return true;
    }

    public static boolean checkPortFormat(String port)
    {
        boolean flag = false;
        try
        {
            int iPort = Integer.parseInt(port);
            if (1 <= iPort && iPort <= 65535)
            {
                flag = true;
            }
        }
        catch (Exception e)
        {
            flag = false;
        }

        return flag;
    }

    /**
     * 
     * @param String
     *            sIpAndPort
     * @return IPAndPort[0]=IP IPAndPort[1]=port if detach fail,size of string
     *         array is 0,
     * 
     */
    public static String[] detachIPandPort(String sIpAndPort)
    {
        String[] IPAndPort = null;
        if (sIpAndPort == null || sIpAndPort.equals(""))
        {
            return IPAndPort;
        }
        if (sIpAndPort.contains(":"))
        {
            String[] arrayIpAndPort = sIpAndPort.split(":");
            if (arrayIpAndPort.length == 2)
            {
                String sIp = arrayIpAndPort[0];
                String sPort = arrayIpAndPort[1];
                boolean isSplitIpAndPortSuccess = checkIpformat(sIp) && checkPortFormat(sPort);
                if (isSplitIpAndPortSuccess)
                {
                    IPAndPort = new String[2];
                    IPAndPort[0] = sIp;
                    IPAndPort[1] = sPort;
                    return IPAndPort;
                }
            }
        }
        else
        {
            boolean isSplitIpAndPortSuccess = checkIpformat(sIpAndPort);
            if (isSplitIpAndPortSuccess)
            {
                IPAndPort = new String[2];
                IPAndPort[0] = sIpAndPort;
                IPAndPort[1] = CommonConstants.defaultPort;
                return IPAndPort;
            }
        }

        return IPAndPort;
    }

    
    /**
     * Get the list of unique_model_numbers by FilterType
     * 
     * @param List
     *            <String> extModelTypes,String swVersionToUpgrade
     * 
     * @return UniqueModelNumbers as M83PF-----ANuni,M83PFT6TZ6AGuni
     */
    public static List<String> getCompatibleModelNumbers(List<String> extModelTypes, List<String> hwIds, String swVersion, ItmModelType itmModelType)
    {
        //get model scope: especially, for unknown radio.
        List<String> extModelScope = getExtModelsScope(extModelTypes, swVersion, itmModelType);
    
        //get the compatible models based on model type and HW id
        List<ModelNumber> compatibleModelNumbers = getCompatibleModelNumers(extModelScope, hwIds);
         
        //get model name from model number
        List<String> compatibleModelNameList = extractNamesAttributeFromModelNumbers(compatibleModelNumbers);
   
        //if it is get by hwId
        return filterBySwAndItmModelType(compatibleModelNameList, swVersion, itmModelType);
    }
    
    
    private static List<String> getExtModelsScope(List<String> extModelTypeList, String swVersion, ItmModelType itmModelType )
    {
        DeviceTypeService deviceTypeSevice = Controller.getInstance().getDevceTypeService();
        List<String> extModelScope = new ArrayList<String>();
        
        boolean isNewRadio = extModelTypeList == null || extModelTypeList.size() == 0;
        if(isNewRadio)
        {
            boolean isSwknown = swVersion != null && swVersion.length() > 0;
            
            if(isSwknown)
            {
                try
                {
                    extModelScope = deviceTypeSevice.getExtModelTypesByFirmWareVersion(swVersion);
                }
                catch (ServiceException e)
                {
                    fgLogger.error("getExtModelTypesByFirmWareVersion failed with exception ", e);
                } 
            }
            //if sw is not known, then get model type scope by iTM model type
            else
            {
                extModelScope = itmModelType.getExtModelTypes();
            }
        }
        else
        {
            extModelScope = extModelTypeList;
        }
        
        return extModelScope;
    }
    
    
    private static List<ModelNumber> getCompatibleModelNumers(List<String> extModelScope, List<String> hwIds)
    {
        DeviceTypeService deviceTypeSevice = Controller.getInstance().getDevceTypeService();
        List<ModelNumber> compatibleModelNumbers = new ArrayList<ModelNumber>();
        
        try
        {
            //if hw id is not known, then use extended model number to query compatible models.
            //however, it is not precise.
            if(hwIds == null || hwIds.size() == 0)
            {
                compatibleModelNumbers = deviceTypeSevice.getSupportConvertRadioModel(extModelScope);
            }
            //if hw id is known, then use hw id to query, it is precise.
            else
            {
                compatibleModelNumbers = deviceTypeSevice.getSupportConvertRadioModelByHwId(hwIds);
                
                //remove it self
                if(extModelScope != null && extModelScope.size() == 1)
                {
                    List<ModelNumber> modelNumberSelfList = new ArrayList<ModelNumber>();
                    try
                    {
                        //actually, extModelScope will only contains one model
                        modelNumberSelfList = Controller.getInstance().getDevceTypeService().getCorrespondingModelNumber(extModelScope);
                    }
                    catch (ServiceException e)
                    {
                        fgLogger.error(e.getMessage());
                    }
                    
                    for (ModelNumber modelNumber : modelNumberSelfList)
                    {
                        for (ModelNumber compModelNumber : compatibleModelNumbers)
                        {
                            if(modelNumber.getName().equals(compModelNumber.getName()))
                            {
                                fgLogger.info("One compatible mode (" + compModelNumber.getName() + ") is filted as it is itself");
                                compatibleModelNumbers.remove(compModelNumber);
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        catch (ServiceException e)
        {
            fgLogger.error(e.getMessage());
        }
        
        return compatibleModelNumbers;
    }
    
    private static List<String> filterBySwAndItmModelType(List<String> compatibleModelNameList, String swVersion, ItmModelType itmModelType)
    {
       //filter from sw version
        List<String> compatiableModelsBySw = new ArrayList<String>();
        if(swVersion != null && swVersion.length() > 0)
        {
            compatiableModelsBySw = getCompatabileModelNumbersBySwVersionToUpgrade(swVersion);
        }
        
        /* filter from iTM model type */
        List<String> modelTypeNames = itmModelType.getExtModelTypes();
        List<ModelNumber> ModelNumberList = new ArrayList<ModelNumber>();
        try
        {
            ModelNumberList = Controller.getInstance().getDevceTypeService().getCorrespondingModelNumber(modelTypeNames);
        }
        catch (ServiceException e)
        {
            fgLogger.error(e.getMessage());
        }
        
        List<String> uniqueNameList = new ArrayList<String>();
        uniqueNameList = extractNamesAttributeFromModelNumbers(ModelNumberList);
        List<String> cloneList = new ArrayList<String>();
        cloneList.addAll(compatibleModelNameList);
        
        for (String modelNumberName : cloneList)
        {
            if (!uniqueNameList.contains(modelNumberName))
            {
                fgLogger.info("One compatible mode (" + modelNumberName + ")is filted as it is cross iTM model type: " + itmModelType.getItmModelTypeName());
                compatibleModelNameList.remove(modelNumberName);
                continue;
            }
            
            
            if(swVersion != null && swVersion.length() > 0 && !compatiableModelsBySw.contains(modelNumberName))
            {
                fgLogger.info("One compatible mode (" + modelNumberName + ")is filted as it is not supported by SW: " + swVersion);
                compatibleModelNameList.remove(modelNumberName);
                continue;
            }
        }
        
        return compatibleModelNameList;
    }
       


    /**
     * Get the list of unique_model_numbers by list of extend mode types
     * 
     * @param swVersionToUpgrade
     *            as R270008276
     * @return List of unique_model_numbers as M83PF-----ANuni,M83PFT6TZ6AGuni
     */
    private static List<String> getCompatabileModelNumbersBySwVersionToUpgrade(String swVersionToUpgrade)
    {
        DeviceTypeService deviceTypeSevice = Controller.getInstance().getDevceTypeService();
        List<String> uniqueNameList = new ArrayList<String>();
        try
        {
            // nSwVersion is firmware version in firmwareversion.xml
            List<String> extModelTypes =
                    deviceTypeSevice.getExtModelTypesByFirmWareVersion(swVersionToUpgrade);
            List<ModelNumber> uniqueModelNumbers =
                    deviceTypeSevice.getCorrespondingModelNumber(extModelTypes);
            uniqueNameList = extractNamesAttributeFromModelNumbers(uniqueModelNumbers);
        }

        catch (ServiceException e)
        {
            fgLogger.error("getExtModelTypesBySwBuildID:SwVersion is " + swVersionToUpgrade);
        }
        return uniqueNameList;
    }
    public static List<String> getIntersection(List<String> ListOne, List<String> ListTwo)
    {
        List<String> intersection = new ArrayList<String>();
        for (String oneString : ListOne)
        {
            if (ListTwo.contains(oneString))
            {
                intersection.add(oneString);
            }
        }
        return intersection;
    }
    private static List<String> extractNamesAttributeFromModelNumbers(List<ModelNumber> modelNumbers)
    {
        List<String> uniqueNameList = new ArrayList<String>();
        for (ModelNumber modelNumber : modelNumbers)
        {
            if (!uniqueNameList.contains(modelNumber.getName()))
            {
                uniqueNameList.add(modelNumber.getName());
            }
        }
        return uniqueNameList;
    }

    public static int transformYesNoToOneZero(String yesOrNo)
    {
        if (yesOrNo.equalsIgnoreCase("yes"))
        {
            return 1;
        }
        if (yesOrNo.equalsIgnoreCase("no"))
        {
            return 0;
        }
        return -1;
    }

    public final static class FileOperation
    {
        public static boolean copy(String oldPath, String newPath)
        {
            try
            {
                File oldFile = new File(oldPath);
                if (oldFile.exists())
                {
                    writeFile(readFile(oldFile), newPath);
                }
            }
            catch (Exception e)
            {
                return false;
            }
            return true;
        }


        public static String readFile(File file)
        {
            BufferedReader bufferedReader = null;
            String originalContent = "";
            try
            {
                bufferedReader = new BufferedReader(new FileReader(file));
                String sLine = "";
                while (sLine != null)
                {
                    originalContent = originalContent + sLine;
                    sLine = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
            catch (Exception e)
            {
                fgLogger.error(e.getMessage());
            }
            return originalContent;
        }

        public static File writeFile(String content, String targetFilePath)
        {
            File targetFile = new File(targetFilePath);
            targetFile.setWritable(true);
            BufferedWriter bufferedWriter;
            try
            {
                bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
                bufferedWriter.write(content);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            catch (IOException e)
            {
                fgLogger.error(e.getMessage());
            }
            return targetFile;
        }

    }

    final public static void addCodeplug(String serverUrl, Codeplug codeplug, String codeplugPath)
            throws ServiceException
    {
        synchronized (obj)
        {
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.addCodeplug(serverUrl, codeplug, codeplugPath);
        }
    }

    /**
     * Get the itm language bundle(list) from language name list
     * */
    public static List<ItmLanguage> getItemLanguageListFromLanguageNameList(String swVersion,
            List<String> languageNameList)
    {
        SoftwareService softwareService = Controller.getInstance().getSoftwareService();
        SupportSoftware software;
        List<ItmLanguage> itmLanguageList = new ArrayList<ItmLanguage>();

        try
        {
            software = softwareService.getSupportedSoftwareByVersion(swVersion);
        }
        catch (ServiceException e)
        {
            fgLogger.error(e.getMessage());
            return null;
        }

        List<ItmLanguage> languageInfos = new ArrayList<ItmLanguage>();
        if (software == null || languageNameList == null)
        {
            return null;
        }

        languageInfos = software.getLanguages();
        for (int i = 0; i < languageNameList.size(); i++)
        {
            ItmLanguage language =
                    LanguageUtil.getItmLanguageFromListByName(languageInfos,
                            languageNameList.get(i));
            if (null != language)
            {
                itmLanguageList.add(language);
            }
        }

        return itmLanguageList;
    }

    public static void CheckHelpFiles()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                FileUtils.unzipHelpFile();
            }
            
        }); 
        thread.start();
    }

    public static boolean getSystemProxyInfo()
    {
        System.setProperty("java.net.useSystemProxies", "true");
        try
        {
            List<?> l = ProxySelector.getDefault().select(new URI("http://www.yahoo.com/"));

            for (Iterator<?> iter = l.iterator(); iter.hasNext();)
            {
                Proxy proxy = (Proxy) iter.next();
                fgLogger.info("proxy hostname : " + proxy.type());

                InetSocketAddress addr = (InetSocketAddress) proxy.address();

                if (addr == null)
                {
                    fgLogger.info("No Proxy");
                }
                else
                {
                    System.setProperty(CommonConstants.PROXYURL, addr.getHostName());
                    System.setProperty(CommonConstants.PROXYPORT, Integer.toString(addr.getPort()));
                }
            }
        }
        catch (Exception e)
        {
            fgLogger.error(e.getMessage());
        }

        return false;
    }
} // public final class ClientUtil
