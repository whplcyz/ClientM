/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
12/01/08   chad001               CCMPD00962248 Export codeplug, move device and folder
03/09/08   ckfm01                CCMPD01089432 adding support for agency full path creation
26/Nov/09  hgkj73                CCMPD01288783 Added interfaces for mandatory template
                                               handling
20/Jan/10  hgkj73                CCMPD01304268 copyright updated, 
                                               removed unused interface  
02/02/10   chad001               ccmpd01306112 Add interface for checking if
                                               a list of devices contains
                                               mandatory templates
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1 
18/Mar/10  hgkj73                CCMPD01325308 iTM 3.0 AP requirements handled
11/11/10   ckfm01                CCMPD01409592 managing of folder changes regarding sitewide. 
22/Jun/11  Hai Dong              CCMPD01524144 Cross group copy: use ext model types 
                                               instead of ItmModelType      
17/July/12 wch378                CCMPD01676451 Enhancement on Auto-Include feature                                           
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Agency;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.EnforcedMandatoryFiles;
import com.mot.dm.common.util.exception.ServiceException;

/**
 * @version 1.00 20 April 2007
 * @author Kevin Xia
 */
public interface AgencyService extends Service
{
    /** 
     * To get default agency(10001),and that is used on special case,
     * it could by-pass the permission checking.
     * 
     * @return String
     * @throws ServiceException
     */
    public Agency getDefaultAgency() throws ServiceException;
    /**
     * To get agency by its identifier.
     * 
     * @param agencyUuid
     * @return Agency
     * @throws ServiceException
     */
    @PostAuthorize("isSuperUser() or hasPermission(returnObject, 'VIEW')")
    public Agency getAgency(String agencyUuid) throws ServiceException;

    /**
     * Add a new agency.
     * 
     * @param agency
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#agency.parentAgency, 'MANAGE_FOLDERS')")
    public Agency addAgency(Agency agency) throws ServiceException;

    /**
     * Used for renaming a folder. All other changes in the provided agency
     * object are disregarded.
     * 
     * @param agency
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#agency, 'MANAGE_FOLDERS')")
    public void renameAgency(Agency agency) throws ServiceException;

    /**
     * remove an agency by their agency identify.
     * 
     * @param agencyUuid
     * @throws ServiceException
     */
    public void removeAgency(String agencyUuid) throws ServiceException;

    /**
     * move an agency as child of the target agency.
     * 
     * @param newAgency
     *            , oldAgency
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#newAgency, 'MANAGE_FOLDERS') and hasPermission(#oldAgency, 'MANAGE_FOLDERS')")
    public void moveAgency(Agency newAgency, Agency oldAgency) throws ServiceException;

    /**
     * If a root agency has been set, then get current root agency from DB. Any
     * authenticated user can access the RootAgency. Note that RootAgency is not
     * same as Default agency.
     * 
     * @return Agency
     * @throws ServiceException
     */
    public Agency getRootAgency() throws ServiceException;

    /**
     * Get all of the agencies.
     * 
     * @return
     * @throws ServiceException
     */
    @PostFilter("isSuperUser() or hasPermission(filterObject, 'VIEW')")
    public List<Agency> getAllAgency() throws ServiceException;

    /**
     * Get all the agencies exclude current root agency.
     * 
     * @return List<Agency>
     * @throws ServiceException
     */
    @PostFilter("isSuperUser() or hasPermission(filterObject, 'VIEW')")
    public List<Agency> getAllAgencyExceptRootAgency() throws ServiceException;

    /**
     * get current agency's children.
     * 
     * @param parentAgencyUuid
     * @return List<Agency>
     * @throws ServiceException
     */
    @PostFilter("isSuperUser() or hasPermission(filterObject, 'VIEW')")
    public List<Agency> getChildAgency(String parentAgencyUuid) throws ServiceException;

    /*
     * Get an agency's parent. @param currentAgencyUuid @return an object Agency
     * 
     * @throws ServiceException
     */
    @PostAuthorize("isSuperUser() or hasPermission(returnObject, 'VIEW')")
    public Agency getParentAgency(String currentAgencyUuid) throws ServiceException;

    /**
     * @param path
     * @return a agency
     * @throws ServiceException
     */
    @PostAuthorize("isSuperUser() or hasPermission(returnObject, 'VIEW')")
    public Agency getAgencyByPath(String path) throws ServiceException;

    /**
     * @param fullPath
     * @return the created agency
     * @throws ServiceException
     */
    public Agency addAgencyFullPath(String fullPath) throws ServiceException;

    /**
     * get the mandatory profiles setup for this folder.
     * 
     * @param agency
     * @return EnforcedMandatoryTemplates - List of all loaded mandatory and
     *         network/fleet templates - its worth noting they are loaded
     *         templates but enforced based on enforce settings on agency
     * @throws ServiceException
     */

    @PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')") 
    public EnforcedMandatoryFiles getMandatoryFiles(Agency agency) throws ServiceException;

    
    
    /**
     * get applicable enforced profiles to be used during programming.
     * This method searches in the agency tree and returns the most relevant.
     * The decision whether to use the templates during programming is
     * controlled by enforce flags on Agency
     * 
     * @param agency
     * @param cpStructureVersion
     * @param modelTypes
     * @return EnforcedMandatoryTemplates
     * @throws ServiceException
     */
    public EnforcedMandatoryFiles getMandatoryFiles(Agency agency,
            String cpStructureVersion, List<String> modelTypes) throws ServiceException;

    /**
     * Saves the Agency object - only enforce flags. Note that this interface
     * cannot be used for rename of folder as this interface requires enforce
     * permissions to operate. It updates the Mandatory templates configured on
     * the agency, if recursive flag is true then the settings of the enforce
     * flag stored in the agency object is applied recursively. Please note that
     * setting enforce flag recursively does not mean sub folders need to be
     * loaded with the mandatory templates of parent folder.
     * 
     * @param agency
     * @param templates
     * @param enforceRecursiveNWT
     * @param enforceRecursiveFT
     * @throws ServiceException
     */

    @PreAuthorize("hasPermission(#agency, 'ENFORCE_NW_TEMPL') or hasPermission(#agency, 'ENFORCE_FM_TEMPL')")
    public void updateAgencyAndMandatoryTemplates(Agency agency,
            EnforcedMandatoryFiles templates, boolean enforceRecursiveNWT,
            boolean enforceRecursiveFT) throws ServiceException;
    
    
    @PreAuthorize("hasPermission(#agency, 'ENFORCE_PROFILE')")
    public void updateAgencyAndMandatoryProfiles(Agency agency,
            EnforcedMandatoryFiles templates, boolean enforceRecursiveProfile) throws ServiceException;
    

    /**
     * To get agency by its Name.
     * 
     * @param agencyName
     * @return Agency
     * @throws ServiceException
     */
    @PostAuthorize("isSuperUser() or hasPermission(returnObject, 'VIEW')")
    public Agency getAgencyByName(String agencyName) throws ServiceException;

    /*
     * check if the device list contains any device that is used as mandatory
     * template
     * 
     * @param list List<Device>
     * 
     * @return boolean
     * 
     * @throws ServiceException
     */
    public boolean listContainMandatoryTemplate(List<Device> list) throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES') and hasPermission(#agency, 'MANAGE_FOLDERS')")
    public void updateSiteWideOnFolders(Agency agency) throws ServiceException;

    @PreAuthorize("hasPermission(#agency, 'MANAGE_FOLDERS')")
    public void modifyAgency(Agency agency) throws ServiceException;
}
