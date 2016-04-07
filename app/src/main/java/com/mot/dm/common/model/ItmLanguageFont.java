/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                  Copyright 2013 Motorola Solutions Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  a23126    June 2013

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
04/Jun/13  a23126                CCMPD01780442 [iTM 6.0.2] Update metadata to split 
                                               language pack size and font pack size.
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ItmLanguageFont extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 3293082938526229538L;

    private String name;
    private String fontID;
    private int size;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFontID()
    {
        return fontID;
    }

    public void setFontID(String fontID)
    {
        this.fontID = fontID;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "name", this.name).append("fontID", this.fontID).append("size", this.size);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof ItmLanguageFont))
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        ItmLanguageFont rhs = (ItmLanguageFont) o;
        return new EqualsBuilder().append(this.name, rhs.name).append(this.fontID, rhs.fontID)
                .append(this.size, rhs.size).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.name).append(this.fontID).append(this.size)
                .hashCode();
    }

}
