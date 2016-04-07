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
19/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Michael Leismann
 */
public class SuppBuildsForExtModelType extends BaseObject
{
    private static final long serialVersionUID = 45674632479348978L;

    private String m_extModelType = "";
    private String m_swVersionBuildId = "";
    private List<Software> m_loadSoftware;

    public String getExtModelType()
    {
        return m_extModelType;
    }

    public void setExtModelType(String p_extModelType)
    {
        m_extModelType = p_extModelType;
    }

    public String getSwVersionBuildId()
    {
        return m_swVersionBuildId;
    }

    public void setSwVersionBuildId(String p_swVersionBuildId)
    {
        m_swVersionBuildId = p_swVersionBuildId;
    }

    public List<Software> getLoadSoftware()
    {
        return m_loadSoftware;
    }

    public void setLoadSoftware(List<Software> p_loadSoftware)
    {
        m_loadSoftware = p_loadSoftware;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("extModelType", m_extModelType);
        sb.append("swVersionBuildId", m_swVersionBuildId);
        sb.append("loadSoftwareList", m_loadSoftware);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof SuppBuildsForExtModelType))
        {
            return false;
        }

        SuppBuildsForExtModelType suppBuildsForExtModelType = (SuppBuildsForExtModelType) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(m_extModelType, suppBuildsForExtModelType.m_extModelType);
        builder.append(m_swVersionBuildId, suppBuildsForExtModelType.m_swVersionBuildId);
        builder.append(m_loadSoftware, suppBuildsForExtModelType.m_loadSoftware);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(m_extModelType).append(m_swVersionBuildId)
                .append(m_loadSoftware).hashCode();
    }

}
