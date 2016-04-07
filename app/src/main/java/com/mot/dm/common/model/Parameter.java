/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database.
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
17/June/14 tqfn38				 CCMPD01902641 Flexera license feature
25/Jun/14 bfq463              CCMPD01900799 active directory support
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Parameter extends BaseObject
{

    private static final long serialVersionUID = 1536930662504175327L;

    // Fields

    private String paraName;

    private String paraValue;

    public Parameter(String paraName, String paraValue)
    {
    	this.paraName = paraName;
    	this.paraValue = paraValue;
    }
    
    public Parameter(){}
    
    public String getParaName()
    {
        return this.paraName;
    }

    public void setParaName(String paraName)
    {
        this.paraName = paraName;
    }

    public String getParaValue()
    {
        return this.paraValue;
    }

    public void setParaValue(String paraValue)
    {
        this.paraValue = paraValue;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("paraName", this.paraName)
                .append("paraValue", this.paraValue);
        return sb.toString();
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof Parameter))
            return false;
        Parameter rhs = (Parameter) other;

        return new EqualsBuilder().append(paraName, rhs.paraName)
                .append(paraValue, rhs.paraValue).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(paraName).append(paraValue).hashCode();
    }
}
