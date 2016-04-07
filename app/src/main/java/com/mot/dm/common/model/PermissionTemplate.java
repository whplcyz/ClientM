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
11/Mar/10   hgkj73               CCMPD01317830 Added for for ITM 3.0 AP feature
04/May/10   hgkj73               CCMPD01340657 updated to match db table
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Represents a Permission template
 * 
 * @author hgkj73
 * 
 */
public class PermissionTemplate extends BaseObject
{

    private static final long serialVersionUID = -8698638165346667009L;

    private String templateName = "";

    private String templateUuid = "";

    private boolean superUser;

    private String notes;

    private int systemPermissionMask;

    private SystemPermissions systemPermissions;

    public PermissionTemplate()
    {
        systemPermissions = new SystemPermissions();
    }

    public PermissionTemplate(String templateName, SystemPermissions systemPermissions)
    {
        this.systemPermissions = systemPermissions;
        this.templateName = templateName;
        this.superUser = false;
    }

    public PermissionTemplate(String templateName, SystemPermissions systemPermissions,
            boolean superUser)
    {
        this.systemPermissions = systemPermissions;
        this.templateName = templateName;
        this.superUser = superUser;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getTemplateUuid()
    {
        return templateUuid;
    }

    public void setTemplateUuid(String templateUuid)
    {
        this.templateUuid = templateUuid;
    }

    public int getSystemPermissionMask()
    {
        return systemPermissionMask;
    }

    @SuppressWarnings("unused")
    private void setSystemPermissionMask(int systemPermissionMask)
    {
        this.systemPermissionMask = systemPermissionMask;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public boolean isSuperUser()
    {
        return superUser;
    }

    public SystemPermissions getSystemPermissions()
    {
        return systemPermissions;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public void setSuperUser(boolean superUser)
    {
        this.superUser = superUser;
    }

    public void setSystemPermissions(SystemPermissions systemPermissions)
    {
        this.systemPermissions = systemPermissions;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 11;
        result = prime * result + (superUser ? 1231 : 1237);
        result = prime * result + ((systemPermissions == null) ? 0 : systemPermissions.hashCode());
        result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
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
        
        final PermissionTemplate other = (PermissionTemplate) obj;
        if (superUser != other.superUser)
            return false;
        
        if (systemPermissions == null)
        {
            if (other.systemPermissions != null)
                return false;
        }
        else if (!systemPermissions.equals(other.systemPermissions))
            return false;
        if (templateName == null)
        {
            if (other.templateName != null)
                return false;
        }
        else if (!templateName.equals(other.templateName))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("templateName", templateName)
                .append("systemPermissions", systemPermissions).append("superUser", superUser);
        return sb.toString();
    }

}
