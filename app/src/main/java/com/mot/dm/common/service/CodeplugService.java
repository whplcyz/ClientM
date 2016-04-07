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
CODED     BY:  Jeff Lu

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database.
09/07/08   chad001               CCMPD01061654 Discard codeplug without using 
                                               servlet     
15/09/08   ckfm01                CCMPD01092340 Removing codeplugVersion table in DB.
                                               & Converting CPversion to Integer.                                 
                                               servlet     
22/09/08   chad001               CCMPD01090964 Codeplug is determined by fullpath
                                               and no need to pass version
14/12/09   hgkj73                CCMPD01288783 Added for Agency partitioning
18/01/10   hgkj73                CCMPD01304268 corrected the security for 
                                               getCodeplugsByDevice
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1                                                                                               
12/Apr/10  hgkj73                CCMPD01325308 Added annotations
27/Apr/10  hgkj73                CCMPD01340657 changed 30TODO to TODO as they
                                               cant be fixed in 3.0
09/Apr/12  gjmv74                CCMPD01640608 DB cleanup fails if a codeplug is used by mandatory templates 
02/Sep/14   bfq463              CCMPD01912723 server manager cleanup panel
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Codeplug;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.util.exception.ServiceException;

public interface CodeplugService extends Service
{

    /**
     * Add a new codeplug into Database.
     * 
     * @param codeplug
     * @throws ServiceException
     */
    // TODO: Can't be secured as of now as it is being used for
    // multiple scenarios at both client and proxy
    public Codeplug addCodeplug(Codeplug codeplug) throws ServiceException;

    /**
     * Update the current codeplug's attributes.
     * 
     * @param codeplug
     * @throws ServiceException
     */
    // TODO: ??? what and how w.r.t security
    public void updateCodeplug(Codeplug codeplug) throws ServiceException;

    /**
     * According the current fullpath to get its codeplug.
     * 
     * @param fullPath
     * @return Codeplug
     * @throws ServiceException
     */
    // TODO: Can't be secured as of now as it is being used for
    // multiple scenarios at both client and proxy
    public Codeplug getCodeplug(String fullPath) throws ServiceException;

    // TODO: Can't be secured as of now as it is being used for
    // multiple scenarios at both client and proxy
    public Codeplug getLatestCodeplug(Device device) throws ServiceException;

    /**
     * Remove a codeplug from database. Its as of not be used from Client/Proxy.
     * As of now only from tests. That is the reason to be with superUser flag.
     * 
     * @param codeplug
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser()")
    public void removeCodeplug(Codeplug codeplug) throws ServiceException;

    /**
     * Get the all of assigned codeplugs under a device.
     * 
     * @param device
     * @return List<Codeplug>
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public List<Codeplug> getCodeplugsByDevice(Device device) throws ServiceException;
    
    /**
     * Get the codeplug which software version is the same with the device
     * current version
     * 
     * @param device
     * @return List<Codeplug>
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public List<Codeplug> getCpByCurrentSoftversion(Device device) throws ServiceException;

    /**
     * Discard a codeplug
     * 
     * @param p_Codeplug
     *            Codeplug
     * @throws ServiceException
     */
    // TODO: Need SPL_PRG permissions. Should we choose the same approach
    // that we chose for Device to get its identifier.
    public void discardCodeplug(Codeplug p_Codeplug) throws ServiceException;

    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public boolean isInMandatoryTemplate(Codeplug codeplug) throws ServiceException;

    /**
     * same with other method named IsInMandatoryTemplate, this method just for server manager and no need authorization
     * @param codeplug
     * @return
     * @throws ServiceException
     */
    public boolean isInMandatoryTemplate4Cleanup(Codeplug codeplug) throws ServiceException;
    
    /**
     * same with other method named getCodeplugsByDevice, this method just for server manager and no need authorization
     * @param device
     * @return
     * @throws ServiceException
     */
    public List<Codeplug> getCodeplugsByDevice4Cleanup(Device device) throws ServiceException;
    
    /**
     * same with other method named removeCodeplug, this method just for server manager and no need authorization
     * @param codeplug
     * @throws ServiceException
     */
    public void removeCodeplug4Cleanup(Codeplug codeplug) throws ServiceException;
    
}
