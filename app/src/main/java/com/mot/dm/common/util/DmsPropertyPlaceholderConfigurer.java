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

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Version 1.0 2007 Jun 12
 * @author w23021(Jason)
 * 
 *         This class is used to extends the Spring's
 *         PropertyPlaceholderConfigurer and override the mergeProperties method
 *         to generate Properties.
 */
public class DmsPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{

    /**
     * override the mergeProperties method to generate Properties.
     */
    protected Properties mergeProperties() throws IOException
    {

        return EnvironmentAccessor.getSelf().getProperties();

    }
}
