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
CODED     BY:  bfq463    May 27, 2014

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/Jun/14 bfq463              CCMPD01900799 active directory support 
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class LdapConfig implements Serializable
{

    private List<FilterAttribute> filterAttributes = new ArrayList<FilterAttribute>();

    public void setFilterAttributes(List<FilterAttribute> filterAttributes)
    {
        if(filterAttributes == null)
        {
            filterAttributes = new ArrayList<FilterAttribute>(); 
        }
        this.filterAttributes = filterAttributes;
    }

    public String generateFilter()
    {
        String prefix = "";
        String suffix = "";
        String wildcard = "=*%s*";
        StringBuilder buff = new StringBuilder();
        
        int userfulCount = 0;
        for (FilterAttribute attribute : filterAttributes)
        {
            if (attribute.isUseful())
            {
                userfulCount++;
                buff.append("(").append(attribute.getAttributeName()).append(wildcard).append(")");
            }
            if (userfulCount > 1)
            {
                prefix = "(|";
                suffix = ")";
            }
        }
        return prefix + buff.toString() + suffix;
    }

    public List<FilterAttribute> getFilterAttributes()
    {
        return filterAttributes;
    }

    public static enum AuthenticationMethod
    {
        None,
        Simple
    }

    public static enum EncryptionMethod
    {
        NoEncryption,
        SSLORTLS,
        StartTLS
    }

    public static final String DEFAULT_PASSWORD = "changeit";
    public static final String CERTIFICATE_PREFIX_ALIAS = "ldap";

    /**
     * The constant for Limit size search result from LDAP server
     */
    public static final int REESULT_LIMIT_SIZE = 100;
    /**
     * SerialVersionUID 
     */
    private static final long serialVersionUID = 5527412797034189772L;

    /**
     * The constant for LdapCtxFactory
     */
    public static final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    /** The constant for "ldaps://" scheme. */
    private static final String LDAPS_SCHEMA = "ldaps://";

    /** The constant for "ldap://" scheme. */
    private static final String LDAP_SCHEMA = "ldap://";

    private String host;
    private int port;
    private boolean isLDAPEnable;
    private EncryptionMethod encryptionMethod;
    private AuthenticationMethod authenticationMethod;
    private String baseDN;
    private String filter;
    private String logonNameAttribute;
    private String displayNameAttribute;
    private String emailAddressAttriubte;
    private String bindDN;
    private String bindPassword;

    public EncryptionMethod getEncryptionMethod()
    {
        return encryptionMethod;
    }

    public void setEncryptionMethod(EncryptionMethod encryptionMethod)
    {
        this.encryptionMethod = encryptionMethod;
    }

    public String getLogonNameAttribute()
    {
        return logonNameAttribute;
    }

    public void setLogonNameAttribute(String logonNameAttribute)
    {
        this.logonNameAttribute = logonNameAttribute;
    }

    public String getDisplayNameAttribute()
    {
        return displayNameAttribute;
    }

    public void setDisplayNameAttribute(String displayNameAttribute)
    {
        this.displayNameAttribute = displayNameAttribute;
    }

    public String getEmailAddressAttriubte()
    {
        return emailAddressAttriubte;
    }

    public void setEmailAddressAttriubte(String emailAddressAttriubte)
    {
        this.emailAddressAttriubte = emailAddressAttriubte;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public boolean isLDAPEnable()
    {
        return isLDAPEnable;
    }

    public void setLDAPEnable(boolean isLDAPEnable)
    {
        this.isLDAPEnable = isLDAPEnable;
    }

    /**
     * @return LDAP server URL. eg:ldap://127.0.0.1:389
     */
    public String getUrl()
    {
        if (this.isSSLEnable())
        {
            return LDAPS_SCHEMA + host + ":" + port;
        }
        return LDAP_SCHEMA + host + ":" + port;
    }

    public boolean isSSLEnable()
    {
        return this.encryptionMethod == EncryptionMethod.SSLORTLS;
    }

    public AuthenticationMethod getAuthenticationMethod()
    {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(
            AuthenticationMethod authenticationMethod)
    {
        this.authenticationMethod = authenticationMethod;
    }

    public String getBaseDN()
    {
        return baseDN;
    }

    public void setBaseDN(String baseDN)
    {
        this.baseDN = baseDN;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public String getBindDN()
    {
        return bindDN;
    }

    public void setBindDN(String bindDN)
    {
        this.bindDN = bindDN;
    }

    public String getBindPassword()
    {
        return bindPassword;
    }

    public void setBindPassword(String bindPassword)
    {
        this.bindPassword = bindPassword;
    }

    public void afterPropertiesSet()
    {
        if (host == null)
        {
            host = "";
        }
        if (encryptionMethod == null)
        {
            encryptionMethod = EncryptionMethod.NoEncryption;
        }
        if (authenticationMethod == null)
        {
            authenticationMethod = AuthenticationMethod.None;
        }
        if (bindDN == null)
        {
            bindDN = "";
        }
        if (bindPassword == null)
        {
            bindPassword = "";
        }
        if (baseDN == null)
        {
            baseDN = "";
        }
        if (filter == null)
        {
            filter = "";
        }
        if (logonNameAttribute == null)
        {
            logonNameAttribute = "";
        }
        if (displayNameAttribute == null)
        {
            displayNameAttribute = "";
        }
        if (emailAddressAttriubte == null)
        {
            emailAddressAttriubte = "";
        }
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("IsLDAPEnable:").append(this.isLDAPEnable()).append(" ")
                .append("Host:").append(this.getHost()).append(" ")
                .append("Port:").append(this.getPort()).append(" ")
                .append("EncryptionMethod:").append(this.getEncryptionMethod()).append(" ")
                .append("AuthenticationMethod:").append(this.getAuthenticationMethod()).append(" ")
                .append("Bind DN:").append(this.getBindDN()).append(" ")
                .append("Bind Password:").append(this.getBindPassword()).append(" ")
                .append("Base DN:").append(this.getBaseDN()).append(" ")
                .append("Search Filter:").append(this.getFilter()).append(" ")
                .append("LogonName Attribute:").append(this.getLogonNameAttribute()).append(" ")
                .append("DisplayName Attribute:").append(this.getDisplayNameAttribute())
                .append(" ")
                .append("Email Attribute:").append(this.getEmailAddressAttriubte());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof LdapConfig))
        {
            return false;
        }
        else
        {
            LdapConfig config = (LdapConfig) o;
            return this.toString().equals(config.toString());
        }
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(isLDAPEnable).append(host).append(port)
                .append(encryptionMethod).append(authenticationMethod).append(bindDN)
                .append(bindPassword)
                .append(baseDN).append(filter).append(logonNameAttribute)
                .append(displayNameAttribute).append(emailAddressAttriubte).hashCode();
    }
}
