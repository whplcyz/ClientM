/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2007-2012 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  Kalyan Pushpala    2009.08.24

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
24/08/09   Kalyan Pushpala       CCMPD01237099 Initial version for handling 
                                               Password/Roles changes exception
15/09/09   Kalyan Pushpala       CCMPD01261809 Localized the error strings
05/01/10   hgkj73                CCMPD01288783 support for Agency partitioning
20/Jan/10  hgkj73                CCMPD01304268 localized error message
04/Feb/10  hgkj73                CCMPD01308413 throws ItmIgnoreException so that
                                               subclasses wont handle the exception
                                               again.
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1
06/May/10  Michael Leismann      CCMPD01344504 Fixed issue with text formatting
                                               in dialog window
20/Oct/10  cjh102                CCMPD01399192 Making the exception handling
                                               more generic
08/Dev/10  ckfm01                CCMPD01448983 Handling of license Exceptions   
14/12/10   cjh102                CCMPD01453332 License expiry
14/01/11   cjh102                CCMPD01462097 Added more specific exception 
                                               handling 
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions  
24/02/12   Zhan XueFeng-A23126	 CCMPD01617126 Various License Manager Issues
20/04/12   Zhan XueFeng-A23126   CCMPD01643630 Wrong exception handling when loading license 
                                               with no signature        
26/Oct/12  Zhao Wei-wch378       CCMPD01713625 catch the RemoteConnectFailureException                                                                                  
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.security.access.AccessDeniedException;

import com.mot.dm.common.util.exception.AuthenticationRequiredException;
import com.mot.dm.common.util.exception.LicenseException;

/**
 * This class acts a interceptor and catches AuthenticationRequiredException and
 * displays the relevant message for the user
 */
public class ClientExceptionHandlingAdvice implements ThrowsAdvice
{
    private static Logger logger = Logger.getLogger("com.mot.dm");
    public void afterThrowing(RemoteAccessException ex) throws Throwable
    {
        logger.info("Throws Advice: RemoteAccessException Exception:", ex);
        ex.printStackTrace();
        throw ex;
    }

    public void afterThrowing(AuthenticationRequiredException ex) throws Throwable
    {
        final String exceptionMessage = ex.getMessage();

        logger.info("Throws Advice: Authentication Exception:", ex);
        ex.printStackTrace();
        
       throw ex;
    }

    public void afterThrowing(final LicenseException ex) throws Throwable
    {
        logger.info("Throws Advice: LicenseException Exception:", ex);
        ex.printStackTrace();
       throw  ex;
    }

    public void afterThrowing(AccessDeniedException ex) throws Throwable
    {
        throw  ex;
    }
}
