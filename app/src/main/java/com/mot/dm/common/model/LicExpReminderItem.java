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
10/12/14   wch378                CCMPD01952388 adjust license expiration logic
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LicExpReminderItem extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 1128716231828400306L;
    
    private String featureName;
    private long daysToExpired;
    private int availableNumber;
    
    public int getAvailableNumber()
    {
        return availableNumber;
    }
    
    public void setAvailableNumber(int availableNumber)
    {
       this.availableNumber = availableNumber;
    }
    
    public String getFeatureName()
    {
        return featureName;
    }
    
    public void setFeatureName(String featureName)
    {
       this.featureName = featureName;
    }
    
    public long getDaysToExpired()
    {
        return daysToExpired;
    }
    
    public void setDaysToExpired(long daysToExpired)
    {
       this.daysToExpired = daysToExpired;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("daysToExpired", this.daysToExpired)
                .append("availableNumber", this.availableNumber)
                .append("featureName", this.featureName)
                .append("id", this.getId())
                .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof LicExpReminderItem))
        {
            return false;
        }
        LicExpReminderItem rhs = (LicExpReminderItem) object;
        return new EqualsBuilder()
                .append(this.featureName, rhs.featureName)
                .append(this.availableNumber, rhs.availableNumber)
                .append(this.daysToExpired, rhs.daysToExpired)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(1620956639, 435499843)
                .append(this.featureName)
                .append(this.availableNumber)
                .append(this.daysToExpired)
                .toHashCode();
    }

}
