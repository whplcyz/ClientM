/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2014 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  David Chen

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Apr/14  a21944                CCMPD01881928 iTM7.0  Folder Refresh Performance Enhance 
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

public abstract class DeviceCommon extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = -6352755050880096162L;
    public static int IS_A_RADIO = 0;
    public static int IS_A_TEMPLATE = 1;
    public static int IS_A_PROFILE = 2;
    protected String deviceUuid;
    protected String deviceId;
    protected String logicalId;
    protected String deviceName;
    protected String serialNumber;
    protected String codeplugVersion;
    protected int guiRadioState;  //this value is calculate out for display, not from database
    protected int codeplugModified = 0;
    protected int templateRadioFlag;
    protected String templateName = "";
    protected int templateModified = 0;
    protected String templateCodeplugVersion = "";
    protected String notes = "";
    protected String itmModelTypeId = "";
    protected String extModelType = "";
    protected String softwareVersion = "";

    public DeviceCommon()
    {
        super();
    }
       
    public String getCodeplugVersion()
    {
        return codeplugVersion;
    }
    
    public void setCodeplugVersion(String codeplugVersion)
    {
        this.codeplugVersion = codeplugVersion;
    }

    public String getSoftwareVersion()
    {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion)
    {
        this.softwareVersion = softwareVersion;
    }

    public String getTemplateCodeplugVersion()
    {
        return templateCodeplugVersion;
    }

    public void setTemplateCodeplugVersion(String p_TemplateCodeplugVersion)
    {
        templateCodeplugVersion = p_TemplateCodeplugVersion;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String p_TemplateName)
    {
        templateName = p_TemplateName;
    }

    public void setTemplateModified(int modified)
    {
        if (modified == 1 || modified == 0)
            templateModified = modified;
    }

    public int getTemplateModified()
    {
        return templateModified;
    }

    public void setCodeplugModified(int codeplugModified)
    {
        this.codeplugModified = codeplugModified;
    }

    public int getCodeplugModified()
    {
        return codeplugModified;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        if (deviceId != null)
            this.deviceId = deviceId.toUpperCase();
        else
            this.deviceId = null;
    }

    public String getLogicalId()
    {
        return logicalId;
    }

    public void setLogicalId(String logicalId)
    {
        this.logicalId = logicalId;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public int getTemplateRadioFlag()
    {
        return templateRadioFlag;
    }

    public void setTemplateRadioFlag(int templateRadioFlag)
    {
        if ((templateRadioFlag == DeviceCommon.IS_A_RADIO) 
        		|| (templateRadioFlag == DeviceCommon.IS_A_TEMPLATE)
        		|| (templateRadioFlag == DeviceCommon.IS_A_PROFILE))
        {
            this.templateRadioFlag = templateRadioFlag;
        }
        else
        { // default is radio
            this.templateRadioFlag = DeviceCommon.IS_A_RADIO;
        }
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * @param serialNumber
     *            the serialNumber to set
     */
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getDeviceUuid()
    {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUniId)
    {
        this.deviceUuid = deviceUniId;
    }

    public boolean isARadio()
    {
        return getTemplateRadioFlag() == IS_A_RADIO;
    }

    public boolean isATemplate()
    {
        return (getTemplateRadioFlag() == IS_A_TEMPLATE);
    }

    public boolean isATemplateOrProfile()
    {
        return ( isATemplate() || isAProfile());
    }

    public boolean isAProfile()
    {
    	return (getTemplateRadioFlag() == IS_A_PROFILE);
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getItmModelTypeId()
    {
        return itmModelTypeId;
    }

    public void setItmModelTypeId(String itmModelTypeId)
    {
        this.itmModelTypeId = itmModelTypeId;
    }

    public String getExtModelType()
    {
        return extModelType;
    }

    public void setExtModelType(String extModelType)
    {
        if (extModelType == null)
            this.extModelType = "";
        else
            this.extModelType = extModelType;
    }

    public boolean hasModifiedCodeplug()
    {
        if (codeplugModified == 1)
        {
    
            return true;
        }
        else
        {
    
            return false;
        }
    }

    public boolean isUnknownRadio()
    {
        return (this.itmModelTypeId.equals(ItmModelType.UnKnown_Radio_ITM_MODEL_TYPE_ID));
    }

}