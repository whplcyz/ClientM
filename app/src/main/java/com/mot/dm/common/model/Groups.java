/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2010 Motorola Inc.                           |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Mar/10   hgkj73               CCMPD01317830 Added Groups feature
21/Mar/10   cjh102               CCMPD01320408 Added method for getting deleted
                                               groups
06/Apr/10   hgkj73               CCMPD01325308 deleted groups removed from equals
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Container for groups
 * 
 * @author hgkj73
 * 
 */
public class Groups extends BaseObject
{

    private static final long serialVersionUID = -1927574599706886473L;

    private Map<String, Group> groups;
    private Set<Group> deletedGroups;

    public List<Group> getDeletedGroups()
    {
        List<Group> returnList = new ArrayList<Group>();
        returnList.addAll(deletedGroups);
        return returnList;
    }

    public Groups()
    {
        groups = new HashMap<String, Group>();
        deletedGroups = new HashSet<Group>();
    }

    /**
     * This must be to use only with Hibernate/Server during fetch of all groups
     * from database.
     * 
     * @param groupsList
     */
    public void setGroups(List<Group> groupsList)
    {
        if (null == groupsList)
        {
            return;
        }
        groups.clear();
        deletedGroups.clear();
        for (Group group : groupsList)
        {
            groups.put(group.getName(), group);
        }
    }

    public List<Group> getGroups()
    {
        List<Group> returnList = new ArrayList<Group>();
        returnList.addAll(groups.values());
        return returnList;
    }

    /**
     * used to add a group
     * 
     * @param group
     */
    public void addGroup(Group group)
    {
        if (null == group || null == group.getName() || 0 == group.getName().length())
        {
            return;
        }
        if (groups.containsKey(group.getName()))
        {
            // Cannot be added as group with the same name
            // already exist. do you intend to update
            // use modifyGroup instead.
            return;
        }
        groups.put(group.getName(), group);
        deletedGroups.remove(group);
    }

    /**
     * used for deletion of a group
     * 
     * @param group
     */
    public void deleteGroup(Group group)
    {
        if (null == group || !groups.containsKey(group.getName()))
        {
            return;
        }
        groups.remove(group.getName());
        deletedGroups.add(group);
    }

    /**
     * modify a group including rename
     * 
     * @param group
     */
    public void modifyGroup(Group group)
    {
        if (null == group)
        {
            return;
        }

        if (groups.containsKey(group.getName()))
        {
            groups.put(group.getName(), group);
            deletedGroups.remove(group);
        } // may be its a rename
        else if (groups.containsKey(group.getOldName()))
        {
            groups.remove(group.getOldName());
            groups.put(group.getName(), group);
            deletedGroups.remove(group);
        }
        return;
    }

    /**
     * used to query if a group with a given group name already exists
     * 
     * @param groupName
     * @return
     */
    public boolean containsGroupWithName(String groupName)
    {
        return groups.containsKey(groupName);
    }

    /**
     * used to get a group with given name, if exists. if groupname doesnt
     * exists method returns null.
     * 
     * @param groupName
     * @return
     */
    public Group getGroup(String groupName)
    {
        if (!groups.containsKey(groupName))
        {
            return null;
        }
        return groups.get(groupName);
    }

    /**
     * add user to a given group name, if exists
     * 
     * @param groupName
     * @param user
     */
    public void addUser(String groupName, User user)
    {
        if (groups.containsKey(groupName))
        {
            groups.get(groupName).addUser(user);
        }
    }

    /**
     * add list of users to a given group name, if exists
     * 
     * @param groupName
     * @param users
     */
    public void addUser(String groupName, List<User> users)
    {
        for (User user : users)
        {
            addUser(groupName, user);
        }
    }

    /**
     * remove a user from a given group name, if exists
     * 
     * @param groupName
     * @param user
     */
    public void removeUser(String groupName, User user)
    {
        if (groups.containsKey(groupName))
        {
            groups.get(groupName).removeUser(user);
        }
    }

    /**
     * remove users from a given group
     * 
     * @param groupName
     * @param users
     */
    public void removeUser(String groupName, List<User> users)
    {
        for (User user : users)
        {
            removeUser(groupName, user);
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        
        if (this == obj)
            return true;
        
        if (getClass() != obj.getClass())
            return false;
        
        final Groups other = (Groups) obj;
        if (groups == null)
        {
            if (other.groups != null)
                return false;
        }
        else if (!groups.equals(other.groups))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "groups", this.groups.values()).append("deleted groups", this.deletedGroups);
        return sb.toString();
    }

}