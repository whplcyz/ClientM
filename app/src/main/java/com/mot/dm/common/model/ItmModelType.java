/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |

|                                                                              |
|                          Copyright 2009-2012 Motorola Solutions Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Michael Leismann   Sep 2009

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure 
28/Sep/09  Michael Leismann      CCMPD01269375 WAP
07/Oct/09  Michael Leismann      CCMPD01272901 WAP not supported for TCR1000
                                               and MTM800E
12/Oct/09  Michael Leismann      CCMPD01273965 WAP is supported for MTM800E-CH
06/Jul/10  Hai Dong              ccmpd01357127 Move Personal data query to 
                                               ClientUtil class   
11/Aug/10  Hai Dong              CCMPD01380651 handle Frodo for recover radio 
20/09/10   chad001               ccmpd01393090 4.0 licensing feature     
06/jan/11  Hai Dong              ccmpd01460066 Using  id instead of name
03/May/11  Sigurdur Jonsson      CCMPD01502973 Panda integration
19/July/12 tqfn38			     CCMPD01665910 proxy back compatibility
10/Aug/12   gjmv74               CCMPD01664121 [iTM R6.0] Feature of German fire,Add a new radio type in the itm client
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Michael Leismann
 */
public class ItmModelType extends BaseObject
{
    private static final long serialVersionUID = 45674679679456978L;

    private String itmModelTypeId = "";
    private String m_itmModelTypeName = "";
    private List<ModelType> m_modelTypes;

    private Map<String, String> m_modelTypeNames;

    public static final String UnKnown_Radio_ITM_MODEL_TYPE_ID = "0";
    public static final String UNKNOWN_ITM_MODEL_TYPE_NAME = "UNKNOWN";

    public static final String MTH800_ITM_MODEL_TYPE_ID = "2";
    public static final String MTP850_ITM_MODEL_TYPE_ID = "3";
    public static final String CEP400_ITM_MTP83_MODEL_TYPE_ID = "4";
    public static final String TCR1000_ITM_MODEL_TYPE_ID = "5";
    public static final String ATEX_ITM_MODEL_TYPE_ID = "6";
    public static final String MTM800E_TRANSCEIVER_ITM_MODEL_TYPE_ID = "7";
    public static final String MTM800E_CONTROL_HEAD_ITM_MODEL_TYPE_ID = "8";
    public static final String TOM_ITM_MODEL_TYPE_ID = "9";
    public static final String MTM5400_ITM_MODEL_TYPE_ID = "10";
    public static final String MTP3250_ITM_MODEL_TYPE_ID = "11";
    public static final String Ethernet_CONTROL_HEAD_ITM_MODEL_TYPE_ID = "12";
    public static final String MTP6000_ITM_MODEL_TYPE_ID = "13";
    // Legacy names - Pre iTM 2.1 release
    public final static String LEGACY_MTP850Ex_350_NAME = "MTP850Ex-350";
    public final static String LEGACY_MTP850_800_NAME = "MTP850-800";
    public final static String LEGACY_MTP850_350_NAME = "MTP850-350";
    public final static String LEGACY_MTP850_3L_NAME = "MTP850-350 class 3L (1.8W)";

    
    public ItmModelType()
    {
    }
    
    public ItmModelType(String itmModelTypeId)
    {
        this.itmModelTypeId = itmModelTypeId;
    }
    
    
    /**
     * Get the ItmModelTypeId
     * 
     * @returnItmModelTypeId
     */
    public String getItmModelTypeId()
    {
        return itmModelTypeId;
    }

    /**
     * Set the ItmModelTypeId
     * 
     * @param p_itmModelTypeId
     */
    public void setItmModelTypeId(String p_itmModelTypeId)
    {
        itmModelTypeId = p_itmModelTypeId;
    }

    /**
     * Get the itm model type name shown when a radio has been created
     * 
     * @return ItmModelTypeName
     */
    public String getItmModelTypeName()
    {
        return m_itmModelTypeName;
    }

    /**
     * Set the itm model type name shown when a radio has been created
     * 
     * @param p_itmModelTypeName
     */
    public void setItmModelTypeName(String p_itmModelTypeName)
    {
        m_itmModelTypeName = p_itmModelTypeName;
    }

    /**
     * Get the model types
     * 
     * @return model types
     */
    public List<ModelType> getModelTypes()
    {
        return m_modelTypes;
    }

    /**
     * Set the model types
     * 
     * @param p_modelTypes
     */
    public void setModelTypes(List<ModelType> p_modelTypes)
    {
        m_modelTypes = p_modelTypes;
    }

    /**
     * Get the model type names
     * 
     * @return model type names map
     */
    public Map<String, String> getModelTypeNames()
    {
        return m_modelTypeNames;
    }

    /**
     * Set the model type names
     * 
     * @param p_modelTypeNames
     */
    public void setModelTypeNames(Map<String, String> p_modelTypeNames)
    {
        m_modelTypeNames = p_modelTypeNames;
    }

    /**
     * Get the extended model types Device as a similar method, however when
     * creating a new radio the device has been created yet, so here it is
     * required to get the list of the extended model types based on the iTM
     * model type, which this method then provides
     * 
     * @return list of extended model types
     * @see setModelTypes
     */
    public List<String> getExtModelTypes()
    {
        List<String> extModelTypes = new ArrayList<String>();
        if (m_modelTypeNames != null && m_modelTypeNames.size() != 0)
        {
            Set<String> set = m_modelTypeNames.keySet();
            Iterator<String> itr = set.iterator();
            while (itr.hasNext())
            {
                String key = itr.next();
                extModelTypes.add(key);
            }
        }
        return extModelTypes;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("itmModelTypeId", itmModelTypeId);
        sb.append("itmModelTypeName", m_itmModelTypeName);
        sb.append("modelTypes", m_modelTypes);

        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof ItmModelType))
        {
            return false;
        }

        ItmModelType itmModelType = (ItmModelType) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(itmModelTypeId, itmModelType.itmModelTypeId);
        builder.append(m_itmModelTypeName, itmModelType.m_itmModelTypeName);
        builder.append(m_modelTypes, itmModelType.m_modelTypes);

        return builder.isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(itmModelTypeId).append(m_itmModelTypeName)
                .append(m_modelTypes).hashCode();
    }

}
