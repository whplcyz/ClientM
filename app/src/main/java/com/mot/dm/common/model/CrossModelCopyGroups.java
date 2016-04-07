/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                  Copyright 2011 Motorola Solutions Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Sigurdur Jonsson    Jun 2011

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
14/Jun/11  Sigurdur Jonsson      CCMPD01518634 Panda integration
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CrossModelCopyGroups extends BaseObject
{
    private static final long serialVersionUID = -6523140874672010484L;
    // Key is fromModel, value is list of toModel
    private Map<String, List<String>> crossModelGroupsMap;

    @Override
    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof CrossModelCopyGroups))
            return false;

        CrossModelCopyGroups crossModelCopyGroups = (CrossModelCopyGroups) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(crossModelGroupsMap, crossModelCopyGroups.crossModelGroupsMap);

        return builder.isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(crossModelGroupsMap).append(crossModelGroupsMap)
                .hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("crossModelGroupsMap", crossModelGroupsMap);

        return sb.toString();
    }

    /**
     * Getter for crossModelGroupsMap
     * 
     * @return
     */
    public Map<String, List<String>> getCrossModelGroupsMap()
    {
        return crossModelGroupsMap;
    }

    /**
     * Setter for crossModelGroupsMap
     */
    public void setCrossModelGroupsMap(Map<String, List<String>> crossModelGroupsMap)
    {
        this.crossModelGroupsMap = crossModelGroupsMap;
    }
}
