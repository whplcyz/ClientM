/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                          Copyright 2009 Motorola Inc.                        |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Michael Leismann   Sep 2009

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure 
03/Sep/12  a23126                CCMPD01692862 iTM6.0- Add flash pack support info in meta-data 
                                               - RadioModelInfo.xml
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Michael Leismann
 */

public class ModelType extends BaseObject
{
    private static final long serialVersionUID = 73569874569875469L;

    private String m_itmModelTypeId;
    private String m_extModelTypeId;
    private String m_modelDescription;
    private String fpSupport;

    /**
     * Returns the ItmModelType
     * 
     * @return the ItmModelType
     */
    public String getItmModelTypeId()
    {
        return m_itmModelTypeId;
    }

    /**
     * Setting the ItmModelType
     * 
     * @param p_itmModelType
     */
    public void setItmModelTypeId(String p_itmModelTypeId)
    {
        m_itmModelTypeId = p_itmModelTypeId;
    }

    /**
     * Returns the ModelDescription
     * 
     * @return the ModelDescription
     */
    public String getModelDescription()
    {
        return m_modelDescription;
    }

    /**
     * Setting the ModelDescription
     * 
     * @param p_modelDescription
     */
    public void setModelDescription(String p_modelDescription)
    {
        m_modelDescription = p_modelDescription;
    }

    /**
     * Returns the ExtModelType
     * 
     * @return the ExtModelType
     */
    public String getExtModelTypeId()
    {
        return m_extModelTypeId;
    }

    /**
     * Setting the ExtModelType
     * 
     * @param p_extModelType
     */
    public void setExtModelTypeId(String p_extModelTypeId)
    {
        m_extModelTypeId = p_extModelTypeId;
    }

    public String getFlashPackSupport()
    {
        return this.fpSupport;
    }

    public void setFlashPackSupport(String flashPackSupport)
    {
        this.fpSupport = flashPackSupport;
    }
    
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        sb.append("extModelTypeId", m_extModelTypeId);
        sb.append("modelDescription", m_modelDescription);
        sb.append("itmModelTypeId", m_itmModelTypeId);
        sb.append("flashSupport", this.fpSupport);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof ModelType))
            return false;

        ModelType modelType = (ModelType) o;

        EqualsBuilder builder = new EqualsBuilder();

        builder.append(m_itmModelTypeId, modelType.m_itmModelTypeId);
        builder.append(m_modelDescription, modelType.m_modelDescription);
        builder.append(m_extModelTypeId, modelType.m_extModelTypeId);
        builder.append(this.fpSupport, modelType.fpSupport);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(m_itmModelTypeId).append(m_modelDescription)
                .append(m_extModelTypeId).hashCode();
    }

}
