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
24/Sep/10  Sigurdur Jonsson      CCMPD01394082 Policy feature, network logging
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 This class contains a single parameter specific for a single
 *         execution of a given Policy
 */
public class PolicyExecutionParam extends BaseObject
{
    private static final long serialVersionUID = -6523140874672010442L;
    private String policyExecutionParamUuid;
    private String policyExecutionUuid;
    private String parameterName;
    private String parameterValue;

    public String getPolicyExecutionParamUuid()
    {
        return policyExecutionParamUuid;
    }

    public void setPolicyExecutionParamUuid(String policyExecutionParameterUuid)
    {
        this.policyExecutionParamUuid = policyExecutionParameterUuid;
    }

    public String getPolicyExecutionUuid()
    {
        return policyExecutionUuid;
    }

    public void setPolicyExecutionUuid(String policyExecutionUuid)
    {
        this.policyExecutionUuid = policyExecutionUuid;
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
        sb.append("policyExecutionParameterUuid", policyExecutionParamUuid);
        sb.append("policyExecutionUuid", policyExecutionUuid);
        sb.append("parameterName", parameterName);
        sb.append("parameterValue", parameterValue);

        return sb.toString();
    }

    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof PolicyExecutionParam))
            return false;

        PolicyExecutionParam policyExecutionParam = (PolicyExecutionParam) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyExecutionParamUuid, policyExecutionParam.policyExecutionParamUuid);
        builder.append(policyExecutionUuid, policyExecutionParam.policyExecutionUuid);
        builder.append(parameterName, policyExecutionParam.parameterName);
        builder.append(parameterValue, policyExecutionParam.parameterValue);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(policyExecutionParamUuid).hashCode();
    }
}
