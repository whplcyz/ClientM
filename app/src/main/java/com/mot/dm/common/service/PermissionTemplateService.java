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
CODED     BY:  hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
12/Mar/10   hgkj73               CCMPD01317830 Added for for ITM 3.0 AP feature
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.PermissionTemplates;
import com.mot.dm.common.util.exception.ServiceException;

public interface PermissionTemplateService extends Service
{
    /**
     * Loads all permission templates
     * 
     * @return
     */
    @PreAuthorize("isSuperUser() or hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public PermissionTemplates getAllPermissionTemplates() throws ServiceException;
}
