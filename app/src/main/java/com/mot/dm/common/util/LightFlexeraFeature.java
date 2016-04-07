package com.mot.dm.common.util;

public class LightFlexeraFeature
{
    public enum FlexeraLicenseType
    {
        MAIN_LICENSE,
        SYSTEM_ON_OFF_LICENSE,
        SYSTEM_CAPACITY_LICENSE,
        RADIO_FEATURE_SITE_WIDE_LICENSE,
        RADIO_FEATURE_TEI_LOCKED_LICENSE,
    };

    private String featureID;
    private FlexeraLicenseType flexeraLicenseType;
    private String featureName;

    public LightFlexeraFeature(String featureID, FlexeraLicenseType flexeraLicenseType,
            String featureName)
    {
        this.featureID = featureID;
        this.flexeraLicenseType = flexeraLicenseType;
        this.featureName = featureName;
    }

    public void setFeatureID(String featureID)
    {
        this.featureID = featureID;
    }

    public String getFeatureID()
    {
        return featureID;
    }

    public void setFlexeraLicenseType(FlexeraLicenseType flexeraLicenseType)
    {
        this.flexeraLicenseType = flexeraLicenseType;
    }

    public FlexeraLicenseType getFlexeraLicenseType()
    {
        return flexeraLicenseType;
    }

    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
    }

    public String getFeatureName()
    {
        return featureName;
    }
}
