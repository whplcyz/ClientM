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
12/Mar/10   hgkj73               CCMPD01317830 Added new user management feature
06/Apr/10   hgkj73               CCMPD01325308 deleted users removed from equals
14/Apr/10  Michael Leismann      CCMPD01327990 Added method to get deleted users
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
 * Container for Users
 * 
 * @author hgkj73
 * 
 */
public class Users extends BaseObject
{

    private static final long serialVersionUID = 1128691650911563460L;

    private Map<String, User> users;
    private Set<User> deletedUsers;

    public Users()
    {
        users = new HashMap<String, User>();
        deletedUsers = new HashSet<User>();
    }

    /**
     * This must be to use only with Hibernate/Server during fetch of all users
     * from database.
     * 
     * @param groupsList
     */
    public void setUsers(List<User> users)
    {
        if (null == users)
        {
            return;
        }
        this.users.clear();
        deletedUsers.clear();
        for (User user : users)
        {
            this.users.put(user.getName().toLowerCase(), user);
        }
    }

    public List<User> getUsers()
    {
        List<User> returnList = new ArrayList<User>();
        returnList.addAll(users.values());
        return returnList;
    }

    public void addUser(User user)
    {
        if (null == user)
        {
            return;
        }
        if (users.containsKey(user.getName().toLowerCase()))
        {
            // Cannot be added as user with the same name
            // already exist. do you intend to update
            // use modifyUser instead.
            return;
        }
        users.put(user.getName().toLowerCase(), user);
        deletedUsers.remove(user);
    }

    public void deleteUser(User user)
    {
        if (null == user || !users.containsKey(user.getName().toLowerCase()))
        {
            return;
        }
        users.remove(user.getName().toLowerCase());
        deletedUsers.add(user);
    }

    public void modifyUser(User user)
    {
        if (null == user || !users.containsKey(user.getName().toLowerCase()))
        {
            return;
        }
        users.put(user.getName().toLowerCase(), user);
        deletedUsers.remove(user);
    }

    public boolean containsUserWithUserName(String username)
    {
        return users.containsKey(username.toLowerCase());
    }

    public User getUser(String username)
    {
        if (!users.containsKey(username.toLowerCase()))
        {
            return null;
        }
        return users.get(username.toLowerCase());
    }

    /**
     * Returns the deleted users
     * 
     * @return
     */
    public Set<User> getDeletedUsers()
    {
        return deletedUsers;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((users == null) ? 0 : users.hashCode());
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
        
        final Users other = (Users) obj;

        if (users == null)
        {
            if (other.users != null)
                return false;
        }
        else if (!users.equals(other.users))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("users",
                this.users.values()).append("deleted users", this.deletedUsers);
        return sb.toString();
    }

}