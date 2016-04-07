/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                   Copyright 2010 - 2011 Motorola Solutions Inc.              |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  w23021(Jason)

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Jan/10  Michael Leismann      CCMPD01301894 Updated header
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

/**
 * Denotes that that user attempts to circumvent a given license during
 * run-time, for example connecting more clients then allowed, or creating more
 * proxies than allowed. Also used in cases where the license file cannot be
 * found or parsed. In other words this exception is used to indicate these
 * cases, which should not happen.
 * 
 * @author cjh102
 * 
 */
public class LicenseException extends RuntimeException
{
    private static final long serialVersionUID = -5293139791505819810L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public LicenseException(String message)
    {
        super(message);
    }

    /**
     * the construction method with detail message and original exception
     * 
     * @param message
     *            the detail message
     * @param throwable
     *            the original exception
     */
    public LicenseException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
