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
19/01/10   hgkj73                CCMPD01304268 added isEmpty method 
25/01/10   hgkj73                CCMPD01306290 apply sub folders per user support
18/Mar/10  hgkj73                CCMPD01325308 iTM 3.0 AP requirements handled 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class acts as container for holding 1. holding the permissions of users
 * on a given object 2. holding the users who are revoked from the permission
 * list 3. holding the users who can be added to the permissions provides the
 * interfaces for both client/server to manage the permissions and users on any
 * given object. this is intended to minimize the bloating code and facilitating
 * to have the code at central place w.r.t how permissions and users organized.
 * 
 * @author hgkj73
 * 
 */
public class ItmAccessControlList extends BaseObject
{

    private static final long serialVersionUID = -8453852602738751238L;

    // holds the permissions of users on a given object
    private Map<ItmSecurityIdentity, ItmAccessControlEntry> aceMap;

    // holding the users who are revoked from the permission list
    private Set<ItmSecurityIdentity> revokeddItmSids;

    // holding the users who can be added to the permissions
    private Set<ItmSecurityIdentity> nonConfiguredItmSids;

    // tracking this will eliminate client to call getNonConfiguredSids
    // again and again
    private boolean nonConfiguredSidsLoaded = false;

    public ItmAccessControlList()
    {
        aceMap = new HashMap<ItmSecurityIdentity, ItmAccessControlEntry>();
        revokeddItmSids = new HashSet<ItmSecurityIdentity>();
        nonConfiguredItmSids = new HashSet<ItmSecurityIdentity>();
    }

    /**
     * returns a list of AccessControlEntries the caller can loop through.
     * 
     * @return List<ItmAccessControlEntry>
     */
    public List<ItmAccessControlEntry> getAccessControlEntryList()
    {
        // May be we need an iterator if client can cope with that (or)
        // see if client can use Collection Instead
        List<ItmAccessControlEntry> aces = new ArrayList<ItmAccessControlEntry>();
        aces.addAll(aceMap.values());
        return aces;
    }

    /**
     * returns a set of SecurityIdenties that have some permissions configured
     * 
     * @return Set<ItmSecurityIdentity>
     */
    public Set<ItmSecurityIdentity> getSecurityIdentityList()
    {
        return aceMap.keySet();
    }

    /**
     * returns set of users who can be configured on a given object
     * 
     * @return Set<ItmSecurityIdentity>
     */
    public Set<ItmSecurityIdentity> getNonConfiguredItmSids()
    {
        return nonConfiguredItmSids;
    }

    /**
     * returns a set of users who are deleted from the given object this is used
     * by only server to update the acl permissions.
     * 
     * @return Set<ItmSecurityIdentity>
     */
    public Set<ItmSecurityIdentity> getRevokedItmSids()
    {
        Set<ItmSecurityIdentity> returnValue = new HashSet<ItmSecurityIdentity>();
        returnValue.addAll(revokeddItmSids);
        return returnValue;

    }

    /**
     * returns true if a SecurityIdentity has corresponding accessControlEntry
     * defined. else false.
     * 
     * @param itmSid
     * @return boolean
     */
    public boolean containsSecurityIdentity(ItmSecurityIdentity itmSid)
    {
        return aceMap.containsKey(itmSid);
    }

    /**
     * get accessControlEntry corresponding to a ItmSecurityIdentity if he is
     * not configured in the system then null will be returned.
     * 
     * @param itmSid
     * @return ItmAccessControlEntry
     */
    public ItmAccessControlEntry getAccessControlEntry(ItmSecurityIdentity itmSid)
    {
        if (aceMap.containsKey(itmSid))
        {
            return aceMap.get(itmSid);
        }
        return null;
    }

    /**
     * get ItmAccessControlEntry by user/group uuid
     * 
     * @param itmSidUuid
     * @return ItmAccessControlEntry
     */
    public ItmAccessControlEntry getAccessControlEntry(String itmSidUuid)
    {
        Set<ItmSecurityIdentity> itmSids = aceMap.keySet();
        for (ItmSecurityIdentity itmSid : itmSids)
        {
            if (itmSid.getUuid().equals(itmSidUuid))
            {
                return aceMap.get(itmSid);
            }
        }
        return null;
    }

    /**
     * adds a ItmAccessControlEntry to the current access control list if it
     * does not exist updates the ItmAccessControlEntry if it already exists
     * 
     * @param itmAce
     */
    public void addOrUpdateAccessControlEntry(ItmAccessControlEntry itmAce)
    {
        aceMap.put(itmAce.getSecurityIdentity(), itmAce);
        revokeddItmSids.remove(itmAce.getSecurityIdentity());
        nonConfiguredItmSids.remove(itmAce.getSecurityIdentity());

    }

    /**
     * adds a list of ItmAccessControlEntry'es to the current access control
     * list if it doesnt exist updates the ItmAccessControlEntry if it already
     * exists NOTE: This is used only from server. because here we dont add the
     * entry if the user have no permissions. This need to be used only from
     * server side. Client code should use addOrUpdateSecIdentityPermissions
     * instead
     * 
     * @param itmAces
     */
    public void addOrUpdateAccessControlEntry(List<ItmAccessControlEntry> itmAces)
    {
        for (ItmAccessControlEntry itmAce : itmAces)
        {
            addOrUpdateAccessControlEntry(itmAce);
        }
    }

    /**
     * deletes a list of ItmAccessControlEntry'es if they exist
     * 
     * @param itmAce
     */
    public void deleteAccessControlEntry(List<ItmAccessControlEntry> itmAces)
    {
        for (ItmAccessControlEntry itmAce : itmAces)
        {
            deleteAccessControlEntry(itmAce);
        }
    }

    /**
     * deletes AccessControlEntry if it exists
     * 
     * @param itmAce
     */
    public void deleteAccessControlEntry(ItmAccessControlEntry itmAce)
    {
        aceMap.remove(itmAce.getSecurityIdentity());
        revokeddItmSids.add(itmAce.getSecurityIdentity());
        nonConfiguredItmSids.add(itmAce.getSecurityIdentity());
    }

    /**
     * deletes user access, if user configured on the list
     * 
     * @param itmAce
     */
    public void deleteUsersAccessControlEntry(ItmSecurityIdentity itmSecurityIdentity)
    {
        aceMap.remove(itmSecurityIdentity);
        revokeddItmSids.add(itmSecurityIdentity);
        nonConfiguredItmSids.add(itmSecurityIdentity);
    }

    /**
     * deletes list of users access from configured list
     * 
     * @param List
     *            <itmSecurityIdentity>
     */
    public void deleteUsersAccessControlEntry(List<ItmSecurityIdentity> itmSids)
    {
        for (ItmSecurityIdentity itmSid : itmSids)
        {
            deleteUsersAccessControlEntry(itmSid);
        }
    }

    /**
     * - adds a permissions for a SecurityIdentity to the current access control
     * list if SecurityIdentity not configured - updates the SecurityIdentity
     * permissions if configured. It sets the applySubFolders setting as well.
     * This is supposed to be used by client code.
     * 
     * @param secIdentity
     * @param permissions
     */
    public void addOrUpdateSecIdentityPermissions(ItmSecurityIdentity secIdentity,
            ItmPermissions permissions, boolean recursive)
    {
        aceMap.put(secIdentity, new ItmAccessControlEntry(secIdentity, permissions, recursive));
        revokeddItmSids.remove(secIdentity);
        nonConfiguredItmSids.remove(secIdentity);
    }

    /**
     * adds a ItmSecurityIdentity with empty permissions
     * 
     * @param itmSid
     */
    public void addItmSecurityIdentity(ItmSecurityIdentity itmSid, ItmPermissions permissions)
    {

        aceMap.put(itmSid, new ItmAccessControlEntry(itmSid, permissions));
        revokeddItmSids.remove(itmSid);
        nonConfiguredItmSids.remove(itmSid);

    }

    /**
     * this is only used to populate the NonConfigured Sids at typically it will
     * be used for Service layer
     * 
     * @param itmSid
     */
    public void addNonConfiguredSid(ItmSecurityIdentity itmSid)
    {
        nonConfiguredSidsLoaded = true;
        nonConfiguredItmSids.add(itmSid);
    }

    /**
     * Can be used to know whether the the NonConfiguredSids loaded. because
     * Once loaded then we don't need to load again and again as this class can
     * track all further updates.
     * 
     * @return
     */
    public boolean isNonConfiguredSidsLoaded()
    {
        return nonConfiguredSidsLoaded;
    }

    /**
     * It is to be used when you want to ignore a user from the lists
     * completely. Typically when a user is deleted from iTM system during an
     * update of his permissions. Server uses this to ignore the deleted user.
     * 
     * @param itmSids
     */
    public void ignoreItmSid(ItmSecurityIdentity itmSid)
    {
        aceMap.remove(itmSid);
        revokeddItmSids.remove(itmSid);
        nonConfiguredItmSids.remove(itmSid);
    }

    /**
     * It is to be used when you want to ignore a user from the lists
     * completely. Typically when a users are deleted from iTM system during an
     * update of their permissions. Server uses this to ignore the deleted
     * users.
     * 
     * @param itmSids
     */
    public void ignoreItmSid(List<ItmSecurityIdentity> itmSids)
    {
        for (ItmSecurityIdentity itmSid : itmSids)
        {
            ignoreItmSid(itmSid);
        }
    }

    /**
     * to know if there are entries that need to be handled.
     * 
     * @param itmSids
     */
    public boolean isEmpty()
    {
        return aceMap.isEmpty() && revokeddItmSids.isEmpty();
    }

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof ItmAccessControlList))
            return false;
        ItmAccessControlList rhs = (ItmAccessControlList) o;
        return new EqualsBuilder().append(this.aceMap, rhs.aceMap)
                .append(this.nonConfiguredItmSids, rhs.nonConfiguredItmSids)
                .append(this.revokeddItmSids, rhs.revokeddItmSids).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.aceMap).append(this.nonConfiguredItmSids)
                .append(this.revokeddItmSids).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("accessControlList", this.aceMap)
                .append("revoked sids", this.revokeddItmSids)
                .append("non condifured sids", this.nonConfiguredItmSids);
        return sb.toString();
    }

}