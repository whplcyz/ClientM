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
CODED     BY:  cnij001    2008.04.15

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
15/05/08   cnij001               CCMPD01021880 Change of server ip-address
17/06/08   chad001               CCMPD01051225 Cleanup repository services
29/07/09   Kalyan Pushpala       CCMPD01237099 Updated the interface so that it
                                               can be used to get third party 
                                               services also
15/09/09   Kalyan Pushpala       CCMPD01261809 Copy right updated   
16/09/09   Jens Hansen           CCMPD01261809 Moved class to common package, so
                                               if can be used by both proxy and
                                               other tools.
23/09/09  Kalyan Pushpala       CCMPD01266263  resetServerAddress need service_del
                                               file as this must be called to set 
                                               IP address from proxy application 
                                               during initialization phase
------------------------------------------------------------------------------*/
package com.mot.dm.common.delegation;

import java.util.Properties;

/**
 * The service delegation
 * 
 * @version 1.00 28 April 2007
 * 
 * @author Kevin Fan
 * 
 */
public final class ServiceDelegation extends AbstractServiceDelegation
{
    private static ServiceDelegation self;

    private ServiceDelegation(String file_name, Properties properties)
    {
        super(file_name, properties);
    }

    public static synchronized ServiceDelegation getInstance(String serverAddress,
            String service_delegation_file)
    {
        if (self == null)
        {
            Properties properties = new Properties();
            properties.put("address", serverAddress.trim());
            self = new ServiceDelegation(service_delegation_file, properties);

            // set the service delegation reference to the wrapper
            ServiceDelegationWrapper.setServiceDelegation(self);
        }

        return self;
    }

    /**
     * The method resetServerAddress resets to server address. We need to create
     * a new instance of the delegation Singleton.
     * 
     * @param serverAddress
     * @return
     */
    public static synchronized void resetServerAddress(String serverAddress,
            String service_delegation_file)
    {
        self = null;
        getInstance(serverAddress, service_delegation_file);
    }
}
