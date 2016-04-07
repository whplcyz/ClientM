/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                       |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  tqfn38 1/8/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
1/8/14     tqfn38                CCMPD01913521 [iTM 7.0] Flexera license feature development. 

------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PendingLicenseFeatureOnDevice extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 4091152426201052733L;
    private String pendingLicenseFeatureUuid;
    private String deviceID;
    private String featureID;
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o)
    {
        PendingLicenseFeatureOnDevice plfoj = (PendingLicenseFeatureOnDevice) o;
        return EqualsBuilder.reflectionEquals(this, plfoj);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public void setFeatureID(String featureID)
    {
        this.featureID = featureID;
    }

    public String getFeatureID()
    {
        return featureID;
    }

    public void setPendingLicenseFeatureUuid(String pendingLicenseFeatureUuid)
    {
        this.pendingLicenseFeatureUuid = pendingLicenseFeatureUuid;
    }

    public String getPendingLicenseFeatureUuid()
    {
        return pendingLicenseFeatureUuid;
    }

    public void setDeviceID(String deviceID)
    {
        this.deviceID = deviceID;
    }

    public String getDeviceID()
    {
        return deviceID;
    }

}
