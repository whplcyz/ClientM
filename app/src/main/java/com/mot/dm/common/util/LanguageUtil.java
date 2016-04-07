package com.mot.dm.common.util;

import java.util.Collections;
import java.util.List;

import com.mot.dm.common.model.Device;
import com.mot.dm.common.model.ItmLanguage;

public final class LanguageUtil
{
    private LanguageUtil()
    {       
    }
    /**
     * If a radio support flash pack, but there is no "English" in flash pack language list.
     * We should add "English" to the installed language list. Because "English" has been embedded
     * in software in this case.
     * 
     * @param dev
     * @param isFlashPackSupport
     *          This parameter used to reminder the invoker that he has to check the flash pack
     *          support of the device.
     * @return
     */
    public static String getInstalledLanguage(Device dev, boolean isFlashPackSupport)
    {
        if (!isFlashPackSupport || (dev.getCurrentCodeplug() == null))
        {
            return "";
        }
        
        List<String> languageList = dev.getCurrentCodeplug().getFlashPackLanguages();
        
        return getInstalledLanguage(languageList);        
    }
    
    public static String getInstalledLanguage(List<String> languageList)
    {
        if (languageList == null)
        {
            return "";
        }

        // If the flash pack does not contain "English", we should add "English" to the list
        boolean hasEnglish = false;
        for (String nextLanguage : languageList)
        {
            if (nextLanguage.contains(CommonConstants.ENGLISH_TEXT))
            {
                hasEnglish = true;
                break;
            }
        }
        if (!hasEnglish)
        {
            languageList.add(CommonConstants.ENGLISH_TEXT);
        }
        
        Collections.sort(languageList);
        
        // convert the list to string
        String installedLanguages = "";
        for (String nextLanguage : languageList)
        {
            if (installedLanguages.length() == 0)
            {
                installedLanguages = nextLanguage; // first one
            }
            else
            {
                installedLanguages = installedLanguages + "," + nextLanguage;
            }
        }
        
        return installedLanguages;
    }
    /*Judge if the languageName is in languages*/
    public static boolean isStrListContainLanguage(List<String> languages,String languageName)
    {
        if (languages == null || languages.isEmpty())
        {
            return false;
        }
        for(String certainLanguage:languages)
        {
            if(certainLanguage.equalsIgnoreCase(languageName))
            {
                return true;
            }
            /*Special case to deal with British English or American English*/
            if(languageName.toUpperCase().contains(CommonConstants.ENGLISH_TEXT)
                    && certainLanguage.toUpperCase().contains(CommonConstants.ENGLISH_TEXT))
            {
                return true;
            }
        }
        return false;
    }
    
    /*Judge if the languageName is in languages*/
    public static boolean isILListContainLanguage(List<ItmLanguage> languages,String languageName)
    {
        if (languages == null || languages.isEmpty())
        {
            return false;
        }
        for(ItmLanguage certainItmLanguage:languages)
        {
            if(certainItmLanguage.getLanguageName().equalsIgnoreCase(languageName))
            {
                return true;
            }
            /*Special case to deal with British English or American English*/
            if(languageName.toUpperCase().contains(CommonConstants.ENGLISH_TEXT)
                    && certainItmLanguage.getLanguageName().toUpperCase().contains(CommonConstants.ENGLISH_TEXT))
            {
                return true;
            }
        }
        return false;
    }
    
    /*get corresponding ItmLanguage object from a list by languageName*/
    public static ItmLanguage getItmLanguageFromListByName(List<ItmLanguage> languages,String languageName)
    {
        if (languages == null || languages.isEmpty())
        {
            return null;
        }
        for(ItmLanguage certainItmLanguage:languages)
        {
            if(certainItmLanguage.getLanguageName().equalsIgnoreCase(languageName))
            {
                return certainItmLanguage;
            }
            /*Special case to deal with British English or American English*/
            if(languageName.toUpperCase().contains(CommonConstants.ENGLISH_TEXT)
                    && certainItmLanguage.getLanguageName().toUpperCase().contains(CommonConstants.ENGLISH_TEXT))
            {
                return certainItmLanguage;
            }
        }
        return null;
    }
}
