/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2009-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73/cjh102

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/09  hgkj73/cjh102         CCMPD01288783 Added for Agency partitioning
19/01/10   hgkj73                CCMPD01304268 java doc added
25/01/10   hgkj73                CCMPD01306290 apply sub folders per user support
18/Mar/10  hgkj73                CCMPD01325308 iTM 3.0 AP requirements handled   
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * container for holding permissions and user together
 * 
 * @author hgkj73
 * 
 */
public class ItmAccessControlEntry extends BaseObject
{

    private static final long serialVersionUID = -1724994374925630401L;

    /**
     * ItmSecurityIdentity which can be either user/role/group etc...
     */
    private ItmSecurityIdentity securityIdentity;

    /**
     * permissions associated with given ItmSecurityIdentity
     */
    private ItmPermissions permissions;

    /**
     * indicates whether these permissions for the uer need to be applied to sub
     * objects as well
     */
    private boolean recursive;

    public ItmAccessControlEntry(ItmSecurityIdentity securityIdentity, ItmPermissions permissions)
    {
        this.securityIdentity = securityIdentity;
        this.permissions = permissions;
        this.recursive = false;
    }

    public ItmAccessControlEntry(ItmSecurityIdentity securityIdentity, ItmPermissions permissions,
            boolean recursive)
    {
        this.securityIdentity = securityIdentity;
        this.permissions = permissions;
        this.recursive = recursive;
    }

    /**
     * get ApplyToSubFolders. This will be used by server while saving the
     * permissions
     * 
     * @return boolean
     */

    public boolean getRecursive()
    {
        return recursive;
    }

    /**
     * set applyToFolders setting for the user. This will be used by client to
     * set the user selection for the user with this entry
     * 
     * @param applyToSubFolders
     */
    public void setRecursive(boolean recursive)
    {
        this.recursive = recursive;
    }

    /**
     * get securityIdentity
     * 
     * @return
     */
    public ItmSecurityIdentity getSecurityIdentity()
    {
        return securityIdentity;
    }

    /**
     * get permissions
     * 
     * @return
     */
    public ItmPermissions getPermissions()
    {
        return permissions;
    }

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof ItmAccessControlEntry))
            return false;
        ItmAccessControlEntry rhs = (ItmAccessControlEntry) o;
        return new EqualsBuilder().append(securityIdentity, rhs.getPermissions())
                .append(permissions, rhs.getPermissions()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(securityIdentity).append(permissions).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "permissions", this.permissions).append("securityIdentity", this.securityIdentity);
        return sb.toString();
    }

}