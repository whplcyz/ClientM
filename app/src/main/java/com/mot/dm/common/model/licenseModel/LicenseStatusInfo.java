package com.mot.dm.common.model.licenseModel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.model.BaseObject;

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
25/June/14 wch378               CCMPD01905766  Flexera license feature iteration2
10/12/14   wch378               CCMPD01952388  adjust license expiration logic
------------------------------------------------------------------------------*/
public class LicenseStatusInfo extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -5681725299436793109L;
    
    public boolean isExpired;
    public boolean isWillExpiredSoon;         
    public long daysToExpired;
    
    public LicenseStatusInfo()
    {
        isExpired = false;
        isWillExpiredSoon = false;        
        daysToExpired = 0;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("isExpired", this.isExpired).append("isWillExpiredSoon", this.isWillExpiredSoon)
        .append("daysToExpired", this.daysToExpired);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LicenseStatusInfo))
            return false;
        LicenseStatusInfo rhs = (LicenseStatusInfo) other;

        return new EqualsBuilder().append(isExpired, rhs.isExpired)
                .append(isWillExpiredSoon, rhs.isWillExpiredSoon).append(daysToExpired, rhs.daysToExpired)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(isExpired).append(isWillExpiredSoon).append(daysToExpired)
                .hashCode();
    }
       
}
