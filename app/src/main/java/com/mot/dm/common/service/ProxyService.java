/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kevin Xia   April 2007

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>             <CR number>   <description>
01/Apr/08  Tommy Thomadsen    CCMPD01013871 Cleanup database.
09/May/08 Sigurdur Jonsson    CCMPD01032333 No warning is provided if user deletes
                                            a proxy for which there are offline jobs.
15/09/09   Kalyan Pushpala    CCMPD01237099 Added getAnonymousSessionKey to support 
                                            anonymous authentication
02/Dec/09  Michael Leismann   CCMPD01287031 Jobs on memory stick feature:
                                            Removed job owner
12/Apr/10  hgkj73             CCMPD01325308 Added annotations
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Proxy;
import com.mot.dm.common.util.exception.ServiceException;

public interface ProxyService extends Service
{
    /**
     * Get a proxy object by the proxy identifier.
     * 
     * @param proxyUuid
     * @return Proxy
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_PROXIES')")
    public Proxy getProxy(String proxyUuid) throws ServiceException;

    /**
     * add a new proxy.
     * 
     * @param proxy
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_PROXIES')")
    public Proxy addProxy(Proxy proxy) throws ServiceException;

    /**
     * This method is to modify a proxy.
     * 
     * @param proxy
     * @throws ServiceException
     */
    // TODO: Cannot secure it now as proxy uses this
    public void modifyProxy(Proxy proxy) throws ServiceException;

    /**
     * remove a proxy by their identifier.
     * 
     * @param proxyUuid
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_PROXIES')")
    public void removeProxy(String proxyUuid) throws ServiceException;

    /**
     * Get all of the proxies.
     * 
     * @return List<Proxy>
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_PROXIES')")
    public List<Proxy> getAllProxy() throws ServiceException;

    /**
     * Get a proxy object by the proxy name.
     * 
     * @param proxyName
     * @return Proxy
     * @throws ServiceException
     */
    public Proxy getProxyByName(String proxyName) throws ServiceException;

    /**
     * Get Anonymous Session password for proxy.
     * 
     * @param proxyUuid
     * @return String - session password
     * @throws ServiceException
     */
    public String getAnonymousSessionKey(String proxyUuid) throws ServiceException;

}
