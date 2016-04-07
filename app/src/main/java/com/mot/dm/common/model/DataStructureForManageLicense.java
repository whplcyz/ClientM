/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                  Copyright 2014 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  ZhaoWei-wch378

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/3/14    wch378        CCMPD01876846 develop flexera license.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mot.dm.common.model.licenseModel.LicenseData;

public class DataStructureForManageLicense extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 788325679492544826L;
    
    public List<FlexeraLicenseSource> licenseSources;
    public LicenseData licenseData;
    public List<ItmModelType> itmModelTypes;
    public String hostID;
    public Timestamp hostDate;
    public String hostName;

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof DataStructureForManageLicense))
        {
            return false;
        }
        DataStructureForManageLicense rhs = (DataStructureForManageLicense) object;
        return new EqualsBuilder()
                .append(this.hostDate, rhs.hostDate)
                .append(this.licenseData, rhs.licenseData)
                .append(this.licenseSources, rhs.licenseSources)
                .append(this.itmModelTypes, rhs.itmModelTypes)
                .append(this.hostName, rhs.hostName)
                .append(this.hostID, rhs.hostID)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(-1122780765, 1920206423)
                .append(this.hostDate)
                .append(this.licenseData)
                .append(this.licenseSources)
                .append(this.itmModelTypes)
                .append(this.hostName)
                .append(this.hostID)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id", this.getId())
                .toString();
    }

}
