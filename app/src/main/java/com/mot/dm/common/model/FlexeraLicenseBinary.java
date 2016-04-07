/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  wch378 28/3/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
28/3/14    wch378                CCMPD01876846 develop flexera license
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FlexeraLicenseBinary extends BaseObject
{

    /**
     * 
     */
    private static final long serialVersionUID = -9176468059598512339L;

    private String binaryUuid;
    private String deviceID;
    private byte[] binary;
    private int binarySize;
    private int totalActiviatedCount;
    private String sourceUuid;

    private Set<FlexeraLicenseBinaryDetail> flexeraLicenseBinaryDetailList;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (!(other instanceof FlexeraLicenseBinary))
        {
            return false;
        }
        FlexeraLicenseBinary rhs = (FlexeraLicenseBinary) other;

        return EqualsBuilder.reflectionEquals(this, rhs);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public void setBinaryUuid(String binaryUuid)
    {
        this.binaryUuid = binaryUuid;
    }

    public String getBinaryUuid()
    {
        return binaryUuid;
    }

    public void setDeviceID(String deviceID)
    {
        this.deviceID = deviceID;
    }

    public String getDeviceID()
    {
        return deviceID;
    }

    public void setSourceUuid(String sourceUuid)
    {
        this.sourceUuid = sourceUuid;
    }

    public String getSourceUuid()
    {
        return sourceUuid;
    }

    public void setBinary(byte[] binary)
    {
        this.binary = binary;
    }

    public byte[] getBinary()
    {
        return binary;
    }

    public void setFlexeraLicenseBinaryDetailList(
            Set<FlexeraLicenseBinaryDetail> flexeraLicenseBinaryDetailList)
    {
        this.flexeraLicenseBinaryDetailList = flexeraLicenseBinaryDetailList;
    }

    /*FlexeraLicenseBinaryDetail can just be used for display,
     * If for program, we need extract detail from binary*/
    public Set<FlexeraLicenseBinaryDetail> getFlexeraLicenseBinaryDetailList()
    {
        return flexeraLicenseBinaryDetailList;
    }

    public void setBinarySize(int binarySize)
    {
        this.binarySize = binarySize;
    }

    public int getBinarySize()
    {
        return binarySize;
    }

    public void setTotalActiviatedCount(int totalActiviatedCount)
    {
        this.totalActiviatedCount = totalActiviatedCount;
    }

    public int getTotalActiviatedCount()
    {
        return totalActiviatedCount;
    }
}
