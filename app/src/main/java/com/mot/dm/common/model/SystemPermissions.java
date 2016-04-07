/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2010 Motorola Inc.                           |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Mar/10  hgkj73                CCMPD01317830 iTM 3.0 System permission 
                                               handling   
20/09/10   chad001               ccmpd01393090 4.0 licensing feature                                                   
21/Sep/10  chad001               ccmpd01394081 Add policies manager                                               
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * container system permissions in iTM
 * 
 * @author hgkj73
 * 
 */
public class SystemPermissions extends ItmPermissions
{

    private static final long serialVersionUID = 1561532091693409429L;

    /**
     * Client Permissions
     */
    private boolean logonToClient = false;
    private boolean manageGroupsAndUsers = false;
    private boolean manageProxies = false;
    private boolean manageSoftware = false;
    private boolean manageLicenses = false;
    private boolean managePolicies = false;
    private boolean importPermission = false;

    /**
     * Proxy permissions
     */
    private boolean logonToProxy = false;
    private boolean manageOfflineJobs = false;
    private boolean configureProxy = false;

    /**
     * Server permissions
     */
    private boolean allowStatusView = false;
    private boolean preventClearAccess = false;

    /**
     * @return the logonToClient
     */
    public boolean getLogonToClient()
    {
        return logonToClient;
    }

    /**
     * @param logonToClient
     *            the logonToClient to set
     */
    public void setLogonToClient(boolean logonToClient)
    {
        this.logonToClient = logonToClient;
    }

    /**
     * @return the manageGroupsAndUsers
     */
    public boolean getManageGroupsAndUsers()
    {
        return manageGroupsAndUsers;
    }

    /**
     * @param manageGroupsAndUsers
     *            the manageGroupsAndUsers to set
     */
    public void setManageGroupsAndUsers(boolean manageGroupsAndUsers)
    {
        this.manageGroupsAndUsers = manageGroupsAndUsers;
    }

    /**
     * @return the manageProxies
     */
    public boolean getManageProxies()
    {
        return manageProxies;
    }

    /**
     * @param manageProxies
     *            the manageProxies to set
     */
    public void setManageProxies(boolean manageProxies)
    {
        this.manageProxies = manageProxies;
    }

    /**
     * @return the manageSoftware
     */
    public boolean getManageSoftware()
    {
        return manageSoftware;
    }

    /**
     * @param manageSoftware
     *            the manageSoftware to set
     */
    public void setManageSoftware(boolean manageSoftware)
    {
        this.manageSoftware = manageSoftware;
    }

    /**
     * @return the importPermission
     */
    public boolean getImportPermission()
    {
        return importPermission;
    }

    /**
     * @param importPermission
     *            the importPermission to set
     */
    public void setImportPermission(boolean importPermission)
    {
        this.importPermission = importPermission;
    }

    /**
     * @return the logonToProxy
     */
    public boolean getLogonToProxy()
    {
        return logonToProxy;
    }

    /**
     * @param logonToProxy
     *            the logonToProxy to set
     */
    public void setLogonToProxy(boolean logonToProxy)
    {
        this.logonToProxy = logonToProxy;
    }

    /**
     * @return the manageOfflineJobs
     */
    public boolean getManageOfflineJobs()
    {
        return manageOfflineJobs;
    }

    /**
     * @param manageOfflineJobs
     *            the manageOfflineJobs to set
     */
    public void setManageOfflineJobs(boolean manageOfflineJobs)
    {
        this.manageOfflineJobs = manageOfflineJobs;
    }

    /**
     * @return the configureProxy
     */
    public boolean getConfigureProxy()
    {
        return configureProxy;
    }

    /**
     * @param configureProxy
     *            the configureProxy to set
     */
    public void setConfigureProxy(boolean configureProxy)
    {
        this.configureProxy = configureProxy;
    }

    /**
     * @return the allowStatusView
     */
    public boolean getAllowStatusView()
    {
        return allowStatusView;
    }

    /**
     * @param allowStatusView
     *            the allowStatusView to set
     */
    public void setAllowStatusView(boolean allowStatusView)
    {
        this.allowStatusView = allowStatusView;
    }

    /**
     * @return the preventClearAccess
     */
    public boolean getPreventClearAccess()
    {
        return preventClearAccess;
    }

    /**
     * @param preventClearAccess
     *            the preventClearAccess to set
     */
    public void setPreventClearAccess(boolean preventClearAccess)
    {
        this.preventClearAccess = preventClearAccess;
    }

    /**
     * getter
     * 
     * @return boolean
     */
    public boolean getManageLicenses()
    {
        return manageLicenses;
    }

    /**
     * setter
     * 
     * @param manageLicenses
     */
    public void setManageLicenses(boolean manageLicenses)
    {
        this.manageLicenses = manageLicenses;
    }

    public boolean getManagePolicies()
    {
        return managePolicies;
    }

    public void setManagePolicies(boolean managePolicies)
    {
        this.managePolicies = managePolicies;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 11;
        result = prime * result + (allowStatusView ? 1231 : 1237);
        result = prime * result + (configureProxy ? 1231 : 1237);
        result = prime * result + (importPermission ? 1231 : 1237);
        result = prime * result + (logonToClient ? 1231 : 1237);
        result = prime * result + (logonToProxy ? 1231 : 1237);
        result = prime * result + (manageGroupsAndUsers ? 1231 : 1237);
        result = prime * result + (manageOfflineJobs ? 1231 : 1237);
        result = prime * result + (manageProxies ? 1231 : 1237);
        result = prime * result + (manageSoftware ? 1231 : 1237);
        result = prime * result + (preventClearAccess ? 1231 : 1237);
        result = prime * result + (manageLicenses ? 1231 : 1237);
        result = prime * result + (managePolicies ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        
        if (this == obj)
            return true;
        
        if (getClass() != obj.getClass())
            return false;
        
        final SystemPermissions other = (SystemPermissions) obj;
        if (allowStatusView != other.allowStatusView)
            return false;
        if (configureProxy != other.configureProxy)
            return false;
        if (importPermission != other.importPermission)
            return false;
        if (logonToClient != other.logonToClient)
            return false;
        if (logonToProxy != other.logonToProxy)
            return false;
        if (manageGroupsAndUsers != other.manageGroupsAndUsers)
            return false;
        if (manageOfflineJobs != other.manageOfflineJobs)
            return false;
        if (manageProxies != other.manageProxies)
            return false;
        if (manageSoftware != other.manageSoftware)
            return false;
        if (preventClearAccess != other.preventClearAccess)
            return false;
        if (manageLicenses != other.manageLicenses)
            return false;
        if (managePolicies != other.managePolicies)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("logonToClient", logonToClient)
                .append("manageGroupsAndUsers", manageGroupsAndUsers)
                .append("manageProxies", manageProxies).append("manageSoftware", manageSoftware)
                .append("importPermission", importPermission).append("logonToProxy", logonToProxy)
                .append("manageOfflineJobs", manageOfflineJobs)
                .append("configureProxy", configureProxy)
                .append("allowStatusView", allowStatusView)
                .append("preventClearAccess", preventClearAccess)
                .append("manageLicenses", manageLicenses).append("managePolicies", managePolicies);

        return sb.toString();

    }

    @Override
    public boolean isEmpty()
    {
        if (allowStatusView || configureProxy || importPermission || logonToClient || logonToProxy
                || manageGroupsAndUsers || manageOfflineJobs || manageProxies || manageSoftware
                || preventClearAccess || manageLicenses || managePolicies)
        {
            return false;
        }
        return true;
    }
}
