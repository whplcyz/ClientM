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
CODED     BY:  w23021(Jason) 1.0 2007 May 12

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
16/July/09 Kalyan Pushpala       CCMPD01237099 Updated the interface so that it
                              				   can be used to get third party 
                              				   services also
------------------------------------------------------------------------------*/

package com.mot.dm.common.delegation;


/**
 * 
 * This interface is used for repostory to get Service.
 */
public interface IServiceDelegation
{

    public Object getService(String servicename);
}
