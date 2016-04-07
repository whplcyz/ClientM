/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                   Copyright 2011-2014 Motorola Solutions Inc.                |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  q19266 Jack Qiu    Jul 4, 2007

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
22/Apr/2008  Jens Hansen        CCMPD01013422 Check if the CPS files exist in 
                                              the specified location.
15/Sep/08  Michael Leismann      CCMPD01087804 Modified text strings to be able
                                               to handle a German client
16/Oct/08  Michael Leismann      CCMPD01087804 Updated after FTR
                                               Integrated with Proxy Error Text
                                               feature
                                               Using Locale object instead of a
                                               String
22/Oct/08  Michael Leismann      CCMPD01087804 Updated as LocaleContext has been
                                               modified
23/Oct/08  Michael Leismann      CCMPD01095091 Updated after FTR
27/Oct/08  Michael Leismann      CCMPD01113181 Added ReturnMessage check
31/Oct/08  Hai Dong              CCMPD01118298 Validate XML before use them
10/Nov/08  Hai Dong              CCMPD01123180 change validation location
05/Jan/09  Hai Dong              CCMPD01145535 Take CM5000 feature. 
12/Dec/08  Michael Leismann      CCMPD01138296 Removed method as part of moving
                                               the operations log to server
20/Jan/09  Hai Dong              CCMPD01125953 Fix KW finding                                                
04/Feb/09  Michael Leismann      CCMPD01159381 initializeLanguageSettings method
                                               uses user properties instead of
                                               Locale.getDefault()
31/Aug/09  Jens Hansen           CCMPD01247176 Moved functionality to the super 
                                               class                                               
31/Oct/09  Hai Dong              CCMPD01282666 Check cli exists                                                                                       
11/Nov/09  chad001              ccmpd01287416  Add method for getting locale.  
16/Jun/11  Hai Dong              CCMPD01523523 Change version check for proxy 
                                               into version check for client     
21/Mar/13  wch378                CCMPD01756334 detach ip and port on client 
4/Sep/13   wch378                CCMPD01811578 the key for getting regional language setting has changed   
9/Sep/13   wch378                CCMPD01813562 update "use regional language logic"     
12/Dec/13  qngv36                CCMPD01843876 [iTM 6.2] In some PC cannot schedule job. 
28/Jan/14  wch378                CCMPD01854222 Back compatibility for language issue  
28/Jan/14  wch378                CCMPD01854222 Back compatibility for language issue                                                                                                                               
25/Jun/14 bfq463              CCMPD01900799 active directory support
------------------------------------------------------------------------------*/
package com.mot.dm.client.dmImpl.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.mot.dm.client.delegation.ServerIpAccessor;
import com.mot.dm.client.dmImpl.control.Controller;
import com.mot.dm.client.util.ClientLanguageUtil;
import com.mot.dm.client.util.ClientSecurityUtil;
import com.mot.dm.client.util.ClientUtil;
import com.mot.dm.client.util.Constants;
import com.mot.dm.client.util.LocalSettings;
import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.User;
import com.mot.dm.common.util.CommonConstants;
import com.mot.dm.common.util.LocaleContext;
import com.mot.dm.common.util.exception.ServiceException;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @Version 1.0 Jul 4, 2007
 * @author q19266 Jack Qiu
 */
public class DmsContext extends LocaleContext
{
    private static DmsContext context = new DmsContext();
    private static User curUser;
    private static User loginUser;
    private static String password;
    private static String serverIp;
    public static Vector<Locale> languagesVector = new Vector<Locale>();
    private static Locale locale;
    private static Device cutDevice;
    private static boolean isDeviceCutted;

    private static Logger fgLogger = LoggerController.createLogger(DmsContext.class);
    /*
     * This message is shown before we can use any localized languages, so we
     * have to use plain English text. Don't try to replace this plain English
     * text into some resource bundle message!!!
     */
    private static String initializeErrorMessage = "";
    private static boolean isLanguageConsistent = true;
    private static boolean isLdapEnable = false;
    
    private static final String CPS_English_String = "en-US"; 
    private static final String CPS_German_String = "de-DE"; 
    private static final String CPS_Spanish_String = "es-ES"; 
    private static final String CPS_Portuguese_String = "pt-BR";
    
    public static List<String> supportedLanguageStringsList = new ArrayList<String>();
    static
    {
        supportedLanguageStringsList.add(CPS_English_String);
        supportedLanguageStringsList.add(CPS_German_String);
        supportedLanguageStringsList.add(CPS_Portuguese_String);
        supportedLanguageStringsList.add(CPS_Spanish_String);
    }

    public static boolean isLdapEnable() 
    {
        return isLdapEnable;
    }

    public static void setLdapEnable(boolean isLdapEnable) 
    {
        DmsContext.isLdapEnable = isLdapEnable;
    }

    /**
     * Initialize this class This is done by finding and setting the language
     * settings
     * 
     * @return false, if this fails, otherwise true
     */
    public boolean initialize()
    {
        fgLogger.info("initialize DmsContext");

        // First, read language settings - if they have been set
        boolean init = initializeLanguageSettings();
        // don't start if string map is invalid
        boolean mapOK = setGlobalStrMap();

        // Did the language setting fail?
        if (!init || !mapOK)
        {
            fgLogger.error("initialize DmsContext failed");
            synchronized(context)
            {
                if (initializeErrorMessage.length() == 0)
                {
                    // The error doesn't come from init, then it comes from mapOK
                    initializeErrorMessage = "The iTM Client cannot be launched. Please re-install the iTM Client and try again!";
                }
            }
            return false;
        }

        return true;
    }

    /**
     * Implementation of super method - not used for this class
     */
    public boolean initialize(Locale p_locale, String p_langPath)
    {
        return false;
    }

    public static DmsContext getInstance()
    {
        return context;
    }

    public static String getInitializeErrorMessage()
    {
        return initializeErrorMessage;
    }

    public static void setCurrentUser(User currentUser)
    {
        curUser = currentUser;
    }

    public static User getCurrentUser()
    {
        return curUser;
    }

    public static void setServerIp(String ip)
    {
        serverIp = ip;
    }

    public static String getServerIp()
    {
        return serverIp;
    }

    public static void setDevice(Device dev)
    {
        cutDevice = dev;
    }

    public static Device getDevice()
    {
        return cutDevice;
    }

    public static void setIsDeviceCutted(boolean isCutted)
    {
        isDeviceCutted = isCutted;
    }

    public static boolean getIsDeviceCutted()
    {
        return isDeviceCutted;
    }

    /**
     * @return the path to the language files
     */
    public static String getLanguagePath()
    {
        return languagePath;
    }

    /**
     * @return the vector with the supported/available languages
     */
    public static Vector<Locale> getLanguages()
    {
        return languagesVector;
    }
    
    public static boolean getLanguageConsistent()
    {
        return isLanguageConsistent;
    }

    public static void initializeServerIp()
    {
        Preferences preferences = Preferences.userRoot().node(Constants.PREF_KEY);
        serverIp = preferences.get(ServerIpAccessor.serverIpKey, "");
        if (ClientUtil.detachIPandPort(serverIp) == null)
        {
            fgLogger.error("The Server IP address or port is illegal");
        }
    }

    /**
     * Read language settings - if they have been set otherwise use default
     * (regional) settings
     * 
     * @return false, if this fails, otherwise true
     */
    private boolean initializeLanguageSettings()
    {
        // Find out where to find the language files
        findLanguagesPath();

        // Return if it could not be found
        if (languagePath.length() == 0)
        {
            initializeErrorMessage = "The iTM Client cannot be launched as no language path defined. Please re-install the iTM Client and try again!";
            return false;
        }

        // Find out which languages this client supports
        findSupportedLanguages();

        // Get language string from registry
        Preferences preferences = Preferences.userRoot().node(Constants.PREF_KEY);
        languageSettings = preferences.get(CommonConstants.LANGUAGE, "");
                         
        if (languageSettings.isEmpty())
        {
        	//if the language setting is not been configured. assume the default is English.
            fgLogger.info("Language settings have been set 'Use English', update to English");
            languageSettings = "_en"; 
        }
                          
        /*correct the language settings*/
        languageSettings = ClientLanguageUtil.getLegalLanguageSettting(languageSettings);
        
        if (languageSettings == null || languageSettings.isEmpty())
        {
            /*cannot launch client, probably client files have been destroyed*/
            initializeErrorMessage = "The iTM Client cannot be launched as no language settings defined. Please re-install the iTM Client and try again!";
            return false;
        }
        
        validConsistentOfLanguageSetting();
        
        fgLogger.info("Will use language settings: " + languageSettings);
       
        /*Must initialize the local by languageSettings*/
        locale = StringToLocale(languageSettings);
        fgLogger.info("initialize the local by languageSettings: " + locale.toString());

        String tableColumnXmlPath = languagePath + Constants.TABLECOLUMN + languageSettings
                + DOT_XML;
        String tableColumnXsdPath = languagePath + Constants.TABLECOLUMN + DOT_XSD;

        File resourceMapXsd = new File(LocaleContext.getResourceMapXsdFullPath());
        if (!resourceMapXsd.exists())
        {
            initializeErrorMessage = "The iTM Client cannot be launched because the ResourceMap XSD file is missing. Please re-install the iTM Client and try again!";
            return false;
        }
        File returnMessageXsd = new File(LocaleContext.getReturnMessageXsdFullPath());
        if (!returnMessageXsd.exists())
        {
            initializeErrorMessage = "The iTM Client cannot be launched because the ReturnMessage XSD file is missing. Please re-install the iTM Client and try again!";
            return false;
        }
        File tableColumnXsd = new File(tableColumnXsdPath);
        if (!tableColumnXsd.exists())
        {
            initializeErrorMessage = "The iTM Client cannot be launched because the tableColumn XSD file is missing. Please re-install the iTM Client and try again!";
            return false;
        }

        /*
         * Validate the resource map XML, return message XML, and table
         * column XML file against the XSD file for each of them. If the
         * validation is fail for one or more of them, the client won't
         * start. User will be asked to re-install the client
         */
        if (!LocalSettings.validate(LocaleContext.getResourceMapFullPath(),
                LocaleContext.getResourceMapXsdFullPath(),
                LocaleContext.getReturnMessageFullPath(),
                LocaleContext.getReturnMessageXsdFullPath(), tableColumnXmlPath,
                tableColumnXsdPath, fgLogger))
        {
            initializeErrorMessage = "The iTM Client cannot be launched because the XSD validation failed. Please re-install the iTM Client and try again!";
            return false;
        }

        return true;
    }

    /**
     * Find the path to the language files
     */
    private void findLanguagesPath()
    {
        languagePath = "";
        try
        {
            File file = new File(Constants.CONFIG_DIR);

            // Check if the directory exists
            if (file.exists() && file.isDirectory())
            {
                languagePath = file.getAbsolutePath() + "\\";
                fgLogger.info("Language path: " + languagePath);
            }
            else
            {
                fgLogger.error("Was not able to find the language path!");
            }
        }
        catch (Exception e)
        {
            fgLogger.error("Exception: " + e.getMessage());
        }
    }
  
    /**
     * Find all the supported languages Language setting are on the format _xx
     * or xx_YY, e.g. _en or de_DE
     */
    private void findSupportedLanguages()
    {
        int resourcemapLength = RESOURCEMAP.length();
        int xmlLength = DOT_XML.length();
        try
        {
            // Get all the files in the directory in a string array
            File dir = new File(languagePath);
            String[] fileList = dir.list();
            if (fileList == null)
            {
                fgLogger.error("List files under the directory returns null");
                return;
            }

            // Look through the files
            // Find the Resourcemap_xx.xml/Resourcemap_xx_YY.xml files and
            // check that a plugin_xx.properties/plugin_xx_YY.properties,
            // a tableColumn_xx.xml/tableColumn_xx_YY.xml and
            // a ReturnMessage_xx.xml/ReturnMessage_xx_YY.xml exist
            for (int i = 0; i < fileList.length; i++)
            {
                String fileName = fileList[i];
                if (fileName.startsWith(RESOURCEMAP) && fileName.endsWith(DOT_XML))
                {
                    String language = fileName.substring(resourcemapLength, fileName.length()
                            - xmlLength);

                    String pluginFile = Constants.PLUGIN + language + Constants.DOT_PROPERTIES;

                    URL propertyFileUrl = getClass().getClassLoader().getResource(pluginFile);

                    File tableColumnFile = new File(languagePath + Constants.TABLECOLUMN + language
                            + DOT_XML);

                    File returnMessageFile = new File(languagePath + RETURNMESSAGE + language
                            + DOT_XML);

                    // If the files exist then add the language to the
                    // language vector
                    if (propertyFileUrl != null && tableColumnFile.exists()
                            && returnMessageFile.exists())
                    {
                        // Add as a locale object
                        languagesVector.add(StringToLocale(language));
                    }
                }
            }
        }
        catch (Exception e)
        {
            fgLogger.error("Exception: " + e.getMessage());
        }
    }

    /**
     * Converts a String to a Locale The format of the string is _xx or _xx_YY
     * (e.g. _en or _de_DE)
     * 
     * @param p_localeString
     *            the String
     * @return the Locale or null
     */
    public static Locale StringToLocale(String p_localeString)
    {
        if (p_localeString == null || p_localeString.length() == 0)
        {
            return null;
        }

        Locale localeInstance = null;

        // Get the language
        String language = p_localeString;
        StringTokenizer st = new StringTokenizer(p_localeString, "_");

        // Get the language string (if available)
        if (st.hasMoreTokens())
        {
            language = st.nextToken().toLowerCase();
        }

        // Check if the language string is available
        if (language != null && language.length() != 0)
        {
            localeInstance = new Locale(language);            
        }

        return localeInstance;
    }

    /**
     * Convert a Locale to a String
     * 
     * @param p_locale
     *            the Locale
     * @return the Locale as a String or an empty String The format of the
     *         string is _xx or _xx_YY (e.g. _en or _de_DE)
     */
    public static String LocaleToString(Locale p_locale)
    {
        String localeString = "";
        if (p_locale == null)
        {
            return localeString;
        }

        String language = p_locale.getLanguage();

        // Language must be set
        if (language.length() != 0)
        {
            localeString = "_" + language;
        }

        return localeString;
    }    

    /*
     * The locale is sued for some GUI pages to format date
     * 
     * @return Locale
     */
    public static Locale getClientLocale()
    {
        return locale;
    }
    
    private static boolean validConsistentOfLanguageSetting()
    {
        String frameworkLanguage = null;
        if(System.getProperties().containsKey("org.osgi.framework.language"))
        {
            frameworkLanguage = System.getProperty("org.osgi.framework.language");
        }
        
        if (frameworkLanguage == null || frameworkLanguage.isEmpty())
        {
            return isLanguageConsistent;
        }
        
        Locale lsLocale = StringToLocale(languageSettings);
        Locale propertyLocale = StringToLocale(frameworkLanguage);
        if(!lsLocale.equals(propertyLocale))
        {
            isLanguageConsistent = false;
            return isLanguageConsistent; 
        }
              
        return isLanguageConsistent;
    }
    
    /**
     * Get the language setting from our DMS Context.
     */
    public static String getLanguageStringsFromSetting()
    {        
        // Only set a locale if its known by dmsContext, otherwise
        // dont use any locale (languageSettings default to "").
        if(locale != null && locale.getLanguage() != null && !locale.getLanguage().isEmpty())
        {        
            for(String languageString : supportedLanguageStringsList)
            {
                if(languageString.toLowerCase().startsWith(locale.getLanguage().toLowerCase()))
                {
                    return languageString;
                }
            }
        }
        
        return CPS_English_String;
    }

    public static User getLoginUser()
    {
        return loginUser;
    }

    public static void setLoginUser(User loginUser)
    {
        DmsContext.loginUser = loginUser;
        DmsContext.curUser = loginUser;
    }


    public static String getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        DmsContext.password = password;
    }
}
