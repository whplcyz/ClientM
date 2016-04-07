/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2014 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kim Mortensen    17 Sep. 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/3/14    wch378               CCMPD01889533 develop flexera license.  
10/12/14   wch378                CCMPD01952388 adjust license expiration logic
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.log.impl.LoggerController;

public class DateTimeUtil
{
    private static Logger logger = LoggerController
    .createLogger(DateTimeUtil.class);
    
    
    public static long daysToExpiry(Date expireDate, Date currentDate)
    {
        long expireDays = 1 + (expireDate.getTime() - currentDate.getTime())
                / CommonConstants.ONEDAYMILLISECONDS; //negative number is not handled, expect to handle it in advance.
        return expireDays;
    }
    
    public static Date parseExpireDate(String expireDateString)
    {
        /*format is 19-jun-2014 from CLL*/
        try
        {
            if (expireDateString == null || expireDateString.isEmpty())
            {
                return new Date(0); 
            }
            
            if(expireDateString.toLowerCase().contains("permanent"))
            {
                return CommonConstants.FLEXERA_LICENSE_MAX_DATE;
            }
                              
            expireDateString = expireDateString.replace(CommonConstants.FLEXERA_LICENSE_EXPIRE_DATE_PREFIX, "").trim();  
            
            Date date = new SimpleDateFormat(CommonConstants.FLEXERA_LICENSE_DATE_FORMAT,Locale.ENGLISH).parse(expireDateString);
            date.setTime(date.getTime() + CommonConstants.ONEDAYMILLISECONDS -1 );
            return date; 
        }
        catch (ParseException e)
        {
            try
            {
            	Date date = new SimpleDateFormat(CommonConstants.FLEXERA_LICENSE_DATE_FORMAT_EX,Locale.ENGLISH).parse(expireDateString);
                date.setTime(date.getTime() + CommonConstants.ONEDAYMILLISECONDS -1 );
                return date;
            }
            catch (ParseException e1)
            {
                logger.error("fail to parse expireDateString: " + expireDateString + " root cause is " + e1.getMessage());
                return new Date(0);
            }
        }
    }   
}
