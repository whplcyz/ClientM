/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  ckfm01    10-04-2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
10/04/08   Kim Mortensen                        fixing Issues after FTR.
09/12/08   Sigurdur Jonsson       CCMPD01137090 APIs for iTM.
20/Jan/09  Michael Leismann      CCMPD01146532 Moved Radio Type variable and
                                               get/set methods to this class
                                               from the RadioParameters class
30/Mar/09  Sigurdur Jonsson      CCMPD01188992 Import Templates via XML.
16/06/10   Kim Mortensen         CCMPD01357654 Adding Language pack code. 
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting    
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.DeviceAdditionalAttributes;
import com.mot.dm.common.model.JobInfo;

/**
 * This class is used to contain all information pr. device found in the XML.
 * The XML file only contains strings, therefore the XML Device will mostly
 * contain strings or list of strings.
 * 
 * @author ckfm01
 * 
 */
public class XmlDevice extends RadioParameters
{
    private String m_templatePath = null;
    private String m_softwareVersion = null;
    private String m_softwareVersionCH = null;
    private String m_agency = null;
    private String m_newTemplateName = null;
    private String m_newTemplateCodeplugFullPath = null;
    private String m_errorDescription = "";
    private List<String> applyProfileList = null;
    private JobInfo pendingLicenseJobinfo = null;
    
    private List<String> m_featurStrings = null;
    private String m_radioType = null;
    private boolean newRadioFlag = true;
    private boolean radioExistFlag = true;
    private int m_deviceType = Device.IS_A_RADIO;
    private Map<String, String> configurableAttributesMap = null;
    private List<DeviceAdditionalAttributes> deviceAdditionalAttributesList = null;

    /**
     * This method returns the template path
     * 
     * @return template path
     */
    public String getTemplatePath()
    {
        return m_templatePath;
    }

    /**
     * This method sets the template path
     * 
     * @param path
     *            is the template path
     */
    public void setTemplatePath(String path)
    {
        m_templatePath = path;
    }
    
    /**
     * This method returns the list of applied file path
     * 
     * @return template path
     */
    public List<String> getProfileFileList()
    {
        return applyProfileList;
    }

    /**
     * This method sets the list of applied file path
     * 
     * @param path
     *            is the template path
     */
    public void setProfileFileList(List<String> prfList)
    {
    	applyProfileList = prfList;
    }

    /**
     * This method sets the software version
     * 
     * @param version
     *            is the software version
     */
    public void setSoftwareVersion(String version)
    {
        m_softwareVersion = version;
    }

    /**
     * This method returns the software version
     * 
     * @return software version
     */
    public String getSoftwareVersion()
    {
        return m_softwareVersion;
    }

    /**
     * This method returns CM5000 radio control head software version
     * 
     * @return control head software version
     */
    public String getSoftwareVersionControlHead()
    {
        return m_softwareVersionCH;
    }

    /**
     * This method sets the CM5000 radio control head software version
     */
    public void setSoftwareVersionControlHead(String version)
    {
        m_softwareVersionCH = version;
    }

    /**
     * This method returns the Agency
     * 
     * @return agency
     */
    public String getAgency()
    {
        return m_agency;
    }

    /**
     * This method sets the Agency
     * 
     * @param m_agency
     *            is the agency
     */
    public void setAgency(String m_agency)
    {
        this.m_agency = m_agency;
    }

    /**
     * This method returns the feature strings
     * 
     * @return the feature strings
     */
    public List<String> getFeatureStrings()
    {
        return m_featurStrings;
    }

    /**
     * This method returns the feature strings
     * 
     * @param m_featureStrings
     *            is the feature strings to set
     */
    public void setFeatureStrings(List<String> m_featurStrings)
    {
        this.m_featurStrings = m_featurStrings;
    }

    /**
     * This method sets the radio type as text
     * 
     * @param p_radioType
     *            is the radio type to set
     */
    public void setRadioType(String p_radioType)
    {
        m_radioType = p_radioType;
    }

    /**
     * This method returns the radio type as a string
     * 
     * @return radio type as a string
     */
    public String getRadioType()
    {
        return m_radioType;
    }

    /**
     * This method returns a flag indicating if this is a new device or a job
     * for existing device
     * 
     * @return true if this is a new radio
     */
    public boolean getNewRadioFlag()
    {
        return newRadioFlag;
    }

    /**
     * This method sets the flag to indicate if this is a new radio or a job for
     * existing radio
     * 
     * @param inNewRadioFlag
     *            , if true then this is a new radio, if false this is a job for
     *            an existing radio.
     */
    public void setNewRadioFlag(boolean inNewRadioFlag)
    {
        this.newRadioFlag = inNewRadioFlag;
    }

    /**
     * This method returns the new Template name as a string
     * 
     * @return Template name as a string
     */
    public String getNewTemplateName()
    {
        return m_newTemplateName;
    }

    /**
     * This method sets the new Template name as a string
     * 
     * @param templateName
     *            is the Template name as a string
     */
    public void setNewTemplateName(String templateName)
    {
        m_newTemplateName = templateName;
    }
    
    
    /**
     * This method returns the new device type as a int
     * 
     * @return Device type as a integer
     */
    public int getDeviceType()
    {
        return m_deviceType;
    }

    /**
     * This method sets the new Template name as a string
     * 
     * @param templateName
     *            is the Template name as a string
     */
    public void setDeviceType(int deviceType)
    {
    	m_deviceType = deviceType;
    }

    /**
     * This method returns the new Template code-plug path as a string
     * 
     * @return Template code-plug path as a string
     */
    public String getNewTemplateCodeplugFullPath()
    {
        return m_newTemplateCodeplugFullPath;
    }

    /**
     * This method sets the new Template code-plug path as a string
     * 
     * @param templateCodeplugFullPath
     *            is the Template code-plug path as a string
     */
    public void setNewTemplateCodeplugFullPath(String templateCodeplugFullPath)
    {
        m_newTemplateCodeplugFullPath = templateCodeplugFullPath;
    }

    /**
     * This method returns an error string for this XML element (used for
     * templates)
     * 
     * @return Error string for this XML element
     */
    public String getErrorDescription()
    {
        return m_errorDescription;
    }

    /**
     * This method sets an error string for this XML element (used for
     * templates)
     * 
     * @param description
     *            is the error string for this XML element
     */
    public void setErrorDescription(String description)
    {
        m_errorDescription = description;
    }

    public void setPendingLicenseJobinfo(JobInfo pendingLicenseJobinfo)
    {
        this.pendingLicenseJobinfo = pendingLicenseJobinfo;
    }

    public JobInfo getPendingLicenseJobinfo()
    {
        return pendingLicenseJobinfo;
    }

    public void setRadioExistFlag(boolean radioExistFlag)
    {
        this.radioExistFlag = radioExistFlag;
    }

    public boolean isRadioExistFlag()
    {
        return radioExistFlag;
    }

    public Map<String, String> getConfigurableAttributesMap()
    {
        return configurableAttributesMap;
    }

    public void setConfigurableAttribute(String attribueType, String attributeValue)
    {
        if(configurableAttributesMap == null)
        {
            configurableAttributesMap = new HashMap<String, String>();
        }
        
        configurableAttributesMap.put(attribueType, attributeValue);
    }

    public List<DeviceAdditionalAttributes> getDeviceAdditionalAttributesList()
    {
        return deviceAdditionalAttributesList;
    }

    public void setDeviceAdditionalAttributes(String attributeUuid, String attributeValue)
    {
        if(deviceAdditionalAttributesList == null)
        {
            deviceAdditionalAttributesList = new ArrayList<DeviceAdditionalAttributes>();
        }
        
        DeviceAdditionalAttributes daa = new DeviceAdditionalAttributes();
        daa.setAttributeTypeUuid(attributeUuid);
        daa.setAttributeValue(attributeValue);
        deviceAdditionalAttributesList.add(daa);
    }
}
