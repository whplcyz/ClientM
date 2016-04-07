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
package com.mot.dm.common.util.log.impl;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.log.inf.ILoggerFactory;

/**
 * The LoggerController class.
 * 
 * @author Jerry zhou
 */
public final class LoggerController
{
    private LoggerController()
    {
        
    }
    private static ILoggerFactory logFactory = new Log4jLoggerFactory();

    public static void registerLoggerFactory(ILoggerFactory factory)
    {
        logFactory = factory != null ? factory : new Log4jLoggerFactory();
    }

    @SuppressWarnings("rawtypes")
    public static Logger createLogger(Class clazz)
    {
        return logFactory.createLogger(clazz);
    }

    public static Logger createDeviceImportLogger(String tag)
    {
        return logFactory.createDeviceImportLogger(tag);
    }

    public static void shutdown()
    {
        logFactory.shutdown();
    }
}
