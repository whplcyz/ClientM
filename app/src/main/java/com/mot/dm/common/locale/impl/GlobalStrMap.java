/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                       Copyright 2007-2008 Motorola Inc.                      |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  iTM
 CODED     BY:  q19266 Jack Qiu, 2007

 ------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
  9/Oct/08   Jens Hansen           CCMPD01095091	Moved common stuff to the common
                                                package 
 28/Oct/08   Sigurdur Jonsson      CCMPD01113181 Proxy Error Text, operation logging.
 ------------------------------------------------------------------------------*/
package com.mot.dm.common.locale.impl;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.locale.inf.IStrMap;

/**
 * @Version 1.0 Apr 20, 2007
 * @author q19266 Jack Qiu
 */
public class GlobalStrMap implements IStrMap, java.io.Serializable
{
    private static final long serialVersionUID = 9034645786597819202L;
    private Map<String, String> mStrCache = null;

    /**
     * Returns a readable message based on the provided key. If the key is not
     * found in the map of availabel messages, then the provided key is provided
     * as the message.
     */
    public String getString(String Key)
    {
        String s = (String) mStrCache.get(Key);
        if (s == null)
        {
            s = Key;
        }
        return s;
    }

    public Map<String, String> getMStrCache()
    {
        return mStrCache;
    }

    public void setMStrCache(Map<String, String> strCache)
    {
        mStrCache = strCache;
    }

    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        return sb.toString();
    }

    public boolean equals(Object obj)
    {
        if ((this == obj))
            return true;
        if ((obj == null))
            return false;
        if (!(obj instanceof GlobalStrMap))
            return false;

        GlobalStrMap rhs = (GlobalStrMap) obj;
        return new EqualsBuilder().append(mStrCache, rhs.mStrCache).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append(mStrCache).hashCode();
    }

}
