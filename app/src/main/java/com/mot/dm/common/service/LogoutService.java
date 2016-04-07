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
CODED     BY: HGKJ73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
12/08/09   Kalyan Pushpala       CCMPD01237099 Initial version
15/09/09   Kalyan Pushpala       CCMPD01261809 Added missing Comments 
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

/**
 * 
 * This interface is used to inform the server during logout of user
 */
public interface LogoutService extends Service
{

    /**
     * interface method for logout of user.
     * 
     * @param String
     *            username
     */
    public void logout(String username);

}
