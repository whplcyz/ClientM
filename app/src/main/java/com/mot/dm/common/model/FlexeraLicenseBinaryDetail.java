/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  wch378 28/3/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/3/14    wch378                CCMPD01876846 develop flexera license
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FlexeraLicenseBinaryDetail extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 872753427107657785L;

    private String licenseBinaryDetailUuid;
    private String activationID;
    private String featureID;
    private String featureVersion;
    private Date expireDate;
    private boolean isPermanent;
    private String binaryUuid;
    private int availableCount;

    public String featureName;

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append(this.licenseBinaryDetailUuid)
                .append(this.activationID)
                .append(this.featureID)
                .append(this.featureVersion)
                .append(this.expireDate)
                .append(this.isPermanent)
                .append(this.binaryUuid)
                .append(this.availableCount);
        return sb.toString();
    }

    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (!(other instanceof FlexeraLicenseBinaryDetail))
        {
            return false;
        }
        FlexeraLicenseBinaryDetail rhs = (FlexeraLicenseBinaryDetail) other;

        return new EqualsBuilder().append(licenseBinaryDetailUuid, rhs.licenseBinaryDetailUuid)
                .append(activationID, rhs.activationID).append(featureID, rhs.featureID)
                .append(this.featureVersion, rhs.featureVersion)
                .append(this.expireDate, rhs.expireDate)
                .append(this.isPermanent, rhs.isPermanent)
                .append(binaryUuid, rhs.binaryUuid).append(availableCount, rhs.availableCount)
                .isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(licenseBinaryDetailUuid)
                .append(activationID).append(featureID).append(this.featureVersion)
                .append(this.expireDate)
                .append(this.isPermanent).append(binaryUuid).append(availableCount).hashCode();
    }

    public void setLicenseBinaryDetailUuid(String licenseBinaryDetailUuid)
    {
        this.licenseBinaryDetailUuid = licenseBinaryDetailUuid;
    }

    public String getLicenseBinaryDetailUuid()
    {
        return licenseBinaryDetailUuid;
    }

    public void setActivationID(String activationID)
    {
        this.activationID = activationID;
    }

    public String getActivationID()
    {
        return activationID;
    }

    public void setFeatureID(String featureID)
    {
        this.featureID = featureID;
    }

    public String getFeatureID()
    {
        return featureID;
    }

    public void setBinaryUuid(String binaryUuid)
    {
        this.binaryUuid = binaryUuid;
    }

    public String getBinaryUuid()
    {
        return binaryUuid;
    }

    public void setAvailableCount(int availableCount)
    {
        this.availableCount = availableCount;
    }

    public int getAvailableCount()
    {
        return availableCount;
    }

    public void setFeatureVersion(String featureVersion)
    {
        this.featureVersion = featureVersion;
    }

    public String getFeatureVersion()
    {
        return featureVersion;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setIsPermanent(boolean isPermanent)
    {
        this.isPermanent = isPermanent;
    }

    public boolean getIsPermanent()
    {
        return isPermanent;
    }
}
