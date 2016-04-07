package com.mot.dm.client.util;

import com.mot.dm.client.delegation.ServiceDelegation;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.JobInfo;
import com.mot.dm.common.service.JobInfoService;
import com.mot.dm.common.util.exception.ServiceException;

public final class ReprogramUtil
{
     public static JobInfo getLastJobinfoOfSelectDevice(Device selectedDevice) throws ServiceException
     {
         JobInfoService jobInfoService = (JobInfoService) ServiceDelegation.getInstance()
         .getService("jobInfoService");
         JobInfo jobInfo = jobInfoService.getRecentestJobInfoByDevice(selectedDevice);
         return jobInfo;
     }
}
