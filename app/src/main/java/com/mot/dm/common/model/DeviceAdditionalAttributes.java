package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DeviceAdditionalAttributes extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = -8518482954959342370L;

    private String deviceUuid;
    private String attributeTypeUuid;
    private String attributeValue;
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        
        if (o == null)
        {
            return false;
        }
        
        if (!(o instanceof DeviceAdditionalAttributes))
        {
            return false;
        }
        
        DeviceAdditionalAttributes rhs = (DeviceAdditionalAttributes) o;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String getDeviceUuid()
    {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid)
    {
        this.deviceUuid = deviceUuid;
    }

    public String getAttributeTypeUuid()
    {
        return attributeTypeUuid;
    }

    public void setAttributeTypeUuid(String attributeTypeUuid)
    {
        this.attributeTypeUuid = attributeTypeUuid;
    }

    public String getAttributeValue()
    {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }

}
