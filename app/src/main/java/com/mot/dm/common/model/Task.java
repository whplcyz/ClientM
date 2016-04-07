/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2011-2014 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  <Name>
CODED     BY:  <Name>    <Date>

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
19/Feb/08  Michael Leismann                    Updated for iTM 1.9
09/Apr/08  Michael Leismann                    Updated toString method so that
                                               task type and task info list is
                                               also appended
20/Jun/11  cvkh36                CCMPD01521711 iTM 5.0: Enhanced API
28/3/14    wch378               CCMPD01889533 develop flexera license.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Version 1.0 2007 June 28
 * @author Kevin Fan
 */
public class Task extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -7706879071888143366L;

    private String taskUuid;

    private String jobUuid;

    /*
     * actionType value defined as below: JOB_ACTION_OVERWRITE_DEV_FW = 0,
     * JOB_ACTION_OVERWRITE_DEV_CP = 1, JOB_ACTION_CLONETO_DEV_CP = 2,
     * JOB_ACTION_UPLOAD_DEV_CP = 3, JOB_ACTION_CHANGE_FIELD_VALUE = 4
     */
    private Integer taskActionType;

    private Integer taskOperationOrder;

    private Set<TaskInfo> taskInfoList;

    public Set<TaskInfo> getTaskInfoList()
    {
        return taskInfoList;
    }

    public void setTaskInfoList(Set<TaskInfo> taskInfoList)
    {
        this.taskInfoList = taskInfoList;
    }

    public String getTaskUuid()
    {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid)
    {
        this.taskUuid = taskUuid;
    }

    public String getJobUuid()
    {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid)
    {
        this.jobUuid = jobUuid;
    }

    public Integer getTaskActionType()
    {
        return taskActionType;
    }

    public void setTaskActionType(Integer taskActionType)
    {
        this.taskActionType = taskActionType;
    }

    public Integer getTaskOperationOrder()
    {
        return taskOperationOrder;
    }

    public void setTaskOperationOrder(Integer taskOperationOrder)
    {
        this.taskOperationOrder = taskOperationOrder;
    }

    /*
     * Return the first task info if there is one. In most of case, one Task
     * only contains one TaskInfo.
     */
    public TaskInfo getFirstTaskInfo()
    {
        TaskInfo taskInfo = null;

        if (taskInfoList != null)
        {
            Iterator<TaskInfo> taskInfoIterator = taskInfoList.iterator();

            if (taskInfoIterator.hasNext())
            {
                taskInfo = taskInfoIterator.next();
            }
        }

        return taskInfo;

    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof Task))
            return false;

        Task rhs = (Task) other;

        return new EqualsBuilder().append(jobUuid, rhs.jobUuid).append(taskUuid, rhs.taskUuid)
                .append(taskOperationOrder, rhs.taskOperationOrder).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(jobUuid).append(taskUuid).append(taskOperationOrder)
                .hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("jobUuid", this.jobUuid).append("taskUuid", this.taskUuid)
                .append("taskActionType", this.taskActionType)
                .append("taskOperationOrder", this.taskOperationOrder)
                .append("taskInfoList", this.taskInfoList);

        return sb.toString();
    }
}
