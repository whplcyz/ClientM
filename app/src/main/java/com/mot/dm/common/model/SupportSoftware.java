/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2013 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001 16/09/09

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
16/sep/09  Hai Dong              CCMPD01259651 New radio type    
05/08/10   Hai Dong              ccmpd01378024 Add support software table.                                           
10/Dec/10  Michael Leismann      CCMPD01452485 Clean up
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting 
22/Jun/11  Hai Dong              CCMPD01524144 Cross group copy
04/Jun/13  a23126                CCMPD01780442 [iTM 6.0.2] Update metadata to split 
                                               language pack size and font pack size.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/*
 * This object is created for mapping to a database table.
 */
public class SupportSoftware extends BaseObject
{
    /*
     * The label like MR5.9
     */
    private String releaseLabel;
    private String buildType;

    private List<ItmLanguageFont> languageFonts = new ArrayList<ItmLanguageFont>();
    private List<ItmModelType> itmModelTypes;
    private List<String> extModelTypes;

    public String getBuildType()
    {
        return buildType;
    }

    public void setBuildType(String buildType)
    {
        this.buildType = buildType;
    }

    public List<String> getExtModelTypes()
    {
        return extModelTypes;
    }

    public void setExtModelTypes(List<String> extModelTypes)
    {
        this.extModelTypes = extModelTypes;
    }

    // sw_version_build_ID (PK) in database, for example, 07
    private String swVersionBuildId;

    // sw_version_release (PK) in database, for example 5620
    private String swVersionRelease;

    /*
     * This is the cps codeplug version matching to the software
     */
    private String codeplugVersion;

    private static final long serialVersionUID = 1L;

    private List<ItmLanguage> languages = new ArrayList<ItmLanguage>();

    private int languageSizeLimit;

    public int getLanguageSizeLimit()
    {
        return languageSizeLimit;
    }

    public void setLanguageSizeLimit(int languageSizeLimit)
    {
        this.languageSizeLimit = languageSizeLimit;
    }

    /**
     * @return List<ItmLanguage>
     */
    public List<ItmLanguage> getLanguages()
    {
        if (languages.size() == 0)
            return languages;
        /**
         * Maybe we don't need to sort the language in model. TODO: remove the
         * sorting if dao layer can sort them
         */
        List<String> ll = new ArrayList<String>();
        for (int ii = 0; ii < languages.size(); ii++)
        {
            ItmLanguage il = languages.get(ii);
            ll.add(il.getLanguageName());
        }
        Collections.sort(ll);

        List<ItmLanguage> newList = new ArrayList<ItmLanguage>();
        for (int ii = 0; ii < ll.size(); ii++)
        {
            String name = ll.get(ii);
            for (int jj = 0; jj < languages.size(); jj++)
            {
                ItmLanguage il = languages.get(jj);
                if (name.equalsIgnoreCase(il.getLanguageName()))
                {
                    newList.add(il);
                    break;
                }
            }
        }

        return newList;
    }

    /**
     * setter
     * 
     * @param languages
     */
    public void setLanguages(List<ItmLanguage> languages)
    {
        this.languages = languages;
    }
    
    public List<ItmLanguageFont> getLanguageFonts()
    {
        return languageFonts;
    }

    public void setLanguageFonts(List<ItmLanguageFont> languageFonts)
    {
        this.languageFonts = languageFonts;
    }

    /*
     * Getter
     * 
     * @return String
     */
    public String getSwVersionBuildId()
    {
        return swVersionBuildId;
    }

    /*
     * Set the build id
     * 
     * @param swVersionBuildId String
     */
    public void setSwVersionBuildId(String swVersionBuildId)
    {
        this.swVersionBuildId = swVersionBuildId;
    }

    /*
     * Getter
     * 
     * @return String
     */
    public String getCodeplugVersion()
    {
        return codeplugVersion;
    }

    /*
     * Set the cp version
     * 
     * @param codeplugVersion String
     */
    public void setCodeplugVersion(String codeplugVersion)
    {
        this.codeplugVersion = codeplugVersion;
    }

    /*
     * Getter
     * 
     * @return String
     */
    public String getSwVersionRelease()
    {
        return swVersionRelease;
    }

    /*
     * Setter
     * 
     * @param swVersionRelease String
     */
    public void setSwVersionRelease(String swVersionRelease)
    {
        this.swVersionRelease = swVersionRelease;
    }

    /*
     * Getter
     * 
     * @return String
     */
    public String getReleaseLabel()
    {
        return releaseLabel;
    }

    /*
     * Setter
     * 
     * @param releaseLabel String
     */
    public void setReleaseLabel(String releaseLabel)
    {
        this.releaseLabel = releaseLabel;
    }

    /*
     * Get the software name like R07xxx5715. here we don't care about
     * encryption type
     * 
     * @return String
     */
    public String getName()
    {
        return buildType + swVersionBuildId + "xxx" + swVersionRelease;
    }

    /*
     * Get the software type
     * 
     * @return String
     */
    public String getSoftType()
    {

        if (itmModelTypes == null || itmModelTypes.size() == 0)
        {
            return null;
        }

        String name = "";
        for (int ii = 0; ii < itmModelTypes.size(); ii++)
        {
            if (name.length() == 0)
                name = name + itmModelTypes.get(ii).getItmModelTypeName();
            else
                name = name + ", " + itmModelTypes.get(ii).getItmModelTypeName();
        }

        return name;
    }

    /*
     * Get the model types
     * 
     * @return List<ItmModelType>
     */
    public List<ItmModelType> getItmModelTypes()
    {
        return itmModelTypes;
    }

    /*
     * Set the model types
     * 
     * @param itmModelTypes List<ItmModelType>
     */
    public void setItmModelTypes(List<ItmModelType> itmModelTypes)
    {
        this.itmModelTypes = itmModelTypes;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("codeplugVersion", this.codeplugVersion)
                .append("languages", this.languages)
                .append("itmModelTypes", this.itmModelTypes)
                .append("softType", this.getSoftType())
                .append("buildType", this.buildType)
                .append("languageSizeLimit", this.languageSizeLimit)
                .append("releaseLabel", this.releaseLabel)
                .append("swVersionRelease", this.swVersionRelease)
                .append("languageFonts", this.languageFonts)
                .append("name", this.getName())
                .append("extModelTypes", this.extModelTypes)
                .append("swVersionBuildId", this.swVersionBuildId)
                .append("id", this.getId())
                .toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(907593731, -1641096991)
                .append(this.buildType)
                .append(this.languages)
                .append(this.languageSizeLimit)
                .append(this.languageFonts)
                .append(this.releaseLabel)
                .append(this.itmModelTypes)
                .append(this.codeplugVersion)
                .append(this.swVersionRelease)
                .append(this.extModelTypes)
                .append(this.swVersionBuildId)
                .toHashCode();
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
        if (!(object instanceof SupportSoftware))
        {
            return false;
        }
        SupportSoftware rhs = (SupportSoftware) object;
        return new EqualsBuilder()
                .append(this.buildType, rhs.buildType)
                .append(this.languages, rhs.languages)
                .append(this.languageSizeLimit, rhs.languageSizeLimit)
                .append(this.languageFonts, rhs.languageFonts)
                .append(this.releaseLabel, rhs.releaseLabel)
                .append(this.itmModelTypes, rhs.itmModelTypes)
                .append(this.codeplugVersion, rhs.codeplugVersion)
                .append(this.swVersionRelease, rhs.swVersionRelease)
                .append(this.extModelTypes, rhs.extModelTypes)
                .append(this.swVersionBuildId, rhs.swVersionBuildId)
                .isEquals();
    }

}