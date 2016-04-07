/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2013 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  ZhaoWei-wch378 25/Jan/13

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/Jan/13 ZhaoWei wch378        CCMPD01731947 import policy by xml 
25/Jan/13 ZhaoWei wch378        CCMPD01737509 import policy by xml 
25/July/13 wch378               CCMPD01797346 change policy type from PictureCollection 
                                              to PictureAndDFCollection
07/Aug/13  wch378               CCMPD01800204  refactor export policy logic, and -type                                                
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.HashMap;

import com.mot.dm.common.model.Policy;
import com.mot.dm.common.model.PolicyType;

public class XmlPolicy extends Policy
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static HashMap<String, Integer> PolicyNameAndIdMapForXml = new HashMap<String, Integer>();

    protected void setPolicyTypeName(String typeName)
    {
        if (type == null)
        {
            type = new PolicyType();
        }
        type.setPolicyTypeName(typeName);
    }

    static
    {
        PolicyNameAndIdMapForXml.put("FLASHREPORTCOLLECTION", 1);
        PolicyNameAndIdMapForXml.put("PICTUREANDDFCOLLECTION", 2);
        PolicyNameAndIdMapForXml.put("PICTUREPUSH", 3);
    }
}
