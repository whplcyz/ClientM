/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                       |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
PRODUCT NAME:  ITM Client   
CODED     BY:  a23126 04/Jun/13

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
04/Jun/13  a23126                CCMPD01780442 [iTM 6.0.2] Update metadata to split 
                                               language pack size and font pack size.
19/06/13   wch378                CCMPD01784068 rename the class    
9/Sep/13   wch378                CCMPD01813562 update "use regional language logic"
15/11/13   wch378                CCMPD01830321 update GUI plugin language dynamically    
28/Jan/14  wch378                CCMPD01854222 Back compatibility for language issue
26/Sep/14  wch378                CCMPD01928113  fix C3 case                                          
------------------------------------------------------------------------------*/


package com.mot.dm.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mot.dm.client.dmImpl.gui.DmsContext;
import com.mot.dm.common.model.ItmLanguage;
import com.mot.dm.common.model.ItmLanguageFont;
import com.mot.dm.common.util.LocaleContext;
import com.mot.dm.common.util.log.impl.LoggerController;

/**
 * @author a23126
 *
 */
public final class ClientLanguageUtil
{
    private static Logger logger = LoggerController.createLogger(DmsContext.class);
    private static Properties propertiesObj = null;
    /**
     * 
     * Calculate the size occupied by language.
     * We should consider the font pack may be shared by different language
     * 
     * @param languageNames
     *  the selected languages
     * @param languageInfo
     * @param fontInfo
     * @return
     */
    public static int GetLanguageSize(List<String> languageNames, List<ItmLanguage> languageInfo,
            List<ItmLanguageFont> fontInfo)
    {
        // get selected itm languages
        List<ItmLanguage> usedLangList = new ArrayList<ItmLanguage>();
        for (String nextLangName : languageNames)
        {
            for (ItmLanguage nextItmLanguage : languageInfo)
            {
                if (nextItmLanguage.getLanguageName().equals(nextLangName))
                {
                    usedLangList.add(nextItmLanguage);
                    break;
                }
            }
        }
        
        // get used fonts
        Set<String> usedFontSet = new HashSet<String>();
        for (ItmLanguage nextItmLanguage : usedLangList)
        {
            usedFontSet.add(nextItmLanguage.getFontID());
        }
        
        // calculate language size
        int totalSize = 0;
        for (ItmLanguage nextLanguage : usedLangList)
        {
            totalSize +=  nextLanguage.getSize();
        }
        for (ItmLanguageFont nextFont : fontInfo)
        {
            if (usedFontSet.contains(nextFont.getFontID()))
            {
                totalSize += nextFont.getSize();
            }
        }
        
        return totalSize;
    }
        
    /*language format:_en,_de*/
    /*if language format is illegal,we will use English as default language */
    public static String getLegalLanguageSettting(String language)
    {
        Locale locale = DmsContext.StringToLocale(language);
        if (!DmsContext.languagesVector.contains(locale))
        {
            logger.error("The locale is not supported: " + DmsContext.LocaleToString(locale));
            // Finally, check if the default language and country
            // is supported
            locale = new Locale(LocaleContext.DEFAULT_LANGUAGE);
            if (!DmsContext.languagesVector.contains(locale))
            {
                logger.error("Default locale is not supported: " + DmsContext.LocaleToString(locale));
                return null;
            }            
        }
        return DmsContext.LocaleToString(locale);
    }    
    
    /*Load Correct Property File by languageSetting, that is according with business logic*/
    public static Properties getDictionaryOfPropertyFile()
    {
        synchronized(logger)
        {
            if (propertiesObj == null)
            {            
                propertiesObj = new Properties();
                String pluginFile =
                        Constants.PLUGIN + DmsContext.getLanguageSettings() + Constants.DOT_PROPERTIES;
                InputStream pluginFileIS =
                        ClientLanguageUtil.class.getResourceAsStream("/" + pluginFile);
                try
                {
                    propertiesObj.load(pluginFileIS);
                }
                catch (IOException e)
                {
                    logger.error(pluginFile + "is loaded fail");
                    /*Load default plugin.properties as remedy*/
                    pluginFile = Constants.PLUGIN + Constants.DOT_PROPERTIES;
                    pluginFileIS = ClientLanguageUtil.class.getResourceAsStream("/" + pluginFile);
                    try
                    {
                        propertiesObj.load(pluginFileIS);
                    }
                    catch (IOException e1)
                    {
                        logger.error(pluginFile + "is loaded fail");
                    }
                }
            }
        }
        
        return propertiesObj;
    }
    
}
