/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2011 Motorola Solutions Inc.                       |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  jnss008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
04/07/11   Sigurdur Jonsson      CCMPD01525355 PandaCps integration, Backup and
                                               Restore
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

/**
 * This exception is thrown when accessing the Panda CPS interface returns an
 * error code
 * 
 * @author jnss008
 * 
 */
public class PandaCpsException extends CheckedException
{
    private static final long serialVersionUID = -4552216017214514053L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public PandaCpsException(String message)
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
    public PandaCpsException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
