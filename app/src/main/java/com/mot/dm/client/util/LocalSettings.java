/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2008-2009 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  cml066    2008.09.18

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
18/Sep/08  Michael Leismann      CCMPD01087804 Used to read language settings
                                               (language and country) from the
                                               registry and then write them to a
                                               property file as they will be
                                               used when starting the iTM Client
                                               Also used to copy property files
                                               to a plugin directory
16/Oct/08  Michael Leismann      CCMPD01087804 Updated after FTR
23/Oct/08  Michael Leismann      CCMPD01095091 Updated after FTR
10/Nov/08  Hai Dong              CCMPD01123180 change validation location
04/Feb/09  Michael Leismann      CCMPD01159381 Moved updatePropertyFile and
                                               checkIfURLExists methods to the
                                               SettingsComposite class
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.mot.dm.common.util.LocaleContext;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @author Michael Leismann
 * 
 *         This class is used when patching the language files
 */
public final class LocalSettings
{
    private LocalSettings()
    {
        
    }
    private static Logger fgLogger = LoggerController.createLogger(LocalSettings.class);

    /**
     * The main method is invoked directly from a batch script
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            // patch the new language set. The parameters are
            // ResourceMap XML, ReturnMessage XML, tableColumn XML
            validatePatchedXmlFiles(args[0], args[1], args[2]);
        }
    }


    /**
     * This method is used for validating the patched XML files before copy them
     * to language folder
     */
    private static void validatePatchedXmlFiles(String resourceXML, String returnMessageXML,
            String tableColumnXML)
    {
        String path = "";
        try
        {
            // This is the directory one level above language directory.
            // patching language bat file is located under the language folder
            File dir = new File("..");
            StringBuffer sb = new StringBuffer();
            sb.append(dir.getCanonicalPath());
            sb.append("\\");
            // XSD files are located under the configuration folder
            sb.append(Constants.CONFIG_DIR);
            sb.append("\\");
            path = sb.toString();
            fgLogger.info("Client configuration path is : " + path);
        }
        catch (Exception e)
        {
            fgLogger.error("Get parent path failed!");
            System.exit(1);
        }

        String resourceMapXsdFile = path + LocaleContext.RESOURCEMAP + LocaleContext.DOT_XSD;
        String returnMessageXsdFile = path + LocaleContext.RETURNMESSAGE + LocaleContext.DOT_XSD;
        String tableColumnXsdFile = path + Constants.TABLECOLUMN + LocaleContext.DOT_XSD;

        if (!validate(resourceXML, resourceMapXsdFile, returnMessageXML, returnMessageXsdFile,
                tableColumnXML, tableColumnXsdFile, fgLogger))
        {
            System.exit(1);
        }
    }

    /**
     * This method is used by both this class and DmsContext, so we need a
     * logger object to log exceptions
     */
    public static boolean validate(String p_resorceMapXml, String p_resourceMapXsd,
            String p_returnMessageXml, String p_returnMessageXsd, String p_tableColumnXml,
            String p_tableColumnXsd, Logger p_logger)
    {
        boolean validateSuccess = true;

        return validateSuccess;
    }

}
