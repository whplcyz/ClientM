/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2012 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  itm    
CODED     BY:   ZhaoWei wch378

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ModelNumber extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -3305809114540294847L;
    private String name;
    private String description;
    private String modelType;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getModelType()
    {
        return modelType;
    }

    public void setModelType(String modelType)
    {
        this.modelType = modelType;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("description", this.description)
                .append("name", this.name)
                .append("modelType", this.modelType)
                .append("id", this.getId())
                .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof ModelNumber))
        {
            return false;
        }
        ModelNumber rhs = (ModelNumber) object;
        return new EqualsBuilder()
                .append(this.modelType, rhs.modelType)
                .append(this.description, rhs.description)
                .append(this.name, rhs.name)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(223497277, 2071787381)
                .append(this.modelType)
                .append(this.description)
                .append(this.name)
                .toHashCode();
    }
}
