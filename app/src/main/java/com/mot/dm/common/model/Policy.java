/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2010-2011 Motorola Solutions Inc.                  |
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
10/Mar/11  Michael Leismann      CCMPD01483126 iTM 5.0: Moved proxy specific
                                               objects to proxy lib package
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author jnss008 The Policy class contains all information for a policy. A
 *         policy is a description of actions that must be executed in a given
 *         situation and for a given collection of devices. A policy has a
 *         number of policy parameters which together with the policy behavior
 *         (i.e. type) defines the policy.
 */
public class Policy extends BaseObject
{
    private static final long serialVersionUID = -6523140874672010414L;
    protected String policyUuid;
    protected String name;
    protected PolicyType type;
    protected String description;
    protected int holdOffTime;
    protected int defaultPriority;
    protected String creator;
    protected Timestamp creationDate;
    protected Set<PolicyParameter> policyParameters = new HashSet<PolicyParameter>();

    public String getPolicyUuid()
    {
        return policyUuid;
    }

    public void setPolicyUuid(String policyUuid)
    {
        this.policyUuid = policyUuid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public PolicyType getType()
    {
        return type;
    }

    public void setType(PolicyType type)
    {
        this.type = type;
    }

    /**
     * Required by the iTM Client to show the policy type name
     * 
     * @return The policy type name if the policy type is not null, otherwise an
     *         empty string
     */
    public String getPoliceTypeName()
    {
        if (type != null)
        {
            return type.getPolicyTypeName();
        }
        return "";
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getHoldOffTime()
    {
        return holdOffTime;
    }

    public void setHoldOffTime(int holdOffTime)
    {
        this.holdOffTime = holdOffTime;
    }

    public int getDefaultPriority()
    {
        return defaultPriority;
    }

    public void setDefaultPriority(int defaultPriority)
    {
        this.defaultPriority = defaultPriority;
    }

    /**
     * Getter method for a set of PolicyParameters
     */
    public Set<PolicyParameter> getPolicyParameters()
    {
        return policyParameters;
    }

    /**
     * Setter method for a set of PolicyParameters
     */
    public void setPolicyParameters(Set<PolicyParameter> policyParameters)
    {
        this.policyParameters = policyParameters;
    }

    /**
     * Getter method for a list of PolicyParameters It converts the set of
     * PolicyParameters to a list
     */
    public List<PolicyParameter> getPolicyParametersList()
    {
        List<PolicyParameter> policyParametersList = new ArrayList<PolicyParameter>();
        if (policyParameters != null)
        {
            for (PolicyParameter policyParameter : policyParameters)
            {
                policyParametersList.add(policyParameter);
            }
        }
        return policyParametersList;
    }

    /**
     * Setter method for a list of PolicyParameters It converts the list of
     * PolicyParameters to a set
     */
    public void setPolicyParametersList(List<PolicyParameter> policyParametersList)
    {
        policyParameters.clear();
        if (policyParametersList != null)
        {
            for (PolicyParameter policyParameter : policyParametersList)
            {
                policyParameters.add(policyParameter);
            }
        }
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyUuid", policyUuid);
        sb.append("name", name);
        sb.append("type", type);
        sb.append("description", description);
        sb.append("holdOffTime", holdOffTime);
        sb.append("defaultPriority", defaultPriority);
        sb.append("policyParameters", policyParameters);

        return sb.toString();
    }

    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof Policy))
            return false;

        Policy policy = (Policy) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(policyUuid, policy.policyUuid);
        builder.append(name, policy.name);
        builder.append(type, policy.type);
        builder.append(description, policy.description);
        builder.append(holdOffTime, policy.holdOffTime);
        builder.append(defaultPriority, policy.defaultPriority);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(name).append(policyUuid).hashCode();
    }
}
