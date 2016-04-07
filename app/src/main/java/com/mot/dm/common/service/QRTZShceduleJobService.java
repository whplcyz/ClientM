
/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2014 Motorola Inc.                           |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Qngv36

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy   <name>                <CR number>   <description>
10/July/14  Wei Huiping           CCMPD01911009 [iTM 7.0] Use Quartz to schedule on-line backup/other jobs
28/Aug/14  qngv36                CCMPD01923183 iTM7.0 Auto schedule online backup.
------------------------------------------------------------------------------*/
package com.mot.dm.common.service;

import java.util.List;

import com.mot.dm.common.model.QRTZSchedulerObject;
import com.mot.dm.common.util.exception.ServiceException;

public interface QRTZShceduleJobService extends Service
{
    public void addScheduleJob(QRTZSchedulerObject job) throws ServiceException;
    
    public void updatedScheduleJob(QRTZSchedulerObject oldSchedule, QRTZSchedulerObject newSchedule) throws ServiceException;

    public void removeScheduleJob(QRTZSchedulerObject job) throws ServiceException;

    public List<QRTZSchedulerObject> getScheduleJobsAll() throws ServiceException;

    public List<QRTZSchedulerObject> getSchedulesByJobType(int jobType) throws ServiceException;

    public void cancelAJob(int jobType) throws ServiceException;
    
    public void startAJobNow(int jobType) throws ServiceException;
    public void startAJobNow(int jobType, String[] parameters) throws ServiceException;
    public boolean isScheduleAlreadyExisted(QRTZSchedulerObject job) throws ServiceException;
	public String[] getJobRunningStatus(int nJobStatus) throws ServiceException;
	
	public boolean checkFolderAccessPermission(String destFolder);
	
	public Boolean isJobRunning(int jobType) throws ServiceException;
}
