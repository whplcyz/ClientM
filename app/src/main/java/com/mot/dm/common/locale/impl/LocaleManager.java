/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                       Copyright 2007-2008 Motorola Inc.                      |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  iTM
 CODED     BY:  Jack Qiu    q19266

 ------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
 26/Jun/08  Kim Mortensen        CCMPD01058454  Making 3.rd try in loading 
                                                DmsContext. Last try is looking 
                                                within its own package.
 11/Sep/08  Michael Leismann      CCMPD01087804 Modified to be able to handle a
                                                German client: using a string
                                                from DmsContext instead of using
                                                the Locale settings
  7/Oct/08  Jens Hansen           CCMPD01095091 Moved class to common package.
                                                Added functionality for return-
                                                message file                                                   
24/Oct/08  Sigurdur Jonsson       CCMPD01113181 Proxy Error Text, remove logging
                                                problems on server
29/Oct/08  Jens Hansen            CCMPD01113181 Logging restored        
31/Oct/08  Hai Dong               CCMPD01118298 Validate XML before use them    
10/Nov/08  Hai Dong               CCMPD01123180 change validation location                                    
 ------------------------------------------------------------------------------*/
package com.mot.dm.common.locale.impl;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mot.dm.common.locale.inf.ILocaleManager;
import com.mot.dm.common.locale.inf.IStrMap;
import com.mot.dm.common.util.LocaleContext;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @Version 1.0 Apr 20, 2007
 * @author q19266 Jack Qiu
 */
public final class LocaleManager implements ILocaleManager
{
    private String m_localeString = null;

    private GlobalStrMap m_globalStrMap = null;

    private static LocaleManager c_localManager;

    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    private static Logger c_fgLogger = LoggerController.createLogger(LocaleManager.class);

    private LocaleManager(String p_localeString)
    {
        m_localeString = p_localeString;
        init();
    }

    public static LocaleManager getInstance(String p_path, String p_localeString)
    {
        if (c_localManager == null)
        {
            c_localManager = new LocaleManager(p_localeString);
        }
        else
        {
            if (!c_localManager.m_localeString.equals(p_localeString))
            {
                c_localManager = new LocaleManager(p_localeString);
            }
        }

        return c_localManager;
    }

    /**
     * This method loads the language specific ResourceMap and ReturnMessage.
     * The two files are validated during client/server startup.
     */
    @SuppressWarnings("deprecation")
    protected void init()
    {
        c_fgLogger.info("Initializing ...");
        boolean readXmlOK = true;
        String xmlFileName;
        URL xmlUrl;
        File xmlFile;

        Map<String, String> mStrCache = new HashMap<String, String>();
        // Load the ResouceMap file
        xmlFileName = LocaleContext.getResourceMapFullPath();
        c_fgLogger.info("xmlFileName: " + xmlFileName);

        try
        {
            xmlFile = new File(xmlFileName);
            xmlUrl = xmlFile.toURL();
        }
        catch (Exception e)
        {
            readXmlOK = false;
            c_fgLogger.error("Resouce map exception: " + e.getMessage());
        }

        // Load ReturnMessage file
        xmlFileName = LocaleContext.getReturnMessageFullPath();
        try
        {
            xmlFile = new File(xmlFileName);
            xmlUrl = xmlFile.toURL();
        }
        catch (Exception e)
        {
            readXmlOK = false;
            c_fgLogger.error("Return message exception: " + e.getMessage(), e);
        }

        if (readXmlOK)
        {
            m_globalStrMap = new GlobalStrMap();
            m_globalStrMap.setMStrCache(mStrCache);
        }
        else
        {
            // The XML file is not valid, don't start the client
            m_globalStrMap = null;
        }
    }

    public IStrMap getGlobalStrMap()
    {
        return m_globalStrMap;
    }

}
