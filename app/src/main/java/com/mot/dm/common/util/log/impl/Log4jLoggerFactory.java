/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                       Copyright 2007-2008 Motorola Inc.                      |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  iTM
 CODED     BY:  Jerry zhou, China, 2007

 ------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
29/Oct/08   Jens Hansen           CCMPD01113181 Added support for different 
                                                log4j property files
------------------------------------------------------------------------------*/

package com.mot.dm.common.util.log.impl;

import java.io.File;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mot.dm.common.util.log.inf.ILoggerFactory;

/**
 * The Factory class of log4j.
 * 
 * @author Jerry zhou
 */
public class Log4jLoggerFactory implements ILoggerFactory
{
    public Log4jLoggerFactory()
    {
        this(true);
    }

    // default configuration for log4j
    public Log4jLoggerFactory(boolean doBasicConfig)
    {
        super();
        if (doBasicConfig)
        {
            File logFolder = new File("logs");
            if (!logFolder.exists())
            {
                logFolder.mkdir();
            }

            // We have to check if we can get access to the client_log4j first.
            // If this is not the case, then we are probably running on the
            // server, hence we check if log4j is available
            URL url = this.getClass().getClassLoader().getResource("client_log4j.properties");
            if (url == null)
            {
                url = this.getClass().getClassLoader().getResource("log4j.properties");
            }
            PropertyConfigurator.configure(url);
        }
    }

    @SuppressWarnings("rawtypes")
    public Logger createLogger(Class clazz)
    {
        // return new Log4jLogger(clazz);
        if (clazz == null)
        {
            throw new IllegalArgumentException("the class is null");
        }
        else
        {
            return Logger.getLogger(clazz);
        }
    }

    public Logger createDeviceImportLogger(String tag)
    {
        return Logger.getLogger(tag);
    }

    public void shutdown()
    {
        LogManager.shutdown();
    }
}
