/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2008 - 2011 Motorola Inc.                    |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen    July 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
24/Sep/08  Jens Hansen           CCMPD01095091 Added getLangCountrySetting()
24/Sep/08  Sigurdur Jonsson      CCMPD01113181 Added getGlobalStringMap()
11/Dec/08  Michael Leismann      CCMPD01138296 Removed language functionality
                                               as part of moving the operations
                                               log to server
23/Jun/10  Sigurdur Jonsson      CCMPD01357127 Automatic application update.
14/01/11   cjh102                CCMPD01462097 Added method for system license
16/Jun/11  Hai Dong              CCMPD01523523 Change version check for proxy 
                                               into version check for client
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import com.mot.dm.common.model.ApplicationInfo;
import com.mot.dm.common.util.exception.ServiceException;

/**
 * This service is used to check if the "clients" connecting to the server are
 * compatible.
 * 
 * @author cjh102
 * 
 */
public interface VersionService extends Service
{
    /**
     * The method is used to check for compatibility between a client/server and
     * a proxy/server
     * 
     * @param clientID
     *            Can be one of PROXY, SERVER, or CLIENT. Defined as static
     *            final variables in VersionNumber.java
     * @param versionNumber
     *            The version number of the calling party
     * @return True if the calling party are compatible with the server,
     *         otherwise false
     * @throws ServiceException
     */
    public boolean checkVersion(String clientID, String versionNumber) throws ServiceException;

    /**
     * The method is used to get information about the iTM client such as
     * version and SPU level.
     * 
     * @return ApplicationInfo object containing the application uuid, name,
     *         version and update level of the iTM Server.
     * @throws ServiceException
     */
    public ApplicationInfo getClientInfo() throws ServiceException;

    /**
     * The method is used to get information about the iTM Server such as
     * version and SPU level.
     * 
     * @return ApplicationInfo object containing the application uuid, name,
     *         version and update level of the iTM Server.
     * @throws ServiceException
     */
    public ApplicationInfo getServerInfo() throws ServiceException;

    /**
     * This method is used during start-up of the client, in which case we need
     * to know if the system license is valid or not. Outside the start-up of
     * the client, please use the methods available through the LicenseService
     * Return TRUE is the system license is valid, otherwise FALSE.
     * 
     * @return
     * @throws ServiceException
     */
    public boolean isSystemLicenseVaild() throws ServiceException;

}
