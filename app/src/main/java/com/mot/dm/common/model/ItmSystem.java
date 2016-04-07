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
10/Mar/10   hgkj73               CCMPD01317830 Added for Agency Partitioning
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Itm System
 * 
 * @author hgkj73
 * 
 */
public class ItmSystem extends BaseObject
{

    private static final long serialVersionUID = -8506700361451215333L;

    private String version = "";

    private String name = "";

    /**
     * get version of ItmServer
     * 
     * @return
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * set version of the Itm Server
     * 
     * @param version
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * get Itm Server name
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set name of the Itm Server
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        
        final ItmSystem other = (ItmSystem) obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (version == null)
        {
            if (other.version != null)
                return false;
        }
        else if (!version.equals(other.version))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "version", this.version).append("name", this.name);
        return sb.toString();
    }

}
