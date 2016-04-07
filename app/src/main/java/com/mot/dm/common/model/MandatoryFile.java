/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2009-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/09  Kalyan Pushpala       CCMPD01288783 initial version'
19/01/10   hgkj73                CCMPD01304268 java doc added
11/Mar/10  hgkj73                CCMPD01317830 iTM 3.0 Enforced Template 
                                               requirements     
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * mandatory template information container
 * 
 * @author hgkj73
 * 
 */
public class MandatoryFile extends BaseObject
{

    private static final long serialVersionUID = 17625539408726L;

    private String agencyUuid;

    /**
     * priority of the template
     */
    private int priority;

    /**
     * template object reference
     */
    private Device mandatoryFile;

    /**
     * codeplug object reference
     */
    private Codeplug codeplug;

    /**
     * get codeplug object
     * 
     * @return Codeplug
     */
    public Codeplug getCodeplug()
    {
        return codeplug;
    }

    /**
     * set codeplug
     * 
     * @param codeplug
     */
    public void setCodeplug(Codeplug codeplug)
    {
        this.codeplug = codeplug;
    }

    public MandatoryFile()
    {
        agencyUuid = null;
        priority = 0;
        mandatoryFile = null;
        codeplug = null;
    }

    public MandatoryFile(String agencyUuid, int priority, Device template, Codeplug codeplug)
    {
        this.agencyUuid = agencyUuid;
        this.priority = priority;
        this.mandatoryFile = template;
        this.codeplug = codeplug;
    }

    /**
     * set template
     * 
     * @param template
     */
    public void setMandatoryFile(Device mandatoryFile)
    {
        this.mandatoryFile = mandatoryFile;
    }

    /**
     * get template
     * 
     * @return device
     */
    public Device getMandatoryFile()
    {
        return mandatoryFile;
    }

    /**
     * set priority
     * 
     * @param priority
     */
    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    /**
     * get priority
     * 
     * @return int
     */
    public int getPriority()
    {
        return priority;
    }

    /**
     * set agencyUuid
     * 
     * @param agencyUuid
     */
    public void setAgencyUuid(String agencyUuid)
    {
        this.agencyUuid = agencyUuid;
    }

    /**
     * get agency uuid
     * 
     * @return String
     */
    public String getAgencyUuid()
    {
        return agencyUuid;
    }

    public boolean isNewVersionAvailable()
    {
        return codeplug.getCodeplugRepositoryVersion() < mandatoryFile.getCurrentCodeplug()
                .getCodeplugRepositoryVersion();
    }

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof MandatoryFile))
            return false;
        MandatoryFile rhs = (MandatoryFile) o;
        return new EqualsBuilder().append(agencyUuid, rhs.getAgencyUuid())
                .append(priority, rhs.getPriority()).append(mandatoryFile, rhs.getMandatoryFile())
                .append(codeplug, rhs.getCodeplug()).isEquals();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("agencyUuid", this.agencyUuid).append("template", this.mandatoryFile)
                .append("priority", priority).append("codeplug", codeplug);
        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 31;
        result = prime * result + ((agencyUuid == null) ? 0 : agencyUuid.hashCode());
        result = prime * result + ((codeplug == null) ? 0 : codeplug.hashCode());
        result = prime * result + priority;
        result = prime * result + ((mandatoryFile == null) ? 0 : mandatoryFile.hashCode());
        return result;
    }

}
