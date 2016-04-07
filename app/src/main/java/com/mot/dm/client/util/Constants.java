/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |

|                                                                              |
|                  Copyright 2007-2012 Motorola Solutions, Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM Client
CODED     BY:  Michael Leismann    March 10, 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
10/Mar/08  Michael Leismann      CCMPD01000364 Initial version
14/Mar/08  Michael Leismann                    ISSI range start set to 1      
31/Mar/08  Michael Leismann                    Added password length constant
03/Apr/08  Michael Leismann                    Added TEI and Radio Name
                                               constants
08/Apr/08  Michael Leismann                    Moved this class to util package
                                               and included the constants from
                                               the LengthConstants class
18/Sep/08  Michael Leismann      CCMPD01087804 German Client: Added constants
16/Oct/08  Michael Leismann      CCMPD01087804 Updated after FTR
23/Oct/08  Michael Leismann      CCMPD01095091 Moved constants to LocaleContext
31/Mar/09  Michael Leismann      CCMPD01189094 Added constants for the Personal
                                               Data/Special Parameters feature
14/Aug/09  Sigurdur Jonsson      CCMPD01235271 Added notes to radio and template
28/Sep/09  Michael Leismann      CCMPD01269375 WAP
17/Mar/10  Kim Mortensen         CCMPD01319807 Constant to text length for 
                                               Notification ID.
25/03/10   chad001               CCMPD01324766  3.0 AP feature 
25/04/10   chad001               ccmpd01340225  GUI issues. username should
                                                be restricted to 16 characters 
                                                as Mysql can support only 16 
                                                characters                                              
18/Nov/10  Michael Leismann      CCMPD01394081  iTM 4.0: Policies  
08/Apr/11   chad001              ccmpd01492704 GUI improvement      
8/Jul/11   cjh102                CCMPD01525915 Added constants for client path 
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
4/Dec/12   tqfn38 		 CCMPD01721424 iTM6.1 Picture Pull & Push
13/10/14   wch378                CCMPD01937887 change license file filter
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

/**
 * @Version 1.0 March 10, 2008
 * @author cml066 Michael Leismann
 */
public final class Constants
{
    private Constants()
    {
        
    }
    /**
     * ISSI range 1-13999999
     */
    public static final int ISSI_RANGE_START = 1;
    public static final int ISSI_RANGE_END = 13999999;

    /**
     * Password range (ASCII) 33-126
     */
    public static final int VALID_PWD_RANGE_START = 33;
    public static final int VALID_PWD_RANGE_END = 126;

    // Password length
    public static final int PWD_LENGTH = 12;
 
    /**
     * Radio Name length
     */
    public static final int RADIO_NAME_MIN_LENGTH = 1;
    public static final int RADIO_NAME_MAX_LENGTH = 128;

    /**
     * Notification ID length 
     */
    public static final int RADIO_NOTIFICATION_ID_MAX_LENGTH = 256;

    /**
     * Length constants
     */
    public static final int DEVICE_NAME_LEN = 128;
    
    /**
     * TEI length: 15 for normal TEI MTM800E Control Head TEI is defined in
     * LengthConstants class
     */
    public static final int DEVICE_TEI_LEN = 15;    
    public static final int CONTROL_HEAD_LEN = 17;
    public static final String CONTROL_HEAD_TEI_CH_START = "CH";
    
    public static final int Ethernet_CONTROL_HEAD_LEN = 10;
    public static final int MAX_CPU_HARDWARE_ID_LEN = 64;
    public static final int MIN_CPU_HARDWARE_ID_LEN = 1;
    public static final int USER_PWD_LEN = 20;
    public static final int USER_NAME_LEN = 50;
    public static final int USER_FULL_NAME_LEN = 128;
    public static final int GROUP_NAME_LEN = 64;
    public static final int CODEPLUG_PWD_LEN = 12;
    public static final int ISSI_LEN = 8;
    public static final int RADIO_NOTES_TEXT_LEN = 1024;
    public static final int ENTITLEMENT_ID_LENGTH = 36;
    public static final int MAX_ENTITLEMENT_ID_LENGTH = 64;
    public static final int MIN_ENTITLEMENT_ID_LENGTH = 1;
    public static final int MIN_FEATURE_ID = 100;
    public static final int MAX_FEATURE_ID = 999;

    /**
     * File extensions
     */
    public static final String DOT_CSV = ".csv";
    public static final String DOT_XML = ".xml";
    public static final String DOT_LOG = ".log";
    public static final String DOT_PROPERTIES = ".properties";
    public static final String DOT_LICX = ".licx";
    public static final String DOT_BIN = ".bin";
    public static final String BOTH_BIN_AND_LICX = "*.bin;*.licx";

    /**
     * Language constants
     */
    public static final String PREF_KEY = "/com/mot/dm/client";
    public static final String PLUGIN = "plugin";
    public static final String PLUGINS_DIR = "plugins\\";
    public static final String CONFIG_DIR = "configuration";
    public static final String LOG_DIR = "/logs";
    public static final String TABLECOLUMN = "tableColumn";    

    /**
     * Line separator
     */
    public final static String LINESEP = System.getProperty("line.separator");

    /*
     * Personal Data/Special Parameters constants
     */
    public static final int PIN_LENGTH = 4;
    public static final int PUK_LENGTH = 8;
    public static final int COMMENT_FIELD_LENGTH = 59;
    public static final int HOME_MODE_DISPLAY_TEXT_LENGTH = 24;
    public static final int SCREEN_SAVER_TEXT_LENGTH = 12;
    public static final int SPECIFY_PHONE_NUMBER_LENGTH = 24;
    public static final int PREFIX_PHONE_NUMBER_LENGTH = 16;
    public static final int PRIVATE_NUMBER_LENGTH = 8;
    public static final int PREFIX_WAP_LENGTH = 119;
    public static final int WAP_LENGTH = 127;
    // Can be "Enable" and "Disable", i.e. max length i 7
    public static final int NEWTWORK_LOGGING_LENGTH = 7;

    public static final int COMPOSITE_WIDTH_LARGE = 1024;
    public static final int COMPOSITE_WIDTH_MIDDLE = 800;
    public static final int COMPOSITE_WIDTH_SMALL = 600;
    public static final int JOB_WIZARD_HEIGHT = 925;
    
    
    //license specific contants
    public static final String Distribution_Center_License_Name = "Distribution Center";
    public static final String Radio_Authentication_License_Name = "Radio Authentication";
    public static final String FLEXERA_LICENSE_SERVER_URL = "Flexera License Server Url";
    public static final String FLEXERA_LICENSE_ONLINE = "Flexera License Online";
    public static final String FLEXERA_LICENSE_SERVER_URL_MODIFY_FLAG = "License Server Url Can Be Modified";
    public static final String FLEXERA_LICENSE_DATE_FORMAT = "dd-MMM-yyyy";
    public static final String FLEXERA_LICENSE_DATE_FORMAT_EX = "yyyy-MM-dd";
    public static final String FLEXERA_LICENSE_EXPIRE_DATE_PREFIX = "Exp:";
    public static final String FLEXERA_LICENSE_FROM_CPS_FILE = "flexera.xml";
    public static final String FLEXERA_LICENSE_CREATE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    //Extended Agency Partitioning
    public static String AGENCY_NAME_RECORD = "AgencyNameRecord";

}
