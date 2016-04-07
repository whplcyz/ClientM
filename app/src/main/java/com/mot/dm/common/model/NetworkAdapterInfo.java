/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                     Copyright 2011 Motorola Solutions Inc.                   |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  ITM     
 CODED     BY:  cjh102    May 2011

 ------------------------------- REVISION HISTORY -------------------------------
 25/May/11     cjh102           CCMPD01484229 Initial version
 *-----------------------------------------------------------------------------
 */
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@SuppressWarnings("rawtypes")
public class NetworkAdapterInfo extends BaseObject implements Comparable
{

    private static final long serialVersionUID = -8764105960224060411L;

    private String macAddress;

    private String description;

    public String getMacAddress()
    {
        return macAddress;
    }

    public void setMacAddress(String macAddress)
    {
        this.macAddress = macAddress;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    // Compare the objects soley on the MAC address
    public int compareTo(Object arg0)
    {
        if (arg0 == null)
            return 1;

        NetworkAdapterInfo otherObj = (NetworkAdapterInfo) arg0;
        return this.getMacAddress().compareTo(otherObj.getMacAddress());
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "Description", this.description).append("MAC accress", this.macAddress);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof NetworkAdapterInfo == false)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        NetworkAdapterInfo rhs = (NetworkAdapterInfo) obj;
        return new EqualsBuilder().append(macAddress, rhs.macAddress)
                .append(description, rhs.description).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(macAddress).append(description).hashCode();
    }
}
