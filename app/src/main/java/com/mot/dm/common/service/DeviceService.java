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

PRODUCT NAME:  ITM Client   
CODED     BY:  Jeff Lu

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
18/02/08   cnij001               CCMPD00968807 Add new template
11/03/08   cjh102                Added getDevicesBySwAndAgency
17/03/08   cjh102                Update after FTR
31/03/08   cjh102                CCMPD01012838 Added method for getting
                                 features for device
21/Apr/08  Michael Leismann                    Added getDevicesByAgencyAndName
                                               method
24-Aug-08  Hai Dong              CCMPD01086627 Get device by ISSI   
09/02/09   chad001               CCMPD01159324 Search radio                                             
4/Mar/09   cjh102                CCMPD01173870 Removed updateFeatureState()
14/12/09   hgkj73                CCMPD01288783 Added for Agency partitioning
20/Jan/10  hgkj73                CCMPD01304268 copyright updated   
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1 
12/Apr/10  hgkj73                CCMPD01325308 Added annotations
06/Apr/10  hgkj73                CCMPD01344405 optimized the annotations
23/Sep/10  cjh102                CCMPD01395323 Added methods - moved from Sell-
                                               ableLicenseService
7/Jan/11   cjh102                CCMPD01459992 Improved template handling                                               
11/Jan/11  cjh102                CCMPD01462022 Clean-up of methods implemented
                                               in LicenseManager
01/Aug/11  ckfm01                CCMPD01538917 Add method for getting all Device UUIDs  
30/Jan/13  qngv36                CCMPD01730570 CP authentication support in iTM--Phase: Beta
11/Apr/14  a21944                CCMPD01881928 iTM7.0  Folder Refresh Performance Enhance
12/Jun/14  a21944                CCMPD01902698 C3:23824104 Long delay for Feature License list for 20000 subscribers 
21/Jun/14  a21944                CCMPD01905380 iTM7.0 Add progress bar on radio operation in right click menu
05/Sep/14  cmd                    CCMPD01926030 C3:Add the updateDeviceGUIProps method
                                                                                          
02/Sep/14   bfq463              CCMPD01912723 server manager cleanup panel
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Agency;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.DeviceAdditionalAttributes;
import com.mot.dm.common.model.DeviceAttributeDefinition;
import com.mot.dm.common.model.DeviceViewItem;
import com.mot.dm.common.model.SellableFeaturesOnDevice;
import com.mot.dm.common.util.exception.ServiceException;

/**
 * @Version 1.00
 * @author Jeff Lu
 */
public interface DeviceService extends Service
{
	/*
	 * Search radio by the key. If deviceUUID is empty string, find the first
	 * radio match to the search field. If it is not empty string, find the
	 * first radio that has UUID larger than deviceUUID. @param key String. The
	 * radio Issi, name, SNR, or TEI
	 * 
	 * @param deviceUUID String. The reference radio's UUID
	 * 
	 * @param field int. The search field
	 * 
	 * @return List<Device>
	 * 
	 * @throws ServiceException
	 */
	public List<Device> searchDevice(String key, String deviceUUID, int field)
			throws ServiceException;

	/**
	 * Add a new device into database. if the device is a radio then we need
	 * create radio permission if it is template then we need one of Create
	 * template from a radio or template in iTM. Create template from a file.
	 * 
	 * @param device
	 * @throws ServiceException
	 */
	public Device addDevice(Device device) throws ServiceException;

	/**
	 * To get all of assigned devices by agency, if recursive is true also get
	 * current' agency children assigned devices. if the recursive flag is false
	 * then it is OK to preAuthorize based on agency view permission, if not we
	 * need to make sure the device that goes must be of view permission.
	 * 
	 * @param agency
	 * @param recursive
	 * @return List<Device>
	 * @throws ServiceException
	 */
	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	@PostFilter("#recursive == false or isSuperUser() or hasPermission(filterObject, 'VIEW')")
	public List<Device> listDevices(Agency agency, boolean recursive)
			throws ServiceException;

	/**
	 * To get all of assigned devices by agency, where device is either radio or
	 * template according to the templateRadioFlag setting.
	 * 
	 * @param agency
	 * @param templateRadioFlag
	 * @return List<Device>
	 * @throws ServiceException
	 */
	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public List<Device> listDevices(Agency agency, int templateRadioFlag)
			throws ServiceException;

	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public List<Device> listDevicesForLicense(Agency agency)
			throws ServiceException;

	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public List<DeviceViewItem> listDeviceViewItems(Agency agency)
			throws ServiceException;

	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public List<Device> listDeviceWithPendingLicenseJob()
			throws ServiceException;

	/**
	 * Update current device's attributes.
	 * 
	 * @param device
	 * @throws ServiceException
	 */
	// TODO: Need to see whats applicable for this case.
	// Seems client uses it for several purposes.
	public void updateDevice(Device device) throws ServiceException;

	/**
	 * Update current device's attributes for OpenDevice dialog.
	 * 
	 * @param only update some attributes of device
	 * @throws ServiceException
	 */
	public void updateDeviceGUIProps(Device device) throws ServiceException;



	/**
	 * Remove a device from Db.
	 * 
	 * @param device
	 * @throws ServiceException
	 */
	@PreAuthorize("(#device.ARadio == true and hasPermission(#device, 'DELETE_RADIO')) or "
			+ "(#device.ARadio == false and hasPermission(#device, 'DELETE_TEMPLATE'))")
	public void removeDevice(Device device) throws ServiceException;

	/**
	 * Remove a list of devices from Db.
	 * 
	 * @param List
	 *            <Device> deviceList
	 * @throws ServiceException
	 */
	public void removeDeviceList(List<Device> deviceList)
			throws ServiceException;

	/**
	 * Moves a device.
	 * 
	 * @param newAgency
	 * @param device
	 * @throws ServiceException
	 */
	@PreAuthorize("hasPermission(#newAgency, 'MOVE_RADIO') and hasPermission(#device, 'MOVE_RADIO')")
	public Device moveDevice(Agency newAgency, Device device)
			throws ServiceException;

	/**
	 * Get a device by its identifier. No Authrization as it is being used by
	 * proxy without login so it is OK for any Authenticated session to access
	 * the same. TODO: However what abt client ? client can access any device if
	 * it has device ID.
	 * 
	 * @param deviceId
	 * @return Device
	 * @throws ServiceException
	 */
	public Device getDeviceById(String deviceId) throws ServiceException;

	/**
	 * Get a device by its tei.
	 * 
	 * @param idList
	 * @return Device
	 * @throws ServiceException
	 */
	@PostFilter("isSuperUser() or hasPermission(filterObject, 'VIEW')")
	public List<Device> getDevicesByIds(List<String> idList)
			throws ServiceException;

	@PreAuthorize("hasPermission(#device.agency, 'VIEW')"
			+ "and hasPermission(#device.agency, 'EXPORT_POLICY_DATA')")
	/**
	 * Get a device by its identifier.
	 * Only to be used by the PolicyExecution Data Export tool
	 * @param deviceId
	 * @return Device
	 * @throws ServiceException
	 */
	public Device getDeviceByIdForPolicyExecutionExport(Device device)
			throws ServiceException;

	/**
	 * returns whether device with specified logicalId exist' It is OK to be
	 * used by any Authenticated Session.
	 * 
	 * @param deviceLogicalId
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean isDeviceExistWithLogicalId(String deviceLogicalId)
			throws ServiceException;

	/**
	 * Get a device by its identifier.
	 * 
	 * @param deviceUuid
	 * @return Device
	 * @throws ServiceException
	 */
	@PostAuthorize("isSuperUser() or hasPermission(#returnObject, 'VIEW')")
	public Device getDeviceByUuid(String deviceUuid) throws ServiceException;

	/**
	 * Get list of all device UUIDs
	 * 
	 * @param NA
	 * @return List<String>
	 * @throws ServiceException
	 */
	@PostAuthorize("isSuperUser()")
	public List<String> getAllDeviceUuids() throws ServiceException;

	/**
	 * Search devices by the filter device's attributes.
	 * 
	 * @param filterDevice
	 * @return List<Device>
	 * @throws ServiceException
	 */
	@PostFilter("isSuperUser() or hasPermission(filterObject, 'VIEW')")
	public List<Device> searchDevice(Device filterDevice)
			throws ServiceException;


	/**
	 * Return all devices (templates and radios) which fit the provided codeplug
	 * version and agency
	 * 
	 * @param agency
	 * @param codeplugVersion
	 * @param onlyReturnTemplates
	 *            If true then only the matching templates are return, if false
	 *            then both radios and templates are returned
	 * @return List of device objects
	 * @throws ServiceException
	 */
	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public List<DeviceViewItem> getDevicesByCodeplugVersionAndAgency(
			Agency agency, String codeplugVersion) throws ServiceException;

	/**
	 * Returns a list of SellableFeautesOnDevice objects
	 * 
	 * @param deviceUuid
	 * @return
	 * @throws ServiceException
	 */
	// TODO: What permissions should the caller have ? may be
	// read permissions on the device. but we have a deviceUuid. In the context
	// where this is used the user already have access to the device, hence we
	// can leave this for 3.0
	public List<SellableFeaturesOnDevice> getFeatureStateForDevice(
			String deviceUuid) throws ServiceException;

	public Map<String, List<String>> getEnabledSellableFeatureOnDevices(
			List<String> deviceUuids) throws ServiceException;

	/**
	 * Get a device by an agency and a device name
	 * 
	 * @param agency
	 * @param name
	 * @return the device
	 * @throws ServiceException
	 */
	@PreAuthorize("isSuperUser() or hasPermission(#agency, 'VIEW')")
	public Device getDevicesByAgencyAndName(Agency agency, String name)
			throws ServiceException;

	/**
	 * get whether a device with given deviceId exists in iTM system It is OK to
	 * be used by any Authenticated Session.
	 * 
	 * @param deviceId
	 * @return
	 * @throws ServiceException
	 */
	public boolean isDeviceExistWithDeviceId(String deviceId)
			throws ServiceException;

	/**
	 * Get the password history of the device
	 * 
	 * @param deviceUid
	 * @return List<String>
	 * @throws ServiceException
	 */
	public List<String> getDeviceCandidatePwds(String deviceUuId)
			throws ServiceException;

	/**
	 * Get the current password of the device
	 * 
	 * @param deviceUid
	 * @return String
	 * @throws ServiceException
	 */
	public String getDevicePasswordByDeviceUuId(String deviceUuId)
			throws ServiceException;

	/**
	 * Get the current password of the device
	 * 
	 * @param deviceUid
	 * @return String
	 * @throws ServiceException
	 */
	public String getDevicePassowrdUsageByDeviceUuId(String deviceId)
			throws ServiceException;

	/**
	 * Set the radio's latest password and flag
	 * 
	 * @param deviceUid
	 * @param password
	 * @param passwordUsage
	 * @return void
	 * @throws ServiceException
	 */
	public void updateDevicePassowrdByUuId(String deviceUuId, String jobUuid,
			String password, String passwordUsage) throws ServiceException;
    
    /**
     * same with other method named getAllDeviceUuids, this method just for server manager and no need authorization 
     * @return
     * @throws ServiceException
     */
    public List<String> getAllDeviceUuids4Cleanup() throws ServiceException;
    
    /**
     * same with other method named getDeviceByUuid, this method just for server manager and no need authorization
     * @param deviceUuid
     * @return
     * @throws ServiceException
     */
    public Device getDeviceByUuid4Cleanup(String deviceUuid) throws ServiceException;
    
    public List<DeviceAttributeDefinition> getAllDeviceAttributeDefinition()  throws ServiceException;
    
    public DeviceAttributeDefinition addOrUpdateDeviceAttributeDefinition(DeviceAttributeDefinition deviceAttributeDefinition) throws ServiceException;
    
    public void updateAllDeviceAttributeDefinitions(List<DeviceAttributeDefinition> deviceAttributeDefinitionList)  throws ServiceException;
    
    public void removeDeviceAttributeDefinition(DeviceAttributeDefinition deviceAttributeDefinition) throws ServiceException;
    
    public List<DeviceAdditionalAttributes> getDeviceAdditionalAttributesListByDeviceUuid(String deviceUuid)  throws ServiceException;
}
