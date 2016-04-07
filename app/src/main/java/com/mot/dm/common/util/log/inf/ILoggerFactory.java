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
CODED     BY:  Jerry Zhou

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Jan/10  Michael Leismann      CCMPD01301894 Updated header
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.log.inf;

import org.apache.log4j.Logger;

/**
 * The ILoggerFactory interface.
 *
 * @author Jerry Zhou
 */
public interface ILoggerFactory
{
	@SuppressWarnings("rawtypes")
    Logger createLogger(Class clazz);
	
	public Logger createDeviceImportLogger(String tag);

    void shutdown();   
}
