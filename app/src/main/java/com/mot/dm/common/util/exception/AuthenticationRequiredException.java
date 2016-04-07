/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2009 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM  
CODED     BY:  Kalyan    2009.08.25

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/08/09   Kalyan Pushpala       CCMPD01237099 Initial Version
------------------------------------------------------------------------------*/

package com.mot.dm.common.util.exception;

/**
 * It is a unchecked exception all our Server Service methods can throw only
 * ServiceException which we cannot extend in this case as we have
 * ServiceExceptions handled across the code in every file. So without changing
 * the Service method declarations we used RuntimeException as of now. May need
 * refactoring.
 * 
 * @Version 1.0 2009 Aug 25
 * @author hgkj73(Kalyan)
 */
public class AuthenticationRequiredException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = -4401330896976863086L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public AuthenticationRequiredException(String message)
    {
        super(message);
    }

    public AuthenticationRequiredException(String message,RuntimeException e)
    {
        super(message,e);
    }
}
