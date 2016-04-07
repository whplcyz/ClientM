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

public class NumberTypeLicense extends BaseLicense
{
    /**
     * 
     */
    private static final long serialVersionUID = 3987193651095619401L;
    public static final int SITEWIDENUMBER = -1;
    public int availableNumber;
    public int usedNumber;
    public boolean isInvalid;
    public String invalidCause; 
    
    public NumberTypeLicense()
    {
        super();
        availableNumber = 0;
        usedNumber = 0;   
        isInvalid = false;
        invalidCause = "";
    }
}
