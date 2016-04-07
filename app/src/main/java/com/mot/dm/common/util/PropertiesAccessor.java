/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  w23021(Jason)

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Jan/10  Michael Leismann      CCMPD01301894 Updated header
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Version 1.0 2007 May 12
 * @author w23021(Jason)
 * 
 *         This class is used to read Property file.
 */
public final class PropertiesAccessor
{
    private PropertiesAccessor()
    {
        
    }
    /**
     * load the properties file
     * 
     * @param pname
     *            the properties file name.
     * @return Properties return the properties.
     */
    @SuppressWarnings("rawtypes")
    public static Properties load(String pname)
    {
        Properties properties = new Properties();

        ResourceBundle bundle = ResourceBundle.getBundle(pname);

        Enumeration enumeration = bundle.getKeys();

        String key, value;

        while (enumeration.hasMoreElements())
        {
            key = (String) enumeration.nextElement();

            value = bundle.getString(key);

            properties.put(key, value);
        }

        return properties;
    }

}
