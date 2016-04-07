/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                 Copyright 2013 Motorola Solutions Inc.                       |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  ITM    
 CODED     BY:  Zhan Xuefeng    

 ------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>         <CR number>   <description>
20/Feb/13  a23126         CCMPD01745023 [iTM 6.1] CP authentication - password queue

------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RadioPassword extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -7930277764639900782L;
    private String jobUuid;
    private String pwdUuid;
    private String devUuid;
    private String pwdValue;    
    private String pwdUsage;
    private int pwdOrder;
    
    public RadioPassword()
    {
    }
    
    public RadioPassword(String devUuid,  String pwdValue, String pwdUsage, int pwdOrder)
    {
        this.devUuid = devUuid;
        this.pwdValue = pwdValue;
        this.pwdOrder = pwdOrder;
        this.pwdUsage = pwdUsage;
        this.jobUuid = "";
    }
    
    public RadioPassword(String devUuid, String jobUuid, String pwdValue, String pwdUsage, int pwdOrder)
    {
        this.devUuid = devUuid;
        this.pwdValue = pwdValue;
        this.pwdOrder = pwdOrder;
        this.pwdUsage = pwdUsage;
        this.jobUuid = jobUuid;
    }

    public String getJobUuid()
    {
    	return jobUuid;
    }
    
    public void setJobUuid(String jobUuid)
    {
    	this.jobUuid = jobUuid;
    }
    
    public String getPwdUuid()
    {
        return pwdUuid;
    }

    public void setPwdUuid(String pwdUuid)
    {
        this.pwdUuid = pwdUuid;
    }

    public String getDevUuid()
    {
        return devUuid;
    }

    public void setDevUuid(String devUuid)
    {
        this.devUuid = devUuid;
    }

    public String getPwdValue()
    {
        return pwdValue;
    }

    public void setPwdValue(String pwdValue)
    {
        this.pwdValue = pwdValue;
    }

    public String getPwdUsage()
    {
    	return this.pwdUsage;
    }
    
    public void setPwdUsage(String pwdUsage)
    {
    	this.pwdUsage = pwdUsage;
    }
    
    public int getPwdOrder()
    {
        return pwdOrder;
    }

    public void setPwdOrder(int pwdOrder)
    {
        this.pwdOrder = pwdOrder;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        sb.append("pwdUuid", this.pwdUuid);
        sb.append("devUuid", this.devUuid);
        sb.append("pwdValue", this.pwdValue);
        sb.append("pwdOrder", this.pwdOrder);
        sb.append("jobUuid",  this.jobUuid);
        sb.append("pwdUsage", this.pwdUsage);

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj instanceof RadioPassword == false)
        {
            return false;
        }

        if (this == obj)
        {
            return true;
        }

        RadioPassword pwd = (RadioPassword) obj;

        return new EqualsBuilder()
                .append(this.getPwdUuid(), pwd.getPwdUuid())
                .append(this.getDevUuid(), pwd.getDevUuid())
                .append(this.getPwdValue(), pwd.getPwdValue())
                .append(this.getPwdOrder(), pwd.getPwdOrder())
                .append(this.getJobUuid(),  pwd.jobUuid)
                .append(this.getPwdUsage(), pwd.pwdUsage)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.pwdUuid)
                .append(this.devUuid).append(this.pwdValue).append(this.pwdOrder).append(this.pwdUsage).append(this.jobUuid).hashCode();
    }

}
