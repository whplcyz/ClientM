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
*/

package com.mot.dm.common.util;


public class ProgressReporter {

	
	private double currentPercentage = 0;
	
	// params for calculate folder copy as sub percentage
	private double folderCopyPercentage = 0;
	private long folderTotalSize = 0;
	private long folderCopiedSize = 0;
	private ScheduleExcutionRecord pJob = null;
	
	public ProgressReporter(ScheduleExcutionRecord job)
	{
		pJob = job;
	}
	
	public void ReportProgress(String info)
	{
		pJob.AddOperationInfo(info);
	}
	
	public void ReportProgress(double percentage)
	{
		currentPercentage = percentage;
		pJob.setJobProcess((int)(percentage*100));
	}
	
	public void ReportProgress(String info, double percentage)
	{
		currentPercentage = percentage;
		pJob.AddOperationInfo(info, (int)(percentage*100));
	}
	
	public void UpdateLastProcessInfo(String info, double percentage)
    {
        currentPercentage = percentage;
        pJob.UpdateLastOperationInfo(info, (int)(percentage*100));
    }
	
	public void ReportError(String info)
	{
	    pJob.setStatus(CommonConstants.FAILED);
	    ReportProgress(info);
	}
	
	
	/**
     * @param folderSize the total size of the to be copied folder
     * @param subPercentage (folder copy time)/(overall time)
     */
	public void SetFolderCopy(long folderSize, double subPercentage)
	{
		folderTotalSize = folderSize;
		folderCopyPercentage = subPercentage;
	}
	
	public void AddCopiedSize(long size)
	{
		folderCopiedSize += size;
		
		if(folderCopiedSize <= folderTotalSize)
		{
			currentPercentage = currentPercentage + ((double)size/folderTotalSize) * folderCopyPercentage;
			ReportProgress(currentPercentage);
		}
	}
}
