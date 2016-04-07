/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                          Copyright 2009 Motorola Inc.                        |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY: Kalyan Pushpala/Jens Hansen 16-Sep-2009    

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
16/09/09 Jens Hansen             CCMPD01261809 Place holder for properties                                                                                          
------------------------------------------------------------------------------*/
package com.mot.dm.common.delegation;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CommonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{

    private Properties properties;

    public CommonPropertyPlaceholderConfigurer(Properties properties)
    {
        this.properties = properties;
    }

    protected Properties mergeProperties() throws IOException
    {
        return this.properties;
    }
}
