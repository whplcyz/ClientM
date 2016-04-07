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
CODED     BY:  Kevin Xia 17-Apr-2007

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
14/Sep/07 Jason Zhang          Add methods checkAddJobInfosForOneX()
 *                              addJobInfoForOneX
13/Mar/08 Tommy Thomadsen      CCMPD01004838 Added execution time
04/Apr/08 Niels Jorgensen      CCMPD00993893 Offline improvements
21/Apr/08 Sigurdur Jonsson     CCMPD01025003 Defect fix, reset job owner
22/Apr/08  Michael Leismann      CCMPD01025893 Removed checkAddJobInfosForOneX
                                               and addJobInfoForOneX interface
                                               methods
23/Apr/08 Niels Jorgensen      CCMPD01025011 Non pending jobs shall not be fetched/
                                            (prepare offline jobs shall remove
                                             finished jobs)
29/Apr/08  Ricardo Camatta  CCMPD01029156 Method to set proxy ownership added.
05/Sep/08  Ricardo Camatta  CCMPD01087353 Query running job enhancement  
22/Sep/08  Jens Hansen      CCMPD01095091 Added comment. 
24/Oct/08  Ricardo Camatta  CCMPD01095091 setJobOwner removed due to refactor-
                                          ings in the proxy lib.
11/Dec/08  Michael Leismann      CCMPD01138296 Added parameters to
                                               listProxyJobInfo and 
                                               updateJobStatus methods as part
                                               of moving the operations log to
                                               the server
                                               Removed method that was not used
19/Feb/09  Michael Leismann      CCMPD01167008 Moved operations log
                                               functionality from
                                               updateJobStatus to logJobStatus
15/Apr/09 Kalyan Pushpala        CCMPD01193804 Changes for iTM 2.0 - Recovery feature
18/Nov/09  Sigurdur Jonsson      CCMPD01287422 iTM 3.0 Feature, job polling.
20/Nov/09  Kim mortensen         CCMPD01287398 Add Notification message to 
                                               JobInfo
02/Dec/09  Michael Leismann      CCMPD01287031 Jobs on memory stick feature:
                                               Removed job owner
14/12/09   hgkj73                CCMPD01288783 Added for Agency partitioning
19/Jan/10  cjh102                CCMPD01304268 Update for agency partitioning   
11/Feb/10  hgkj73                CCMPD01310676 Upgrade Spring3.0,SpringSec3.0.1 
12/Feb/10  Michael Leismann      CCMPD01312990 Added transaction handling in
                                               proxy lib
09/Mar/10  Sigurdur Jonsson      CCMPD01320819 Rescheduling Expired and Scheduled
                                               jobs.
12/Apr/10  hgkj73                CCMPD01325308 Added annotations
27/Apr/10  hgkj73                CCMPD01340657 changed 30TODO to TODO as they
                                               cant be fixed in 3.0
02/07/10   ckfm01                CCMPD01366268 Moving Operations logging to 
                                               server side.
10/Mar/11  Michael Leismann      CCMPD01483126 iTM 5.0: Moved proxy specific
                                               objects to proxy lib package
12/July/11 hgkj73                CCMPD01528572 add interface for pending jobs under 
                                               a given agency path
05/May/14  a21944          CCMPD01889582 iTM7.0  Folder Refresh Performance Enhance                                                
21/Jun/14  a21944                CCMPD01905380 iTM7.0 Add progress bar on radio operation in right click menu
04/08/14   tqfn38                CCMPD01913521 Flexera license development  
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mot.dm.common.model.Codeplug;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.JobInfo;
import com.mot.dm.common.model.LightDeviceInfo;
import com.mot.dm.common.util.CommonConstants.QueryKeyType;
import com.mot.dm.common.util.CommonConstants.QueryPollType;
import com.mot.dm.common.util.CommonConstants.QueryTerminalType;
import com.mot.dm.common.util.exception.ServiceException;

public interface JobInfoService extends Service
{
    /**
     * Get a jobInfo by its identifier.
     * 
     * @param jobUuid
     * @return JobInfo
     * @throws ServiceException
     */
    // TODO: in future ????
    public JobInfo getJobInfo(String jobUuid) throws ServiceException;

    /**
     * get the JobInfo information which are belong the current device.
     * 
     * @param device
     * @return List<JobInfo>
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public List<JobInfo> getJobInfoByDevice(Device device) throws ServiceException;


    /**
     * add a new JobInfo into DB.
     * 
     * @param jobInfo
     * @return JobInfo
     * @throws ServiceException
     */
    public JobInfo addJobInfo(JobInfo jobInfo) throws ServiceException;

    /**
     * reschedule an expired or scheduled job.
     * 
     * @param List
     *            <JobInfo>
     * @return List<JobInfo>
     */
    public List<JobInfo> rescheduleJobs(List<JobInfo> jobInfoList) throws ServiceException;

    /**
     * To create a notification message
     * 
     * @param device
     *            to send the notification to.
     * @param notificationMessage
     *            containing the actual message.
     * @throws ServiceException
     */
    public void createNotification(List<Device> deviceList, String notificationMessage)
            throws ServiceException;

    /**
     * remove current jobInfo by its identifier. Note: its only used from tests
     * and hence markled as super user
     * 
     * @param jobUuid
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser()")
    public void removeJobInfo(String jobUuid) throws ServiceException;

    /**
     * Get a JobInfo list based on the device identifiers in the deviceIdList
     * and the job states specified in the queryJobStatus array. Device
     * identifier can be either DeviceID or Device UID which is indicated by the
     * parameter (1) If returnDevicesWithoutJobs is true, but the device
     * identifier does not exist in the database, a jobInfo element is returned
     * with the flag isKnown set to false. (2) If returnDevicesWithoutJobs is
     * true and the device exists in the database, but no jobs for that device
     * exist then a jobInfo element is returned with the flag isKnown is set to
     * true. (3) If returnDevicesWithoutJobs is false the device will not be
     * added to the list if there are no jobs for that device.
     * 
     * @param deviceIdList
     * @param queryJobStates
     * @param queryType
     * @param returnDevicesWithoutJobs
     * @param userName
     * @param proxyName
     * @param pollQuery
     * @return List<JobInfo>
     * @throws ServiceException
     */
    // TODO: as of now may be no need for security as it is
    // CHECKSTYLE IGNORE ParameterNumber FOR NEXT 50 LINES
    // only being used from proxy. but in future need to have a way to check
    // if proxy user can query job on the device with passed deviceId
    public List<JobInfo> listJobInfo(String deviceId, int[] jobStates,
            QueryKeyType queryType, boolean returnDevicesWithoutJobs, String userName,
            String proxyName, QueryPollType pollQuery, QueryTerminalType terminalType) throws ServiceException;

    /**
     * @param deviceIdList
     * @return List<JobInfo>
     * @throws ServiceException
     */
    // TODO: as of now may be no need for security as it is
    // only being used from proxy. but in future need to have a way to check
    // if proxy user can query job on the device with passed deviceId
    public List<JobInfo> listPendingJobInfoByDeviceId(String deviceId)
            throws ServiceException;

    /**
     * According to a device to search it's recentest created job.
     * 
     * @param device
     * @return JobInfo
     * @throws ServiceException
     */
    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public JobInfo getRecentestJobInfoByDevice(Device device) throws ServiceException;

    @PreAuthorize("isSuperUser() or hasPermission(#device, 'VIEW')")
    public JobInfo getRecentestJobInfoByDeviceUuid(String deviceUuid) throws ServiceException;
    
    
    /**
     * Updates the job status
     * 
     * @param jobInfo
     * @param codeplug
     * @param uploadCodeplug
     * @param username
     * @param proxyname
     * @throws ServiceException
     */
    // TODO: as of now may be no need for security as it is
    // only being used from proxy. but in furute need to have a way to check
    // if proxy user can update this jobinfo for a device
    public void updateJobStatus(JobInfo jobInfo, Codeplug codeplug, boolean uploadCodeplug,
            String p_userName, String p_proxyName) throws ServiceException;

    // TODO: this is being used by both client and proxy hence cannot
    // put under security as of now
    public void updateJobStatus(String jobUuid, int jobStatus) throws ServiceException;

    /**
     * Set the execution time to "now" on the specified job.
     * 
     * @param jobUuid
     * @throws ServiceException
     */
    // TODO: What and how to apply security. Currently only used when
    // canceling + terminating jobs. Why is this a separate service call at
    // all?
    public void setExecutionTime(String jobUuid) throws ServiceException;

    public List<JobInfo> listPendingJobInfoInAgencyWithPath(String agencyName)
            throws ServiceException;
        
    public JobInfo fetchProbableJobInfo(List<JobInfo> jobInfoList) throws ServiceException;

    //  for Proxy download offline job, itm7.0 performance optimize
    public List<LightDeviceInfo> listLightDeviceInfoByAgency(String agencyName) throws ServiceException;
    
    public List<LightDeviceInfo> getPendingLightDeviceInfoList(List<String> deviceUuidList) throws ServiceException;
    
    public List<JobInfo> getJobInfoByStatus(int jobStatus);
}
