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

package com.mot.dm.common.service;

import java.security.cert.X509Certificate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.LdapConfig;
import com.mot.dm.common.model.LdapNetWorkStatus;
import com.mot.dm.common.model.LdapUser;
import com.mot.dm.common.util.exception.ServiceException;

public interface LdapService
{
    /**
     * authenticate user against LDAP server with userName and password
     * @param userName iTM userName
     * @param password
     * @return true if success otherwise false
     * @throws ServiceException
     */
    public boolean authenticate(String userName, String password)
            throws ServiceException;

    /**
     * search LDAP server with filter condition userName, empty list will be returned if there
     * is not any user matched. 
     * @param userName
     * @return
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(system, 'MANAGE_GROUPS_USERS')")
    public List<LdapUser> filterUser(String userName) throws ServiceException;

    /**
     * check  if the network is OK, it will return true if success, otherwise false.
     * when it return false, you can get the certificate chain in parameter certificate.
     * @param host 
     * @param port 
     * @param encryptionMethod
     * @param certificate this parameter MUST not be null, you can not get the certificate chain if this parameter is NULL.
     * @return true if success, otherwise false
     */
    public LdapNetWorkStatus checkNetworkConnection(String host, int port, LdapConfig.EncryptionMethod encryptionMethod);

    /**
     * check if the parameter is correct with other LDAP configuration parameter already exist in database.
     * @param bindDN
     * @param bindPassword
     * @return true if success otherwise false
     * @throws ServiceException
     */
    public boolean checkAuthentication(String bindDN, String bindPassword);

    /**
     * 
     * @return a new LdapConfig instance
     * @throws ServiceException
     */
    public LdapConfig getLdapConfig() throws ServiceException;;

    public void updateLdapConfig(LdapConfig config)
            throws ServiceException;

    public boolean isLDAPEnable() ;

    /**
     * import a certificate into JVM default trust keystore
     * @param certificate a X509Certificate will be added into JVM default trust keystore
     * @throws ServiceException
     */
    public void importCertificate(X509Certificate certificate) throws ServiceException;

    /**
     * remove a certificate from JVM default trust keystore
     * @param certificate a X509Certificate will be removed from JVM default trust keystore
     * @throws ServiceException
     */
    public void removeCertificate(X509Certificate certificate) throws ServiceException;

    /**
     * export a certificate from JVM default trust keystore as a certificate file
     * @param certificate a X509Certificate will be exported as a certificate file
     * @param filepath the full path (contains file name and file expanded-name, eg: c:\\certificates\\ldap.cer ) of
     * the certificate file 
     * @throws ServiceException
     */
    public void exportCertificate(X509Certificate certificate, String filepath)
            throws ServiceException;

    /**
     * get all LDAP certificates from JVM default trust keystore
     * @return List<X509Certificate> an list, empty list will be returned if there are not any 
     * LDAP certificates in trust JVM default keystore
     * @throws ServiceException
     */
    public List<X509Certificate> getCertificate() throws ServiceException;
    
    public boolean testFilter(String filter);
}
