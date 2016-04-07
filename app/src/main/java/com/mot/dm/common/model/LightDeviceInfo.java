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
05/May/14  a21944                CCMPD01889582 iTM7.0  Folder Refresh Performance Enhance 
13/Jun/2014  QNGV36             CCMPD01902714   [iTM7.0]Download/sync offline job cannot update policy priority
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mot.dm.common.util.BinEnc;
import com.mot.dm.common.util.CryptoSingleton;


/**
 * @author A21944
 *
 */
public class LightDeviceInfo extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -5233493921729344385L;
    private String deviceUuid;
    private String deviceName;
    private String deviceId;
    private String logicalId;
    private String serialNumber;
    private String itmModelTypeName;    
    private String lastJobUuid;
    private Integer jobStatus;
    private String policyUuids;
    private String policyPriorities;
    private String passwordValues;
    private String notes;
    
    /* (non-Javadoc)
     * @see com.mot.dm.common.model.BaseObject#toString()
     */
    @Override
    public String toString()
    {
        return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
    }

    /* (non-Javadoc)
     * @see com.mot.dm.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        return org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals(this, obj);
    }

    /* (non-Javadoc)
     * @see com.mot.dm.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode()
    {
        return org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(this);
    }

    public String getDeviceUuid()
    {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid)
    {
        this.deviceUuid = deviceUuid;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getLogicalId()
    {
        return logicalId;
    }

    public void setLogicalId(String logicalId)
    {
        this.logicalId = logicalId;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getItmModelTypeName()
    {
        return itmModelTypeName;
    }

    public void setItmModelTypeName(String itmModelTypeName)
    {
        this.itmModelTypeName = itmModelTypeName;
    }

    public String getLastJobUuid()
    {
        return jobStatus == null ? null : lastJobUuid;
    }

    public void setLastJobUuid(String lastJobUuid)
    {
        this.lastJobUuid = lastJobUuid;
    }

    public Integer getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String getPolicyUuids()
    {
        return policyUuids;
    }
    
    public String[] getPolicyUuidArray()
    {
        return policyUuids == null ? null : StringUtils.split(policyUuids, (char) 11);
    }
    
    public void setPolicyUuids(String policyUuids)
    {
        this.policyUuids = policyUuids;
    }
    
    public String getPolicyPriorities()
    {
        return policyPriorities;
    }
    
    public String[] getPolicyPrioritiesArray()
    {
        return policyPriorities == null ? null : StringUtils.split(policyPriorities, (char) 11);
    }
    
    public void setPolicyPriorities(String policyPriorities)
    {
        this.policyPriorities = policyPriorities;
    }

   

    public String getPasswordValues()
    {
        return passwordValues;
    }

    public List<String> getRadioCandidatePasswordList()
    {
    	List<String> pwdList = new ArrayList<String>();
    	
    	String[] pwdArray = passwordValues == null ? null : StringUtils.split(passwordValues, (char) 11);
        if(pwdArray != null)
        {
        	for(int i = 0; i < pwdArray.length; i++)
        	{
        		// decode and decrypt the value obtained
        		byte[] tmp = BinEnc.decode(pwdArray[i]);
            	if (tmp != null)
            	{
            		String pwdStr = CryptoSingleton.getInstance().decrypt(tmp);
            		if(pwdStr != null && !pwdList.contains(pwdStr))
            		{
            		    pwdList.add(pwdStr);
            		}
            	}
        	}
        }
        
        return pwdList;
    }

    public void setPasswordValues(String passwordValues)
    {
        this.passwordValues = passwordValues;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }    
}
