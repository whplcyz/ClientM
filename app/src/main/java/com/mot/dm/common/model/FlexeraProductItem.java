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
9/Sep/14   wch378                CCMPD01928626 FTR follow up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FlexeraProductItem extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 5823733416967126687L;

    private String lineitemUuid;
    private String activationID;
    private Timestamp expireDate;
    private String durationTime;
    private boolean permanent;
    private boolean trailLicense;
    private String flexeraFeatureID;
    private String flexeraProductName;
    private String flexeraProductVersion;
    private int availableCount;
    private int totalCount;
    private String sourceUuid;
        
    private boolean duplicated;
    private Timestamp iTMServerDate;
    private boolean activatedbyOtherHost = false;
    private boolean changed = false;

    public String getLineitemUuid()
    {
        return lineitemUuid;
    }

    public void setLineitemUuid(String lineitemUuid)
    {
        this.lineitemUuid = lineitemUuid;
    }

    public String getActivationID()
    {
        return activationID;
    }

    public void setActivationID(String activationID)
    {
        this.activationID = activationID;
    }

    public Timestamp getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate)
    {
        this.expireDate = expireDate;
    }

    public boolean getPermanent()
    {
        return permanent;
    }

    public void setPermanent(boolean permanent)
    {
        this.permanent = permanent;
    }

    public int getAvailableCount()
    {
        return availableCount;
    }

    public void setAvailableCount(int availableCount)
    {
        this.availableCount = availableCount;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getSourceUuid()
    {
        return sourceUuid;
    }

    public void setSourceUuid(String sourceUuid)
    {
        this.sourceUuid = sourceUuid;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
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
        if (!(other instanceof FlexeraProductItem))
        {
            return false;
        }
        FlexeraProductItem rhs = (FlexeraProductItem) other;

        return EqualsBuilder.reflectionEquals(this, rhs);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }
   
    public void setFlexeraProductName(String flexeraProductName)
    {
        this.flexeraProductName = flexeraProductName;
    }

    public String getFlexeraProductName()
    {
        return flexeraProductName;
    }

    public void setFlexeraProductVersion(String flexeraProductVersion)
    {
        this.flexeraProductVersion = flexeraProductVersion;
    }

    public String getFlexeraProductVersion()
    {
        return flexeraProductVersion;
    }

    public void setDuplicated(boolean duplicated)
    {
        this.duplicated = duplicated;
    }

    public boolean isDuplicated()
    {
        return duplicated;
    }

    public void setiTMServerDate(Timestamp iTMServerDate)
    {
        this.iTMServerDate = iTMServerDate;
    }

    public Timestamp getiTMServerDate()
    {
        return iTMServerDate;
    }

    public void setFlexeraFeatureID(String flexeraFeatureID)
    {
        this.flexeraFeatureID = flexeraFeatureID;
    }

    public String getFlexeraFeatureID()
    {
        return flexeraFeatureID;
    }

    public void setActivatedbyOtherHost(boolean activatedbyOtherHost)
    {
        this.activatedbyOtherHost = activatedbyOtherHost;
    }

    public boolean isActivatedbyOtherHost()
    {
        return activatedbyOtherHost;
    }
    
    public void setChanged(boolean changed)
    {
        this.changed = changed;
    }

    public boolean isChanged()
    {
        return changed;
    }

    public boolean isTrailLicense()
    {
        return trailLicense;
    }

    public void setTrailLicense(boolean trailLicense)
    {
        this.trailLicense = trailLicense;
    }

    public String getDurationTime()
    {
        return durationTime;
    }

    public void setDurationTime(String durationTime)
    {
        this.durationTime = durationTime;
    }
}
