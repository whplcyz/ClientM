/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  wch378 30/6/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/June/14 wch378               CCMPD01905766  Flexera license feature iteration2
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.util.HashMap;
import java.util.Map;

public class FlexeraFeatureConstants
{
    public static final String iTMSYSTEMLICENSEID = "100";
    public static final String iTMSYSTEMLICENSEFEATURENAME = "iTM 7.0 System License";
    /*System capacity license*/
    public static final String CLIENT = "210";
    public static final String RADIO = "212";
    public static final String PROXY = "211";
    public static final String PICTURE = "213";
    public static final String NOTIFICATION_LICENSE = "202";
    public static final String MS_LOGS_POLICIES_LICENSE = "204";
    /*key is ID, value is Name*/
    private static Map<String,String> SystemCapacityFeatureID2NameMap;

    /*System no-capacity license*/
    public static final String AGENCY_PARTIONING_LICENSE = "200";
    public static final String ENFORCE_PROFILE_LICENSE = "201";   
    public static final String ODBC_VIEW_LICENSE = "203";    
    public static final String NETWORKED = "205";
    public static final String DISTRIBUTION_CENTER_LICENSE = "206";
    public static final String RADIO_PROGRAMMING = "207";
    public static final String Radio_Authentication = "208";
    public static final String INDIVIDUAL_IDENTIFY_CONTROL_HEAD = "209";
    public static final String CONFIGURABLE_ATTRIBUTE = "214";
    /*key is ID, value is Name*/
    private static Map<String,String> SystemNoCapacityFeatureID2NameMap;
    
    static
    {
        SystemCapacityFeatureID2NameMap = new HashMap<String, String>();
        SystemCapacityFeatureID2NameMap.put(CLIENT, "Client");
        SystemCapacityFeatureID2NameMap.put(PROXY, "Proxy");
        SystemCapacityFeatureID2NameMap.put(RADIO, "Radio");
        SystemCapacityFeatureID2NameMap.put(PICTURE, "Radio with PICS Policies");
        SystemCapacityFeatureID2NameMap.put(NOTIFICATION_LICENSE, "Notification");
        SystemCapacityFeatureID2NameMap.put(MS_LOGS_POLICIES_LICENSE, "MS Mobility Log Collection Policies");
        SystemCapacityFeatureID2NameMap.put(CONFIGURABLE_ATTRIBUTE, "Configurable Attributes");
        
        SystemNoCapacityFeatureID2NameMap = new HashMap<String, String>();
        SystemNoCapacityFeatureID2NameMap.put(iTMSYSTEMLICENSEID, iTMSYSTEMLICENSEFEATURENAME);       
        SystemNoCapacityFeatureID2NameMap.put(AGENCY_PARTIONING_LICENSE, "Shared Server");
        SystemNoCapacityFeatureID2NameMap.put(ENFORCE_PROFILE_LICENSE, "Enforce Profile");     
        SystemNoCapacityFeatureID2NameMap.put(ODBC_VIEW_LICENSE, "ODBC View");         
        SystemNoCapacityFeatureID2NameMap.put(NETWORKED, "Networked");
        SystemNoCapacityFeatureID2NameMap.put(DISTRIBUTION_CENTER_LICENSE, "Distribution Center");
        SystemNoCapacityFeatureID2NameMap.put(RADIO_PROGRAMMING, "Radio Programming");
        SystemNoCapacityFeatureID2NameMap.put(Radio_Authentication, "Radio Authentication");
        SystemNoCapacityFeatureID2NameMap.put(INDIVIDUAL_IDENTIFY_CONTROL_HEAD, "Individually Identified Control Heads");
    }
    
    public static Map<String,String> getSystemCapacityFeatureID2NameMap()
    {        
        return SystemCapacityFeatureID2NameMap;
    }
    
    public static Map<String,String> getSystemNoCapacityFeatureID2NameMap()
    {        
        return SystemNoCapacityFeatureID2NameMap;
    }
}
