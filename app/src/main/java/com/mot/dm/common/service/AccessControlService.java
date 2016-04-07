/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM   
CODED     BY:  hgkj73/cjh102  

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/11/09   hgkj73/cjh102         CCMPD01288783 Added for Agency partitioning
25/01/10   hgkj73                CCMPD01306290 apply sub folders per user support
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1 
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.BaseObject;
import com.mot.dm.common.model.ItmAccessControlEntry;
import com.mot.dm.common.model.ItmAccessControlList;
import com.mot.dm.common.model.ItmPermissions;
import com.mot.dm.common.model.ItmSecurityIdentity;
import com.mot.dm.common.util.exception.ServiceException;

public interface AccessControlService extends Service
{
    /**
     * returns permissions associated with the given securityIdentity for given
     * domain object
     * 
     * @param BaseObject
     * @param userName
     * @return Permissions
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'VIEW')")
    public ItmPermissions getPermissions(BaseObject object, ItmSecurityIdentity securityIdentity)
            throws ServiceException;

    /**
     * returns permissions associated with the current logged in user for given
     * domain object
     * 
     * @param BaseObject
     * @param userName
     * @return Permissions
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'VIEW')")
    public ItmPermissions getPermissions(BaseObject object) throws ServiceException;

    /**
     * returns AccessControlList for a given domain object
     * 
     * @param object
     * @return AccessControlList
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'VIEW')")
    public ItmAccessControlList getAccessControlList(BaseObject object) throws ServiceException;

    /**
     * Updates permissions on given domain object. It replaces the permissions
     * with the given AccessControlList - Its worth noting recursive flag
     * setting h in the accessControlEntry for a user, will be ignored if it
     * does not apply for the given domain object, for example - recursive may
     * be applicable for folders only as of now. - This interface will for each
     * entry in the list - add the permissions, if they doesnt exist - Updates
     * the permissions, if they exist - if all permissions revoked then it is
     * left to the implementation whether the permissions are updated or they
     * are entirely deleted from database.
     * 
     * @param object
     * @param listPermissions
     *            - This is complete list of users and their permissions.
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'ASSIGN_PERMISSIONS')")
    public void updatePermissions(BaseObject object, ItmAccessControlList acl)
            throws ServiceException;

    /**
     * Updates permissions on given domain object for a specific
     * securityIdentity. - This interface will - add the permissions, if they
     * doesnt exist - Updates the permissions, if they exist - if all
     * permissions revoked then it is left to the implementation whether the
     * permissions are updated or they are entrirely deleted from database. -
     * Its worth noting that the recursive flag in accessControl entry will be
     * ignored if it does not apply for the given domain object, for example
     * recursive may be applicable for folders only as of now.
     * 
     * @param object
     * @param AccessControlEntry
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'ASSIGN_PERMISSIONS')")
    public void updatePermissions(BaseObject object, ItmAccessControlEntry accessControlEntry)
            throws ServiceException;

    /**
     * Updates the given ItmAccessControl List with a set of
     * SecurityIdentities(users, roles, groups etc..) who are not on the input
     * acl. Note that input Acl need to be obtained from getAccessControlList
     * interface.
     * 
     * @param object
     *            - This is used to verify if the user have MANAGE_USERS
     *            permission on the current object.
     * @param ItmAccessControlList
     *            itmAcl
     * @return List<SecurityIdentity> - that are
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#object, 'ASSIGN_PERMISSIONS')")
    public ItmAccessControlList getNonConfiguredSids(BaseObject object, ItmAccessControlList itmAcl)
            throws ServiceException;

}
