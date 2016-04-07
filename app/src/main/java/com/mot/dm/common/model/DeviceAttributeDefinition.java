package com.mot.dm.common.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DeviceAttributeDefinition extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String attributeTypeUuid;
    private String attributesTypeName;    
    private String preDefineValues;
    private boolean allowInputEmpty = true;
    private boolean xmlImportEnable;
    private boolean xmlExportEnable;
    private boolean operationLogEnable;
    private int displayOrder = 0;
    private boolean inUsing;

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
        
        if (!(o instanceof DeviceAttributeDefinition))
        {
            return false;
        }
        
        return EqualsBuilder.reflectionEquals(this, (DeviceAttributeDefinition)o);
    }

    @Override
    public int hashCode()
    {
        // TODO Auto-generated method stub
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String getAttributeTypeUuid()
    {
        return attributeTypeUuid;
    }

    public void setAttributeTypeUuid(String attributeTypeUuid)
    {
        this.attributeTypeUuid = attributeTypeUuid;
    }

    public String getAttributeTypeName()
    {
        return attributesTypeName;
    }

    public void setAttributeTypeName(String attributeTypeName)
    {
        this.attributesTypeName = attributeTypeName;
    }

    public boolean isAllowInputEmpty()
    {
        return allowInputEmpty;
    }

    public void setAllowInputEmpty(boolean allowInputEmpty)
    {
        this.allowInputEmpty = allowInputEmpty;
    }

    public boolean isXmlImportEnable()
    {
        return xmlImportEnable;
    }

    public void setXmlImportEnable(boolean xmlImportEnable)
    {
        this.xmlImportEnable = xmlImportEnable;
    }

    public boolean isXmlExportEnable()
    {
        return xmlExportEnable;
    }

    public void setXmlExportEnable(boolean xmlExportEnable)
    {
        this.xmlExportEnable = xmlExportEnable;
    }

    public String getPreDefineValues()
    {
        return preDefineValues;
    }

    public void setPreDefineValues(String preDefineValues)
    {
        this.preDefineValues = preDefineValues;
    }

    public int getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    public boolean isInUsing()
    {
        return inUsing;
    }

    public void setInUsing(boolean inUsing)
    {
        this.inUsing = inUsing;
    }

    public boolean isOperationLogEnable()
    {
        return operationLogEnable;
    }

    public void setOperationLogEnable(boolean operationLogEnable)
    {
        this.operationLogEnable = operationLogEnable;
    }
    
    public void removeFromList(List<DeviceAttributeDefinition> deviceAttributeDefinitionList)
    {
        for(int i = 0; i< deviceAttributeDefinitionList.size(); i++)
        {
            DeviceAttributeDefinition dad = deviceAttributeDefinitionList.get(i);
            if(dad.getAttributeTypeUuid().equals(attributeTypeUuid))
            {
                deviceAttributeDefinitionList.remove(i);
                break;
            }
        }
    }
}
