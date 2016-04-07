/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2008-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kalyan Pushpala 30/07/09

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
30/07/09   Kalyan Pushpala       CCMPD01237099 Login and Logout utility is 
                                               made Which can be used by all clients 
                                               to authenticate on server
15/09/09   Kalyan Pushpala       CCMPD01261809 Added overloaded methods so that 
                                               tools that has different strategy
                                               for initializing the bean factory 
                                               can also use the utils.
23/09/09   Kalyan Pushpala       CCMPD01266263 Improved logging.
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1  
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import org.apache.log4j.Logger;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.rcp.RemoteAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mot.dm.common.delegation.ServiceDelegationWrapper;
import com.mot.dm.common.service.LogoutService;
import com.mot.dm.common.util.exception.AuthenticationRequiredException;
import com.mot.dm.common.util.exception.LicenseException;
import com.mot.dm.common.util.exception.LicenseInvalid;
import com.mot.dm.common.util.exception.LicenseNoConnectionAvailable;
import com.mot.dm.common.util.exception.LicenseNotNetworked;

/**
 * This class should contain common methods
 * 
 * @author cml066
 * 
 */
public final class SecurityUtils
{
    private SecurityUtils()
    {
        
    }
    private static Logger fgLogger = Logger.getLogger("com.mot.dm");

    /**
     * Common logout utility for proxy, client and other tools
     * 
     * @param username
     */

    public static void logout(String username, LogoutService logoutService)
    {
        try
        {
            logoutService.logout(username);
            fgLogger.info("  Logged out user:" + username);
        }
        catch (RemoteAccessException ex)
        {
            // ignore the exception as it means server may not be available. so
            // never mind
            fgLogger.info("  Logged out user:" + username);
        }
        catch (AuthenticationRequiredException ex)
        {
            // ignore the exception as it means user is already logged out
            fgLogger.info("  Logged out user:" + username);
        }
        catch (Exception ex)
        {
            // intentional supression of any exception during login process as
            // we dont
            // want any thing else to report incase of failure. Logout typically
            // executed
            // when the application wants to exit or change the user which
            // doesnt need to care
            // any exceptions ( like server down, no authorization, etc...)
            fgLogger.error("  SecurityUtils.logout(): Logout Failed: Exception", ex);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * Common login utility for proxy, client and other tools
     * 
     * @param username
     *            , password
     * @return true - successful authentication false - failed to login
     */

    public static boolean login(String username, String password,
            RemoteAuthenticationProvider remoteAuthProvider,StringBuilder errorType)
    {
        try
        {
            Authentication auth = new UsernamePasswordAuthenticationToken(username,
                    password);
            // Need to set it so that username/password can be part of the login
            // request so that server can authenticate using basic-auth
            SecurityContextHolder.getContext().setAuthentication(auth);
            // Here if the username/passwd are correct then we get
            // Authentication object
            // populated with GrantedAuthorities (permissions) for the user.
            auth = remoteAuthProvider.authenticate(auth);
            if (auth != null)
            {
                SecurityContextHolder.getContext().setAuthentication(auth);
                fgLogger.info("  SecurityUtils.Login() : Login Successful");
                return true;
            }
        }
        catch (RemoteAccessException ex)
        {
            if (ex.getCause() instanceof AuthenticationRequiredException)
            {
                AuthenticationRequiredException cause = (AuthenticationRequiredException)ex.getCause();
                if(cause.getCause() != null && errorType !=null)
                {
                    errorType.delete(0, errorType.length());
                    errorType.append(cause.getCause().getMessage());
                }
                fgLogger.info("  SecurityUtils.Login(): Login Failed - Invalid credentials");
            }
            else
            {
                fgLogger.error("  SecurityUtils.Login():  Login Failed - RemoteAccessException", ex);
            }
        }
        catch (LicenseException le)
        {
            // With respect to license exceptions, which ends up here, we would
            // like to re-throw then to provide the underlying application (for
            // instance the provisioning tool) to give a more detailed error
            // message to the user. Normally these exceptions will be handled by
            // the outer exception handling layers of client
            // (ClientExceptionHandlingAdvice) and proxy (BaseAction)
            if (le instanceof LicenseNotNetworked)
            {
                fgLogger.info("  SecurityUtils.Login(): Login Failed - No networked license");
                throw le;
            }
            else if (le instanceof LicenseNoConnectionAvailable)
            {
                fgLogger.info("  SecurityUtils.Login(): Login Failed - No more client connections available");
                throw le;
            }
            else if (le instanceof LicenseInvalid)
            {
                fgLogger.info("  SecurityUtils.Login(): Login Failed - System license invalid");
                throw le;
            }
            else
            {
                fgLogger.error("  SecurityUtils.Login():  Login Failed - LicenseException", le);
            }
        }
        catch (Exception ex)
        {
            // intentional suppress of any exception during login process as we
            // dont
            // want any thing else to happen other than showing invalid
            // credentials.
            fgLogger.error("  SecurityUtils.Login(): Login Failed - Exception", ex);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return false;
    }
    
    /**
     * Common logout utility for proxy, client and other tools which use
     * ServiceDelegation interface for initializing the bean factory Classes
     * using this utility need to make sure that ServiceDelegationWrapper is
     * properly initialized before they use this utility
     * 
     * @param username
     */

    public static void logout(String username)
    {
        LogoutService logoutService = (LogoutService) ServiceDelegationWrapper
                .getService("logoutService");
        logout(username, logoutService);

    }

    /**
     * Common login utility for proxy, client and other tools which use
     * ServiceDelegation interface for initializing the bean factory Classes
     * using this utility need to make sure that ServiceDelegationWrapper is
     * properly initialized before they use this utility
     * 
     * @param username
     *            , password
     * @return true - successful authentication false - failed to login
     */

    public static boolean login(String username, String password)
    {
        RemoteAuthenticationProvider remoteAuthProvider = (RemoteAuthenticationProvider) ServiceDelegationWrapper
                .getService("remoteAuthenticationProvider");
        return login(username, password, remoteAuthProvider,null);
    }
    
    /**
     * special handle for client and proxy login, let client and proxy know failed reason if authentication is failed when LDAP enable.
     * @param username
     * @param password
     * @param ldapErrorMsg
     * @return
     */
    public static boolean login(String username, String password, StringBuilder errorType)
    {
        RemoteAuthenticationProvider remoteAuthProvider = (RemoteAuthenticationProvider) ServiceDelegationWrapper
                .getService("remoteAuthenticationProvider");
        return login(username, password, remoteAuthProvider,errorType);
    }
}
