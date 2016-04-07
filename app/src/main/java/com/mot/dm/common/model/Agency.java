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
CODED     BY:  w22796    2007.04.18

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
13/Mar/08  Tommy Thomadsen       CCMPD01004838 Added full-path method.
26/03/08   chad001               CCMPD01010519 Get rid of agency full path
01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database. 
26/Nov/09  hgkj73                CCMPD01288783 Added for agency partitioning
27/Apr/10  hgkj73                CCMPD01340657 updated for iTM 3.0 AP 
                                               requirements
11/11/10   ckfm01                CCMPD01409592 New flag on agency.
16/12/10   chad001               CCMPD01454937 Set default site wide license 
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author w22796
 * @version 1.0, 2007.04.18
 * 
 */
public class Agency extends BaseObject
{

    private static final long serialVersionUID = 4427730912855346401L;

    public static final String rootFolderUUID = "10000";
    
    public static final String defaultFolderUUID = "10001";

    private FolderPermissions currentUserPermissions = null;

    private int enforceMask = 0;

    private String agencyUuid;

    private String agencyName;

    private int useSiteWideLicense = -1;

    private int rootFlag;

    private Agency parentAgency;

    private final int networkTemplateBitValue = 1;
    private final int fleetmapTemplateBitValue = 2;
    private final int profileBitValue = 4;

    public Agency()
    {
    }

    public String getAgencyUuid()
    {
        return this.agencyUuid;
    }

    public void setAgencyUuid(String agencyId)
    {
        this.agencyUuid = agencyId;
    }

    public String getAgencyName()
    {
        return this.agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public int getRootFlag()
    {
        return this.rootFlag;
    }

    public void setRootFlag(int rootFlag)
    {
        this.rootFlag = rootFlag;
    }

    public Agency getParentAgency()
    {
        return parentAgency;
    }

    public void setParentAgency(Agency parentAgency)
    {
        this.parentAgency = parentAgency;
    }

    /**
     * Return the full path of a agency in a printable format e.g.
     * "/Default/subfolder1/subfolder2" If an error occur, null will be
     * returned.
     */
    public String getPrintablePath()
    {
        if (getRootFlag() == 1)
        {
            return "/";

        }
        else if (getRootFlag() == 2)
        {
            return "/" + getAgencyName();

        }
        else
        {
            Agency parentFolder = getParentAgency();
            if (parentFolder == null)
            {
                return null;
            }
            return parentFolder.getPrintablePath() + "/" + getAgencyName();
        }
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("agencyId", this.agencyUuid).append("agencyName", this.agencyName)
                .append("id", this.getId()).append("rootFlag", this.rootFlag)
                .append("useSiteWideLicense", this.useSiteWideLicense);
        return sb.toString();
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof Agency))
            return false;
        Agency rhs = (Agency) other;

        return new EqualsBuilder().append(agencyUuid, rhs.agencyUuid)
                .append(agencyName, rhs.agencyName).append(rootFlag, rhs.rootFlag).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(agencyUuid).append(agencyName).append(rootFlag)
                .hashCode();
    }

    /**
     * returns permissions for the currently logged in user
     * 
     * @return Permissions
     */
    public FolderPermissions getCurrentUserPermissions()
    {
        return currentUserPermissions;
    }

    /**
     * Set the enforce mask - This is for Hibernate - not to use from clients
     * 
     * @return Permissions
     */
    public void setEnforceMask(int enforceMask)
    {
        this.enforceMask = enforceMask;
    }

    /**
     * Get the enforce mask - This is for Hibernate - not to use from clients
     * 
     * @return Permissions
     */
    public int getEnforceMask()
    {
        return this.enforceMask;
    }

    /**
     * give the enforce nw template setting
     * 
     * @return boolean true - nw templates enforced false - nw templates NOT
     *         enforced
     */
    public boolean getEnforceNetworkTemplates()
    {
        if ((enforceMask & networkTemplateBitValue) == networkTemplateBitValue)
            return true;

        return false;
    }
    

    /**
     * give the enforce fleet template setting
     * 
     * @return boolean true - fleet templates enforced false - fleet templates
     *         NOT enforced
     */
    public boolean getEnforceFleetTemplates()
    {
        if ((enforceMask & fleetmapTemplateBitValue) == fleetmapTemplateBitValue)
            return true;

        return false;
    }
    
   

    /**
     * set the enforce nw template setting
     * 
     * @param boolean true - turn on enforce nw templates false - turn off
     *        enforce nw templates
     */
    public void setEnforceNetworkTemplates(boolean flag)
    {
        if (getEnforceNetworkTemplates() != flag)
            enforceMask = (enforceMask ^ networkTemplateBitValue);
    }
    
    /**
     * give the enforce profiles setting
     * 
     * @return boolean true - profile enforced false - profile
     *         NOT enforced
     */
    public boolean getEnforceProfiles()
    {
        if ((enforceMask & profileBitValue) == profileBitValue)
            return true;

        return false;
    }
    
   

    /**
     * set the enforce nw template setting
     * 
     * @param boolean true - turn on enforce nw templates false - turn off
     *        enforce nw templates
     */
    public void setEnforceProfiles(boolean flag)
    {
        if (getEnforceProfiles() != flag)
            enforceMask = (enforceMask ^ profileBitValue);
    }
    

    /**
     * set the enforce fleet template setting
     * 
     * @param boolean true - turn on enforce fleet templates false - turn off
     *        enforce fleet templates
     */
    public void setEnforceFleetTemplates(boolean flag)
    {
        if (getEnforceFleetTemplates() != flag)
            enforceMask = (enforceMask ^ fleetmapTemplateBitValue);
    }

    /**
     * This is for Server not to use from - clients
     * 
     * @param currentUserPermissions
     */
    public void setCurrentUserPermissions(FolderPermissions currentUserPermissions)
    {
        this.currentUserPermissions = currentUserPermissions;
    }

    /**
     * Method for getting the flag that tells if you can use sidewide license on
     * the folder or not.
     * 
     * @return
     */
    public int getUseSiteWideLicense()
    {
        return useSiteWideLicense;
    }

    /**
     * Method for setting the flag that tells if you can use sidewide license on
     * the folder or not.
     * 
     * @return
     */
    public void setUseSiteWideLicense(int useSiteWideLicense)
    {
        this.useSiteWideLicense = useSiteWideLicense;
    }
}
