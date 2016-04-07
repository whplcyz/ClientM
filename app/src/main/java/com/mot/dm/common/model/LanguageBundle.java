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
15/Jun/10  Hai Dong              CCMPD01357654 Add language set  
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/*
 * Model object for LanguageBundle
 */
public class LanguageBundle extends BaseObject
{
    private static final long serialVersionUID = 1L;

    private String bundleId;

    private String bundleName;

    private String languages;

    /*
     * Default constructor required by hibernate
     */
    public LanguageBundle()
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
        if (o instanceof LanguageBundle == false)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        LanguageBundle rhs = (LanguageBundle) o;
        return new EqualsBuilder().append(this.bundleName, rhs.getBundleName())
                .append(languages, rhs.getLanguages()).append(bundleId, rhs.getBundleId())
                .isEquals();
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
        return new HashCodeBuilder().append(bundleId).append(bundleName).append(languages)
                .hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("bundleId", this.bundleId).append("bundleName", this.bundleName)
                .append("languages", this.languages);
        return sb.toString();
    }

    /*
     * Get the language bundle name
     * 
     * @return String
     */
    public String getBundleName()
    {
        return bundleName;
    }

    /*
     * Set the language bundle name
     * 
     * @param bundleName String
     */
    public void setBundleName(String bundleName)
    {
        this.bundleName = bundleName;
    }

    /*
     * Get the languages
     * 
     * @return String
     */
    public String getLanguages()
    {
        return languages;
    }

    /*
     * Set the languages
     * 
     * @param languages String
     */
    public void setLanguages(String languages)
    {
        this.languages = languages;
    }

    /*
     * Get the bundle id
     * 
     * @return String
     */
    public String getBundleId()
    {
        return bundleId;
    }

    /*
     * Set the bundle id
     * 
     * @param bundleId String
     */
    public void setBundleId(String bundleId)
    {
        this.bundleId = bundleId;
    }
}
