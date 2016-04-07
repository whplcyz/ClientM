/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2011-2013 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Michael Leismann    September 2010

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
06/Oct/10  Michael Leismann      CCMPD01387289 iTM 4.0: Network Logging
20/Dec/10  Sigurdur Jonsson      CCMPD01455045 Policy feature, network logging
18/Jan/11  Sigurdur Jonsson      CCMPD01460388 Adding cleanup for policy
                                               executions
31/Jan/11  Kalyan Pushpala       CCMPD01468446 Cannot see jobs if no export 
                                               policy permission
10/Mar/11  Michael Leismann      CCMPD01483126 iTM 5.0: Moved proxy specific
                                               objects to proxy lib package
12/July/11 Kalyan Pushpala       CCMPD01528572 added get Policies under given
                                               agencyPath.
 4/Dec/12  tqfn38 		         CCMPD01721424 iTM6.1 Picture Pull & Push  
21/Jun/13  tqfn38                CCMPD01783643 Change Request 2659 - Picture Push and Pull policies have to be licensed in iTM 
07/Aug/13  wch378               CCMPD01800204  refactor export policy logic, and -type                                              
02/Sep/14   bfq463              CCMPD01912723 server manager cleanup panel
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.Policy;
import com.mot.dm.common.model.PolicyDeviceMap;
import com.mot.dm.common.model.PolicyExecution;
import com.mot.dm.common.model.PolicyExecutionSequence;
import com.mot.dm.common.model.PolicyType;
import com.mot.dm.common.model.SuppPolTypeForItmModelType;
import com.mot.dm.common.util.exception.ServiceException;

public interface PolicyService extends Service
{
    /**
     * Create a Policy
     * 
     * @param policy
     * @return Policy
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_POLICIES')")
    public Policy addPolicy(Policy policy) throws ServiceException;

    /**
     * Modify a Policy
     * 
     * @param policy
     * @return Policy
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_POLICIES')")
    public Policy modifyPolicy(Policy policy) throws ServiceException;

    /**
     * Remove a Policy
     * 
     * @param policy
     * @return void
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(system, 'MANAGE_POLICIES')")
    public void removePolicy(Policy policy) throws ServiceException;

    /**
     * List all Policies
     * 
     * @return List<Policy>
     * @throws ServiceException
     */
    public List<Policy> getPoliciesAll() throws ServiceException;
    
    /**
     * List all Policies with special iTM Model Type.
     * 
     * @return List<Policy>
     * @throws ServiceException
     */
    public List<Policy> getPoliciesByiTMModelType(String iTMModelTypeID) throws ServiceException;

    /**
     * Assign a policy to a device
     * 
     * @param policyDeviceMap
     * @return boolean, true if assigned, false if not assigned
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#policyDeviceMap.device, 'ASSIGN_AND_UNASSIGN_POLICIES')")
    public boolean assignPolicyToDevice(PolicyDeviceMap policyDeviceMap) throws ServiceException;

    /**
     * Modify an assigned policy
     * 
     * @param policyDeviceMap
     * @return PolicyDeviceMap
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#policyDeviceMap.device, 'ASSIGN_AND_UNASSIGN_POLICIES')")
    public PolicyDeviceMap modifyAssignedPolicy(PolicyDeviceMap policyDeviceMap)
            throws ServiceException;

    /**
     * Unassign a policy to a device
     * 
     * @param policyDeviceMap
     * @return boolean, true if unassigned, false if not unassigned
     * @throws ServiceException
     */
    @PreAuthorize("hasPermission(#policyDeviceMap.device, 'ASSIGN_AND_UNASSIGN_POLICIES')")
    public boolean unAssignPolicyToDevice(PolicyDeviceMap policyDeviceMap) throws ServiceException;

    /**
     * Get Policy by name
     * 
     * @return Policy
     * @throws ServiceException
     */
    public Policy getPolicyByName(String policyName) throws ServiceException;
    
    
    /**
     * Get Policy by uuid
     * 
     * @return Policy
     * @throws ServiceException
     */
    public Policy getPolicyByUuid(String policyUuid) throws ServiceException;

    /**
     * Insert a list of PolicyExecutions including PolicyExecutionParams into
     * the database
     * 
     * @param policyExecutionList
     * @throws ServiceException
     */
    public void insertExecutedPolicy(List<PolicyExecution> policyExecutionList)
            throws ServiceException;

    /**
     * Remove a list of PolicyExecution and associated PolicyExecutionParam from
     * the database
     * 
     * @param policyExecutionList
     * @throws ServiceException
     */
    public void removeExecutedPolicies(List<PolicyExecution> policyExecutionList)
            throws ServiceException;

    /**
     * Get a assigned polices list based device identifiers
     * 
     * @param deviceIdList
     *            , list of TEIs
     * @return List<PolicyDeviceMap>
     * @throws ServiceException
     */
    public List<PolicyDeviceMap> getAssignedPoliciesByDeviceIds(List<String> deviceIdList)
            throws ServiceException;

    /**
     * Get all supported policy types for all itm model types.
     * 
     * @return List<SuppPolTypeForItmModelType>
     */
    public List<SuppPolTypeForItmModelType> getSuppPolTypeAll();
    
    /**
     * Get all supported policy types for a specific itm model types.
     * @param itmModelTypeId
     * 
     * @return List<SuppPolTypeForItmModelType>
     */
    public List<SuppPolTypeForItmModelType> getSuppPolTypeByItmModelTypeId(String itmModelTypeId);

    /**
     * Get all supported iTM model types for a specific policy types.
     * @param itmModelTypeId
     * 
     * @return List<SuppPolTypeForItmModelType>
     */
    public List<SuppPolTypeForItmModelType> getItmModelTypeBySuppPolTypeUuid(String suppPolTypeUuid);
    
    /**
     * Get all the policy types
     * 
     * @return List<PolicyType>
     */
    public List<PolicyType> getAllPolicyTypes();

    /**
     * Get all executed policies for a specific device and within a given time
     * interval
     * 
     * @param device
     * @param tsFrom
     * @param tsTo
     * @return List<PolicyExecution>
     */
    @PreAuthorize("isSuperUser() or hasPermission(#device.agency, 'VIEW')")
    public List<PolicyExecution> getExecutedPoliciesByDeviceId(Device device, Timestamp tsFrom,
            Timestamp tsTo);

    /**
     * Get all executed policies for a specific policy and within a given time
     * interval
     * 
     * @param policy
     *            , if policy is null return for all policies
     * @param tsFrom
     * @param tsTo
     * @return List<PolicyExecution>
     */
    public List<PolicyExecution> getExecutedPoliciesByPolicyName(Policy policy, Timestamp tsFrom,
            Timestamp tsTo,int intFromPolicyId,int intToPolicyId);
    
    

    /**
     * Get all executed policies 
     * 
     * @return List<PolicyExecution>
     */
    public List<PolicyExecution> getAllExecutedPolicies();
    
    /**
     * Get list of devices which have a given policy assigned to it
     * 
     * @param policyName
     *            , null means all policies
     * @return List of device objects
     * @throws ServiceException
     */
    // TODO: change this to return true/false instead of returning the list,
    // because all we are actually interested in, is whether the policy has been
    // assigned to any radios
    public List<Device> getDevicesByAssignedPolicyName(String policyName) throws ServiceException;

    /**
     * Get a assigned polices list under given agency
     * 
     * @param agencyPath
     * @return List<PolicyDeviceMap>
     * @throws ServiceException
     */
    public List<PolicyDeviceMap> getAssignedPoliciesInAgencyWithPath(String agencyPath)
            throws ServiceException;

    public List<PolicyExecutionSequence> getPolicyExecutionSequenceByUUidList(List<String> uuidList)
    throws ServiceException;
    
    /**
     * same with other method named getExecutedPoliciesByDeviceId, this method just for server manager and no need authorization
     * @param device
     * @param tsFrom
     * @param tsTo
     * @return
     */
    public List<PolicyExecution> getExecutedPoliciesByDeviceId4Cleanup(Device device, Timestamp tsFrom,
            Timestamp tsTo);
}
