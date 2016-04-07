/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2011-2013 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   Hai Dong

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting 
04/Jun/13  a23126                CCMPD01780442 [iTM 6.0.2] Update metadata to split 
                                               language pack size and font pack size.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * Model object for ItmLanguage
 */
public class ItmLanguage extends BaseObject
{
    private static final long serialVersionUID = 1L;

    private String languageName;
    private int size;
    private String fontID;

    /**
     * 
     * @return String
     */
    public String getLanguageName()
    {
        return languageName;
    }

    /**
     * setter
     * 
     * @param languageName
     */
    public void setLanguageName(String languageName)
    {
        this.languageName = languageName;
    }

    /**
     * 
     * @return int
     */
    public int getSize()
    {
        return size;
    }

    /**
     * setter
     * 
     * @param size
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    public String getFontID()
    {
        return fontID;
    }

    public void setFontID(String fontID)
    {
        this.fontID = fontID;
    }

    /**
     * Check if the obect o equals the ItmLanguage object
     * 
     * @see com.mot.dm.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ItmLanguage == false)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        ItmLanguage rhs = (ItmLanguage) o;
        return new EqualsBuilder().append(this.languageName, rhs.languageName)
                .append(this.fontID, rhs.fontID).append(this.size, rhs.size).isEquals();
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
        return new HashCodeBuilder().append(languageName).append(size).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb =
                new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                        "languageName", this.languageName).append("fontID", this.fontID)
                        .append("size", this.size);
        return sb.toString();
    }

}
