/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Sigurdur Jonsson 30-November-2009

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
30/Nov/09  Sigurdur Jonsson      CCMPD01287416 Job scheduling, initial version
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class JobSchedule extends BaseObject
{
    private static final long serialVersionUID = 904445961031331384L;
    private String jobScheduleUuid;
    private JobInfo jobInfo;
    private Timestamp scheduleStartDate;
    private Timestamp scheduleExpireDate;
    private long activePeriodDuration;
    private int nForTheNextActivePeriod;
    private int scheduleRecurrence;
    private int cycleTime;

    public String toString()
    {
        ToStringBuilder sb;
        sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("jobScheduleUuid", this.jobScheduleUuid).append("jobInfo", this.jobInfo)
                .append("scheduleStartDate", this.scheduleStartDate)
                .append("scheduleExpireDate", this.scheduleExpireDate)
                .append("activePeriodDuration", this.activePeriodDuration)
                .append("nForTheNextActivePeriod", this.nForTheNextActivePeriod)
                .append("scheduleRecurrence", this.scheduleRecurrence)
                .append("cycleTimeSeconds", this.cycleTime);
        return sb.toString();
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JobSchedule))
            return false;
        JobSchedule rhs = (JobSchedule) other;
        return this.toString().equalsIgnoreCase(rhs.toString());
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(jobScheduleUuid).append(jobInfo)
                .append(this.scheduleStartDate).append(this.scheduleExpireDate)
                .append(this.activePeriodDuration).append(this.nForTheNextActivePeriod)
                .append(this.scheduleRecurrence).append(this.cycleTime).hashCode();
    }

    public String getJobScheduleUuid()
    {
        return jobScheduleUuid;
    }

    public void setJobScheduleUuid(String jobScheduleUuid)
    {
        this.jobScheduleUuid = jobScheduleUuid;
    }

    public JobInfo getJobInfo()
    {
        return jobInfo;
    }

    public void setJobInfo(JobInfo jobInfo)
    {
        this.jobInfo = jobInfo;
    }

    public Timestamp getScheduleStartDate()
    {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(Timestamp scheduleStartDate)
    {
        this.scheduleStartDate = scheduleStartDate;
    }

    public Timestamp getScheduleExpireDate()
    {
        return scheduleExpireDate;
    }

    public void setScheduleExpireDate(Timestamp scheduleExpireDate)
    {
        this.scheduleExpireDate = scheduleExpireDate;
    }

    public long getActivePeriodDuration()
    {
        return activePeriodDuration;
    }

    public void setActivePeriodDuration(long activePeriodDuration)
    {
        this.activePeriodDuration = activePeriodDuration;
    }

    public int getNForTheNextActivePeriod()
    {
        return nForTheNextActivePeriod;
    }

    public void setNForTheNextActivePeriod(int forTheNextActivePeriod)
    {
        nForTheNextActivePeriod = forTheNextActivePeriod;
    }

    public int getScheduleRecurrence()
    {
        return scheduleRecurrence;
    }

    public void setScheduleRecurrence(int scheduleRecurrence)
    {
        this.scheduleRecurrence = scheduleRecurrence;
    }

    public int getCycleTimeMilliSeconds()
    {
        return cycleTime;
    }

    public void setCycleTimeMilliSeconds(int cycleTimeSeconds)
    {
        this.cycleTime = cycleTimeSeconds;
    }
}
