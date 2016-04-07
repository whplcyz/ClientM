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
package com.mot.dm.common.model.licenseModel;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.model.BaseObject;

public class LicenseItem extends BaseObject
{
    public LicenseStatusInfo licenseStatusInfo;   
    public Date expiredDate;
    public boolean isPermanent;
    public int availableNumber;
    public UUID licenseItemUuid;
    
    public LicenseItem()
    {
        licenseStatusInfo = new LicenseStatusInfo();
        expiredDate = new Date();
        isPermanent = false;
        availableNumber = 0;
        licenseItemUuid = UUID.randomUUID();
    }
    /**
     * 
     */
    private static final long serialVersionUID = -1827519949687076572L;

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("licenseItemUuid", this.licenseItemUuid)
        .append("licenseStatusInfo", this.licenseStatusInfo).append("expiredDate", this.expiredDate)
        .append("isPermanent", this.isPermanent).append("availableNumber", this.availableNumber);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LicenseItem))
            return false;
        LicenseItem rhs = (LicenseItem) other;

        return new EqualsBuilder().append(licenseItemUuid, rhs.licenseItemUuid).append(licenseStatusInfo, rhs.licenseStatusInfo)
                .append(expiredDate, rhs.expiredDate)
                .append(isPermanent, rhs.isPermanent)
                .append(availableNumber, rhs.availableNumber).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(licenseItemUuid).append(licenseStatusInfo).append(expiredDate).append(isPermanent).append(availableNumber)
                .hashCode();
    }

}
