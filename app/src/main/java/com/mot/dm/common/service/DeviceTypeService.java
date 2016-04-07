/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |

|                                                                              |
|                Copyright 2011-2015 Motorola Solutions Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Server   
CODED     BY:  Michael Leismann   Sep 2009

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Sep/09  Michael Leismann      CCMPD01259651 Added header
                                               Updated to support the new radio
                                               type structure 
01/Oct/09  Jens Hansen           CCMPD01269158 Added method for getting itm 
                                               model type based on extended 
                                               model type           
16/jun/10  Hai Dong              CCMPD01357654 language bundles     
06/Jul/10  Hai Dong              ccmpd01365714 Personal data                                                                                    
11/May/11  Sigurdur Jonsson      CCMPD01502973 Panda integration
15/Jun/11  Sigurdur Jonsson      CCMPD01518634 Panda CPS integration
12/jul/12   gjmv74               CCMPD01675886 searching feature both through extmodeltype and itmmodeltypeId
21/Aug/12   gjmv74               CCMPD01689285 [iTM R6.0] Feature of German fire - opendevice TEI length check,
                                               find option TEI check, gui changes about wizardpageFeatures
03/Sep/12  a23126                CCMPD01692862 iTM6.0- Add flash pack support info in meta-data 
                                               - RadioModelInfo.xml    
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
26/Mar/13  wch378                CCMPD01750151 Prevent convert ext-model across itm-ModelType 
09/May/13  wch378         CCMPD01770362 iTM6.1 Judge personal data or special parameter is supported 
                                               by SwVersion(a new factor)  
24/June/13 wch378                 CCMPD01785776 rebuild pd and sp when target softerware or model change                                         
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

/**
 * @Version 1.0 2007 April 18
 * @author w23021(Jason)
 * 
 * This interface is used to operate device type in database
 */
import java.util.List;

import com.mot.dm.common.model.CrossModelCopyGroups;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.ModelNumber;
import com.mot.dm.common.model.ParameterItem;
import com.mot.dm.common.util.exception.ServiceException;

public interface DeviceTypeService extends Service
{
    /**
     * Get ItmModelTypes by an itmModelTypeId
     * 
     * @param iTMModelTypeId
     *            the iTMModelTypeId
     * @return ItmModelType return the ItmModelType according the iTMModelTypeId
     * @throws ServiceException
     *             if there is an error the method will throw ServiceException
     */
    public ItmModelType getItmModelTypeByItmModelTypeId(String iTMModelTypeId)
            throws ServiceException;

    /**
     * Get ItmModelType by an itmModelTypeName
     * 
     * @param iTMModelTypeName
     *            the iTMModelTypeName
     * @return ItmModelType return the ItmModelType according to the
     *         iTMModelTypeName
     * @throws ServiceException
     *             if there is an error the method will throw ServiceException
     */
    public ItmModelType getItmModelTypeByItmModelTypeName(String iTMModelTypeName)
            throws ServiceException;


    /**
     * Get all of the ItmModelTypes from Db.
     * 
     * @return List<ItmModelType> All ItmModelType's
     * @throws ServiceException
     *             if there is error in getDeviceTypes, the method will throw
     *             ServiceException.
     */
    public List<ItmModelType> getItmModelTypes() throws ServiceException;

    /**
     * Get all of the ItmModelTypes from Db.
     * 
     * @param swBuildId
     *            String
     * @return List<ItmModelType> All ItmModelType's
     * @throws ServiceException
     *             if there is error in getDeviceTypes, the method will throw
     *             ServiceException.
     */
    public List<ItmModelType> getItmModelTypesBySwBuildID(String swBuildId) throws ServiceException;
    
    /**
     * Get all of the ExtModelTypes from Db.
     * 
     * @param swBuildId
     *            String
     * @return List<ItmModelType> All ExtModelType's
     * @throws ServiceException
     *             if there is error in getDeviceTypes, the method will throw
     *             ServiceException.
     */
    public List<String> getExtModelTypesBySwBuildID(String swBuildId) throws ServiceException;
    
    /**
     * Get the extended model types by firmware version
     * 
     * @param fwVersion
     * @return list of extended model type, List<String>
     */
    public List<String> getExtModelTypesByFirmWareVersion(String fwVersion) throws ServiceException;

    /**
     * Returns an ItmModelType object based on the extended model type
     * 
     * @param extModelTypeId
     * @return
     * @throws ServiceException
     */
    public ItmModelType getItmModelTypeByExtModelTypeId(String extModelTypeId)
            throws ServiceException;
  
    public List<ParameterItem> getExtModelSupportParameterList(
            List<String> programParameterList,List<String> extModelTypeList, String fwVersion) throws ServiceException;

    /**
     * check if a extendmodel type and personal data combination is supported
     * 
     * @param itmModelTypeId String
     * 
     * @param extModelTypes <Set>String
     * 
     * @return ParameterItem
     * 
     * @throws ServiceException
     */
    public ParameterItem getExtModelSupportParameter(String programParameter,
            List<String> extModelTypeList,String fwVersion) throws ServiceException;
    
    /**
     * Get cross model copy groups information
     * 
     * @return CrossModelCopyGroups All cross model copy groups information
     * @throws ServiceException
     *             If error occurs in getCrossModelCopyGroupInformation, the
     *             method will throw ServiceException.
     */
    public CrossModelCopyGroups getCrossModelCopyGroupInformation() throws ServiceException;
    
    
    /**
     * @param extModelType
     * @param codeplugVersion
     * @return
     */
    public boolean isFlashPackSupport(String extModelType, String codeplugVersion);
    
    /*
     * get all the radio models which is supported to convert 
     * 
     * @param extModelType
     * @return List<ModelNumber>
     * 
     * @throws ServiceException
     */
    public List<ModelNumber> getCorrespondingModelNumber(List<String> extModelType) throws ServiceException;
    
    /*
     * get all the radio models which is supported to convert 
     * 
     * @param List<String> extModelTypes
     * @return List<ModelNumber>
     * 
     * @throws ServiceException
     */
    public List<ModelNumber> getSupportConvertRadioModel(List<String> extModelTypes) throws ServiceException;
    
    
    /*
     * get all the radio models which is supported to convert 
     * 
     * @param List<String> extModelTypes
     * @return List<ModelNumber>
     * 
     * @throws ServiceException
     */
    public List<ModelNumber> getSupportConvertRadioModelByHwId(List<String> hwIDs) throws ServiceException;
    
    /*
     * get all the radio models which is supported to convert 
     * 
     * @param String uniqueModelNumber
     * @return String ExtModelType
     * 
     * @throws ServiceException
     */
    public List<String> getExtModelTypesByUniqueModelNumbers(List<String> uniqueModelNumbers) throws ServiceException;
}
