/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  wch378 28/3/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/3/14    wch378                CCMPD01876846 develop flexera license
25/June/14 wch378               CCMPD01905766  Flexera license feature iteration2
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FlexeraFeature extends BaseObject
{
    private static final long serialVersionUID = 4807171258012369355L;

    private String featureID;
    private String featureName;
    private String featureShortName;
    private String siteWideFeatureID;
    private String siteWideFeatureName;
    private String dependOn;
    private String excludeOf;
    private String hardwareDependsOn;
   
    public String getHardwareDependsOn()
    {
        return hardwareDependsOn;
    }

    public void setHardwareDependsOn(String hardwareDependsOn)
    {
        this.hardwareDependsOn = hardwareDependsOn;
    }

    public String getFeatureID()
    {
        return featureID;
    }

    public void setFeatureID(String featureID)
    {
        this.featureID = featureID;
    }

    public String getFeatureName()
    {
        return featureName;
    }

    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
        setFeatureShortName(getShortName(featureName));
    }
    
    public String getFeatureShortName()
    {
        return featureShortName;
    }

    private void setFeatureShortName(String featureShortName)
    {
        this.featureShortName = featureShortName;
    }


    public String getSiteWideFeatureID()
    {
        return siteWideFeatureID;
    }

    public void setSiteWideFeatureID(String siteWideFeatureID)
    {
        this.siteWideFeatureID = siteWideFeatureID;
    }

    public String getSiteWideFeatureName()
    {
        return siteWideFeatureName;
    }

    public void setSiteWideFeatureName(String siteWideFeatureName)
    {
        this.siteWideFeatureName = siteWideFeatureName;
    }
   
    public String getDependOn()
    {
        return dependOn;
    }

    public void setDependOn(String dependOn)
    {
        this.dependOn = dependOn;
    }

    public String getExcludeOf()
    {
        return excludeOf;
    }

    public void setExcludeOf(String excludeOf)
    {
        this.excludeOf = excludeOf;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb =
                new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                        .append("featureID", this.featureID)
                        .append("featureName", this.featureName)
                        .append("siteWideFeatureID", this.siteWideFeatureID)
                        .append("siteWideFeatureName", this.siteWideFeatureName)
                        .append("dependOn", this.dependOn).append("excludeOf", this.excludeOf);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (!(other instanceof FlexeraFeature))
        {
            return false;
        }
        FlexeraFeature rhs = (FlexeraFeature) other;

        return new EqualsBuilder().append(featureID, rhs.featureID)
                .append(featureName, rhs.featureName)
                .append(siteWideFeatureID, rhs.siteWideFeatureID)
                .append(siteWideFeatureName, rhs.siteWideFeatureName)
                .append(dependOn, rhs.dependOn).append(excludeOf, rhs.excludeOf).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(featureID)
                .append(featureName)
                .append(siteWideFeatureID).append(siteWideFeatureName)
                .append(dependOn).append(excludeOf)
                .hashCode();
    }
    
    /**
     * Returns a four-letter string derived from the parameter fullName
     * 
     * @param fullName
     *            , the full name which is to be shortened
     * @return shortName, four-letter (abbreviated) name corresponding to the
     *         fullName.
     * 
     *         Following algorithm is used to shorten the fullName : 1. Split
     *         the words by "whitespace" and "/" delimiter's. 2. If there are
     *         four words take first letter from each 3. If there are 3 words
     *         take 2 from first word and one from each other words. 4. If there
     *         are 2 words take 2 from each word 5. If there is only 1 word take
     *         all four from the one word, if the word has more than 4 ,
     *         otherwise take the full word.
     * 
     */
    private String getShortName(String fullName)
    {
        if (fullName == null || fullName.trim().length() <= 0)
            return null;

        String shortName = null;

        // Split using two delimiter's for now:
        // 1. White-space characters. 2. "/" Forward slash.
        String[] splitFullName = fullName.split("[\\s+\\/]");

        if (splitFullName.length >= 4)
        {
            shortName = splitFullName[0].substring(0, 1) + splitFullName[1].substring(0, 1)
                    + splitFullName[2].substring(0, 1) + splitFullName[3].substring(0, 1);
        }
        else if (splitFullName.length == 3)
        {
            shortName = splitFullName[0].substring(0, 2) + splitFullName[1].substring(0, 1)
                    + splitFullName[2].substring(0, 1);
        }
        else if (splitFullName.length == 2)
        {
            shortName = splitFullName[0].substring(0, 2) + splitFullName[1].substring(0, 2);
        }
        else if (splitFullName.length == 1)
        {
            if (splitFullName[0].length() > 4)
                shortName = splitFullName[0].substring(0, 4);
            else
                shortName = splitFullName[0];
        }
        return shortName;
    }

}
