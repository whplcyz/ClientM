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

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/06/08 chad001                CCMPD01051225 Compress codeplug. Cleanup 
                                              repository classes under common
                                              package
16/07/09 Kalyan Pushpala        CCMPD01237099 Updated the getService method as 
                                     		  interface is changed
16/09/09 Jens Hansen            CCMPD01261809 Special client implementation of 
                                              service delegation
16/09/09 Kalyan Pushpala        CCMPD01261809 Refactor so that a custom Service
                                              deleg file can be loaded which is
                                              used by client side tool say import
------------------------------------------------------------------------------*/

package com.mot.dm.client.delegation;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mot.dm.common.delegation.AbstractServiceDelegation;
import com.mot.dm.common.delegation.ServiceDelegationWrapper;
import com.mot.dm.R;

public final class ServiceDelegation extends AbstractServiceDelegation
{

    private static Logger fgLogger = Logger.getLogger("com.mot.dm");
    private static ServiceDelegation self;

    // This tag is to identify whether to load the service again.
    private static boolean isLoadAgain = false;

    private static String serviceDlegationFile = "service_delegation.xml";

    private ServiceDelegation(String file_name, Properties properties)
    {
        super(file_name, properties);

    }

    public static void setServiceDlegationFile(String delegationFile)
    {
        serviceDlegationFile = delegationFile;
    }

    public static ServiceDelegation getInstance()
    {
        try
        {
            if (isLoadAgain || self == null)
            {
                self = new ServiceDelegation(serviceDlegationFile, ServerIpAccessor.getSelf()
                        .getProperties());

                // set the service delegation reference to the wrapper
                ServiceDelegationWrapper.setServiceDelegation(self);
            }
        }
        catch (IOException e)
        {
            fgLogger.fatal("Could not get server IP from properties", e);
        }

        return self;
    }

    public static void setIsLoadAgain(boolean tagForLoad)
    {
        isLoadAgain = tagForLoad;
    }

    public static boolean getIsLoadAgain()
    {
        return isLoadAgain;
    }
}
