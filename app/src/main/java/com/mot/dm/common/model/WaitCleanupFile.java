/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2012 Motorola Inc.                           |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  tqfn38

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
10/Dec/12  tqfn38      CCMPD01726291 Policy feature, picture pull & push
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class WaitCleanupFile extends BaseObject
{
    private static final long serialVersionUID = 45674632479348989L;

    private String fileName;
    private Timestamp canBeDeletedTime;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Timestamp getCanBeDeletedTime()
    {
        return canBeDeletedTime;
    }

    public void setCanBeDeletedTime(Timestamp canBeDeletedTime)
    {
        this.canBeDeletedTime = canBeDeletedTime;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("fileName", fileName);
        sb.append("canBeDeletedTime", canBeDeletedTime.toString());

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        
        if (this == o)
        {
            return true;
        }

        WaitCleanupFile waitCleanupFile = (WaitCleanupFile) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(fileName, waitCleanupFile.fileName);
        builder.append(canBeDeletedTime, waitCleanupFile.canBeDeletedTime.toString());

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(canBeDeletedTime.toString()).append(fileName).hashCode();
    }
}
