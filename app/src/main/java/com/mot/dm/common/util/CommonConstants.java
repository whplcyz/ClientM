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

PRODUCT NAME:  ITM
CODED     BY:  Kim Mortensen    17 Sep. 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
19/Mar/08  Kim Mortensen         ccmpd01089432 Making common constants.
12/Jan/09  chad001               CCMPD01159324 Search radio
05/Mar/09  Sigurdur Jonsson      CCMPD01166176  Handling of new user type statusUser
                                                and statusUserSSL.
20/Apr/09  Michael Leismann      CCMPD01189094 Added constants for the Personal
                                               Data/Special Parameters feature
26/May/09  Michael Leismann      CCMPD01205263 A constant has been renamed
27/May/09  Michael Leismann      CCMPD01205263 Added constants
14/Jul/09  Michael Leismann      CCMPD01237696 Added constant
19/Sep/09  Michael Leismann      CCMPD01259651 Added constant
28/Sep/09  Michael Leismann      CCMPD01269375 WAP
18/Nov/09  Sigurdur Jonsson      CCMPD01287422 iTM 3.0 Feature, job polling.
23/Jun/10  Sigurdur Jonsson      CCMPD01357127 Automatic application update.
24/Aug/10  Sigurdur Jonsson      CCMPD01385372 Read server version and patch
                                               level from parameter table.
20/09/10   chad001               ccmpd01393090 4.0 licensing feature                                                
27/Oct/10  Michael Leismann      CCMPD01387289 iTM 4.0: Network Logging
18/Jan/11  Sigurdur Jonsson       CCMPD01460388 Adding cleanup for policy
                                                executions. Moved a constant
                                                from proxy\Constants.java
28/Feb/11  Sigurdur Jonsson      CCMPD01478764 iTM 5.0: Workaround for broken
                                               JAXP schema validation in
                                               java 1.6 
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting                                                  
04/May/11  Michael Leismann      CCMPD01502974 iTM 5.0: Proxy lib part of Panda
                                               integration - polling
16/Jun/11  Hai Dong              CCMPD01523523 Change version check for proxy 
                                               into version check for client                                               
10/Jun/11  Michael Leismann      CCMPD01516805 iTM 5.0: Enhanced API -
                                               Upload/download of UserData file
24/06/11   cjh102                CCMPD01523954 Added support for release package                                               
18/July/11 cjh102                CCMPD01537918 Added constant for temporary
                                               release package location                                               
31/Jul/11  Michael Leismann      CCMPD01535002 iTM 5.0: Removed recovery warning
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature 
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
21/Jan/13  qngv36                REM_CP_Authentication_Beta_Dev Support CP authentication in iTM
 4/Dec/12  tqfn38 		 		 CCMPD01721424 iTM6.1 Picture Pull & Push
28/Feb/13  tqfn38                CCMPD01748338 The warning icon for Access code shall be hidden if user does not select collect DMS
9/July/13  wch378                CCMPD01791920 picture extraction API enhancement 
31/July/13 wch378                CCMPD01799628 fix bug on iTM-I06.10.00.06
31/July/13 qngv36                CCMPD01790940 Support datalight upgrade in iTM6.1  
21/Aug/13  wch378                CCMPD01805423 fix display issue    
5/Sep/13   wch378                CCMPD01811614 check if user data is in non-English language.    
9/Sep/13   wch378                CCMPD01813562 update "use regional language logic" 
28/3/14    wch378                CCMPD01889533 develop flexera license.
25/Jun/14  bfq463                CCMPD01900799 active directory support  
28/Aug/14  qngv36                CCMPD01923183 iTM7.0 Auto schedule online backup.
13/Jan/15  qngv36                CCMPD01957644 iTM7.0 Update default backup count to 10.
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.util.Date;

public final class CommonConstants
{
    private CommonConstants()
    {
        
    }

    
    public static final String LICENSE_REPOSITORY_PATH = "licenses";

    public static final String ENGLISH_TEXT = "ENGLISH";

    /**
     * Length constants
     */
    public static final int AGENCY_NAME_LEN = 128;

    /**
     * Unknown
     */
    public static final String UNKNOWN = "Unknown";

    /*
     * The search radio operation index. The client passes the index value to
     * server while searching for a radio that is based on TEI, ISSI, Serial
     * number, or name.
     */
    public static final int SEARCH_BY_TEI = 0;
    public static final int SEARCH_BY_ISSI = 1;
    public static final int SEARCH_BY_SNR = 2;
    public static final int SEARCH_BY_NAME = 3;

    /*
     * iTM user role id�s.
     */
    public static final String SUPER_USER = "1";
    public static final String CLIENT_USER = "2";
    public static final String PROXY_SUPER_USER = "3";
    public static final String PROXY_USER = "4";
    public static final String STATUS_USER = "5";
    public static final String STATUS_USER_SSL = "6";

    /*password usage to indicate what the propose of protection*/
    public static final String NO_PROTECTION_STRING = "Remove Protection";
    public static final String PROTECT_CODEPLUG_ONLY_STRING = "Protect Codeplug";
    public static final String PROTECT_RADIO_ONLY_STRING = "Protect Radio";
    public static final String PROTECT_RADIO_CODEPLUG_STRING = "Protect Radio and Codeplug";
    
    public static final String PASSWORD_TASK_PARAMETER_NAME = "Password"; 
    
    //If you modify/add new status, please synchronize the xsd(iTM_New_Radio_Import.xsd) file
    public static final String UNKNOWN_PROTECION_STATUS = "-1";
    public static final String NO_PROTECTION = "0";
    public static final String PROTECT_CODEPLUG_ONLY = "1";
    public static final String PROTECT_RADIO_ONLY = "2";
    public static final String PROTECT_RADIO_CODEPLUG = "3";
    
    public static final String defaultPort = "8080";
    public static final String policyMaxSN_ParaName = "PolicyMaxSN";
    
    public static final String CommentFlag = "--";
    public static final String procedureStartFlag = "!!";
    public static final String procedureStopFlag = "!!!";
    
    public static final String PolicyResultsFolder = "policy-results";
    
    public static final String WizardPagePersonalData = "WizardPagePersonalData";
    
    public static final String HeaderLanguageID = "LanguageID";
    public static final String LANGUAGE = "LANGUAGE";
    
    public static final String NETWORKPROXYMODE = "NetworkProxyMode";
    public static final String NEEDAUTHENTICATIONUSER = "NeedAuthenticationUser";
    public static final String AUTHENTICATIONUSERNAME = "AuthenticationUserName";
    public static final String AUTHENTICATIONPASSWORD = "AuthenticaionPassword";
    public static final String NETWORKPROXYADDRESS = "NewworkProxyAddress";
    public static final String NETWORKPROXYPORT = "NetworkProxyPort";
    public static final String FLEXERALICENSEURL = "127.0.0.1";
    public static final String FLEXERALICENSEUSERNAME = "mototrboquery";
    public static final String FLEXERALICENSEPASSWORD = "C1p2s3Q42009";
    public static final String PROXYURL = "http.proxy.url";
    public static final String PROXYPORT = "http.proxy.port";
    
    public static final int    FLEXERALICENSENOUSERPROXY = 0;
    public static final int    FLEXERALICENSEUSESYSTEMRPROXY = 1;
    public static final int    FLEXERALICENSECUSTOMIZEPROXY = 2;
    public static final int    ONEDAYMILLISECONDS = 86400000;
    
    public static boolean IS_DEBUG_MODE = true;
    
    /*after 200 years*/
    /*1000 * 60 * 60 * 24 * 365 * 200*/
    public static final long TWOHundredYEARS = 6307200000000L;
    public static final Date FLEXERA_LICENSE_MAX_DATE = new Date(System.currentTimeMillis() + TWOHundredYEARS);
    
    public static String getFlexeraHome()
    {
        String userDir = System.getProperty("user.dir");
        String flexeraHome = "";
        if (userDir.lastIndexOf("Tomcat") != -1)
        {
            flexeraHome = (userDir + System.getProperty("file.separator") + "bin");  //Tomcat\Bin
        }
        else
        {
            flexeraHome = (userDir + System.getProperty("file.separator") + "Flexera"); 
        }
        
        return flexeraHome;
    }
    
    /*
     * Query type for jobs
     */
    public static enum QueryKeyType
    {
        QUERY_TYPE_DEVICE_ID,
        QUERY_TYPE_DEVICE_UID,
        QUERY_TYPE_DEVICE_UID_AS_TEI
    }

    /*
     * Query poll type for jobs
     */
    public static enum QueryPollType
    {
        QUERY_POLL_TYPE_POLL,
        QUERY_POLL_TYPE_CONNECT
    }
    
    /*
     * Query TerminalType for jobs
     */
    public static enum QueryTerminalType
    {
        NGCH_CH,
        NGCH_TRANSCEIVER,
        PORTABLE,
        OTHER
    }

    /*
     * Download types This is used to prioritize the download, where
     * DOWNLOAD_TYPE_ON_DEMAND has the highest priority Note: If the names are
     * modified then ProcessFileDownload.jsp should be updated as well
     */
    public static enum DownloadType
    {
        DOWNLOAD_TYPE_ON_DEMAND,
        DOWNLOAD_TYPE_AUTOMATIC
    }
    
    public static enum PolicyParameterTypeInXml
    {
        StartAddress,
        EndAddress,
        CollectDF,
        DFAccessCode,
        DefaultDFAccessCode,
        CollectPicture,
        RadioFolderPathForCollection,
        DeleteAfterCollect,
        SrcFilePath,
        RadioFolderPathForPush,
        OperationType,
    }

    public static enum PolicyParameterTypeInTask
    {
        START_ADDRESS,
        END_ADDRESS,
        COLLECT_DF,
        ACCESS_CODE,
        DEFAULT_ACCESS_CODE,
        COLLECT_PICTURE,
        PICTURE_FOLDER,
        DELETE_DF_PICTURE,
        PICTURE_FILE_FROM,
        RADIO_FOLDER,
        OPERATION_TYPE,
        UPDATE_COUNT,
        SERVER_PICTURE_FILE
    }

    /*
     * Parameter names - used for jobs/tasks
     */
    public static final String PARAMETER_PASSWORD_FLAG = "Password Usage";
    public static final String PARAMETER_EHNANCEMENT_PASSWORD = "Enhancement Password";
    public static final String PARAMETER_PASSWORD = "Password";
    public static final String PARAMETER_ISSI = "ISSI";
    public static final String PARAMETER_CONTACT_BOOK = "Contact Book";
    public static final String PARAMETER_MENU_SHORTCUTS = "Menu Shortcuts";
    public static final String PARAMETER_SCREEN_SAVER = "Screen Saver";
    public static final String PARAMETER_AUDIO_SETTINGS = "Audio Settings";
    public static final String PARAMETER_GPS = "GPS";
    public static final String PARAMETER_TALKGROUPS = "Talkgroups";
    public static final String PARAMETER_PIN = "PIN";
    public static final String PARAMETER_PUK = "PUK";
    public static final String PARAMETER_COMMENT_FIELD = "Comment Field";
    public static final String PARAMETER_PHONE_NUMBER = "Phone Number";
    public static final String PARAMETER_PRIVATE_NUMBER = "Private Number";
    public static final String PARAMETER_SCREEN_SAVER_TEXT = "Screen Saver Text";
    public static final String PARAMETER_HOME_MODE_DISPLAY_TEXT = "Home Mode Display Text";
    public static final String PARAMETER_WAP = "WAP";
    public static final String PARAMETER_LIMITED_TMO_TG = "Limit number of TMO talkgroups";
    public static final String PARAMETER_LIMITED_TMO_TG_XML_ALIAS = "Limit Number of TMO Talkgroups";
    public static final String PARAMETER_LIMITED_DMO_TG = "Limit number of DMO talkgroups";
    public static final String PARAMETER_LIMITED_DMO_TG_XML_ALIAS = "Limit Number of DMO Talkgroups";
    public static final String PARAMETER_NETWORK_LOGGING = "MS Mobility Logging";

    public static final String[] PERSONAL_DATA_ARRAY = new String[]
            { CommonConstants.PARAMETER_CONTACT_BOOK, CommonConstants.PARAMETER_MENU_SHORTCUTS
            , CommonConstants.PARAMETER_SCREEN_SAVER, CommonConstants.PARAMETER_AUDIO_SETTINGS
            , CommonConstants.PARAMETER_GPS, CommonConstants.PARAMETER_TALKGROUPS };
    
    public static final String[] SPECIAL_PARAMETER_ARRAY = new String[]
          { PARAMETER_PIN,
            PARAMETER_PUK,
            PARAMETER_COMMENT_FIELD,
            PARAMETER_SCREEN_SAVER_TEXT,
            PARAMETER_HOME_MODE_DISPLAY_TEXT,
            PARAMETER_PHONE_NUMBER,
            PARAMETER_PRIVATE_NUMBER,
            PARAMETER_WAP,
            PARAMETER_LIMITED_TMO_TG,
            PARAMETER_LIMITED_DMO_TG };
    /*
     * Used as a tag to distinguish between a text with "ISSI" and an ISSI tag
     * See com.mot.dm.client.dmImpl.gui.composite.WizardPageSpecialParameters
     * and com.mot.dm.client.dmImpl.gui.composite.WizardPageSummary for details
     */
    public static final String PARAMETER_ISSI_TAG = "<ISSI-TAG>";

    /*
     * Personal Data/Special Parameters constants
     */
    public static final int KEEP_VALUE = 0;
    public static final int TEMPLATE_VALUE = 1;
    public static final int SPECIFY_VALUE = 2;
    // No value is used to indicate that no value has been set
    public static final int NO_ENUM_VALUE = 99;
    public static final String PERSONAL_DATA = "Personal Data";
    public static final String SPECIAL_PARAMETERS = "Special Parameters";
    public static final String ENABLE_STRING = "Enable";
    public static final String DISABLE_STRING = "Disable";

    /*
     * Personal Data/Special Parameters constants Used by Operations Log and
     * Export Data Tool
     */
    public static final String KEEP_STRING = "KeepAsIs";
    public static final String TEMPLATE_STRING = "CopyFromTemplate";
    public static final String SPECIFY_STRING = "Specify";

    /*
     * Constants for names of parameters in the parameter table
     */
    public static final String ITM_SERVER_VERSION_PARAM = "DBVersion";
    public static final String ITM_SERVER_SPU_LEVEL_PARAM = "PatchVersion";

    /*
     * Policy file path
     */
    public static final String POLICY_REPOSITORY_PATH = "policy-results";

    /*
     * Path for complete release package
     */
    public static final String COMPLETE_RELEASEPACKAGE_PATH = "releasePackage/completePackage";

    /*
     * Path for temporary release package
     */
    public static final String TEMPORARY_RELEASEPACKAGE_PATH = "releasePackage/temporary";
        
    /*
     * s19 extension
     */
    public static final String S19_EXTENSION = "s19";

    /*
     * Constants for XML/XSD schema
     */
    public final static String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    public final static String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    // User Data file path and extension
    public static final String USER_DATA_REP_DIR = "Job/Task/UserData";
    public static final String USER_DATA_EXTENSION = ".xml";
    
    // Hot Fix file path and extension
    public static final String HOT_FIX_REP_DIR = "Job/Task/HotFix";
    public static final String HOT_FIX_EXTENSION = ".hfx";
    
    // Convert Radio Model TaskInfo taskname, and the taskname is used for proxy.
    public static final String TaskInfo_TaskName_ConvertModelType = "Target Model Type";

    public static final String MS_LOG_FILE_PATH = "MS_LOG_FILE_PATH";
    
    public static final String PICTURE_COLLECTION_FILE_PATH = "PICTURE_C_FILE_PATH";
    
    // Picture Push policy file path and extension
    public static final String PICTURE_PUSH_FILE_FIX_REP_DIR = "Policy/PicturePush";
    
    public static final String PICTURE_PUSH_UPDATE_COUNT = "UPDATE_COUNT";
    
    public static final String PICTURE_PUSH_SERVER_FILE = "SERVER_PICTURE_FILE"; 
    
    public static final String PICTURE_FILE_FIX_EXTENSION = ".zip";
    
    // MS_MOBILITY_LOGGING policy result file path
    public static final String MS_MOBILITY_LOGGING_FILE_FIX_REP_DIR = POLICY_REPOSITORY_PATH
            + "/Mobility_Logging";
    
    // Picture collection policy result file path
    public static final String PICTURE_COLLECTION_FILE_FIX_REP_DIR = POLICY_REPOSITORY_PATH
            + "/Picture_Collection";
    
    public static final int    PICTURE_PUSH_FILE_MAX_SIZE = 1024*1024*1024;
    
    public static final String UPPER_STR_UNLIMITED_IN_XML = "UNLIMITED"; 
    
    public enum REQUEST_STATUS
    { 
    	INVALID_REQUEST,
    	NO_REQUEST, 
    	HAS_REQUEST
    }
    
   public static final int AVAIABLE = 0; 
   public static final int DOWNLOADED = 1; 
   public static final int OUTOFDATE = 2; 
   public static final int OBSOLETE = 3; 
   
   public static final int ERROR = -1;
   public static final int DOWNLOADING = 0;
    //LDAP parameter key
    public static final String LDAP_HOST = "LDAP_HOST";
    public static final String LDAP_PORT = "LDAP_PORT";
    public static final String LDAP_ENCRYPTION_METHOD = "LDAP_ENCRYPTION";
    public static final String LDAP_BASEDN = "LDAP_BASEDN";
    public static final String LDAP_AUTHENTICATION_METHOD = "LDAP_AUTHENTICATION";
    public static final String LDAP_BIND_DN = "LDAP_BIND_DN";
    public static final String LDAP_BIND_PASSWORD = "LDAP_BIND_PASSWORD";
    public static final String LDAP_SEARCH_FILTER = "LDAP_SEARCH_FILTER";
    public static final String LDAP_LOGON_NAME_ATTRIBUTE = "LDAP_LOGON_NAME";
    public static final String LDAP_DISPLAY_NAME_ATTRIBUTE = "LDAP_DISPLAY_NAME";
    public static final String LDAP_EMAIL_ADDRESS_ATTRIBUTE = "LDAP_EMAIL_ADDRESS";
    public static final String LDAP_ENABLE = "LDAP_ENABLE";
    public static final String LDAP_ATTRIBUTE1 = "LDAP_ATTRIBUTE1";
    public static final String LDAP_ATTRIBUTE2 = "LDAP_ATTRIBUTE2";
    public static final String LDAP_ATTRIBUTE3 = "LDAP_ATTRIBUTE3";
    public static final String LDAP_ATTRIBUTE4 = "LDAP_ATTRIBUTE4";
    public static final String LDAP_ATTRIBUTE5 = "LDAP_ATTRIBUTE5";
    public static final String LDAP_ATTRIBUTE6 = "LDAP_ATTRIBUTE6";
    
    public static final String QUARTZ_ALL_WEEK_DAY_FLAG = "2-6";
    public static final String QUARTZ_SPLITTER = "_";
    public static final String QUARTZ_EXPESS_SPLITER = " ";
    public static final String QUARTZ_CRON_EXPRESS_SPLITER = ",";
    
 
    //---- Quart schedule related ----
    public static final int JOB_BACKUP = 0;
    public static final int JOB_EXPORT_RADIO = 1;
    
    public static final int DAILY = 0;
    public static final int WEEKLY = 1;
    public static final int MONTHLY = 2;
    public static final int ONCE = 3; 
    
    public static final int COMPLETED = 0;
    public static final int FAILED = 1;
    public static final int CANCELED = 2;
    public static final int RUNNING = 3;
    public static final int PAUSED = 4;
    public static final int IGNORED = 5;
    
    /**
     * Constants for OLH
     */
    public static final String PRE_HELP_PATH = "iTMHelphtml";
    public static final String BASE_PATH = "iTM_Help";
    public static final String HELP_FILE = "index.html";
    

    public static String BackupDefaultFolderName="DataBackup";
    /*the string of the backup path is meaningless, it just used to distinguish normal user backup location*/
    public static String BackupDefaultFolderNameForAccessError= BackupDefaultFolderName + "/s!89A5F879K9z23mt584g2p4";
    public static String BackupRootPath = "Backup Root Path";
    public static String AutoScheduleBackupEanbled="Auto Schedule Backup Enabled";
    public static String BackupMaxCount="Backup Max Count";
    public static String BackupMaxGByte="Backup Max G-Bytes";     
    
    public static int nDefaultBackupMaxCount = 10;
    public static int InfiniteSize = -1;
    public static int nDefaultBackupMaxGBytes = InfiniteSize;
    
    //LDAP authentication failed type
    public static String AuthenticateToLDAP_NetworkError = "AuthenticateToLDAP_NetworkError";
    public static String Authenticate_AuthenticationError = "Authenticate_AuthenticationError";
    public static String AuthenticateToLDAP_UnknownError = "AuthenticateToLDAP_UnknownError";
    public static final String FLEXERA_LICENSE_DATE_FORMAT = "dd-MMM-yyyy";
    public static final String FLEXERA_LICENSE_DATE_FORMAT_EX = "yyyy-MM-dd";
    public static final String FLEXERA_LICENSE_EXPIRE_DATE_PREFIX = "Exp:";
    
    public static final String DOT_BIN = ".bin";
    public static final String ANONYMOUS_LOGIN_FLAG = "isAnonymousProxy";
    
    public static final int FEATURE_STATUS_ENABLED = 1;                  //already enabled
    public static final int FEATURE_STATUS_SUPPORTED = -1;                //supported, but, not enabled.
    public static final int FEATURE_STATUS_SUPPORTED_IN_HIGHER_FW = -2;   //supported in higher software version.
    public static final int FEATURE_STATUS_NOT_SUPPORTED_BY_HARDWARE = -3 ;//it is not supported due to hardware dependent
    
    public static final String LANG_ENGLISH = "en";
    public static final String LANG_GERMAN = "de";
    public static final String LANG_SPANISH = "es";
    public static final String LANG_PORTUGUESE = "pt";
}
