/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                         Copyright 2010 Motorola Inc.                         |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM   
CODED     BY:  hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Mar/10   hgkj73               CCMPD01317830 Added Groups feature
12/Apr/10  hgkj73                CCMPD01325308 iTM 3.0 AP requirements handled
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Groups;
import com.mot.dm.common.util.exception.ServiceException;

public interface GroupService extends Service
{
    /**
     * Interface get all loaded groups in the system
     * 
     * @return Groups
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public Groups getGroups() throws ServiceException;

    /**
     * Interface to update the given groups as per the infromation in the Groups
     * object
     * 
     * @param groups
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public void updateGroups(Groups groups) throws ServiceException;
}
