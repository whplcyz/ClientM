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
23/Sep/10  Sigurdur Jonsson      CCMPD01394082 Policy feature, network logging
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 This class declares a Policy type. A Policy is defined by
 *         Policy type together with Policy type parameters and Policy type
 *         behavior.
 */
public class PolicyType extends BaseObject
{
    private static final long serialVersionUID = 45674632479348980L;

    private String policyTypeId = "";
    private String policyTypeName = "";

    public String getPolicyTypeId()
    {
        return policyTypeId;
    }

    public void setPolicyTypeId(String policyTypeUuid)
    {
        this.policyTypeId = policyTypeUuid;
    }

    public String getPolicyTypeName()
    {
        return policyTypeName;
    }

    public void setPolicyTypeName(String policyTypeName)
    {
        this.policyTypeName = policyTypeName;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyTypeUuid", policyTypeId);
        sb.append("policyTypeName", policyTypeName);

        return sb.toString();
    }

    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof PolicyType))
            return false;

        PolicyType policyType = (PolicyType) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyTypeName, policyType.policyTypeName);
        builder.append(policyTypeId, policyType.policyTypeId);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(policyTypeName).append(policyTypeId).hashCode();
    }
}
