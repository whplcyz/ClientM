/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  wch378 28/3/14

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/June/14 wch378               CCMPD01905766  Flexera license feature iteration2
------------------------------------------------------------------------------*/
package com.mot.dm.common.model.licenseModel;

public class OnOffTypeLicense extends BaseLicense
{
    /**
     * 
     */
    private static final long serialVersionUID = 5761722350514435562L;
    public boolean isEnabled;
    
    public OnOffTypeLicense()
    {
        super();
        isEnabled = false;
    }
}
