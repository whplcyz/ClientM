/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2011-2015 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  <Name>
CODED     BY:  <Name>    <Date>

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
27/Feb/08  Michael Leismann                    Initial version
19/Mar/08  Kim Mortensen         CCMPD01002089 Minor changes from FTR.
27-Mar-08  Michael Leismann      CCMPD01002089 Updated FTR findings
31-Mar-08  Michael Leismann                    Moved this class to new package
03-Apr-08  Michael Leismann                    Added TEI, Radio Name and Radio
                                               Type get and set methods
14-Apr-08  Michael Leismann                    Added  device type list and get
                                               and set methods
09/Jan/09  Michael Leismann      CCMPD01146532 Added software and softwarelist
                                               for CM5000 ControlHead
                                               Updated hasParameterBeenModified
                                               method to check all software 
20/Jan/09  Michael Leismann      CCMPD01146532 Removed device type list and
                                               radio type
31/Mar/09  Michael Leismann      CCMPD01189094 Added variables for the Personal
                                               Data/Special Parameters feature
06/May/09  Michael Leismann      CCMPD01189094 Updated after FTR
27/May/09  Michael Leismann      CCMPD01205263 A constant has been renamed
13/Jul/09  Sigurdur Jonsson      CCMPD01235271 Added notes to radio
16/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure
28/Sep/09  Michael Leismann      CCMPD01269375 WAP
15/Oct/09  Jens Hansen           CCMPD01275889 Added Set to contain a distinct
                                               list of extended model types in
                                               the selected list of radios
11/Nov/09  Hai Dong              CCMPD01287416 Add job schedule  
21/Nov/09  chad001               CCMPD01287398  Add notification                                                
04/Jan/10  Hai Dong              CCMPD01290881 Add agency partitioning
09/03/10   ckfm01                CCMPD01319807 Adding notification ID. 
29/04/10   chad001               ccmpd01341985 correct new radio behavior 
04/may/10  Hai Dong              CCMPD01343234 Default settings is using template
16/06/10   Kim Mortensen         CCMPD01357654 Adding Language pack code.  
21/Sep/10  chad001               ccmpd01394081 Add policies manager   
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting
16/Jun/11  cvkh36                CCMPD01521711 iTM 5.0: Enhanced API   
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature   
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model      
21/Jan/13  qngv36                REM_CP_Authentication_Beta_Dev Support CP authentication in iTM     
2/Sep/13   wch378                CCMPD01809803 fix known issue that refers to enforced template 
28/3/14    wch378                CCMPD01889533 develop flexera license.                                      
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mot.dm.common.model.Codeplug;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.FlexeraFeature;
import com.mot.dm.common.model.ItmLanguage;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.MandatoryFile;
import com.mot.dm.common.model.Software;
import com.mot.dm.common.model.licenseModel.OnOffTypeLicense;
import com.mot.dm.common.util.CommonConstants;

public class RadioParameters
{
    /*
     * Job schedule data
     */
    private Date startTime;
    private Date endTime;
    private int recurrences = 0;

    private String m_TEI = null;
    private String m_radioName = null;
    private String m_notification_id = null;
    private String m_ISSI = null;
    private String m_targetModelName = null;
    private String m_password = null;
    private String m_password_Usage = null;
    private boolean m_multipleSelection = false;
    private boolean m_bProtectRadio = false;
    private boolean m_bProtectCodeplug = false;
    private boolean felxeraOnlineMode = false;
    private List<Software> m_upgradeSoftwareList = null;
    private List<Software> m_upgradeSoftwareListControlHead = null;
    private String m_software = null;
    private String m_softwareControlHead = null;
    private Codeplug m_template = null;
    private List<Codeplug> m_profileList = new ArrayList<Codeplug>();
    private List<FlexeraFeature> m_features = null;
    private Map<String, List<String>> enabledSellableFeatureOnDeviceMap = null;
    private Map<String, List<String>> licensedSellableFeatureOnDeviceMap = null;
    private Map<String, String> avaiableSellableFeatureLicenseList = null;
    private Map<String, OnOffTypeLicense> licensedSiteWideSellableFeature = null;
    private Map<String, Set<String>> sellableFeatureMap = null;
    private Map<String, String> deviceAttributeMap = null;
    private List<Device> m_deviceList = null;
    private List<ItmLanguage> languages;

    private ItmModelType m_itmModelType = null;
    private String m_extModelType = null;
    private List<String> m_radioHwIDList = null;

    // Personal Data variables
    private String m_contactBook = null;
    private String m_menuShortcuts = null;
    private String m_screenSaver = null;
    private String m_audioSettings = null;
    private String m_gps = null;
    private String m_talkGroups = null;

    // Special Parameters variables
    private int m_pinEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_pukEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_commentFieldEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_phoneNumberEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_privateNumberEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_homeModeDisplayTextEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_screenSaverTextEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_wapEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_limitedTMOTGEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_limitedDMOTGEnum = CommonConstants.NO_ENUM_VALUE;
    private int m_networkLoggingEnum = CommonConstants.NO_ENUM_VALUE;
    private String m_pin = null;
    private String m_puk = null;
    private String m_commentField = null;
    private String m_phoneNumber = null;
    private String m_privateNumber = null;
    private String m_homeModeDisplayText = null;
    private String m_screenSaverText = null;
    private String m_notes = null;
    private String m_wap = null;
    private String m_limitedTMOTG = null;
    private String m_limitedDMOTG = null;
    private String m_networkLogging = null;
    private String notificationMessage = null;

    private MandatoryFile networkTemplate;
    private MandatoryFile fleetmapTemplate;
    private List<MandatoryFile> mandatoryProfiles = new ArrayList<MandatoryFile>();
    private boolean isCloneJob = false;
    private boolean isAutoGeneratePw = false;

    // We need to know if all ISSIs are known,
    // if multiple devices have been selected
    private boolean m_areISSIsKnown = false;

    // The path to user data (CPS XML user data path)
    private String m_userDataPath = null;

    // The path to hot fix file
    private String m_hotFixPath = null;

    private boolean hasProgramFirmwarePermission;
    private boolean hasProgramCodeplugPermission;
    private boolean hasLimitedNubmerOfTGPermission;

    public void setAutoGeneratePwFlag(boolean AutoGeneratePwFlag)
    {
        isAutoGeneratePw = AutoGeneratePwFlag;
    }

    public boolean getAutoGeneratePwFlag()
    {
        return isAutoGeneratePw;
    }

    public String getTargetModelName()
    {
        return m_targetModelName;
    }

    public void setTargetModelName(String p_targetModelName)
    {
        m_targetModelName = p_targetModelName;
    }

    /**
     * getter
     * 
     * @return
     */
    public List<ItmLanguage> getLanguages()
    {
        return languages;
    }

    /**
     * setter
     * 
     * @param languages
     */
    public void setLanguages(List<ItmLanguage> languages)
    {
        this.languages = languages;
    }

    /**
     * Constructor
     */
    public RadioParameters()
    {
    }

    /**
     * Set TEI
     */
    public void setTEI(String p_TEI)
    {
        m_TEI = p_TEI;
    }

    /**
     * Get TEI
     * 
     * @return the TEI
     */
    public String getTEI()
    {
        return m_TEI;
    }

    /**
     * Set Radio Name
     */
    public void setRadioName(String p_radioName)
    {
        m_radioName = p_radioName;
    }

    /**
     * Get Radio Name
     * 
     * @return the radio name
     */
    public String getRadioName()
    {
        return m_radioName;
    }

    /**
     * Set the itm model type
     */
    public void setItmModelType(ItmModelType p_itmModelType)
    {
        m_itmModelType = p_itmModelType;
    }

    /**
     * Get the itm model type
     * 
     * @return the itm model type
     */
    public ItmModelType getItmModelType()
    {
        return m_itmModelType;
    }

    /**
     * Set the ISSI, null if unchanged.
     */
    public void setISSI(String p_ISSI)
    {
        m_ISSI = p_ISSI;
    }

    /**
     * Get the ISSI, null if unchanged.
     * 
     * @return the ISSI
     */
    public String getISSI()
    {
        return m_ISSI;
    }

    /**
     * set current pwd will use to protect codeplug or not
     */
    public void setProtectCodeplug(boolean bProtectCP)
    {
        m_bProtectCodeplug = bProtectCP;
    }

    /**
     * get current pwd will  protect codeplug or not
     */
    public boolean getProtectCodeplug()
    {
        return m_bProtectCodeplug;
    }

    /**
     * set current pwd will use to protect codeplug or not
     */
    public void setProtectRadio(boolean bProtectRadio)
    {
        m_bProtectRadio = bProtectRadio;
    }

    /**
     * get current pwd will  protect codeplug or not
     */
    public boolean getProtectRadio()
    {
        return m_bProtectRadio;
    }

    /**
     * Set the changed password, null if unchanged.
     */
    public void setPassword(String password)
    {
        m_password = password;
    }

    /**
     * Get the changed codeplug password, null if unchanged.
     * 
     * @return the codeplug password
     */
    public String getPassword()
    {
        return m_password;
    }

    /**
     * Set the changed password, null if unchanged.
     */
    public void setPasswordUsage(String passwordUsage)
    {
        m_password_Usage = passwordUsage;
    }

    /**
     * Get the changed codeplug password, null if unchanged.
     * 
     * @return the codeplug password
     */
    public String getPasswordUsage()
    {
        return m_password_Usage;
    }

    /**
     * Set weather this is a multiple selection or not.
     * 
     * @param p_multipleSelection
     */
    public void setMultipleSelection(boolean p_multipleSelection)
    {
        m_multipleSelection = p_multipleSelection;
    }

    /**
     * check if this is a multiple selection or not
     * 
     * @return boolean
     */
    public boolean getMultipleSelection()
    {
        return m_multipleSelection;
    }

    /**
     * Sets the list of the SW that can be selected For the CM5000 this is the
     * Transceiver software
     * 
     * @param p_upgradeSoftwareList
     */
    public void setUpgradeSoftList(List<Software> p_upgradeSoftwareList)
    {
        m_upgradeSoftwareList = p_upgradeSoftwareList;
    }

    /**
     * Returns the list of SW that can be selected For the CM5000 this is the
     * Transceiver software
     * 
     * @return
     */
    public List<Software> getUpgradeSoftList()
    {
        return m_upgradeSoftwareList;
    }

    /**
     * Sets the list of the SW that can be selected for a CM5000 ControlHead
     * 
     * @param p_upgradeSoftwareList
     */
    public void setUpgradeSoftListControlHead(List<Software> p_upgradeSoftwareListControlHead)
    {
        m_upgradeSoftwareListControlHead = p_upgradeSoftwareListControlHead;
    }

    /**
     * Returns the list of SW that can be selected for a CM5000 ControlHead
     * 
     * @return
     */
    public List<Software> getUpgradeSoftListControlHead()
    {
        return m_upgradeSoftwareListControlHead;
    }

    /**
     * Set the selected SW For the CM5000 this is the Transceiver software
     * 
     * @param p_swVersion
     */
    public void setSW(String p_software)
    {
        m_software = p_software;
    }

    /**
     * Get the selected SW For the CM5000 this is the Transceiver software
     * 
     * @return the select SW
     */
    public String getSW()
    {
        return m_software;
    }

    /**
     * Set the selected SW for the CM5000 ControlHead
     * 
     * @param p_swVersion
     */
    public void setSWControlHead(String p_softwareControlHead)
    {
        m_softwareControlHead = p_softwareControlHead;
    }

    /**
     * Get the selected SW for the CM5000 ControlHead
     * 
     * @return the select SW
     */
    public String getSWControlHead()
    {
        return m_softwareControlHead;
    }

    /**
     * Set the selected template
     * 
     * @param p_template
     */
    public void setTemplate(Codeplug p_codeplug)
    {
        m_template = p_codeplug;
    }

    /**
     * Get the selected template
     * 
     * @return the select template
     */
    public Codeplug getTemplate()
    {
        return m_template;
    }

    /**
     * Set the selected profile list
     * 
     * @param p_template
     */
    public void setProfileList(List<Codeplug> p_profileList)
    {
        if (null == p_profileList)
        {
            m_profileList = new ArrayList<Codeplug>();
            return;
        }

        m_profileList = p_profileList;
    }

    /**
     * Get the selected profile
     * 
     * @return the select template
     */
    public List<Codeplug> getProfileList()
    {
        return m_profileList;
    }

    public List<FlexeraFeature> getFeatures()
    {
        return m_features;
    }

    public void setFeatures(List<FlexeraFeature> p_features)
    {
        m_features = p_features;
    }

    /**
     * Returns the list of devices
     * 
     * @return list of devices
     */
    public List<Device> getDeviceList()
    {
        return m_deviceList;
    }

    public void setDeviceList(List<Device> p_deviceList)
    {
        m_deviceList = p_deviceList;
    }
    
    public List<String> getRadioHwIDList()
    {
        return m_radioHwIDList;
    }

    public void setRadioHwIDList(List<String> radioHwIDs)
    {
        this.m_radioHwIDList = radioHwIDs;
    }

    /*
     * check if parameters changed without applying template.
     * 
     * @return boolean
     */
    public boolean hasSpecifiedParameters()
    {
        if (m_pinEnum == CommonConstants.SPECIFY_VALUE
                || m_pukEnum == CommonConstants.SPECIFY_VALUE
                || m_commentFieldEnum == CommonConstants.SPECIFY_VALUE
                || m_phoneNumberEnum == CommonConstants.SPECIFY_VALUE
                || m_privateNumberEnum == CommonConstants.SPECIFY_VALUE
                || m_homeModeDisplayTextEnum == CommonConstants.SPECIFY_VALUE
                || m_screenSaverTextEnum == CommonConstants.SPECIFY_VALUE
                || m_wapEnum == CommonConstants.SPECIFY_VALUE
                || m_limitedTMOTGEnum == CommonConstants.SPECIFY_VALUE
                || m_limitedDMOTGEnum == CommonConstants.SPECIFY_VALUE
                || m_networkLoggingEnum != CommonConstants.KEEP_VALUE
                && m_networkLoggingEnum != CommonConstants.NO_ENUM_VALUE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
     * Check if general settings like ISSI or template changed.
     * 
     * @return boolean
     */
    public boolean hasGeneralParametersChanged()
    {
        if (m_ISSI != null || m_password != null || m_software != null
                || m_template != null || m_features != null || m_softwareControlHead != null)
            return true;
        return false;
    }

    /**
     * Checks if at least one of the parameters has been modified
     * 
     * @return true if one of the parameters isn't null, otherwise false
     */
    public boolean hasParameterBeenModified()
    {
        // Check if one of the parameters isn't null
        // Note: There is no need to check the Personal Data variables
        // as these are only set if the template is also set
        // For the Special Parameters variables, the value should
        // also be different from the no/keep value
        if (m_ISSI != null
                || m_password != null
                || m_software != null
                || m_template != null
                || m_features != null
                || (m_profileList != null && m_profileList.size() > 0)
                || m_softwareControlHead != null
                || (m_pinEnum != CommonConstants.KEEP_VALUE && m_pinEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_pukEnum != CommonConstants.KEEP_VALUE && m_pukEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_commentFieldEnum != CommonConstants.KEEP_VALUE && m_commentFieldEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_phoneNumberEnum != CommonConstants.KEEP_VALUE && m_phoneNumberEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_privateNumberEnum != CommonConstants.KEEP_VALUE && m_privateNumberEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_homeModeDisplayTextEnum != CommonConstants.KEEP_VALUE && m_homeModeDisplayTextEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_screenSaverTextEnum != CommonConstants.KEEP_VALUE && m_screenSaverTextEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_wapEnum != CommonConstants.KEEP_VALUE && m_wapEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_networkLoggingEnum != CommonConstants.KEEP_VALUE && m_networkLoggingEnum != CommonConstants.NO_ENUM_VALUE)
                || (languages != null)
                || (m_userDataPath != null && m_userDataPath.length() != 0)
                || (m_hotFixPath != null && m_hotFixPath.length() != 0)
                || (m_targetModelName != null && m_targetModelName.length() != 0)
                || m_limitedTMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedTMOTGEnum != CommonConstants.NO_ENUM_VALUE
                || m_limitedDMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedDMOTGEnum != CommonConstants.NO_ENUM_VALUE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean hasFirmwareRelatedAttribute()
    {
        if (m_software != null || m_softwareControlHead != null)
        {
            return true;
        }
        return false;
    }

    public boolean hasCodeplugRelatedAttribute()
    {
        if (m_ISSI != null
                || m_password != null
                || m_template != null
                || m_features != null
                || (m_profileList != null && m_profileList.size() > 0)
                || (m_pinEnum != CommonConstants.KEEP_VALUE && m_pinEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_pukEnum != CommonConstants.KEEP_VALUE && m_pukEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_commentFieldEnum != CommonConstants.KEEP_VALUE && m_commentFieldEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_phoneNumberEnum != CommonConstants.KEEP_VALUE && m_phoneNumberEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_privateNumberEnum != CommonConstants.KEEP_VALUE && m_privateNumberEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_homeModeDisplayTextEnum != CommonConstants.KEEP_VALUE && m_homeModeDisplayTextEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_screenSaverTextEnum != CommonConstants.KEEP_VALUE && m_screenSaverTextEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_wapEnum != CommonConstants.KEEP_VALUE && m_wapEnum != CommonConstants.NO_ENUM_VALUE)
                || (m_networkLoggingEnum != CommonConstants.KEEP_VALUE && m_networkLoggingEnum != CommonConstants.NO_ENUM_VALUE)
                || (languages != null)
                || (m_userDataPath != null && m_userDataPath.length() != 0)
                || (m_hotFixPath != null && m_hotFixPath.length() != 0)
                || (m_targetModelName != null && m_targetModelName.length() != 0)
                || m_limitedTMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedTMOTGEnum != CommonConstants.NO_ENUM_VALUE
                || m_limitedDMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedDMOTGEnum != CommonConstants.NO_ENUM_VALUE)
        {
            return true;
        }
        return false;
    }
    
    public boolean hasLimitedTGRelatedAttribute()
    {
    	if ( m_limitedTMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedTMOTGEnum != CommonConstants.NO_ENUM_VALUE
                || m_limitedDMOTGEnum != CommonConstants.KEEP_VALUE && m_limitedDMOTGEnum != CommonConstants.NO_ENUM_VALUE)
        {
            return true;
        }
        return false;
    }


    /**
     * Set the Contact Book
     * 
     * @param the
     *            Contact Book
     */
    public void setContactBook(String p_contactBook)
    {
        m_contactBook = p_contactBook;
    }

    /**
     * Get the ContactBook
     * 
     * @return the ContactBook
     */
    public String getContactBook()
    {
        return m_contactBook;
    }

    /**
     * Set the Menu Shortcuts
     * 
     * @param the
     *            Menu Shortcuts
     */
    public void setMenuShortcuts(String p_menuShortcuts)
    {
        m_menuShortcuts = p_menuShortcuts;
    }

    /**
     * Get the Menu Shortcuts
     * 
     * @return the Menu Shortcuts
     */
    public String getMenuShortcuts()
    {
        return m_menuShortcuts;
    }

    /**
     * Set the Screen Saver
     * 
     * @param the
     *            Screen Saver
     */
    public void setScreenSaver(String p_screenSaver)
    {
        m_screenSaver = p_screenSaver;
    }

    /**
     * Get the Screen Saver
     * 
     * @return the Screen Saver
     */
    public String getScreenSaver()
    {
        return m_screenSaver;
    }

    /**
     * Set the Audio Settings
     * 
     * @param the
     *            Audio Settings
     */
    public void setAudioSettings(String p_audioSettings)
    {
        m_audioSettings = p_audioSettings;
    }

    /**
     * Get the Audio Settings
     * 
     * @return the Audio Settings
     */
    public String getAudioSettings()
    {
        return m_audioSettings;
    }

    /**
     * Set the GPS
     * 
     * @param the
     *            GPS
     */
    public void setGPS(String p_gps)
    {
        m_gps = p_gps;
    }

    /**
     * Get the GPS
     * 
     * @return the GPS
     */
    public String getGPS()
    {
        return m_gps;
    }

    /**
     * Set the Talk Groups
     * 
     * @param the
     *            Talk Groups
     */
    public void setTalkGroups(String p_talkGroups)
    {
        m_talkGroups = p_talkGroups;
    }

    /**
     * Get the Talk Groups
     * 
     * @return the Talk Groups
     */
    public String getTalkGroups()
    {
        return m_talkGroups;
    }

    /**
     * Set the PIN enum
     * 
     * @param the
     *            new PIN enum
     */
    public void setPinEnum(int p_pinEnum)
    {
        m_pinEnum = p_pinEnum;
    }

    /**
     * Get the PIN enum
     * 
     * @return the PIN enum
     */
    public int getPinEnum()
    {
        return m_pinEnum;
    }

    /**
     * Set the PIN number
     * 
     * @param the
     *            new PIN number
     */
    public void setPin(String p_pin)
    {
        m_pin = p_pin;
    }

    /**
     * Get the PIN number
     * 
     * @return the PIN number
     */
    public String getPin()
    {
        return m_pin;
    }

    /**
     * Set the PUK enum
     * 
     * @param the
     *            new PUK enum
     */
    public void setPukEnum(int p_pukEnum)
    {
        m_pukEnum = p_pukEnum;
    }

    /**
     * Get the PUK enum
     * 
     * @return the PUK enum
     */
    public int getPukEnum()
    {
        return m_pukEnum;
    }

    /**
     * Set the PUK number
     * 
     * @param the
     *            new PUK number
     */
    public void setPuk(String p_puk)
    {
        m_puk = p_puk;
    }

    /**
     * Get the PUK number
     * 
     * @return the PUK number
     */
    public String getPuk()
    {
        return m_puk;
    }

    /**
     * Set the Comment Field enum
     * 
     * @param the
     *            Comment Field enum
     */
    public void setCommentFieldEnum(int p_commentFieldEnum)
    {
        m_commentFieldEnum = p_commentFieldEnum;
    }

    /**
     * Get the Comment Field enum
     * 
     * @return the Comment Field enum
     */
    public int getCommentFieldEnum()
    {
        return m_commentFieldEnum;
    }

    /**
     * Set the Comment Field
     * 
     * @param the
     *            Comment Field
     */
    public void setCommentField(String p_commentField)
    {
        m_commentField = p_commentField;
    }

    /**
     * Get the Comment Field
     * 
     * @return the Comment Field
     */
    public String getCommentField()
    {
        return m_commentField;
    }

    /**
     * Set the Phone Number enum
     * 
     * @param the
     *            Phone Number enum
     */
    public void setPhoneNumberEnum(int p_phoneNumberEnum)
    {
        m_phoneNumberEnum = p_phoneNumberEnum;
    }

    /**
     * Get the Phone Number enum
     * 
     * @return the Phone Number enum
     */
    public int getPhoneNumberEnum()
    {
        return m_phoneNumberEnum;
    }

    /**
     * Set the Phone Number
     * 
     * @param the
     *            Phone Number
     */
    public void setPhoneNumber(String p_phoneNumber)
    {
        m_phoneNumber = p_phoneNumber;
    }

    /**
     * Get the Phone Number
     * 
     * @return the Phone Number
     */
    public String getPhoneNumber()
    {
        return m_phoneNumber;
    }

    /**
     * Set the Private Number enum
     * 
     * @param the
     *            Private Number enum
     */
    public void setPrivateNumberEnum(int p_privateNumberEnum)
    {
        m_privateNumberEnum = p_privateNumberEnum;
    }

    /**
     * Get the Private Number enum
     * 
     * @return the Private Number enum
     */
    public int getPrivateNumberEnum()
    {
        return m_privateNumberEnum;
    }

    /**
     * Set the Private Number
     * 
     * @param the
     *            Private Number
     */
    public void setPrivateNumber(String p_privateNumber)
    {
        m_privateNumber = p_privateNumber;
    }

    /**
     * Get the Private Number
     * 
     * @return the Private Number
     */
    public String getPrivateNumber()
    {
        return m_privateNumber;
    }

    /**
     * Set the Home Mode Display Text enum
     * 
     * @param the
     *            Home Mode Display Text enum
     */
    public void setHomeModeDisplayTextEnum(int p_homeModeDisplayTextEnum)
    {
        m_homeModeDisplayTextEnum = p_homeModeDisplayTextEnum;
    }

    /**
     * Get the Home Mode Display Text enum
     * 
     * @return the Home Mode Display Text enum
     */
    public int getHomeModeDisplayTextEnum()
    {
        return m_homeModeDisplayTextEnum;
    }

    /**
     * Set the Home Mode Display Text
     * 
     * @param the
     *            Home Mode Display Text
     */
    public void setHomeModeDisplayText(String p_homeModeDisplayText)
    {
        m_homeModeDisplayText = p_homeModeDisplayText;
    }

    /**
     * Get the Home Mode Display Text
     * 
     * @return the Home Mode Display Text
     */
    public String getHomeModeDisplayText()
    {
        return m_homeModeDisplayText;
    }

    /**
     * Set the Screen Saver Text enum
     * 
     * @param the
     *            Screen Saver Text enum
     */
    public void setScreenSaverTextEnum(int p_screenSaverTextEnum)
    {
        m_screenSaverTextEnum = p_screenSaverTextEnum;
    }

    /**
     * Get the Screen Saver Text enum
     * 
     * @return the Screen Saver Text enum
     */
    public int getScreenSaverTextEnum()
    {
        return m_screenSaverTextEnum;
    }

    /**
     * Set the Screen Saver Text
     * 
     * @param the
     *            Screen Saver Text
     */
    public void setScreenSaverText(String p_screenSaverText)
    {
        m_screenSaverText = p_screenSaverText;
    }

    /**
     * Get the Screen Saver Text
     * 
     * @return the Screen Saver Text
     */
    public String getScreenSaverText()
    {
        return m_screenSaverText;
    }

    /**
     * Set the WAP enum
     * 
     * @param the
     *            WAP enum
     */
    public void setWapEnum(int p_wapEnum)
    {
        m_wapEnum = p_wapEnum;
    }

    /**
     * Get the WAP enum
     * 
     * @return the WAP enum
     */
    public int getWapEnum()
    {
        return m_wapEnum;
    }

    /**
     * Set the WAP
     * 
     * @param the
     *            WAP
     */
    public void setWap(String p_wap)
    {
        m_wap = p_wap;
    }

    /**
     * Get the WAP
     * 
     * @return the WAP
     */
    public String getWap()
    {
        return m_wap;
    }
    

    /**
     * Set the notes which are to be attached to the new radio
     * 
     * @param the
     *            notes
     */
    public void setNotes(String notes)
    {
        m_notes = notes;
    }

    /**
     * Get the notes attached to the new radio
     * 
     * @return the notes
     */
    public String getNotes()
    {
        return m_notes;
    }

    /**
     * Set whether or not all ISSIs are known (if multiple device have been
     * selected)
     * 
     * @param p_areISSIKnown
     */
    public void setAreISSIsKnown(boolean p_areISSIsKnown)
    {
        m_areISSIsKnown = p_areISSIsKnown;
    }

    /**
     * Are all ISSIs known (if multiple device have been selected)?
     * 
     * @return
     */
    public boolean getAreISSIsKnown()
    {
        return m_areISSIsKnown;
    }

    /*
     * Get the ext model type set
     * 
     * @return Set
     */
    public String getExtModelType()
    {
        return m_extModelType;
    }

    /*
     * Set the ext model type set
     * 
     * @param mExtModelTypesInCollection Set<String>
     */
    public void setExtModelType(String mExtModelType)
    {
        m_extModelType = mExtModelType;
    }

    /*
     * Get start time of the job schedule
     * 
     * @return Date
     */
    public Date getStartTime()
    {
        return startTime;
    }

    /*
     * Set start time of the job schedule
     * 
     * @param startTime Date
     */
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    /*
     * Get end time of the job schedule
     * 
     * @return Date
     */
    public Date getEndTime()
    {
        return endTime;
    }

    /*
     * Set end time of the job schedule
     * 
     * @param endTime Date
     */
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    /*
     * Get recurrence of the job schedule
     * 
     * @return int
     */
    public int getRecurrences()
    {
        return recurrences;
    }

    /*
     * Set recurrence of the job schedule
     * 
     * @param recurrences int
     */
    public void setRecurrences(int recurrences)
    {
        this.recurrences = recurrences;
    }

    /*
     * Get notification message
     * 
     * @return String
     */
    public String getNotificationMessage()
    {
        return notificationMessage;
    }

    /*
     * Set notification message
     * 
     * @param notificationMessage String
     */
    public void setNotificationMessage(String notificationMessage)
    {
        this.notificationMessage = notificationMessage;
    }

    /*
     * Get the network template
     * 
     * @return MandatoryTemplate
     */
    public MandatoryFile getNetworkTemplate()
    {
        return networkTemplate;
    }

    /*
     * set the network template
     * 
     * @param networkTemplate MandatoryTemplate
     */
    public void setNetworkTemplate(MandatoryFile networkTemplate)
    {
        this.networkTemplate = networkTemplate;
    }

    /*
     * Get the mandatory profiles list
     * */
    public List<MandatoryFile> getMandatoryProfiles()
    {
        return mandatoryProfiles;
    }

    /*
     * set the mandatory profiles list
     * */
    public void setMandatoryProfiles(List<MandatoryFile> profilesList)
    {
        if (null == profilesList)
        {
            mandatoryProfiles = new ArrayList<MandatoryFile>();
            return;
        }

        mandatoryProfiles = profilesList;
    }

    /*
     * Get the fleetmap template
     * 
     * @return MandatoryTemplate
     */
    public MandatoryFile getFleetmapTemplate()
    {
        return fleetmapTemplate;
    }

    /*
     * set the fleetmap template
     * 
     * @param fleetmapTemplate MandatoryTemplate
     */
    public void setFleetmapTemplate(MandatoryFile fleetmapTemplate)
    {
        this.fleetmapTemplate = fleetmapTemplate;
    }

    /**
     * get the Notification ID
     * 
     * @return
     */
    public String getNotificationId()
    {
        return m_notification_id;
    }

    /**
     * Set the Notification ID
     * 
     * @param m_notification_id
     */
    public void setNotificationId(String m_notification_id)
    {
        this.m_notification_id = m_notification_id;
    }

    /*
     * check if the parameters is taken from a clone job
     * 
     * @return boolean
     */
    public boolean isCloneJob()
    {
        return isCloneJob;
    }

    /*
     * Set the parameter
     * 
     * @param isCloneJob boolean
     */
    public void setCloneJob(boolean isCloneJob)
    {
        this.isCloneJob = isCloneJob;
    }

    /**
     * Get the network logging enum
     * 
     * @return int
     */
    public int getNetworkLoggingEnum()
    {
        return m_networkLoggingEnum;
    }

    /**
     * Set the network logging enum
     * 
     * @param networkLoggingEnum
     */
    public void setNetworkLoggingEnum(int networkLoggingEnum)
    {
        m_networkLoggingEnum = networkLoggingEnum;
    }

    /**
     * Get the network logging
     * 
     * @return String
     */
    public String getNetworkLogging()
    {
        return m_networkLogging;
    }

    /**
     * Set the network logging
     * 
     * @param m_networkLogging
     */
    public void setNetworkLogging(String networkLogging)
    {
        m_networkLogging = networkLogging;
    }

    /**
     * Get the user data path.
     */
    public String getUserDataPath()
    {
        return m_userDataPath;
    }

    /**
     * Set the user data path.
     */
    public void setUserDataPath(String userDataPath)
    {
        m_userDataPath = userDataPath;
    }

    /**
     * Get the hot fix path.
     */
    public String getHotFixPath()
    {
        return m_hotFixPath;
    }

    /**
     * Set the hot fix path.
     */
    public void setHotFixPath(String hotFixPath)
    {
        m_hotFixPath = hotFixPath;
    }

    public boolean getProgramFirmwarePermission()
    {
        return hasProgramFirmwarePermission;
    }

    public void setProgramFirmwarePermission(boolean hasProgramFirmwarePermission)
    {
        this.hasProgramFirmwarePermission = hasProgramFirmwarePermission;
    }

    public boolean getProgramCodeplugPermission()
    {
        return hasProgramCodeplugPermission;
    }

    public void setProgramCodeplugPermission(boolean hasProgramCodeplugPermission)
    {
        this.hasProgramCodeplugPermission = hasProgramCodeplugPermission;
    }

    public void setEnabledSellableFeatureOnDeviceMap(
            Map<String, List<String>> enabledSellableFeatureOnDeviceMap)
    {
        this.enabledSellableFeatureOnDeviceMap = enabledSellableFeatureOnDeviceMap;
    }

    public boolean isFeatureEnabledOnDevice(String deviceUUID, String featureID)
    {
        return (enabledSellableFeatureOnDeviceMap != null
                && enabledSellableFeatureOnDeviceMap.containsKey(deviceUUID)
                && enabledSellableFeatureOnDeviceMap.get(deviceUUID).contains(featureID));
    }

    public Map<String, List<String>> getEnabledSellableFeatureOnDeviceMap()
    {
        return enabledSellableFeatureOnDeviceMap;
    }

    public void setLicensedSellableFeatureOnDeviceMap(
            Map<String, List<String>> licensedSellableFeatureOnDeviceMap)
    {
        this.licensedSellableFeatureOnDeviceMap = licensedSellableFeatureOnDeviceMap;
    }

    public boolean isFeatureLicensedForDevice(String deviceID, String featureID)
    {
        return (licensedSellableFeatureOnDeviceMap != null
                && licensedSellableFeatureOnDeviceMap.containsKey(deviceID)
                && licensedSellableFeatureOnDeviceMap.get(deviceID).contains(featureID));
    }

    public Map<String, List<String>> getLicensedSellableFeatureOnDeviceMap()
    {
        return licensedSellableFeatureOnDeviceMap;
    }

    public void setAvaiableSellableFeatureLicenseList(
            Map<String, String> avaiableSellableFeatureLicenseList)
    {
        this.avaiableSellableFeatureLicenseList = avaiableSellableFeatureLicenseList;
    }

    public Map<String, String> getAvaiableSellableFeatureLicenseList()
    {
        return avaiableSellableFeatureLicenseList;
    }

    public void setLicensedSiteWideSellableFeature(
            Map<String, OnOffTypeLicense> licensedSiteWideSellableFeature)
    {
        this.licensedSiteWideSellableFeature = licensedSiteWideSellableFeature;
    }

    public boolean isSiteWideFeatureLicensed(String featureID)
    {
        return (licensedSiteWideSellableFeature != null
                && licensedSiteWideSellableFeature.containsKey(featureID) && licensedSiteWideSellableFeature
                    .get(featureID).isEnabled);
    }

    public Map<String, OnOffTypeLicense> getLicensedSiteWideSellableFeature()
    {
        return licensedSiteWideSellableFeature;
    }

    public void setFelxeraOnlineMode(boolean felxeraOnlineMode)
    {
        this.felxeraOnlineMode = felxeraOnlineMode;
    }

    public boolean isFelxeraOnlineMode()
    {
        return felxeraOnlineMode;
    }

    public void setSellableFeatureMap(Map<String, Set<String>> sellableFeatureMap)
    {
        this.sellableFeatureMap = sellableFeatureMap;
    }

    public Map<String, Set<String>> getSellableFeatureMap()
    {
        return sellableFeatureMap;
    }

    public Map<String, String> getDeviceAttributeMap()
    {
        return deviceAttributeMap;
    }

    public void setDeviceAttributeMap(Map<String, String> deviceAttributeMap)
    {
        this.deviceAttributeMap = deviceAttributeMap;
    }

	/**
	 * @return the m_limitedTMOTGEnum
	 */
    public int getLimitedTMOTGEnum() {
		return m_limitedTMOTGEnum;
	}

	/**
	 * @param m_limitedTMOTGEnum the m_limitedTMOTGEnum to set
	 */
    public void setLimitedTMOTGEnum(int m_limitedTMOTGEnum) {
		this.m_limitedTMOTGEnum = m_limitedTMOTGEnum;
	}

	/**
	 * @return the m_limitedDMOTGEnum
	 */
    public int getLimitedDMOTGEnum() {
		return m_limitedDMOTGEnum;
	}

	/**
	 * @param m_limitedDMOTGEnum the m_limitedDMOTGEnum to set
	 */
    public void setLimitedDMOTGEnum(int m_limitedDMOTGEnum) {
		this.m_limitedDMOTGEnum = m_limitedDMOTGEnum;
	}

	/**
	 * @return the m_limitedTMOTG
	 */
    public String getLimitedTMOTG() {
		return m_limitedTMOTG;
	}

	/**
	 * @param m_limitedTMOTG the m_limitedTMOTG to set
	 */
    public void setLimitedTMOTG(String m_limitedTMOTG) {
		this.m_limitedTMOTG = m_limitedTMOTG;
	}

	/**
	 * @return the m_limitedDMOTG
	 */
    public String getLimitedDMOTG() {
		return m_limitedDMOTG;
	}

	/**
	 * @param m_limitedDMOTG the m_limitedDMOTG to set
	 */
    public void setLimitedDMOTG(String m_limitedDMOTG) {
		this.m_limitedDMOTG = m_limitedDMOTG;
	}

	/**
	 * @return the hasLimitedNubmerOfTGPermission
	 */
	public boolean getLimitedNubmerOfTGPermission() {
		return hasLimitedNubmerOfTGPermission;
	}

	/**
	 * @param hasLimitedNubmerOfTGPermission the hasLimitedNubmerOfTGPermission to set
	 */
	public void setLimitedNubmerOfTGPermission(
			boolean hasLimitedNubmerOfTGPermission) {
		this.hasLimitedNubmerOfTGPermission = hasLimitedNubmerOfTGPermission;
	}

}
