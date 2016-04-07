/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2010-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  cjh102

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
14/01/11   cjh102                CCMPD01462097 Added more specific exception 
                                               handling    
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions   
24/2/12	   Zhan XueFeng-A23126	 CCMPD01617126 Various License Manager Issues          
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

/**
 * This class is used to indicate that a license is deemed invalid while loading
 * and validating the license against the current system
 * 
 * @author cjh102
 * 
 */
public class LicenseInvalid extends LicenseException
{
    private static final long serialVersionUID = -4552216017214514055L;

    /**
     * the construction method with detail message
     * 
     * @param message
     *            the detail message
     */
    public LicenseInvalid(String message)
    {
        super(message);
    }
}
