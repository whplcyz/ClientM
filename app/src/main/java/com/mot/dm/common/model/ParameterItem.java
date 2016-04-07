package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ParameterItem extends BaseObject {  
    
    public static final String MAX_NUMBER_ATTRIBUTE_NAME = "maxNumber";
    /**
     * 
     */
    private static final long serialVersionUID = -7141855129987989042L;
    private String attributeName;
    private String attributeValue;
    private String paramterName;
    private boolean isSupport;
	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}
	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	/**
	 * @return the paramterName
	 */
	public String getParamterName() {
		return paramterName;
	}
	/**
	 * @param paramterName the paramterName to set
	 */
	public void setParamterName(String paramterName) {
		this.paramterName = paramterName;
	}
    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("attributeName", this.attributeName);
        sb.append("attributeValue", this.attributeValue);
        sb.append("paramterName", this.paramterName);

        return sb.toString();
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof ParameterItem))
            return false;

        ParameterItem spAttributeItem = (ParameterItem) o;
        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.attributeName, spAttributeItem.attributeName);
        builder.append(this.attributeValue, spAttributeItem.attributeValue);
        builder.append(this.paramterName, spAttributeItem.paramterName);;

        return builder.isEquals();
    }
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(attributeName).append(attributeValue).append(paramterName)
                .hashCode();
    }
    /**
     * @return the isSupport
     */
    public boolean getIsSupport()
    {
        return isSupport;
    }
    /**
     * @param isSupport the isSupport to set
     */
    public void setIsSupport(boolean isSupport)
    {
        this.isSupport = isSupport;
    } 
}
