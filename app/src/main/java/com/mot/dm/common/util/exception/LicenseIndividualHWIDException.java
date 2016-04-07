/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                    Copyright 2013 Motorola Solutions Inc.                    |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  tqfn38

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
27/11/13   tqfn38                CCMPD01834655 [iTM 6.2]	Individually Identified Control Heads
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

public class LicenseIndividualHWIDException extends LicenseException
{
    private static final long serialVersionUID = 4409297577425084496L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public LicenseIndividualHWIDException(String message)
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
    public LicenseIndividualHWIDException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
