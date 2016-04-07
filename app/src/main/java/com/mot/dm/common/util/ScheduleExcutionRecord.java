/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  Wei Huping(Qngv36)

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
09/July/14  Qngv36               CCMPD01394082 [iTM 7.0]Support scheduled on-line back up.
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.model.BaseObject;



/**
 * 
 * @author qngv36 
 * Description The QRTZScheduleJob class contains all information for a Quartz schedule job. A
 *         Quartz job is a description of actions that will be auto be schedule by Quartz.
 */
public class ScheduleExcutionRecord extends BaseObject 
{
	private static final long serialVersionUID = 649195200235607421L;
	protected String recordUuid = "";
	protected String jobName = "";
	protected String triggerName = "";
	protected String groupName = "";
	protected Date startTime = new Date();
	protected Date endTime = new Date();
	protected int status;
	protected int jobType;
	protected int reoccurPattern;
	protected String addtionInformation;
  
	
	int nProcess = 0;
    List<String> lstOperationInfo = new ArrayList<String>();
    boolean isCancelling = false;
    private Lock accessLock = new ReentrantLock();
    
	public String getRecordUuid() 
	{
		return recordUuid;
	}

	public void setRecordUuid(String recordUuid) 
	{
		this.recordUuid = recordUuid;
	}

	public String getJobName() 
	{
		return jobName;
	}

	public void setJobName(String jobName) 
	{
		this.jobName = jobName;
	}

	public String getTriggerName() 
	{
		return triggerName;
	}

	public void setTriggerName(String triggerName) 
	{
		this.triggerName = triggerName;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}

	public Date getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getJobType() 
	{
		return jobType;
	}

	public void setJobType(int jobType) 
	{
		this.jobType = jobType;
	}

	public void setReoccurPattern(int reoccurPattern)
	{
		this.reoccurPattern = reoccurPattern;
	}
	
	public int getReoccurPattern()
	{
		return reoccurPattern;
	}
	
	public void setAddtionInformation(String addtionInformation)
	{
		this.addtionInformation = addtionInformation;
	}
	
	public String getAddtionInformation()
	{
		return addtionInformation;
	}
	
	/** 
     * To set the process of the job
     * 
     */
    public void setJobProcess(int nProcess) 
    {
        this.nProcess = nProcess;
    }

    /** 
     * To get the process of the job
     * 
     */
    public int getJobProcess() 
    {
        return this.nProcess;
    }

    public void AddOperationInfo(String opInfo)
    {
        accessLock.lock();
        try
        {
            lstOperationInfo.add(opInfo);
        }
        finally 
        {
            accessLock.unlock();
        }
    }
    
    public void AddOperationInfo(String opInfo, int nProcess)
    {
        accessLock.lock();
        try
        {
            lstOperationInfo.add(opInfo);
            this.nProcess = nProcess;
        }
        finally 
        {
            accessLock.unlock();
        }
    }
    
    public void UpdateLastOperationInfo(String opInfo, int nProcess)
    {
        accessLock.lock();
        try
        {
            int nLastPosIndex =  lstOperationInfo.size() - 1;
            if(nLastPosIndex >= 0)
            {
                lstOperationInfo.remove(nLastPosIndex);
            }
            
            lstOperationInfo.add(opInfo);
            this.nProcess = nProcess;
        }
        finally 
        {
            accessLock.unlock();
        }
    }
    
    
    public String[] GetOperationInfo(Boolean bRemove)
    {
        accessLock.lock();        
        try
        {
            String[] result = lstOperationInfo.toArray(new String[0]);
            if(bRemove)
            {
                lstOperationInfo.clear();                
            }
            
            return result;
        }
        finally 
        {       
            accessLock.unlock();
        }
    }
    
    public  boolean isCancelling()
    {
        return isCancelling;
    }
    
    public void cancelJob(boolean isCancelling)
    {
         this.isCancelling = isCancelling;
    }

	@Override
	public String toString() 
	{
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
		.append( "recordUuid", this.recordUuid.toString())
		.append("jobName", this.jobName.toString())
		.append("triggerName", this.triggerName.toString())
		.append("groupName", this.groupName.toString())
		.append("startTime", this.startTime.toString())
		.append("endTime", this.endTime.toString())
		.append("status", this.status)
		.append("reoccurPattern", this.reoccurPattern)
		.append("addtionInformation", this.addtionInformation)
		.append("jobType", this.jobType);

        return sb.toString();
	}
	
	public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof ScheduleExcutionRecord))
        {
            return false;
        }

        ScheduleExcutionRecord job = (ScheduleExcutionRecord) o;
        
        return new EqualsBuilder().append(this.recordUuid, job.recordUuid)
        .append(this.jobName, job.jobName)
        .append(this.triggerName, job.triggerName)
        .append(this.groupName, job.groupName)
        .append(this.startTime, job.startTime)
        .append(this.endTime, job.endTime)
        .append(this.status, job.status)
        .append(this.jobType, job.jobType)
        .isEquals();
    }

    public int hashCode()
    {
    	return new HashCodeBuilder()
    	.append(this.recordUuid)
    	.append(this.jobName)
        .append(this.startTime.toString())
        .append(this.endTime.toString())
        .append(this.status)
        .append(this.jobType)
        .append(this.groupName).
        append(this.triggerName)
        .hashCode();
    }


}
