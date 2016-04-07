/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2010-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  cjh102 Oct 2010

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/11/10   ckfm01                CCMPD01409592 Minor changes
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LicenseFileDetailContainer extends BaseObject
{
    private static final long serialVersionUID = 8128600855731279235L;

    /**
     * Fill with data for a feature license
     */
    public String licenseSourceUuid;
    public List<LicenseFileDetail> licenseFileDetails;

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof LicenseFileDetailContainer))
            return false;
        LicenseFileDetailContainer ld = (LicenseFileDetailContainer) o;

        return new EqualsBuilder().append(licenseSourceUuid, ld.licenseSourceUuid).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(licenseSourceUuid).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        sb.append("licenseSourceUuid: ", licenseSourceUuid);

        return sb.toString();
    }
}
