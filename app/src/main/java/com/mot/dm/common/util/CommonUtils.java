/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2008-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Michael Leismann 25/Jun/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/Jun/08  Michael Leismann      CCMPD01055990 Added method that can convert a
                                               java.util.Date to the xs:dateTime
                                               format
01/07/08   Kim Mortensen         CCMPD01058454 Adding harddisk size check util.
19/Aug/08  Michael Leismann      CCMPD01082471 Removed OS check as "Windows
                                               2003 " is missing and all OSs are
                                               using cmd.exe
20/Jan/09  Hai Dong              CCMPD01125953 Fix KW finding
22/Apr/09  Michael Leismann      CCMPD01189094 Added method used by the Copy
                                               Wizard Groups and Special
                                               Parameters tasks
30/07/09   Kalyan Pushpala       CCMPD01237099 Common Login utility is made
                                               Which can be used by all clients 
                                               to authenticate on server
15/09/09   Kalyan Pushpala       CCMPD01261809 Login and Logout Moved to SecurityUtils
                                               class so that CommonUtils can be 
                                               Used from Tools where we don't need to
                                               access services
06/Jan/10  Michael Leismann      CCMPD01295648 Updated getFreeDiskSpace method
                                               so that it can also handle a
                                               directory that contains spaces
18/Jan/10  Michael Leismann      CCMPD01302843 Jobs on memory stick feature:
                                               Added checksum
09/Mar/10  Sigurdur Jonsson      CCMPD01320819 Rescheduling Expired and Scheduled
                                               jobs.
23/Mar/10  Michael Leismann      CCMPD01316559 Added logging statements
                                               Updated moveFile method
12/Apr/10  Michael Leismann      CCMPD01328003 Updated getFreeDiskSpace to be
                                               able to handle all kinds of
                                               thousand separators
01/10/10   Kim Mortensen         CCMPD01398027 Adding method for getting 
                                               sorted MAC Address.
11/11/10   Kim Mortensen         CCMPD01409592 Changing mac address getter 
                                               to more generic method.
08/12/10   Kim Mortesnen         CCMPD01448983 MacAddress Handling
27/12/10   cvkh36                CCMPD01453343 iTM4.0 performance issue
11/Jan/11  cjh102                CCMPD01461502 Moved methods to OsUtils, so
                                               they can become part of the itm
                                               Updater
22/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
                                               Moved method to ServerUtil class
10/Jun/11  Michael Leismann      CCMPD01516767 iTM 5.0: Removed method
17/Jun/11  Michael Leismann      CCMPD01516805 iTM 5.0: Removed createChecksum
                                               method as there exists a separate 
                                               class (with different algorithm)
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


/**
 * This class should contain common methods
 * 
 * @author cml066
 * 
 */
public final class CommonUtils
{

    private CommonUtils()
    {

    }

    // Logger
    // private static Logger c_fgLogger = Logger.getLogger("com.mot.dm");
    // private static String CLASS_NAME = "CommonUtils";

    /**
     * Converts a "java.util.Date" to the "xs:dateTime" format:
     * YYYY-MM-DDThh:mm:ss
     * 
     * @param p_date
     * @return null if p_date is null, otherwise the Date in the "xs:dateTime"
     *         format
     */
    public static String dateToXmlDateAndTimeConverter(Date p_date)
    {
        // Return null if p_date is null
        if (p_date == null)
        {
            return null;
        }

        // Otherwise convert the Date to the "xs:dateTime" format
        Calendar cal = Calendar.getInstance();
        cal.setTime(p_date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        String xmlDate = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "")
                + day + "T" + (hour < 10 ? "0" : "") + hour + ":" + (min < 10 ? "0" : "") + min
                + ":" + (sec < 10 ? "0" : "") + sec;

        return xmlDate;
    }

    /**
     * Converts a 0 to "KeepAsIs", a 1 to "CopyFromTemplate" or a 2 to "Specify"
     * If not 0, 1 or 2 then the parameter value string is returned
     * 
     * @param p_value
     * @return
     */
    public static String convertParameterValueToText(String p_value)
    {
        if (p_value.equals("" + CommonConstants.KEEP_VALUE))
        {
            return CommonConstants.KEEP_STRING;
        }
        else if (p_value.equals("" + CommonConstants.TEMPLATE_VALUE))
        {
            return CommonConstants.TEMPLATE_STRING;
        }
        else if (p_value.equals("" + CommonConstants.SPECIFY_VALUE))
        {
            return CommonConstants.SPECIFY_STRING;
        }
        else
        {
            return p_value;
        }
    }

    /**
     * Converts "KeepAsIs" to 0, "CopyFromTemplate" to 1 or "Specify" to 2 If
     * not one of the texts then -99 is returned
     * 
     * @param p_text
     * @return
     */
    public static int convertParameterTexttoValue(String p_text)
    {
        if (p_text.equals(CommonConstants.KEEP_STRING))
        {
            return CommonConstants.KEEP_VALUE;
        }
        else if (p_text.equals(CommonConstants.TEMPLATE_STRING))
        {
            return CommonConstants.TEMPLATE_VALUE;
        }
        else if (p_text.equals(CommonConstants.SPECIFY_STRING))
        {
            return CommonConstants.SPECIFY_VALUE;
        }
        else
        {
            return -99;
        }
    }

    /*
     * Converts password usage to protection status.
     * Such as CommonConstants.NO_PROTECTION and CommonConstants.NO_PROTECTION ==> Unprotected
     * 
     * @param getDevicePwdUsage
     * */
    public static String convertPwdUsageToProtectionStrID(String getDevicePwdUsage)
    {
        String unknownStatus = "General.ProtectionStatusUnknown";
        String protectedStastus = "General.ProtectionStatusProtected";
        String unprotectedStatus = "General.ProtectionStatusUnprotected";

        if (null == getDevicePwdUsage || getDevicePwdUsage.isEmpty()
                || getDevicePwdUsage.equals(CommonConstants.UNKNOWN_PROTECION_STATUS))
        {
            return unknownStatus;
        }
        else if (getDevicePwdUsage.equals(CommonConstants.NO_PROTECTION)
                || getDevicePwdUsage.equals(CommonConstants.PROTECT_CODEPLUG_ONLY))
        {
            return unprotectedStatus;
        }
        else if (getDevicePwdUsage.equals(CommonConstants.PROTECT_RADIO_ONLY)
                || getDevicePwdUsage.equals(CommonConstants.PROTECT_RADIO_CODEPLUG))
        {
            return protectedStastus;
        }
        else
        {
            return unknownStatus;
        }
    }
    
    public static String ByteArray2HexString(byte[] bytes)
    {
        if(bytes == null)
        {
            return null;
        }
        
        StringBuilder builder = new StringBuilder();
        for(byte bValue: bytes)
        {
            builder.append(String.format("%02X", bValue));
        }
        
        return builder.toString();
    }
    

    public static byte[] HexString2ByteArray(String byteString)
    {
        if(null == byteString)
        {
            return null;
        }
        
        byte[] bytes = new byte[byteString.length() / 2];
        
        
        for(int i = 0; i < byteString.length() / 2; i++)
        {
            String sb = byteString.substring(i*2, i*2+2);
            try
            {
                bytes[i] = (byte) (Integer.parseInt(sb, 16) & 0x00FF); 
            }
            catch(Exception ex)
            {
                continue;
            }
            
        }
        
        return bytes;
    }
    
    public static void ConverHwInfo(String hwConfigInfo, HashMap<Integer, byte[]> hwConfigMap)
    {
        if (hwConfigInfo == null || hwConfigInfo.trim().equals(""))
        {
            return;
        }

        // Get the column order string and split it into an array
        String[] hwConfigInfoArr = hwConfigInfo.split("@");
        if (hwConfigInfoArr == null || hwConfigInfoArr.length <= 1)
        {
            return;
        }

        for (int i = 1; i < hwConfigInfoArr.length; i++)
        {
            String[] hwConfigIdAndValue = hwConfigInfoArr[i].split("_");
            if (hwConfigIdAndValue == null)
            {
                continue;
            }

            if (hwConfigIdAndValue.length == 0 || hwConfigIdAndValue[0] == null) //key cannot be empty
            {
                continue;
            }

            
            if (hwConfigIdAndValue.length > 1 && null != hwConfigIdAndValue[1] && hwConfigIdAndValue[1].length() > 0)
            {
                hwConfigMap.put(Integer.parseInt(hwConfigIdAndValue[0]),
                        CommonUtils.HexString2ByteArray(hwConfigIdAndValue[1]));
            }
            else
            {
                hwConfigMap.put(Integer.parseInt(hwConfigIdAndValue[0]), new byte[] {});
            }
        }
    }
}
