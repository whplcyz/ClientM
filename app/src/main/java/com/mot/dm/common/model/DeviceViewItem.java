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
04/08/14   tqfn38                CCMPD01913521 Flexera license development  
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;


import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
/**
 * @author A21944
 *
 */
public class DeviceViewItem extends DeviceCommon
{

    private static final long serialVersionUID = 1662669875357081620L;
    
    public DeviceViewItem()
    {
        super();
    }
    public DeviceViewItem(Device device)
    {
        super();
        deviceUuid = device.deviceUuid;
        deviceId = device.deviceId;
        logicalId = device.logicalId;
        deviceName = device.deviceName;
        serialNumber = device.serialNumber;
        codeplugModified = device.codeplugModified;
        templateRadioFlag = device.templateRadioFlag;
        templateName = device.templateName;
        templateModified = device.templateModified;
        templateCodeplugVersion = device.templateCodeplugVersion;
        notes = device.notes;
        itmModelTypeId = device.itmModelTypeId;
        extModelType = device.extModelType;
        softwareVersion = device.softwareVersion;
       
        agencyUuid = device.getAgency().getAgencyUuid();
        codeplugVersion = device.getCurrentCodeplug() == null ? null : device.getCurrentCodeplug().getCodeplugVersion();
        cpRepositoryVersion = device.getCurrentCodeplug() == null ? null : device.getCurrentCodeplug().getCodeplugRepositoryVersion();
        itmModelTypeName = device.getItmModelType() == null ? null : device.getItmModelType().getItmModelTypeName();
        jobStatus = device.getLastJob() == null ? null : device.getLastJob().getJobStatus();
        
        if( device.getPolicyDeviceMapList() ==null)
        {
            policyNames = null;
        }
        else
        {
            ArrayList<String>  s = new ArrayList<String>();
            for(PolicyDeviceMap pd : device.getPolicyDeviceMapList())
            {
                s.add(pd.getPolicy().getName());
            }
            policyNames = StringUtils.join(s.toArray(), (char) (11));
        }
    }

    private String agencyUuid;
    private Integer cpRepositoryVersion;    
    private String itmModelTypeName;
    
    private Integer jobStatus = 0;
    private String policyNames = "";

    
   

    public String getAgencyUuid()
    {
        return agencyUuid;
    }

    public Integer getCpRepositoryVersion()
    {
        return cpRepositoryVersion;
    }
    
    public int getGuiRadioState()
    {
        int state = 0;
        // If the device is a template, it shall be either ready or have a
        // modified codeplug
        if (isATemplateOrProfile())
        {
            if (hasModifiedCodeplug())
            {
                if(isAProfile())
                {
                    state = (StatusConstants.DEVICE_PRLMODIFIED);
                }
                else
                {
                    state = (StatusConstants.DEVICE_CPMODIFIED);
                }
                return state;
            }
            
             state = (StatusConstants.DEVICE_READY);
            return state;
        }

        // If the radio has no codeplugs, it shall appear as new, but only
        // if it doesn't have a last job - this will ensure that a new device
        // with a job pending isn't reported as "New" but as "Job pending"
        if (!hasModifiedCodeplug()
                && this.isNew()
                && ( isJobNull() || isJobCanceled() || isJobTerminated()))
        {
            state = StatusConstants.DEVICE_NEW;
            return state;
        }

        // If a modified codeplug exists and no job exists to apply the changes,
        // show "modified codeplug"
        if (hasModifiedCodeplug() && !(isJobPending() || isJobRunning()))
        {
            state = (StatusConstants.DEVICE_CPMODIFIED);
            return state;
        }

        // Show the status of the last job
        switch (getJobStatus())
        {
        case StatusConstants.JOB_STATUS_CANCELED:
        case StatusConstants.JOB_STATUS_TERMINATED:
        case StatusConstants.JOB_STATUS_PERFORMED:
            state =(StatusConstants.DEVICE_READY);
            break;
        case StatusConstants.JOB_STATUS_PENDING:
            state =(StatusConstants.DEVICE_PENDING);
            break;
        case StatusConstants.JOB_STATUS_WAITING:
            state =(StatusConstants.DEVICE_WAITING);
            break;
        case StatusConstants.JOB_STATUS_RUNNING:
            state =(StatusConstants.DEVICE_RUNNING);
            break;
        case StatusConstants.JOB_STATUS_FAILED:
            state =(StatusConstants.DEVICE_FAILED);
            break;
        case StatusConstants.JOB_STATUS_EXPIRED:
            state =(StatusConstants.DEVICE_EXPIRED);
            break;
        case StatusConstants.JOB_STATUS_PENDING_LICENSE:
            state = (StatusConstants.DEVICE_PENDING_LICENSE);
            break;
        default:
            state =(StatusConstants.DEVICE_FAILED);
            break;
        }
        return state;
    }

    public String getItmModelTypeName()
    {
        return itmModelTypeName;
    }

    public Integer getJobStatus()
    {
        return jobStatus;
    }

    public String getPolicyNames()
    {
        return policyNames;
    }
  
    public boolean isJobNull()
    {
        return jobStatus == null;
    }

    public boolean isJobCanceled()
    {
        return jobStatus != null && (jobStatus == StatusConstants.JOB_STATUS_CANCELED);
    }

    public boolean isJobPartial()
    {
        return jobStatus != null && (jobStatus == StatusConstants.JOB_STATUS_PARTIAL);
    }

    public boolean isJobPending()
    {
        return jobStatus!=null && (jobStatus == StatusConstants.JOB_STATUS_PENDING
                || jobStatus == StatusConstants.JOB_STATUS_PENDING_LICENSE
                || jobStatus == StatusConstants.JOB_STATUS_WAITING);
    }

    public boolean isJobRunning()
    {
        return jobStatus != null && (jobStatus == StatusConstants.JOB_STATUS_RUNNING);
    }

    public boolean isJobTerminated()
    {
        return jobStatus != null && (jobStatus == StatusConstants.JOB_STATUS_TERMINATED);
    }

    public boolean isNew()
    {
        return (cpRepositoryVersion == null);
    }

    public boolean isOwnPolicy() //check if there's record in policy_device_map
    {
        return (policyNames != null && !policyNames.isEmpty());
    }

    public void setAgencyUuid(String agencyUuid)
    {
        this.agencyUuid = agencyUuid;
    }

    public void setCpRepositoryVersion(Integer cpRepositoryVersion)
    {
        this.cpRepositoryVersion = cpRepositoryVersion;
    }

    public void setItmModelTypeName(String itmModelTypeNameOnGui)
    {
        this.itmModelTypeName = itmModelTypeNameOnGui;
    }

    public void setJobStatus(Integer jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public void setPolicyNames(String policyNames)
    {
        this.policyNames = policyNames;
    }

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











  
}
