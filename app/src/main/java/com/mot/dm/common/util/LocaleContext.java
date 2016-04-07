/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  cjh102    October 7, 2008 - based on DmsContext.java

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
7/Oct/08   cjh102                CCMPD01095091 Moved the common stuff of the
                                               DmsContext file to the common
                                               package and named it Locale-
                                               Context                                               
22/Oct/08  Michael Leismann      CCMPD01095091 Made class and some methods
                                               abstract so that server and proxy
                                               also can extend this class
23/Oct/08  Jens Hansen           CCMPD01095091 Added common language constants
                                               to the file and updated *Return-
                                               Message methods    
31/Oct/08  Hai Dong              CCMPD01118298 Validate XML before use them                                                                                          
12/Dec/08  Michael Leismann      CCMPD01138296 Removed method as part of moving
                                               the operations log to server
31/Aug/09  Jens Hansen           CCMPD01247176 Added method to launch OLH                                               
17/Mar/10  Michael Leismann      CCMPD01325167 Modified launch of OLH to be
                                               able to launch CHM
                                               (compiled HTML) files
17/03/11   chad001               ccmpd01484763 autotest  
16/Dec/11  cmdg37                CCMPD01953493 Change the method for executing commands 
                                               
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.mot.dm.common.locale.impl.LocaleManager;
import com.mot.dm.common.locale.inf.IStrMap;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @Version 1.0 Jul 4, 2007
 * @author q19266 Jack Qiu
 */
public abstract class LocaleContext
{
    protected static IStrMap strMap;
    protected static String languageSettings = "";
    protected static String languagePath = "";

    private static Logger c_fgLogger = LoggerController.createLogger(LocaleContext.class);

    /**
     * File extension
     */
    public static final String DOT_XML = ".xml";
    public static final String DOT_XSD = ".xsd";    

    /**
     * Language constants
     */
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String DEFAULT_COUNTRY = "";
    public static final String RESOURCEMAP = "ResourceMap";
    public static final String RETURNMESSAGE = "ReturnMessage";

    /**
     * Empty constructor. To be implemented by extending classes
     * 
     * @return True if the initialization went well, otherwise false
     */
    public abstract boolean initialize();
    
    /**
     * Initialize this class To be implemented by extending classes
     * 
     * @param p_locale
     *            A Locale object which denotes the language/country files to
     *            use
     * @param p_langPath
     *            The path to the language files
     * @return True if the initialization went well, otherwise false
     */
    public abstract boolean initialize(Locale p_locale, String p_langPath);

    /**
     * Setting up the strMap. Return true if the map is not null, else false.
     */
    protected boolean setGlobalStrMap()
    {
        strMap = LocaleManager.getInstance(languagePath, languageSettings).getGlobalStrMap();
        return (strMap != null);
    }

    /**
     * Getting the strMap
     */
    public IStrMap getGlobalStrMap()
    {
        return LocaleManager.getInstance(languagePath, languageSettings).getGlobalStrMap();
    }

    /**
     * Returns the messages stored in the ResouceMap.xml
     * 
     * @param key
     *            The key used to lookup the message
     * @return A string containing the message
     */
    public static String getString(String key)
    {
        if (strMap != null)
            return strMap.getString(key);
        return "";
    }

    /**
     * Get the detailed message for a return source/code combination. If the
     * detailed message is not available we'll try to get the corresponding
     * short message
     * 
     * @param source
     *            Denotes the source
     * @param code
     *            denotes the return code
     * @return String
     */
    public static String getDetailedReturnMessage(int source, int code)
    {
        String key = source + "." + code + ".detailed";

        // Get the detailed message
        String message = getString(key);
        if (!key.equals(message))
            return message;

        // The message equals the key, hence the detailed message was not
        // available - let's try to get the short message
        return getShortReturnMessage(source, code);
    }

    /**
     * Get the short message for a return source/code combination. In case the
     * short message for the given error code is not available we try to get the
     * detailed default message for the given error source. In case we cannot
     * get any messages for the error source/code combination an "unknown"
     * string is returned.
     * 
     * @param source
     *            Denotes the source
     * @param code
     *            denotes the return code
     * @return String
     */
    public static String getShortReturnMessage(int source, int code)
    {
        String key = source + "." + code + ".short";
        String message = getString(key);
        if (!key.equals(message))
            return message;

        // The message equals the key, hence the error source/code we are trying
        // to look up is not available. We'll look up the default message
        // instead - let's try to get the detailed default message first.
        if (code != 0)
        {
            message = getDetailedReturnMessage(source, 0);
            if (!key.equals(message))
                return message;
        }

        return "Unknown";
    }

    /*
     * Get the resource map file full path
     */
    public static String getResourceMapFullPath()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(languagePath);
        sb.append(RESOURCEMAP);
        sb.append(languageSettings);
        sb.append(DOT_XML);
        return sb.toString();
    }

    /*
     * Get the return message file full path
     */
    public static String getReturnMessageFullPath()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(languagePath);
        sb.append(RETURNMESSAGE);
        sb.append(languageSettings);
        sb.append(DOT_XML);
        return sb.toString();
    }

    /*
     * Get the resource map XSD file full path
     */
    public static String getResourceMapXsdFullPath()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(languagePath);
        sb.append(RESOURCEMAP);
        sb.append(DOT_XSD);
        return sb.toString();
    }

    /*
     * Get the return message XSD file full path
     */
    public static String getReturnMessageXsdFullPath()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(languagePath);
        sb.append(RETURNMESSAGE);
        sb.append(DOT_XSD);
        return sb.toString();
    }

    /**
     * Lanches the most suitable on-line help. It first attempts to find an OLH
     * for the chosen language, if this cannot be found, then the English OLH is
     * launched.
     * 
     * @param cmp
     */
    public static void lanuchOLH(CommonMessagePresentaion cmp)
    {
        try
        {
            FileUtils.unzipHelpFile();
            
            String fileName = getHelpFileName();
            if(fileName == null || fileName.isEmpty())
            {
                fileName = getDefaultHelpFileName();
            }
            
            if(fileName == null || fileName.isEmpty())
            {
                cmp.setDisplayMessage(getString("ShowHelpContents.noHelpFileMsgTitle"), getString("ShowHelpContents.noHelpFileMsgBody"));
            }
            else
            {
                // Invokes the windows default application to handle OLH files.
                // The command will NOT fail if no application exists to handle OLH
                // files. In this case windows will pop-up a message to user that
                // no application can be used to open the file.
                String[] commands = new String[] { "cmd", "/c", "start", fileName };
                c_fgLogger.info(Arrays.toString(commands));
                Runtime.getRuntime().exec(commands);
            }
        }
        catch (Exception ex)
        {
            c_fgLogger.error(ex);
            cmp.setDisplayMessage(getString("ShowHelpContents.noExeMsgTitle"),  getString("ShowHelpContents.noExeMsgBody"));
            return;
        }
    }

    private static String getHelpFileName()
    {
        // File name for language specific OLH file
        String sLanguageSettings = getLanguageSettings();
        
        if(sLanguageSettings.startsWith("_"))
        {
            sLanguageSettings = sLanguageSettings.substring(1);
        }
        
        String fileName =
                CommonConstants.BASE_PATH + System.getProperty("file.separator") + CommonConstants.PRE_HELP_PATH
                        + sLanguageSettings + System.getProperty("file.separator") + CommonConstants.HELP_FILE; 
        c_fgLogger.info("Language specific OLH: " + fileName);
        
        File file = new File(fileName);
        if (file.exists())
        {
            c_fgLogger.info("Help file found: " + fileName);
            return fileName;
        }
        
        c_fgLogger.info("Help file not found: " + fileName);                
        return null;
    }
    
    private static String getDefaultHelpFileName()
    {        
        String fileName =
                CommonConstants.BASE_PATH + System.getProperty("file.separator") + CommonConstants.PRE_HELP_PATH
                        + DEFAULT_LANGUAGE + System.getProperty("file.separator") + CommonConstants.HELP_FILE; 
        c_fgLogger.info("Language specific OLH: " + fileName);
        
        File file = new File(fileName);
        if (file.exists())
        {
            c_fgLogger.info("Help file found: " + fileName);
            return fileName;
        }
        
        c_fgLogger.info("Help file not found: " + fileName);                
        return null;
    }

    /**
     * @return the language settings as a string
     */
    public static String getLanguageSettings()
    {        
        if (languageSettings.length() >= 3)
        {
            return languageSettings.substring(0, 3);
        }
        else
        {
            return languageSettings;
        }
    }
}
