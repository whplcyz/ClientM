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
CODED     BY:  wch378

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/July/13  wch378         CCMPD01798198   policy extract api 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PolicyExecutionSequence extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -4155373348646008473L;
    private String policyExecutionUuid;
    private int policyExecutionSN;
    private String policyExecutionSequenceUUid;
    
    public String getPolicyExecutionSequenceUUid()
    {
        return policyExecutionSequenceUUid;
    }
    public void setPolicyExecutionSequenceUUid(String policyExecutionSequenceUUid)
    {
        this.policyExecutionSequenceUUid = policyExecutionSequenceUUid;
    }
    
    public int getPolicyExecutionSN()
    {
        return policyExecutionSN;
    }
    
    public void setPolicyExecutionSN(int policyExecutionSN)
    {
        this.policyExecutionSN = policyExecutionSN;
    }

    public String getPolicyExecutionUuid()
    {
        return policyExecutionUuid;
    }

    public void setPolicyExecutionUuid(String policyExectutionUuid)
    {
        this.policyExecutionUuid = policyExectutionUuid;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("policyExecutionUuid", policyExecutionUuid);
        sb.append("policyExecutionSN", policyExecutionSN);

        return sb.toString();
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
        
        final PolicyExecutionSequence other = (PolicyExecutionSequence) obj;   
        if (policyExecutionUuid == null)
        {
            if (other.policyExecutionUuid != null)
            {
                return false;
            }
        }
        else if (!policyExecutionUuid.equals(other.policyExecutionUuid))
        {
            return false;
        }
        
        if (policyExecutionSN!=other.policyExecutionSN)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(policyExecutionUuid).hashCode();
        
    }

}
