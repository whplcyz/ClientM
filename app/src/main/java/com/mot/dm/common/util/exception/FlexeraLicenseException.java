package com.mot.dm.common.util.exception;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.log.impl.LoggerController;

public class FlexeraLicenseException extends LicenseException
{

    /**
     * 
     */
    private static final long serialVersionUID = -1498731648619049178L;
    private static Logger fgLogger = LoggerController.createLogger(FlexeraLicenseException.class);

    public FlexeraLicenseException(int cllCode, int extendedCllError, int extendedFlxError)
    {
        super("License.Server.Exception");
        fgLogger.error("CllCode: " + Integer.toString(cllCode) + "ExtendedCllError: " + Integer.toString(extendedCllError) + "ExtenedFlxError: " + Integer.toString(extendedFlxError));
    }
    
    public FlexeraLicenseException(String exceptionMessage)
    {
        super(exceptionMessage);
    }

}
