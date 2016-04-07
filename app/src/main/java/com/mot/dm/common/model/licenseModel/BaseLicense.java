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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.model.BaseObject;

public class BaseLicense extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 6463906993555656308L;
    private String relatedFeatureID;
    private String relatedFeatureName;
    public List<LicenseItem> licenseItemList;
   
    public BaseLicense()
    {
        relatedFeatureID = "";  
        relatedFeatureName = "";
        licenseItemList = new ArrayList<LicenseItem>();
    }
    
    public String getRelatedFeatureName()
    {
        return relatedFeatureName;
    }
    
    public void setRelatedFeatureName(String relatedFeatureName)
    {
        this.relatedFeatureName = relatedFeatureName;
    }
    
    public String getRelatedFeatureID()
    {
        return relatedFeatureID;
    }
    
    public void setRelatedFeatureID(String relatedFeatureID)
    {
        this.relatedFeatureID = relatedFeatureID;
    }   
       
    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("relatedFeatureID", this.relatedFeatureID).append("relatedFeatureName", this.relatedFeatureName)
        .append("licenseItemList", this.licenseItemList);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof BaseLicense))
            return false;
        BaseLicense rhs = (BaseLicense) other;

        return new EqualsBuilder().append(relatedFeatureID, rhs.relatedFeatureID).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(relatedFeatureID).hashCode();
    }
}
