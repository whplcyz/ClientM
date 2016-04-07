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

PRODUCT NAME:  ITM    
CODED     BY:   Hai Dong

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
06/Jul/10  Hai Dong              ccmpd01365714 Personal data  
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * Model object for Personal data map
 */
public class ProgramParameterMap extends BaseObject
{

    private static final long serialVersionUID = 1L;

    private String programParameter;

    private ItmModelType itmModelType;

    /*
     * Default constructor required by hibernate
     */
    public ProgramParameterMap()
    {
    }

    /*
     * Check if the obect o equals the LanguageBundle object
     * 
     * @see com.mot.dm.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ProgramParameterMap == false)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        ProgramParameterMap rhs = (ProgramParameterMap) o;
        return new EqualsBuilder().append(this.itmModelType, rhs.getItmModelType())
                .append(programParameter, rhs.getProgramParameter()).isEquals();
    }

    /*
     * Return the hashcode of the object
     * 
     * @see com.mot.dm.common.model.BaseObject#hashCode()
     * 
     * @return int hashcode
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(itmModelType).append(programParameter).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "itmModelType", this.itmModelType)
                .append("programParameter", this.programParameter);
        return sb.toString();
    }

    public ItmModelType getItmModelType()
    {
        return itmModelType;
    }

    public void setItmModelType(ItmModelType itmModelType)
    {
        this.itmModelType = itmModelType;
    }

    public String getProgramParameter()
    {
        return programParameter;
    }

    public void setProgramParameter(String programParameter)
    {
        this.programParameter = programParameter;
    }
}
