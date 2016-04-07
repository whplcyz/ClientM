package com.mot.dm.common.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LicenseFileDetail extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String deviceID;
    private String featureName;
    private int featureCount;
    private boolean isValid;
    
    public boolean getIsValid()
    {
        return isValid;
    }

    public void setIsValid(boolean isValid)
    {
        this.isValid = isValid;
    }
    
    public int getFeatureCount()
    {
        return featureCount;
    }
    
    public void setFeatureCount(int featureCount)
    {
        this.featureCount = featureCount;
    }
    /*private CapacityLicensesInfo capacityLicensesInfo;
    
    public CapacityLicensesInfo getCapacityLicensesInfo()
    {
        return capacityLicensesInfo;
    }
    
    public void setCapacityLicensesInfo(CapacityLicensesInfo capacityLicensesInfo)
    {
        this.capacityLicensesInfo = capacityLicensesInfo;
    }*/
    
    public String getDeviceID()
    {
        return deviceID;
    }
    
    public void setDeviceID(String deviceID)
    {
        this.deviceID = deviceID;
    }
    
    public String getFeatureName()
    {
        return featureName;
    }
    
    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("deviceID", this.deviceID)
        .append("featureName", this.featureName);             
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LicenseFileDetail other = (LicenseFileDetail) obj;

        if (featureName == null)
        {
            if (other.featureName != null)
                return false;
        }
        else if (!featureName.equals(other.featureName))
            return false;
        if (deviceID == null)
        {
            if (other.deviceID != null)
                return false;
        }
        else if (!deviceID.equals(other.deviceID))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(deviceID).append(featureName)
        .hashCode();
    }
}
