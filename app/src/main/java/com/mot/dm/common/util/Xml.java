/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2008-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Kim Mortensen August 2009

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
30/Jun/10  Michael Leismann      CCMPD01357138 Based on the Xml class from the
                                               upgrade package
30/Mar/12  Huiping Wei           CCMPD01619190 Unicode on XML on external iTM Interfaces
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author ckfm01
 */
public final class Xml
{
    private static Xml c_xml = null;

    /**
     * Constructor
     */
    private Xml()
    {
    }

    /**
     * This is a singleton
     * 
     * @return
     */
    public static Xml getInstance()
    {
        if (c_xml == null)
        {
            c_xml = new Xml();
        }
        return c_xml;
    }

    /**
     * This gets the value of the object <p_nodeName> from the XML Element
     * <p_nodeElement> at index <p_itemNo>
     * 
     * @param p_nodeName
     * @param p_nodeElement
     * @param p_itemNo
     * @return
     */
    public String getNodeFromXML(String p_nodeName, Element p_nodeElement, int p_itemNo)
    {
        NodeList radioNameNL = p_nodeElement.getElementsByTagName(p_nodeName);
        Element radioNameELM = (Element) radioNameNL.item(p_itemNo);
        if (radioNameELM != null && radioNameELM.getChildNodes() != null)
        {
            NodeList radioNameNLValue = radioNameELM.getChildNodes();
            if (radioNameNLValue.getLength() > 0)
            {
                return (((Node) radioNameNLValue.item(0)).getNodeValue());
            }
            else if (radioNameNLValue.getLength() == 0)
            {
                return "";
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /*
     * Only escape 5 XML reserved entities(& > < " ')
     * 
     * @param value - the value.
     * 
     * @return--the escaped xml string value
     * 
     * Additional Info: We used StringEscapeUtils.escapeXml to escape XML
     * entities, however, unicode characters greater than 0x7f are currently
     * escaped to their numerical. For details, please refer:
     * http://www.docjar.org
     * /docs/api/org/apache/commons/lang/StringEscapeUtils.html
     * #escapeXml(String)
     */
    public String friendlyEscapeXML(String value)
    {
        String friendlyEscapeXMLStr = value;
        String[] xmlReservedArr =
        { "&", ">", "<", "\'", "\"" };
        String[] escapseXMLArr =
        { "&amp;", "&gt;", "&lt;", "&apos;", "&quot;" };

        if (null != friendlyEscapeXMLStr)
        {
            // First, unescape the XML string value as it may have some escaped
            // sub-string, and it will goes to wrong if
            // escape them will leads to wrong value.
            // Such as the original value is "Test &amp;", we should not escape
            // its value to "Test &ampamp", instead, we should keep its value
            // "Test &amp;".
            for (int i = 0; i < escapseXMLArr.length; i++)
            {
                friendlyEscapeXMLStr = friendlyEscapeXMLStr.replace(escapseXMLArr[i],
                        xmlReservedArr[i]);
            }

            // Second, escape the xml value
            for (int i = 0; i < xmlReservedArr.length; i++)
            {
                friendlyEscapeXMLStr = friendlyEscapeXMLStr.replace(xmlReservedArr[i],
                        escapseXMLArr[i]);
            }
        }

        return friendlyEscapeXMLStr;
    }

}
