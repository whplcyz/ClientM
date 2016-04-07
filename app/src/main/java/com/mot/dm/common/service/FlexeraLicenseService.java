/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  ZhaoWei-wch378

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy    <name>               <CR number>     <description>
28/3/14    wch378               CCMPD01876846 develop flexera license.  
28/3/14    wch378               CCMPD01889533 develop flexera license.         
16/6/14    tqfn38        CCMPD01904094 Flexera License Feature Develop.     
03/07/13   tqfn38                CCMPD01907396 Flexera license development 
04/08/14   tqfn38                CCMPD01913521 Flexera license development   
14/Oct/14  tqfn38                CCMPD01938194 The job status is still in "Pending License"  when import XML for radio on online mode  
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mot.dm.common.model.FlexeraProductItem;
import com.mot.dm.common.model.FlexeraLicenseSource;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.DataStructureForManageLicense;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.FlexeraFeature;
import com.mot.dm.common.model.FlexeraLicenseBinary;
import com.mot.dm.common.model.ItmModelType;
import com.mot.dm.common.model.LicExpReminderItem;
import com.mot.dm.common.model.licenseModel.LicenseData;
import com.mot.dm.common.model.licenseModel.OnOffTypeLicense;
import com.mot.dm.common.util.exception.LicenseException;
import com.mot.dm.common.util.exception.ServiceException;

public interface FlexeraLicenseService extends Service
{
    /**
     * Add a LicenseSource.
     * license source may be an entitlement or "binary" file.
     * 
     * @param FlexeraLicenseSource
     * @return LicenseSource
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public FlexeraLicenseSource addFlexeraLicense(String userName, String sourceName,
            int flexeeraSourceType, Map<String, byte[]> deviceBinaryMap,
            List<FlexeraProductItem> flexeraProductItemList,String checkSum) throws ServiceException;
    
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public FlexeraLicenseSource addFlexeraLicenseBinarayOnly(String userName,
            Map<String, byte[]> deviceBinaryMap) throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public void removeLicenseSource(FlexeraLicenseSource licenseSource)
            throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public void resetLicenseData() throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public List<FlexeraLicenseSource> getAllFlexeraLicenseSource() throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public List<FlexeraLicenseSource> getLicenseSourceByLicenseSourceType(int licenseSourceType)
            throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public DataStructureForManageLicense getDataForManageLicense() throws ServiceException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public LicenseData getLicenseData();
        
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public List<Boolean> isBinarysDuplicate(List<FlexeraLicenseBinary> flexeraLicenseBinarylist);
    
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public boolean hasManageLicensePermission();
    
    public boolean isSystemLicenceEnabled(String systemNoCapacityFeatureId);
    
    public boolean isSystemCapacityLicenceEnabled(String systemCapacityFeatureId);

    /**
    * Returns a hash map of devices and the features to each device
    * 
    * @param devices
    * @return
    * @throws ServiceException
    */
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public HashMap<String, List<FlexeraFeature>> getRadioFeatureLicensesMapForRadios(
            List<Device> devices) throws LicenseException;

    /**
    * Returns a list of the radio features applicable for a given radio type -
    * ie. regardless of the firmware version. To be used when ordering TEI
    * locked licenses in which case the licenses are ordered based on the radio
    * type and not restricted by a firmware release. The following query will
    * return all the features available for a Barney: select * from
    * sellablefeatureforsw where itm_model_type_id = 3;
    * 
    * @param modelTypes
    * @return
    * @throws ServiceException
    */
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public List<FlexeraFeature> getFeaturesByModeltype(List<String> modelTypes)
            throws LicenseException;
    
    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public Map<String, List<String>> getRadioModelTypes2SellableFeatureIDsByModelTypes(List<String> modelTypes) 
            throws LicenseException;

    @PreAuthorize("hasPermission(system, 'MANAGE_LICENSES')")
    public List<Device> getRadiosByTypeAndLicense(ItmModelType modelType, boolean mustHaveLicense,
            String featureID) throws LicenseException;

    /**
    * Returns a list of all the feature licenses bought for a specific radio.
    * In case the system is running the system-wide feature licenses, then this
    * will not be reflected in the returned data.
    * 
    * @param device
    * @param extModelTypes
    * @return
    * @throws ServiceException
    */
    public List<FlexeraFeature> getRadioFeatureLicensesForRadio(Device device, String targetExtMT)
            throws LicenseException;
   
    public List<FlexeraFeature> getAllFlexeraFeatures() throws ServiceException;

    public List<ItmModelType> getITMModelTypes() throws ServiceException;
    
    public String getHostID();
    
    public List<LicExpReminderItem> getLicExpReminderItems();
    
    public String getLicenseInvalidInfo();
    
    public Map<String,List<String>> getLicensedFeaturesForDevices(List<String> deviceIDs) throws LicenseException;
    
    public List<String> getLicensedFeaturesForDevice(String deviceID, boolean fromLicenseBinary) throws LicenseException;
    
    public Map<String, Set<String>> getSellableFeatureInfoBySWandModelTypes(String software, List<String> modelTypes) throws LicenseException;
    
    public Map<String, String> getAvaiableSellableFeatureLicenseList();
    
    public Map<String, OnOffTypeLicense> getSiteWideSellableFeatureListList();

    public List<FlexeraProductItem> getAvaiableSellingFeatureItems();
    
    public void updateEntitlementDetails(List<FlexeraProductItem> entitlementDetails);

    /**
     * Ask if its possible/allowed to assign a Notification ID to a device.
     * 
     * @return
     */
    public boolean canAddNotificationIDtoDevice();
    
    public Map<String, List<String>> getAllDevicesPendingLicenseFeatures();
    
    public boolean checkIfDevicesPendingLicenseFeatures();
    
    public void updatePendingLicenseJobsWhenChangeSiteWideFlagOnFolder();
    
    public boolean canUseConfigurableAttributes();
    
    public boolean canNewConfigurableAttributes();
    
    public boolean allConfigurableAttributesExpire();
}
