/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen, 15/Sep/2008

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
15/Sep/08  Jens Hansen           CCMPD01095091 Proxy Error Text, first version
22/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReturnCodeInfo extends BaseObject
{
    private static final long serialVersionUID = 3382458142097464669L;

    private String returnCodeUuid;

    /** The source of the return code - defined in the ICD */
    private int returnSource;

    /** The return code - defined in the ICD */
    private int returnCode;

    /** The server time and date when the return code was created. */
    private Timestamp returnCodeDate;

    public int getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(int returnCode)
    {
        this.returnCode = returnCode;
    }

    public Timestamp getReturnCodeDate()
    {
        return returnCodeDate;
    }

    public void setReturnCodeDate(Timestamp returnCodeDate)
    {
        this.returnCodeDate = returnCodeDate;
    }

    public String getReturnCodeUuid()
    {
        return returnCodeUuid;
    }

    public void setReturnCodeUuid(String returnCodeUuid)
    {
        this.returnCodeUuid = returnCodeUuid;
    }

    public int getReturnSource()
    {
        return returnSource;
    }

    public void setReturnSource(int returnSource)
    {
        this.returnSource = returnSource;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        ReturnCodeInfo rhs = (ReturnCodeInfo) other;
        return new EqualsBuilder().append(returnCodeUuid, rhs.returnCodeUuid)
                .append(returnCodeDate, rhs.returnCodeDate).append(returnSource, rhs.returnSource)
                .append(returnCode, rhs.returnCode).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.returnCodeUuid).append(this.returnCodeDate)
                .append(this.returnSource).append(this.returnCode).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("returnCodeUuid", this.returnCodeUuid)
                .append("returnCodeDate", this.returnCodeDate)
                .append("returnSource", this.returnSource).append("returnCode", this.returnCode);
        return sb.toString();
    }
}