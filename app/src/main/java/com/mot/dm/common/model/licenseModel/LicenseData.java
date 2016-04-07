/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                  Copyright 2011-2013 Motorola Solutions Inc.                 |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  ITM     
 CODED     BY:  pkt483    2008.01.11

 ------------------------------- REVISION HISTORY -------------------------------
 16/Mar/09  pkt483          CCMPD01181873 Creation
 01/10/10   ckfm01          CCMPD01398027 Adding MotoLM Licensing
 11/11/10   ckfm01          CCMPD01409592 Licensing part II                                                            
 06/Dec/10  ckfm01          CCMPD01450107 Updated for test possibility.                                                            
 08/12/10   ckfm01          CCMPD01448983 Small fixes. 
 14/12/10   cjh102          CCMPD01453332 License expiry
 02/Mar/11  Kim Mortensen   CCMPD01471488 Fixing klocwork findings.   
 9/06/11    cjh102          CCMPD01476570 Updates for license exceptions
 21/Jun/13  tqfn38          CCMPD01783643 Change Request 2659 - Picture Push and Pull policies have to be licensed in iTM                                                        
 -------------------------------------------------------------------------------*/
package com.mot.dm.common.model.licenseModel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.mot.dm.common.model.BaseObject;

/**
 * Stores data from license.lic file about licensing server name, host id,
 * feature licenses information and digital sign
 * 
 * @author pkt483
 */

public class LicenseData extends BaseObject 
{   
    /**
     * 
     */
    private static final long serialVersionUID = 7952964346092233971L;
    
    /*1.Assign when start server, add or remove license*/
    /*key is FeatureID, value is number of NumberTypeLicense)*/
    public Map<String,NumberTypeLicense> systemCapacityLicMap = new HashMap<String,NumberTypeLicense>();
    /*key is FeatureID, value is OnOffTypeLicense*/
    public Map<String,OnOffTypeLicense> systemNoCapacityLicMap = new HashMap<String,OnOffTypeLicense>();
    /*key is FeatureID, value is OnOffTypeLicense*/
    public Map<String,OnOffTypeLicense> siteWideRadioSellingFeatureLicMap = new HashMap<String,OnOffTypeLicense>(); 
     
    /*This map is not loaded in memory*/ 
    /*key is FeatureID, value is number of NumberTypeLicense)*/
    public Map<String,NumberTypeLicense> teiBindRadioSellingFeatureMap = new HashMap<String,NumberTypeLicense>(); 
          
    public boolean isSystemLicenceEnabled(String systemFeatureId)
    {
        if(systemNoCapacityLicMap.containsKey(systemFeatureId))
        {
            return systemNoCapacityLicMap.get(systemFeatureId).isEnabled;
        }
        return false;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id", this.getId())
                .toString();
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
        if (!(object instanceof LicenseData))
        {
            return false;
        }
        LicenseData rhs = (LicenseData) object;
        return new EqualsBuilder()
                .append(this.teiBindRadioSellingFeatureMap, rhs.teiBindRadioSellingFeatureMap)
                .append(this.systemCapacityLicMap, rhs.systemCapacityLicMap)
                .append(this.systemNoCapacityLicMap, rhs.systemNoCapacityLicMap)
                .append(this.siteWideRadioSellingFeatureLicMap,
                        rhs.siteWideRadioSellingFeatureLicMap)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(866921813, -618386013)
                .append(this.teiBindRadioSellingFeatureMap)
                .append(this.systemCapacityLicMap)
                .append(this.systemNoCapacityLicMap)
                .append(this.siteWideRadioSellingFeatureLicMap)
                .toHashCode();
    }     
}
