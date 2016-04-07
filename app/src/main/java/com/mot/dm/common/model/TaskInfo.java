/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                Copyright 2007-2014 Motorola Solutions Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kim Mortensen    

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/Feb/08  Kim mortensen         CCMPD00963953 initial version
09/Apr/08  Michael Leismann                    Updated toString method
28/Apr/08  Jens Hansen           CCMPD01020823 Fixed klocwork issue
04/08/14   tqfn38                CCMPD01913521 Flexera license development  
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TaskInfo extends BaseObject
{
    public final static String SellingFeaturePendingLicense = "0";
    public final static String SellingFeatureEnable = "1";
    public final static String SellingFeatureKeep = "2";      
    
    /**
     * TaskInfo. Getters and setters for the TaskInfo structure. This structure
     * is the last in the JobInfo structure: - JobInfo -> Task -> TaskInfo ICD
     * Section 2.6.5
     */
    private static final long serialVersionUID = -7706879071888143367L;

    private String taskInfoUuid;

    private String taskUuid;

    private Integer taskInfoOperationOrder;

    private String parameter;

    private String value;

    public String getTaskUuid()
    {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid)
    {
        this.taskUuid = taskUuid;
    }

    public String getTaskInfoUuid()
    {
        return taskInfoUuid;
    }

    public void setTaskInfoUuid(String taskInfoUuid)
    {
        this.taskInfoUuid = taskInfoUuid;
    }

    public Integer getTaskInfoOperationOrder()
    {
        return taskInfoOperationOrder;
    }

    public void setTaskInfoOperationOrder(Integer taskInfoOperationOrder)
    {
        this.taskInfoOperationOrder = taskInfoOperationOrder;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getparameter()
    {
        return parameter;
    }

    public void setparameter(String parameter)
    {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof TaskInfo))
            return false;

        TaskInfo rhs = (TaskInfo) other;

        return new EqualsBuilder().append(taskUuid, rhs.taskUuid)
                .append(taskInfoUuid, rhs.taskInfoUuid)
                .append(taskInfoOperationOrder, rhs.taskInfoOperationOrder)
                .append(parameter, rhs.parameter).append(value, rhs.value).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(taskUuid).append(taskInfoUuid)
                .append(taskInfoOperationOrder).append(parameter).append(value).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("taskUuid", this.taskUuid).append("taskInfoUuid", this.taskInfoUuid)
                .append("taskInfoOperationOrder", this.taskInfoOperationOrder)
                .append("parameter", this.parameter).append("value", this.value);

        return sb.toString();
    }
}
