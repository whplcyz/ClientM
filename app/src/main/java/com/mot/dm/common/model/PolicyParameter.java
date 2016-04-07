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
 * @author jnss008 The PolicyParameter class contains all information to
 *         describe a given parameter for a Policy type. Since a Policy can have
 *         many parameters of different types, a generic approach for Policy
 *         parameter is needed. To meet this demand the (name, value) pair
 *         approach is used.
 */
public class PolicyParameter extends BaseObject
{

    private static final long serialVersionUID = -6523140874672010414L;
    private String policyParameterUuid;
    private String policyUuid;
    private String parameterName;
    private String parameterValue;

    public PolicyParameter()
    {
        
    }
    
    public PolicyParameter(String policyParameterUuid,String policyUuid,String parameterName,String parameterValue)
    {
        this.policyParameterUuid = policyParameterUuid;
        this.policyUuid = policyUuid;
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }
    
    public String getPolicyParameterUuid()
    {
        return policyParameterUuid;
    }

    public void setPolicyParameterUuid(String policyParameterUuid)
    {
        this.policyParameterUuid = policyParameterUuid;
    }

    public String getPolicyUuid()
    {
        return policyUuid;
    }

    public void setPolicyUuid(String policyUuid)
    {
        this.policyUuid = policyUuid;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }

    public String getParameterValue()
    {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue)
    {
        this.parameterValue = parameterValue;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyParameterUuid", policyParameterUuid);
        sb.append("policyUuid", policyUuid);
        sb.append("parameterName", parameterName);
        sb.append("parameterValue", parameterValue);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof PolicyParameter))
        {
            return false;
        }

        PolicyParameter policyParameter = (PolicyParameter) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyParameterUuid, policyParameter.policyParameterUuid);
        builder.append(policyUuid, policyParameter.policyUuid);
        builder.append(parameterName, policyParameter.parameterName);
        builder.append(parameterValue, policyParameter.parameterValue);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(policyParameterUuid).hashCode();
    }
}
