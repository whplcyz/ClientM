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
10/Mar/10   hgkj73               CCMPD01317830 Added Groups feature
21/Mar/10   cjh102               CCMPD01320408 Removed creation date from equals
                                               method
20/Apr/10   hgkj73               CCMPD01325308 Minor updates
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Represents a Group
 * 
 * @author hgkj73
 * 
 */
public class Group extends ItmSecurityIdentity
{

    private static final long serialVersionUID = -6633805584011419339L;

    private String groupUuid;

    private String groupName = "";

    private String oldName = "";

    private String notes = "";

    private Timestamp creationDate;

    private Set<User> users;

    public Group()
    {
        users = new HashSet<User>();
    }

    public Group(String groupName)
    {
        this.groupName = groupName;
        users = new HashSet<User>();
    }

    public Group(String groupName, String notes)
    {
        this.groupName = groupName;
        this.notes = notes;
        users = new HashSet<User>();
    }

    public String getGroupUuid()
    {
        return groupUuid;
    }

    public void setGroupUuid(String groupUuid)
    {
        this.groupUuid = groupUuid;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        if (!this.groupName.equals(groupName))
        {
            oldName = this.groupName;
            this.groupName = groupName;
        }
    }

    /**
     * this is not to be used by client/server. will be used only by Groups
     * class
     * 
     * @return String
     */
    public String getOldName()
    {
        return oldName;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setUsers(Set<User> users)
    {
        if (null == users)
        {
            return;
        }
        for (User user : users)
        {
            if (user != null)
            {
                this.users.add(user);
            }
        }
    }

    public Set<User> getUsers()
    {
        return this.users;
    }

    public void addUser(User user)
    {
        this.users.add(user);
    }

    public void removeUser(User user)
    {
        this.users.remove(user);
    }

    public void addUser(List<User> users)
    {
        this.users.addAll(users);
    }

    public void removeUser(List<User> users)
    {
        this.users.removeAll(users);
    }

    @Override
    public String getName()
    {
        return getGroupName();
    }

    @Override
    public String getDetails()
    {
        return getNotes();
    }

    @Override
    public String getUuid()
    {
        return getGroupUuid();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result + ((groupUuid == null) ? 0 : groupUuid.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
        
        final Group other = (Group) obj;
        if (groupName == null)
        {
            if (other.groupName != null)
                return false;
        }
        else if (!groupName.equals(other.groupName))
            return false;
        if (groupUuid == null)
        {
            if (other.groupUuid != null)
                return false;
        }
        else if (!groupUuid.equals(other.groupUuid))
            return false;
        if (notes == null)
        {
            if (other.notes != null)
                return false;
        }
        else if (!notes.equals(other.notes))
            return false;
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
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("groupUuid", this.groupUuid).append("groupName", this.groupName)
                .append("notes", this.notes).append("creationdate", this.creationDate)
                .append("users", this.users);
        return sb.toString();
    }

}