/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2011-2014 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  thmt001 07/Feb/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
07/Feb/08  thmt001        ccmpd00977875 Load SW modifications
12/Jan/09  Michael Leismann      CCMPD01146563 Added getLoadedSoftwares method
22/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure
12/Apr/10  hgkj73                CCMPD01325308 Added annotations
05/08/10   Hai Dong              ccmpd01378024 Add support software table.
04/May/11  Michael Leismann      CCMPD01502974 iTM 5.0: Proxy lib part of Panda
                                               integration - polling
10/May/11  Michael Leismann      CCMPD01505986 iTM 5.0: Proxy lib part of Panda
                                               integration - off-line and
                                               stand-alone part
17/May/11  Hai Dong              CCMPD01502972 Get SW version by CP version
21/Jun/11  cjh102                CCMPD01518983 Added method for adding 
                                               release package 
10/10/14   cmdg37                CCMPD01930966 Enhancement for adding release packages,
                                               multi-selection is supported.                                                     
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

/**
 * @Version 1.0 2007 April 18
 * @author w23021(Jason)
 * 
 * This interface is used to operate software in db
 */
import java.util.List;


import com.mot.dm.common.model.Software;
import com.mot.dm.common.model.SupportSoftware;
import com.mot.dm.common.util.exception.ServiceException;

public interface SoftwareService extends Service
{

    /**
     * Get the software based on radio ext model type and codeplug structure
     * version.
     * 
     * @param extModelType
     * @param codeplugStructureVersion
     * @return SupportSoftware
     * @throws ServiceException
     */
    public SupportSoftware getSwByExtModelTypeAndCodepluigStructureVersion(String extModelType,
            String codeplugStructureVersion) throws ServiceException;

    /**
     * Get a list of Software by a list of extModelTypes
     * 
     * @param extModelTypes
     *            the extModelTypes
     * @return list of LoadSoftware return the LoadSoftware list according to
     *         the extModelTypes
     * @throws DataAccessException
     *             if there is an error the method will throw
     *             DataAccessException
     */
    public List<Software> getLoadedSwByExtModelType(List<String> extModelTypes)
            throws ServiceException;

    /**
     * Get a list of Software based on the extended model type and the sw
     * version build type
     * 
     * @param extModelTypes
     * @param swVerBuildType
     *            Typically "R", "MR", "MP"
     * @return
     * @throws ServiceException
     */
    public List<Software> getLoadedSwByExtModelTypeAndSwBuildType(List<String> extModelTypes,
            String swVerBuildType) throws ServiceException;

    /**
     * Add software record
     * 
     * @param software
     *            the software model
     * @return The Software object
     * @throws ServiceException
     *             if there is error in addSoftware, the method will throw
     *             ServiceException.
     */
    public Software addSoftware(Software software) throws ServiceException;

    /**
     * This method is used when adding  new Panda release packages to the system
     * 
     * @param repositoryFileNames
     * @return the resultMsgs
     *	       [null] : import successfully
     *         other value : the exception message.
     * @throws ServiceException
     */
    public String[] addReleasePackages(String[] repositoryFileNames) throws ServiceException;

    /**
     * Get Software by its path.
     * 
     * @param softwareFullPath
     *            the software's path in file system.
     * @return The Software object
     * @throws ServiceException
     *             if there is error in getSoftware, the method will throw
     *             ServiceException.
     */
    public Software getSoftware(String softwareFullPath) throws ServiceException;

    /**
     * Used to establish if a given software version is supported or not
     * 
     * @param softwareVersion
     * @return True if the software is supported, otherwise false
     * @throws ServiceException
     *             if there is service error, the method will throw
     *             ServiceException.
     */
    public boolean isSoftwareSupportedByVersion(String softwareVersion) throws ServiceException;

    /**
     * Used to get supported software by a version
     * 
     * @param softwareVersion
     * @return the supported software object
     * @throws ServiceException
     *             if there is service error, the method will throw
     *             ServiceException.
     */
    public SupportSoftware getSupportedSoftwareByVersion(String softwareVersion)
            throws ServiceException;

    /**
     * Get the supported softwares
     * 
     * @return List<SupportSoftware>
     * @throws ServiceException
     */
    public List<SupportSoftware> getSupportSoftwares() throws ServiceException;

    /**
     * Get all of softwares from database.
     * 
     * @return A list with all the loaded softwares in database
     * @throws ServiceException
     *             If there is an error, the method will throw ServiceException
     */
    public List<Software> getLoadedSoftwares() throws ServiceException;

    /**
     * Get all of release packages from database but only if the prefetch of
     * release packages is enabled
     * 
     * @return A list with all the loaded softwares in database
     * @throws ServiceException
     *             If there is an error, the method will throw ServiceException
     */
    public List<Software> getPrefetchablePackages() throws ServiceException;

    /**
     * Remove a software file from the database, i.e. set the full path to null.
     * The record will remain in the database.
     * 
     * @param software
     *            Software for which full path will be cleared.
     * @throws ServiceException
     *             if there is error in getSoftwares, the method will throw
     *             ServiceException.
     */
    public void removeSoftware(Software software) throws ServiceException;

    /**
     * Get loaded sw by version, e.g. R070005620
     * 
     * @param softwareVersion
     * @return Software
     * @throws ServiceException
     */
    public Software getLoadedSoftwareByVersion(String softwareVersion) throws ServiceException;
}
