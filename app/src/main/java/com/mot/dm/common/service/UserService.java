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

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/Sep/08  chad001               CCMPD01090964 Add log for adding/deleting user
15/Mar/10   hgkj73               CCMPD01317830 Added new user management feature
14/Apr/10  Michael Leismann      CCMPD01327990 Updated user service code
                                               (removed obsolete methods)
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

/**
 * @Version 1.0 2007 April 15
 * @author w23021(Jason)
 * 
 * This interface is provide for Client to operate user.
 */
import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.User;
import com.mot.dm.common.model.Users;
import com.mot.dm.common.util.exception.ServiceException;

public interface UserService extends Service
{
    /**
     * Change the password of the currently logging in user.
     * 
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws ServiceException
     */
    @PreAuthorize("#username == principal.username")
    public boolean changePassword(String username, String oldPassword, String newPassword)
            throws ServiceException;

    /**
     * This method is used to get the user according it's id.
     * 
     * @param userid
     *            the user's username.
     * @return User return the user model
     * @throws ServiceException
     *             if there is error in getUser,the method will throw
     *             ServiceException.
     */
    public User getUser(String username) throws ServiceException;

    /**
     * This method is used to get all users in the database
     * 
     * @return Users
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public Users getAllUsers() throws ServiceException;

    /**
     * This method is used to update given users
     * 
     * @param Users
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public void updateUsers(Users users) throws ServiceException;

}
