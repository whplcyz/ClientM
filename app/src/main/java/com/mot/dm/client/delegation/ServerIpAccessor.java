/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2013 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  cml066    September 11, 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Sep/08  Michael Leismann      CCMPD01087804 Code clean-up
                                               Moved nodeName to Constants class
21/Mar/13  wch378                CCMPD01756334 detach ip and port on client                                              
24/Jan/13  Wch378-ZhaoWei        CCMPD01699534 BB-Demo  
1/Aug/13    wch378          CCMPD01799762 trace policy enhancement                                             
------------------------------------------------------------------------------*/
package com.mot.dm.client.delegation;

import java.io.IOException;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.mot.dm.client.util.ClientUtil;
import com.mot.dm.client.util.Constants;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @Version 1.0 2007 July 6
 * @author q19358 Johnny Fu
 */
public final class ServerIpAccessor
{

    private static ServerIpAccessor self;
    private Properties returnProperties;
    private static Logger fgLogger = LoggerController.createLogger(ServerIpAccessor.class);
    public static final String serverIpKey = "SERVER_IP";
    public static String serverAddressFromParameter = "192.168.31.196:8080";

	private ServerIpAccessor() throws IOException {
		String address = serverAddressFromParameter;
		returnProperties = new Properties();
		returnProperties.put("address", address.toString().trim());
	}

    /**
     * Sington class
     *
     * @return EnvironmentAccessor return the instance of self.
     * @throws IOException
     */
    public static ServerIpAccessor getSelf() throws IOException
    {

        if (ServiceDelegation.getIsLoadAgain())
        {
            self = new ServerIpAccessor();
        }
        else if (self == null)
        {
            self = new ServerIpAccessor();
        }
        return self;
    }

    public Properties getProperties() throws IOException
    {
        return returnProperties;
    }   
}
