/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                    Copyright 2014 Motorola Solutions, Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  bfq463    May 27, 2014

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/Jun/14 bfq463              CCMPD01900799 active directory support 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.io.Serializable;

public class FilterAttribute implements Serializable
{
    
    public FilterAttribute copy()
    {
        FilterAttribute attribute = new FilterAttribute();
        attribute.setAttributeKey(attributeKey);
        attribute.setAttributeName(attributeName);
        attribute.setUseful(isUseful);
        attribute.setAttributeOldName(attributeOldName);
        return attribute;
    }
    /**
     * 
     */
    private static final long serialVersionUID = 4246351194513393426L;

    public FilterAttribute()
    {
        attributeOldName = "";
        attributeName = "";
        isUseful = false;
        attributeKey = "";
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
        if (attributeName == null)
        {
            attributeName = "";
        }
        this.attributeName = attributeName;
    }

    public boolean isUseful()
    {
        return isUseful;
    }

    public void setUseful(boolean isUseful)
    {
        this.isUseful = isUseful;
    }

    private String attributeOldName;
    public String getAttributeOldName()
    {
        return attributeOldName;
    }

    public void setAttributeOldName(String attributeOldName)
    {
        if(attributeOldName == null)
        {
            attributeOldName = "";
        }
        this.attributeOldName = attributeOldName;
    }
    
    private String attributeName;
    private boolean isUseful;
    private String attributeKey;

    public String getAttributeKey()
    {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey)
    {
        this.attributeKey = attributeKey;
    }

    @Override
    public int hashCode()
    {
        int retuslt = 0;
        retuslt += isUseful ? 1 : 0;
        retuslt += attributeKey == null ? 0 : attributeKey.hashCode();
        retuslt += attributeName == null ? 0 : attributeName.hashCode();
        retuslt += attributeOldName == null ? 0 : attributeOldName.hashCode();
        return retuslt;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof FilterAttribute)
        {
            FilterAttribute other = (FilterAttribute) obj;
            if (this.isUseful == other.isUseful
                    && this.attributeKey.equals(other.attributeKey)
                    && this.attributeName.equals(other.attributeName)
                    && this.attributeOldName.equals(other.attributeOldName)
                    )
            {
                return true;
            }
        }
        return false;
    }
}
