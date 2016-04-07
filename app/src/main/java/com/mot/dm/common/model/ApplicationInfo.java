/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2010-2012 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  Sigurdur Jonsson    

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
23/Jun/10  Sigurdur Jonsson      CCMPD01357127 Automatic application update.
29/Jul/10  Kalyan Pushpala       CCMPD01357138 corrected the version comment.
22/08/12   a23126                CCMPD01690055 Prevent RPK loading while server is running in compatibility model
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ApplicationInfo extends BaseObject
{
    private static final long serialVersionUID = 6374211903648834053L;

    private String applicationUuid;
    private String applicationName; // iTMClient, iTMProxy, iTMServer
    private String version; // Major and Minor versions
    private int updatePackage; // Sequence starts with 0 = no update
    
    // server compatibility mode
    private boolean compatibilityEnabled = false;

    /**
     * Getter for the applicationUuid
     * 
     * @return applicationUuid
     */
    public String getApplicationUuid()
    {
        return applicationUuid;
    }

    /**
     * Setter for the applicationId
     * 
     * @param applicationId
     */
    public void setApplicationUuid(String applicationUuid)
    {
        this.applicationUuid = applicationUuid;
    }

    /**
     * Getter for the applicationName
     * 
     * @return applicationName
     */
    public String getApplicationName()
    {
        return applicationName;
    }

    /**
     * Setter for the applicationName
     * 
     * @param applicationName
     */
    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    /**
     * Getter for the version
     * 
     * @return version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * Setter for the version
     * 
     * @param version
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * Getter for the updatePackage
     * 
     * @return updatePackage
     */
    public int getUpdatePackage()
    {
        return updatePackage;
    }

    /**
     * Setter for the uppdatePackage
     * 
     * @param updatePackage
     */
    public void setUpdatePackage(int updatePackage)
    {
        this.updatePackage = updatePackage;
    }
    
    /**
     * Set the compatibility mode to enabled.
     */
    public void setCompatibilityModeEnabled()
    {
        this.compatibilityEnabled = true;
    }
    
    /**
     * Set the compatibility mode to disabled.
     */
    public void setCompatibilityModeDisabled()
    {
        this.compatibilityEnabled = false;
    }
    
    /**
     * This property is only applicable for server application.
     * 
     * @return
     * true - the compatibility mode is enabled
     * false - the compatibility mode is disabled
     */
    public boolean getCompatibiliytModel()
    {
        return this.compatibilityEnabled;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(applicationName).append(version).append(updatePackage)
                .hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof ApplicationInfo))
            return false;

        ApplicationInfo rhs = (ApplicationInfo) obj;
        return new EqualsBuilder().append(applicationName, rhs.getApplicationName())
                .append(version, rhs.getVersion()).append(updatePackage, rhs.getUpdatePackage())
                .isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("applicationName", this.applicationName).append("version", this.version)
                .append("updatePackage", this.updatePackage);
        return sb.toString();
    }
}
