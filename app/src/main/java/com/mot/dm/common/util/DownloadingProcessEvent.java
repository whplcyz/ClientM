package com.mot.dm.common.util;

public interface DownloadingProcessEvent {
	public void DownloadingEvent(String strFileName, long nFileSize, int nStatusCode);
}
