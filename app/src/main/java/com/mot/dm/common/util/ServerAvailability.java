/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2007-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen 14/Jul/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
30/Jul/08  cjh102                CCMPD01067239 Added catch for exception to
                                               avoid infinite loop
03/Aug/08  chad001               CCMPD01076315 remove catch exception
20/Jan/09  chad001               CCMPD01125953 Fix KW finding 
11/May/09  chad001               CCMPD01205865 Fix customer issue for caching 
                                               server address
07/Aug/09  chad001               ccmpd01248932 Avoid using isReachable()     
14/01/11   cjh102                CCMPD01462097 Added method for system license                                          
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.mot.dm.common.service.VersionService;
import com.mot.dm.common.util.exception.ServiceException;

/**
 * This class is used for check the server availability. To make a fast check if
 * a server exists the pinghost method is used, afterwards the checkVersion is
 * used to make sure that the version between the client/proxy mathces that of
 * the server.
 * 
 * @author cjh102
 * 
 */
public class ServerAvailability
{

    private static boolean cacheFlagSet = false;

    /**
     * Empty constructor
     */
    public ServerAvailability()
    {
    }

    /**
     * "Ping" the host to establish is a host is present at the IP address and
     * port.
     * 
     * @param hostAddress
     *            The host address of the server including the port number
     * @return True if the host is present - otherwise false
     */
    public boolean pingHost(String hostAddress)
    {
        boolean result = false;
        int retries = 2;
        int retryInterval = 3000;
        String[] splitString = hostAddress.split(":");

        if (splitString.length < 2)
            return false;

        String ipAddress = new String(splitString[0]);
        String portStr = new String(splitString[1]);
        /*
         * Here we disable the ip address caching
         */
        if (!cacheFlagSet)
        {
            java.security.Security.setProperty("networkaddress.cache.ttl", "0");
            cacheFlagSet = true;
        }

        int port = 0;
        try
        {
            port = Integer.parseInt(portStr);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        Socket t = null;

        InetSocketAddress socketAddr = new InetSocketAddress(ipAddress, port);
        int retryCount = 0;
        boolean finished = false;
        while (finished == false)
        {
            try
            {
                if (retryCount > retries)
                {
                    finished = true;
                }
                t = new Socket();
                t.connect(socketAddr, retryInterval);
                if (t.isConnected() == true)
                {
                    result = true;
                    finished = true;
                }
                t.close();
            }
            catch (IOException e)
            {
                // Failed to connect a DataInputStream socket for ping.
                closeSocket(t);
                retryCount++;
            }
            catch (Exception e)
            {
                // An abnormal exception occurred, but we still want to update
                // the counter to make sure we don't end in an infinite loop.
                // Basically this should be logged to the error log, but this is
                // currently not possible as the client and the proxy uses
                // different means of logging.
                closeSocket(t);
                retryCount++;
            }
        } // end while
        return result;
    }

    /*
     * To make a generic close socket method to avoid duplicate code
     * 
     * @param t Socket
     */
    private void closeSocket(Socket t)
    {
        if (t != null)
        {
            try
            {
                t.close();
            }
            catch (Exception e)
            {
                // we can do nothing now unless we got a common logging
                // tool
            }
        }
    }

    /**
     * Checks the provided version against the server server
     * 
     * @param versionService
     *            The version service object
     * @param ID
     *            Can be one of the ID's specified in VersionNumber.java
     * @param versionNumber
     *            The version number specified in VersionNumber.java
     * @return True if the provided version is ok, otherwise false.
     */
    public boolean checkVersion(VersionService versionService, String ID, String versionNumber)
            throws ServiceException
    {
        return versionService.checkVersion(ID, versionNumber);
    }

    public boolean isSystemLicenseValid(VersionService versionService) throws ServiceException
    {
        return versionService.isSystemLicenseVaild();
    }
}
