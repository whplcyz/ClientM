/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2010-2014 Motorola Solutions Inc.            |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/Sep/08  chad001               CCMPD01090964 Add log for adding/deleting user
7/Sep/09   cjh102                CCMPD01252392 Updated equals method to use
                                               MessageDigest for the password
26/Nov/09  hgkj73                CCMPD01288783 Added support for agency partitioning
20/Jan/10  hgkj73                CCMPD01304268 copyright updated
10/Mar/10  hgkj73                CCMPD01317830 Updated for AP.
14/Apr/10  cjh102                CCMPD01327990 Removed creator.
04/May/10  Michael Leismann      CCMPD01340657 Added clone method
14/Sep/10  cvkh36                CCMPD01388671 Migrate MySQL to PostgreSQL
25/Jun/14 bfq463              CCMPD01900799 active directory support
30/Jun/14 bfq463              CCMPD01905014 active directory support
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.security.MessageDigest;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Version 1.0 2007 April 15
 * @author w23021(Jason)
 */
public class User extends ItmSecurityIdentity
{

    private static final long serialVersionUID = 7673406188936823810L;

    private String DN;

    public String getDN()
    {
        return DN;
    }

    public void setDN(String dN)
    {
        DN = dN;
    }

    private String userUuid;

    private String userId;

    private String userFullName;
    /*
     * Password has to be stored as binary in PostgreSQL since it may contain
     * special character e.g. 0x00
     */
    private byte[] hashedPassWordBytes;

    //store plain password from client input
    private String plainPassword; 
    
    public String getPlainPassword()
    {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword)
    {
        this.plainPassword = plainPassword;
    }

    private boolean superUser = false;

    private boolean builtInUser = false;

    private SystemPermissions systemPermissions = new SystemPermissions();

    /**
     * Clone method
     * 
     * @return the cloned object, or null
     */
    public Object clone()
    {
        User user = (User) super.clone();
        if (user != null)
        {
            SystemPermissions sp = (SystemPermissions) systemPermissions.clone();
            if (sp != null)
            {
                user.setSystemPermissions(sp);
                return user;
            }
        }

        return null;
    }

    /**
     * @return the superUser
     */
    public boolean isSuperUser()
    {
        return superUser;
    }

    /**
     * @return the builtInUser
     */
    public boolean isBuiltInUser()
    {
        return builtInUser;
    }

    /**
     * @return the systemPermissions
     */
    public SystemPermissions getSystemPermissions()
    {
        return systemPermissions;
    }

    /**
     * @param superUser
     *            the superUser to set
     */
    public void setSuperUser(boolean superUser)
    {
        this.superUser = superUser;
    }

    /**
     * @param builtInUser
     *            the builtInUser to set
     */
    public void setBuiltInUser(boolean builtInUser)
    {
        this.builtInUser = builtInUser;
    }

    /**
     * @param systemPermissions
     *            the systemPermissions to set
     */
    public void setSystemPermissions(SystemPermissions systemPermissions)
    {
        this.systemPermissions = systemPermissions;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userid)
    {
        this.userId = userid;
    }

    public String getUserFullName()
    {
        return userFullName;
    }

    public void setUserFullName(String userFullName)
    {
        this.userFullName = userFullName;
    }

    public byte[] getHashedPassWordBytes()
    {
        return this.hashedPassWordBytes;
    }

    public void setHashedPassWordBytes(byte[] hashedPasswordBytes)
    {
        this.hashedPassWordBytes = hashedPasswordBytes;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("userId", this.userId);
        sb.append("usesFullname", this.userFullName);
        sb.append("DN", this.DN);
        sb.append("password", this.hashedPassWordBytes);
        sb.append("system permissions", this.systemPermissions);

        return sb.toString();
    }

    public String getUserUuid()
    {
        return userUuid;
    }

    public void setUserUuid(String userUniId)
    {
        this.userUuid = userUniId;
    }

    @Override
    public String getDetails()
    {
        return userFullName;
    }

    @Override
    public String getUuid()
    {
        return getUserUuid();
    }

    @Override
    public String getName()
    {
        return userId;
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
        
        final User other = (User) obj;
        if (builtInUser != other.builtInUser)
            return false;
        if (superUser != other.superUser)
            return false;
        if (systemPermissions == null)
        {
            if (other.systemPermissions != null)
                return false;
        }
        else if (!systemPermissions.equals(other.systemPermissions))
            return false;
        if (userFullName == null)
        {
            if (other.userFullName != null)
                return false;
        }
        else if (!userFullName.equals(other.userFullName))
            return false;
        if (userId == null)
        {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;

        if (DN == null)
        {
            if (other.DN != null)
                return false;
        }
        else if (!DN.equals(other.DN))
            return false;

        if (userUuid == null)
        {
            if (other.userUuid != null)
                return false;
        }
        else if (!userUuid.equals(other.userUuid))
            return false;

        if (plainPassword == null)
        {
            if (other.plainPassword != null)
                return false;
        }
        else if (!plainPassword.equals(other.plainPassword))
            return false;
        
        // Special handling for comparing the passwords
        return MessageDigest.isEqual(this.hashedPassWordBytes, other.hashedPassWordBytes);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder(1185060469, -706886255)
                .append(this.DN)
                .append(this.hashedPassWordBytes)
                .append(this.userId)
                .append(this.systemPermissions)
                .append(this.plainPassword)
                .append(this.userFullName)
                .append(this.builtInUser)
                .append(this.superUser)
                .append(this.userUuid)
                .toHashCode();
    }

}
