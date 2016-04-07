/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2011-2013 Motorola Inc.                      |
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
14/01/11   chad001               CCMPD01461050 sort on all tables
08/Feb/11  Sigurdur Jonsson      CCMPD01470657 Klocwork baseline for iTM5.0
9/July/13  wch378                CCMPD01791920 picture extraction API enhancement
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 This class contains information about a single execution of a
 *         given Policy
 */
public class PolicyExecution extends BaseObject
{
    private static final long serialVersionUID = -6523140874672010443L;
    private String policyExecutionUuid;
    private String policyName;
    private String policyType;
    private String deviceId;
    private int status; // Refers to constants in StatusConstants.java
    private Timestamp executionTime;
    private String executionProxyName;
    private String executionUserName;
    private int priority;
    private ReturnCodeInfo returnCodeInfo;
    private List<PolicyExecutionParam> policyExecutionParamList = new ArrayList<PolicyExecutionParam>();
    public PolicyExecutionSequence policyExecutionSequence;
    
    public String getPolicyExecutionUuid()
    {
        return policyExecutionUuid;
    }

    public void setPolicyExecutionUuid(String policyExectutionUuid)
    {
        this.policyExecutionUuid = policyExectutionUuid;
    }

    public String getPolicyName()
    {
        return policyName;
    }

    public void setPolicyName(String policyName)
    {
        this.policyName = policyName;
    }

    public String getPolicyType()
    {
        return policyType;
    }

    public void setPolicyType(String policyType)
    {
        this.policyType = policyType;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Timestamp getExecutionTime()
    {
        return executionTime;
    }

    public void setExecutionTime(Timestamp executionTime)
    {
        this.executionTime = executionTime;
    }

    public String getExecutionProxyName()
    {
        return executionProxyName;
    }

    public void setExecutionProxyName(String executionProxyName)
    {
        this.executionProxyName = executionProxyName;
    }

    public List<PolicyExecutionParam> getPolicyExecutionParamList()
    {
        return policyExecutionParamList;
    }

    public void setPolicyExecutionParamList(List<PolicyExecutionParam> policyExecutionParamList)
    {
        this.policyExecutionParamList = policyExecutionParamList;
    }

    public String getExecutionUserName()
    {
        return executionUserName;
    }

    public void setExecutionUserName(String executionUserName)
    {
        this.executionUserName = executionUserName;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public ReturnCodeInfo getReturnCodeInfo()
    {
        return returnCodeInfo;
    }

    public void setReturnCodeInfo(ReturnCodeInfo returnCodeInfo)
    {
        this.returnCodeInfo = returnCodeInfo;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyExectutionUuid", policyExecutionUuid);
        sb.append("policyName", policyName);
        sb.append("policyType", policyType);
        sb.append("deviceId", deviceId);
        sb.append("status", status);
        sb.append("executionTime", executionTime);
        sb.append("executionProxyName", executionProxyName);
        sb.append("policyExecutionParamList", policyExecutionParamList);
        sb.append("executionUserName", executionUserName);
        sb.append("priority", priority);

        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(policyExecutionUuid).hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        
        if (this == obj)
            return true;
        
        if (getClass() != obj.getClass())
            return false;
        
        final PolicyExecution other = (PolicyExecution) obj;
        if (deviceId == null)
        {
            if (other.deviceId != null)
                return false;
        }
        else if (!deviceId.equals(other.deviceId))
            return false;
        if (executionProxyName == null)
        {
            if (other.executionProxyName != null)
                return false;
        }
        else if (!executionProxyName.equals(other.executionProxyName))
            return false;
        if (executionTime == null)
        {
            if (other.executionTime != null)
                return false;
        }
        else if (!executionTime.equals(other.executionTime))
            return false;
        if (executionUserName == null)
        {
            if (other.executionUserName != null)
                return false;
        }
        else if (!executionUserName.equals(other.executionUserName))
            return false;
        if (policyExecutionParamList == null)
        {
            if (other.policyExecutionParamList != null)
                return false;
        }
        else if (!policyExecutionParamList.equals(other.policyExecutionParamList))
            return false;
        if (policyExecutionUuid == null)
        {
            if (other.policyExecutionUuid != null)
                return false;
        }
        else if (!policyExecutionUuid.equals(other.policyExecutionUuid))
            return false;
        if (policyName == null)
        {
            if (other.policyName != null)
                return false;
        }
        else if (!policyName.equals(other.policyName))
            return false;
        if (policyType == null)
        {
            if (other.policyType != null)
                return false;
        }
        else if (!policyType.equals(other.policyType))
            return false;
        if (priority != other.priority)
            return false;
        if (returnCodeInfo == null)
        {
            if (other.returnCodeInfo != null)
                return false;
        }
        else if (!returnCodeInfo.equals(other.returnCodeInfo))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

}
