/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
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
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

/**
 * @Version 1.0 2007 April 12
 * @author w23021(Jason)
 */
public class ServiceException extends CheckedException
{

    /**
     * 
     */
    private static final long serialVersionUID = -4401430661976863086L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public ServiceException(String message)
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
    public ServiceException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
