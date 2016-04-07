/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2012 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kevin Xia 17-Apr-2007

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
13/Mar/08  Tommy Thomadsen       CCMPD01004838 Include tasks in the view.
24/Mar/08  Tommy Thomadsen       CCMPD01006519 Updated job status.
09/Apr/08  Michael Leismann                    Updated toString method
11/Sep/08  Sigurdur Jonsson      CCMPD01090964 Add user name for job creator.
22/Sep/08  Jens Hansen           CCMPD01095091 Added ref to ReturnCodeInfo
20/02/09   chad001               CCMPD01171824 status api  
20/Nov/09  Kim mortensen         CCMPD01287398 Add Notification message to 
                                               JobInfo
11/Nov/09  chad001               ccmpd01287416 Add job schedule
02/Dec/09  Michael Leismann      CCMPD01287031 Jobs on memory stick feature:
                                               Removed job owner
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
20/Jun/11  cvkh36                CCMPD01521711 iTM 5.0: Enhanced API
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature 
14/Oct/14  tqfn38                CCMPD01938194 The job status is still in "Pending License"  when import XML for radio on online mode
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class JobInfo extends BaseObject
{
    private static final long serialVersionUID = 904445961031331384L;

    private String userName;

    /*
     * The proxy that executes the job
     */
    private String executeJobProxyName;

    // String containing notification message for the job.
    private String notificationMessage = "";

    private String jobUuid;

    /**
     * Valid values are defined in
     * 
     * @see com.mot.dm.common.model.StatusConstants
     */
    private int jobStatus;

    /** The time and date when the job was created. */
    private Timestamp createdDate;

    /**
     * The time and date when the job was executed. Null if it has not been
     * executed.
     */
    private Timestamp executedDate;
    /*
     * job schedule parameters
     */
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private int scheduleRecurrences;

    private Device device;

    private Set<Task> tasksList;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String inUserName)
    {
        this.userName = inUserName;
    }

    private ReturnCodeInfo returnCodeInfo;

    public Set<Task> getTasksList()
    {
        return tasksList;
    }

    public void setTasksList(Set<Task> tasksList)
    {
        this.tasksList = tasksList;
    }

    public String getJobUuid()
    {
        return this.jobUuid;
    }

    public void setJobUuid(String jobId)
    {
        this.jobUuid = jobId;
    }

    /*
     * Set the proxy name
     * 
     * @param mName String
     */
    public void setExecuteJobProxyName(String mName)
    {
        executeJobProxyName = mName;
    }

    /*
     * Get the proxy name
     * 
     * @return String
     */
    public String getExecuteJobProxyName()
    {
        return executeJobProxyName;
    }

    public Device getDevice()
    {
        return this.device;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    public int getJobStatus()
    {
        return this.jobStatus;
    }

    public void setJobStatus(int jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String toString()
    {
        ToStringBuilder sb;
        if (this.scheduleStartDate != null && this.scheduleEndDate != null)
        {
            sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                    .append("userName", this.userName).append("jobUuid", this.jobUuid)
                    .append("device", this.device).append("createdDate", this.createdDate)
                    .append("executedDate", this.executedDate)
                    .append("scheduleStartDate", this.scheduleStartDate)
                    .append("scheduleEndDate", this.scheduleEndDate)
                    .append("scheduleRecurrence", this.scheduleRecurrences)
                    .append("notificationMessage", this.notificationMessage)
                    .append("jobStatus", this.jobStatus).append("tasksList", this.tasksList)
                    .append("executeJobProxyName", this.executeJobProxyName)
                    .append("returnCodeInfo", this.returnCodeInfo);
        }
        else
        {
            sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                    .append("userName", this.userName).append("jobUuid", this.jobUuid)
                    .append("device", this.device).append("createdDate", this.createdDate)
                    .append("executedDate", this.executedDate)
                    .append("notificationMessage", this.notificationMessage)
                    .append("jobStatus", this.jobStatus).append("tasksList", this.tasksList)
                    .append("executeJobProxyName", this.executeJobProxyName)
                    .append("returnCodeInfo", this.returnCodeInfo);
        }

        return sb.toString();
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JobInfo))
            return false;
        JobInfo rhs = (JobInfo) other;
        return this.toString().equalsIgnoreCase(rhs.toString());
    }

    public int hashCode()
    {

        return new HashCodeBuilder().append(userName).append(jobUuid).append(device)
                .append(createdDate).append(executedDate).append(jobStatus).append(tasksList)
                .append(returnCodeInfo).hashCode();
    }

    public Timestamp getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate)
    {
        this.createdDate = createdDate;
    }

    public Timestamp getExecutedDate()
    {
        return executedDate;
    }

    public void setExecutedDate(Timestamp executedDate)
    {
        this.executedDate = executedDate;
    }

    public ReturnCodeInfo getReturnCodeInfo()
    {
        return returnCodeInfo;
    }

    public void setReturnCodeInfo(ReturnCodeInfo returnCodeInfo)
    {
        this.returnCodeInfo = returnCodeInfo;
    }

    /*
     * Get the schedule start date
     * 
     * @return Date
     */
    public Date getScheduleStartDate()
    {
        return scheduleStartDate;
    }

    /*
     * Set the schedule start date
     * 
     * @param scheduleStartDate Date
     */
    public void setScheduleStartDate(Date scheduleStartDate)
    {
        this.scheduleStartDate = scheduleStartDate;
    }

    /*
     * Get the schedule end date
     * 
     * @return Date
     */
    public Date getScheduleEndDate()
    {
        return scheduleEndDate;
    }

    /*
     * Set the schedule end date
     * 
     * @param scheduleEndDate Date
     */
    public void setScheduleEndDate(Date scheduleEndDate)
    {
        this.scheduleEndDate = scheduleEndDate;
    }

    /*
     * Get the schedule recurrences
     * 
     * @return int
     */
    public int getScheduleRecurrences()
    {
        return scheduleRecurrences;
    }

    /*
     * Set the schedule recurrences
     * 
     * @param scheduleRecurrences int
     */
    public void setScheduleRecurrences(int scheduleRecurrences)
    {
        this.scheduleRecurrences = scheduleRecurrences;
    }

    /*
     * Get notification message
     * 
     * @return String
     */
    public String getNotificationMessage()
    {
        return notificationMessage;
    }

    /*
     * Set notification message
     * 
     * @param String
     */
    public void setNotificationMessage(String notificationMessage)
    {
        this.notificationMessage = notificationMessage;
    }

    /**
     * Get the user data file path at server if there is one.
     * 
     * @return String user data path at server if there is one or empty string
     */
    public String getUserDataPath()
    {
        String userDataPath = "";
        TaskInfo userDataTaskInfo = null;
        if (tasksList != null)
        {

            for (Task task : tasksList)
            {
                if (task.getTaskActionType().equals(StatusConstants.TASK_TYPE_APPLY_USER_DATA))
                {
                    userDataTaskInfo = task.getFirstTaskInfo();
                    break;
                }
            }

        }

        if (userDataTaskInfo != null)
        {
            userDataPath = userDataTaskInfo.getparameter();
        }

        return userDataPath;
    }
    
    /**
     * Get the hot fix file path at server if there is one.
     * 
     * @return String hot fix path at server if there is one or empty string
     */
    public String getHotFixPath()
    {
        String hotFixPath = "";
        TaskInfo hotFixTaskInfo = null;
        if (tasksList != null)
        {

            for (Task task : tasksList)
            {
                if (task.getTaskActionType().equals(StatusConstants.TASK_TYPE_APPLY_HOT_FIX))
                {
                    hotFixTaskInfo = task.getFirstTaskInfo();
                    break;
                }
            }

        }

        if (hotFixTaskInfo != null)
        {
            hotFixPath = hotFixTaskInfo.getparameter();
        }

        return hotFixPath;
    }
    
    public List<String> getFeatureIDListInTask()
    {
        if (getTasksList() != null && getTasksList().size() > 0)
        {
            for (Task task : getTasksList())
            {
                if (task.getTaskActionType().equals(StatusConstants.TASK_TYPE_FEATURES)
                        && task.getTaskInfoList() != null && task.getTaskInfoList().size() > 0)
                {
                    List<String> featureIDList = new ArrayList<String>();
                    for (TaskInfo taskInfo : task.getTaskInfoList())
                    {
                        featureIDList.add(taskInfo.getparameter());
                    }
                    return featureIDList;
                }
            }
        }
        
        return null;
    }
    
    public List<String> getPendingLicenseFeatureIDListInTask()
    {
        if (getTasksList() != null && getTasksList().size() > 0)
        {
            for (Task task : getTasksList())
            {
                if (task.getTaskActionType().equals(StatusConstants.TASK_TYPE_FEATURES)
                        && task.getTaskInfoList() != null && task.getTaskInfoList().size() > 0)
                {
                    List<String> pendingLicenseFeatureIDList = new ArrayList<String>();
                    for (TaskInfo taskInfo : task.getTaskInfoList())
                    {
                        if (taskInfo.getValue().equals(TaskInfo.SellingFeaturePendingLicense))
                        {
                            pendingLicenseFeatureIDList.add(taskInfo.getparameter());
                        }
                    }
                    return pendingLicenseFeatureIDList;
                }
            }
        }
                            
        return null;
    }
}
