/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                    Copyright 2014 Motorola Solutions, Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  bfq463    May 26, 2014

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description> 
25/Jun/14 bfq463              CCMPD01900799 active directory support
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class LdapUser extends BaseObject
{
    private static final long serialVersionUID = 8809508850326377753L;
    private String logonName;
    private String displayName;
    private String email;
    private String DN;

    public LdapUser()
    {

    }

    public String getDN()
    {
        return DN;
    }

    public void setDN(String dN)
    {
        DN = dN;
    }

    public String getLogonName()
    {
        return logonName;
    }

    public void setLogonName(String logonName)
    {
        this.logonName = logonName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return new StringBuilder().append("LogonName:").append(logonName)
                .append("DisplayName:").append(displayName).append("Email:")
                .append(email).toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o != null && (o instanceof LdapUser))
        {
            LdapUser user = (LdapUser) o;
            if (this.displayName.equals(user.getDisplayName())
                    && this.logonName.equals(user.getLogonName())
                    && this.email.equals(user.getEmail()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(logonName).append(displayName)
                .append(email).hashCode();
    }
}
