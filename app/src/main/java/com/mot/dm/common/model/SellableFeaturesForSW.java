/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2011-2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen    Feb 2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
18/03/08 cjh102                  Update after FTR
01/Sep/09  Michael Leismann      CCMPD01258322 Software feature improvements
22/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
28/3/14    wch378                CCMPD01876846 develop flexera license.
28/3/14    wch378               CCMPD01889533 develop flexera license.  
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SellableFeaturesForSW extends BaseObject
{
    /**
     * For java.io.Serializable
     */
    private static final long serialVersionUID = 5678399503106873207L;

    private ItmModelType itmModelType;

    private FlexeraFeature sellableLicense;

    private String softwareVersion;

    public ItmModelType getItmModelType()
    {
        return itmModelType;
    }

    public void setItmModelType(ItmModelType itmModelType)
    {
        this.itmModelType = itmModelType;
    }

    public FlexeraFeature getSellableLicense()
    {
        return sellableLicense;
    }

    public void setSellableLicense(FlexeraFeature sellableLicense)
    {
        this.sellableLicense = sellableLicense;
    }

    public String getSoftwareVersion()
    {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion)
    {
        this.softwareVersion = softwareVersion;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SellableFeaturesForSW))
            return false;
        SellableFeaturesForSW rhs = (SellableFeaturesForSW) other;

        return new EqualsBuilder().append(itmModelType, rhs.itmModelType)
                .append(sellableLicense, rhs.sellableLicense).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(itmModelType).append(sellableLicense).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "software version", this.softwareVersion);
        return sb.toString();
    }

}
