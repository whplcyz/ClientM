/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                         Copyright 2010 Motorola Inc.                         |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  cjh102  Oct 2010

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Oct/10  cjh102                CCMPD01399192 Initial Version
------------------------------------------------------------------------------*/
package com.mot.dm.client.delegation;

import com.mot.dm.common.util.AuthCommonsHttpInvokerRequestExecutor;
import com.mot.dm.common.util.VersionNumber;

public class ClientAuthCommonsHttpInvokerRequestExecutor extends
        AuthCommonsHttpInvokerRequestExecutor
{
    /**
     * All classes extending this must implement this method to return the
     * appropriate ID as specified in the VersionNumber class
     * 
     * @return
     */
    protected String getClientID()
    {
        return VersionNumber.CLIENT_ID;
    }
}
