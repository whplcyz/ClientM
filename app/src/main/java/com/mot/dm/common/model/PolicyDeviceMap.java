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

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 This class maps a given policy to a given device. A given
 *         policy can be referred to by many device and may have different
 *         execution priority for each device.
 */
public class PolicyDeviceMap extends BaseObject
{

    private static final long serialVersionUID = -6523140874672010416L;
    private String policyDeviceMapUuid;
    private int priority; // execution priority of a policy within a device
    private Policy policy;
    private Device device;
    private Timestamp assignedDate;
    private String assignedBy;
    private Timestamp modifiedDate;
    private Timestamp lastExecutedTimeStamp;

    public String getPolicyDeviceMapUuid()
    {
        return policyDeviceMapUuid;
    }

    public void setPolicyDeviceMapUuid(String policyRadioMapUuid)
    {
        this.policyDeviceMapUuid = policyRadioMapUuid;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public Policy getPolicy()
    {
        return policy;
    }

    public void setPolicy(Policy policy)
    {
        this.policy = policy;
    }

    public Timestamp getAssignedDate()
    {
        return assignedDate;
    }

    public void setAssignedDate(Timestamp assignedDate)
    {
        this.assignedDate = assignedDate;
    }

    public String getAssignedBy()
    {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy)
    {
        this.assignedBy = assignedBy;
    }

    public Device getDevice()
    {
        return device;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    public String getPolicyName()
    {
        return this.policy.getName();
    }

    public void setPolicyName(String policyName)
    {
        this.policy.setName(policyName);
    }

    public PolicyType getPolicyType()
    {
        return this.policy.getType();
    }

    public void setPolicyType(PolicyType policyType)
    {
        this.policy.setType(policyType);
    }

    /**
     * Required by the iTM Client to show the policy type name
     * 
     * @return The policy type name if the policy type is not null, otherwise an
     *         empty string
     */
    public String getPoliceTypeName()
    {
        if (policy != null && policy.getType() != null)
        {
            return policy.getType().getPolicyTypeName();
        }
        return "";
    }

    public Timestamp getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getLastExecutedTimeStamp()
    {
        return lastExecutedTimeStamp;
    }

    public void setLastExecutedTimeStamp(Timestamp lastExecutedTimeStamp)
    {
        this.lastExecutedTimeStamp = lastExecutedTimeStamp;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyRadioMapUuid", policyDeviceMapUuid);
        sb.append("policy", policy);
        sb.append("device", device);
        sb.append("priority", priority);
        sb.append("assignedDate", assignedDate);
        sb.append("assignedBy", assignedBy);
        sb.append("modifiedDate", modifiedDate);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof PolicyDeviceMap))
        {
            return false;
        }

        PolicyDeviceMap policyDeviceMap = (PolicyDeviceMap) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyDeviceMapUuid, policyDeviceMap.policyDeviceMapUuid);
        builder.append(policy, policyDeviceMap.policy);
        builder.append(device, policyDeviceMap.device);
        builder.append(priority, policyDeviceMap.priority);
        builder.append(priority, policyDeviceMap.assignedDate);
        builder.append(priority, policyDeviceMap.assignedBy);
        builder.append(priority, policyDeviceMap.modifiedDate);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(policyDeviceMapUuid).hashCode();
    }

}
