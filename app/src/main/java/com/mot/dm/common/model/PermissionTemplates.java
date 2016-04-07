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
12/Mar/10   hgkj73               CCMPD01317830 Added for for ITM 3.0 AP feature
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Container for Permission templates
 * 
 * @author hgkj73
 * 
 */
public class PermissionTemplates extends BaseObject
{

    private static final long serialVersionUID = 4702312617587307074L;

    List<PermissionTemplate> permTemplates;

    public PermissionTemplates()
    {
        permTemplates = new ArrayList<PermissionTemplate>();
    }

    /**
     * @return the permTemplates
     */
    public List<PermissionTemplate> getPermTemplates()
    {
        return permTemplates;
    }

    /**
     * @param permTemplates
     *            the permTemplates to set
     */
    public void setPermTemplates(List<PermissionTemplate> permTemplates)
    {
        if (null == permTemplates)
        {
            return;
        }
        this.permTemplates.clear();
        this.permTemplates.addAll(permTemplates);
    }

    /**
     * retun matching template
     * 
     * @param perm
     * @return null if not found
     */
    public PermissionTemplate getMatchingTemplate(SystemPermissions perm, boolean superUserFlag)
    {
        for (PermissionTemplate permTemp : permTemplates)
        {
            if (permTemp.getSystemPermissions().equals(perm)
                    && superUserFlag == permTemp.isSuperUser())
            {
                return permTemp;
            }
        }

        return null;
    }

    public PermissionTemplate getMatchingTemplate(String tempName)
    {
        for (PermissionTemplate permTemp : permTemplates)
        {
            if (permTemp.getTemplateName().equals(tempName))
            {
                return permTemp;
            }
        }

        return null;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 11;
        result = prime * result + ((permTemplates == null) ? 0 : permTemplates.hashCode());
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
        
        final PermissionTemplates other = (PermissionTemplates) obj;
        if (permTemplates == null)
        {
            if (other.permTemplates != null)
                return false;
        }
        else if (!permTemplates.equals(other.permTemplates))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "permTemplates", permTemplates);
        return sb.toString();
    }

}
