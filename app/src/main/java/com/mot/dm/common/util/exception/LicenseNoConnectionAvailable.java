/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                   Copyright 2011 Motorola Solutions Inc.                     |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  cjh102

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

public class LicenseNoConnectionAvailable extends LicenseException
{
    private static final long serialVersionUID = -5041228980444244948L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public LicenseNoConnectionAvailable(String message)
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
    public LicenseNoConnectionAvailable(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
