/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                     Copyright 2011 Motorola Solutions Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  cjh102    Jul 2011

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
8/Jul/11   cjh102                CCMPD01525915 Initial version                                               
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.log.impl.LoggerController;

public class ItmFilenameFilter implements FilenameFilter
{
    private String filenameExtension = null;

    private static Logger fgLogger = LoggerController.createLogger(ItmFilenameFilter.class);

    public ItmFilenameFilter(String filenameExtension)
    {
        this.filenameExtension = filenameExtension;
    }

    public boolean accept(File dir, String filename)
    {
        if (filename.endsWith(filenameExtension))
        {
            fgLogger.debug("Filename accepted: " + filename);
            return true;
        }
        else
        {
            fgLogger.debug("Filename not accepted: " + filename);
            return false;
        }
    }

}
