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
package com.mot.dm.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Version 1.0 2007 Jun 12
 * @author w23021(Jason)
 * 
 *         This class is used to get the ip address of server in system
 *         environment.
 */
public final class EnvironmentAccessor
{

    private static EnvironmentAccessor self;

    private Properties returnProperties;

    private EnvironmentAccessor() throws IOException
    {

        returnProperties = new Properties();

        String address = System.getenv("DM_SERVER_ADDRESS");

        if (address == null)
            throw new IOException("you does not add the address in system environment");

        returnProperties.put("address", address.trim());
    }

    /**
     * Sington class
     * 
     * @return EnvironmentAccessor return the instance of self.
     * @throws IOException
     */
    public static EnvironmentAccessor getSelf() throws IOException
    {

        if (self == null)
            self = new EnvironmentAccessor();

        return self;
    }

    public Properties getProperties() throws IOException
    {

        return returnProperties;

    }

}
