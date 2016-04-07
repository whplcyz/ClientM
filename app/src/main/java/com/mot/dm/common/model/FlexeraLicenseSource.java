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
04/08/14   tqfn38                CCMPD01913521 Flexera license development  
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FlexeraLicenseSource extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1412401934173995783L;

    private String sourceUuid;
    private String userName;
    private Timestamp loadDate;
    private int sourceTypeID;
    private String sourceName;
    private String checkSum;

    private Set<FlexeraProductItem> flexeraProductItemList;
    private Set<FlexeraLicenseBinary> flexeraLicenseBinaryList;
    
    public final static int FlexeraEntitlementSourceType = 1;
    public final static int FlexeraFileSourceType = 2;
    public final static int FlexeraNoSourceInfoType = 3;
    
    public String getCheckSum()
    {
        return checkSum;
    }

    public void setCheckSum(String checkSum)
    {
        this.checkSum = checkSum;
    }

    public String getSourceUuid()
    {
        return sourceUuid;
    }

    public void setSourceUuid(String sourceUuid)
    {
        this.sourceUuid = sourceUuid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Timestamp getLoadDate()
    {
        return loadDate;
    }

    public void setLoadDate(Timestamp loadDate)
    {
        this.loadDate = loadDate;
    }

    public void setSourceTypeID(int sourceTypeID)
    {
        this.sourceTypeID = sourceTypeID;
    }

    public int getSourceTypeID()
    {
        return sourceTypeID;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

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
        
        if (!(other instanceof FlexeraLicenseSource))
        {
            return false;
        }
        
        FlexeraLicenseSource rhs = (FlexeraLicenseSource) other;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }

    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public void setFlexeraLicenseBinaryList(Set<FlexeraLicenseBinary> flexeraLicenseBinaryList)
    {
        this.flexeraLicenseBinaryList = flexeraLicenseBinaryList;
    }

    public Set<FlexeraLicenseBinary> getFlexeraLicenseBinaryList()
    {
        return flexeraLicenseBinaryList;
    }

    public void setFlexeraProductItemList(Set<FlexeraProductItem> flexeraProductItemList)
    {
        this.flexeraProductItemList = flexeraProductItemList;
    }

    public Set<FlexeraProductItem> getFlexeraProductItemList()
    {
        return flexeraProductItemList;
    }   
}
