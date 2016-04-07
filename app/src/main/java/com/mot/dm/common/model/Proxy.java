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
 CODED     BY:  Jeff Lu    

 ------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database. 
06/May/08  Niels Jorgensen       CCMPD01030655 The Server entry in manage proxies 
                                               shall not be visible
08/Jun/10  Sigurdur Jonsson      CCMPD01357127 Automatic application update.
06/Aug/10  chad001               CCMPD01379654 add proxy online time       
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
04/Apr/11   chad001              ccmpd01491459 update proxy 
27/June/2012 wch378             wch378_autoinclude itm6.0 new feature auto-include radio 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Proxy extends BaseObject
{

    private static final long serialVersionUID = 6374211903648834042L;

    // serverUuid in Database.
    public static final String serverUuid = "1111";

    private String proxyUuid;

    private String proxyName;

    private String proxySwVersion;

    private int proxyUpdateLevel;

    private Integer proxyStatus;

    private Timestamp lastOnlineTime;

    private String notes = "";
    
    private Boolean isAutoInclude;
    
    private Agency agency;
    
    @Override
    public long getId()
    {
        if (null != agency)
        {
            return agency.getId();
        }
        return super.getId();
    }

    
    public boolean getIsAutoInclude()
    {
        return isAutoInclude;
    }

    public void setIsAutoInclude(boolean isAutoInclude)
    {
        this.isAutoInclude = isAutoInclude;
    }
    
    public Agency getAgency()
    {
        return agency;
    }

    public void setAgency(Agency agency)
    {
        this.agency = agency;
    }
    
/*    public String getAgencyUUid()
    {
        return agencyUUid;
    }
    
    public void setAgencyUUid(String agencyUUid)
    {
        this.agencyUUid = agencyUUid;
    }*/
    

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    /**
     * @return the proxyId
     */
    public String getProxyUuid()
    {
        return proxyUuid;
    }

    /**
     * @param proxyId
     *            the proxyId to set
     */
    public void setProxyUuid(String proxyId)
    {
        this.proxyUuid = proxyId;
    }

    /**
     * @return the proxyName
     */
    public String getProxyName()
    {
        return proxyName;
    }

    /**
     * @param proxyName
     *            the proxyName to set
     */
    public void setProxyName(String proxyName)
    {
        this.proxyName = proxyName;
    }

    /**
     * @return the proxySwVersion
     */
    public String getProxySwVersion()
    {
        return proxySwVersion;
    }

    /**
     * @param proxySwVersion
     *            the proxySwVersion to set
     */
    public void setProxySwVersion(String proxySwVersion)
    {
        this.proxySwVersion = proxySwVersion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(proxyUuid).append(proxyName).append(proxySwVersion)
                .append(proxyStatus).append(proxyUpdateLevel).hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Proxy == false)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        Proxy rhs = (Proxy) obj;
        /**
         * this one can be null, so we check it before other fields
         */
        if (lastOnlineTime != rhs.lastOnlineTime)
            return false;

        return new EqualsBuilder().append(proxyUuid, rhs.proxyUuid)
                .append(proxyName, rhs.proxyName).append(proxySwVersion, rhs.proxySwVersion)
                .append(proxyStatus, rhs.proxyStatus).append(notes, rhs.notes)
                .append(proxyUpdateLevel, rhs.proxyUpdateLevel).isEquals();

    }

    public String toString()
    {
        String lastTime = "";
        if (lastOnlineTime != null)
            lastTime = lastOnlineTime.toString();

        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("proxyId", this.proxyUuid).append("proxyName", this.proxyName)
                .append("status", this.proxyStatus).append("updateLevel", this.proxyUpdateLevel)
                .append("proxySwVersion", this.proxySwVersion).append("notes", this.notes)
                .append("lastOnlineTime", lastTime);
        return sb.toString();
    }

    /**
     * @return the status
     */
    public Integer getProxyStatus()
    {
        return proxyStatus;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setProxyStatus(Integer status)
    {
        this.proxyStatus = status;
    }

    /**
     * @return the update level
     */
    public int getProxyUpdateLevel()
    {
        return proxyUpdateLevel;
    }

    /**
     * @param proxyUpdateLevel
     *            , the update level to set
     */
    public void setProxyUpdateLevel(int proxyUpdateLevel)
    {
        this.proxyUpdateLevel = proxyUpdateLevel;
    }

    /**
     * Getter for lastOnlineTime
     * 
     * @return Timestamp
     */
    public Timestamp getLastOnlineTime()
    {
        return lastOnlineTime;
    }

    /**
     * Setter for lastOnlineTime
     * 
     * @param lastOnlineTime
     *            Timestamp
     */
    public void setLastOnlineTime(Timestamp lastOnlineTime)
    {
        this.lastOnlineTime = lastOnlineTime;
    }
}
