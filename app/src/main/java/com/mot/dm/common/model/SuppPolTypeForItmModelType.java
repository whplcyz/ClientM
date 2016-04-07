/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2010 Motorola Inc.                           |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  Sigurdur Jonsson

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
22/Sep/10  Sigurdur Jonsson      CCMPD01394082 Policy feature, network logging
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 This class describes which Policy types are supported for
 *         given itm Model type.
 */
public class SuppPolTypeForItmModelType extends BaseObject
{
    private static final long serialVersionUID = 45674632479348979L;

    private String policyTypeId;
    private String itmModelTypeId;
    private PolicyType policyType;    

    public String getPolicyTypeId()
    {
        return policyTypeId;
    }

    public void setPolicyTypeId(String policyTypeId)
    {
        this.policyTypeId = policyTypeId;
    }

    public String getItmModelTypeId()
    {
        return itmModelTypeId;
    }

    public void setItmModelTypeId(String itmModelTypeId)
    {
        this.itmModelTypeId = itmModelTypeId;
    }

    public PolicyType getPolicyType()
    {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType)
    {
        this.policyType = policyType;
    }

    public String getPolicyTypeName()
    {
        return this.policyType.getPolicyTypeName();
    }

    public void setPolicyTypeName(String policyTypeName)
    {
        this.policyType.setPolicyTypeName(policyTypeName);
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyType", policyType);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof SuppPolTypeForItmModelType))
        {
            return false;
        }

        SuppPolTypeForItmModelType suppPolTypeForItmModelType = (SuppPolTypeForItmModelType) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyType, suppPolTypeForItmModelType.policyType);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(policyType).hashCode();
    }
}
