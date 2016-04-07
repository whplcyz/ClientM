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
CODED     BY:  cjh102, Oct 2010

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/11/10   ckfm01               CCMPD01409592    Minor changes
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CapacityLicensesInfo extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 689490623475901713L;
    public static final int SITEWIDE = -1;

    /**
     * Reference to the flexeraFeature object
     */
    private FlexeraFeature flexeraFeature = null;

    /**
     * Number of licenses in the system for the sellable license
     */
    private int numberOfLicenses = 0;

    /**
     * Number of license in the system used for the sellable license
     */
    private int numberOfUsedLicenses = 0;

    public FlexeraFeature getFlexeraFeature()
    {
        return flexeraFeature;
    }

    public void setSellableLicense(FlexeraFeature flexeraFeature)
    {
        this.flexeraFeature = flexeraFeature;
    }

    public int getNumberOfLicenses()
    {
        return numberOfLicenses;
    }

    public void setNumberOfLicenses(int numberOfLicenses)
    {
        this.numberOfLicenses = numberOfLicenses;
    }

    public int getNumberOfUsedLicenses()
    {
        return numberOfUsedLicenses;
    }

    public void setNumberOfUsedLicenses(int numberOfUsedLicenses)
    {
        this.numberOfUsedLicenses = numberOfUsedLicenses;
    }

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof CapacityLicensesInfo))
            return false;
        CapacityLicensesInfo rhs = (CapacityLicensesInfo) o;

        return new EqualsBuilder().append(flexeraFeature, rhs.flexeraFeature)
                .append(numberOfLicenses, rhs.numberOfLicenses)
                .append(numberOfUsedLicenses, rhs.numberOfUsedLicenses).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(flexeraFeature).append(numberOfLicenses)
                .append(numberOfUsedLicenses).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("flexeraFeature", this.flexeraFeature);
        sb.append("numberOfLicenses", this.numberOfLicenses);
        sb.append("numberOfUsedLicenses", this.numberOfUsedLicenses);
        return sb.toString();
    }
}
