/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2008-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen    Feb 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
31/03/08   cjh102                CCMPD01012838 Added methods for setting and
                                               getting SellableLicenses
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SellableFeaturesOnDevice extends BaseObject
{
    /**
     * For java.io.Serializable
     */
    private static final long serialVersionUID = 8649962183247989244L;

    private String deviceUuid;

    private String featureID;

    private Integer featureState;

    public String getDeviceUuid()
    {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid)
    {
        this.deviceUuid = deviceUuid;
    }

    public Integer getFeatureState()
    {
        return featureState;
    }

    public void setFeatureState(Integer featureState)
    {
        this.featureState = featureState;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        
        if ((other == null))
            return false;
        
        if (!(other instanceof SellableFeaturesOnDevice))
            return false;

        return EqualsBuilder.reflectionEquals(this, other);
    }

    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setFeatureID(String featureID)
    {
        this.featureID = featureID;
    }

    public String getFeatureID()
    {
        return featureID;
    }

}
