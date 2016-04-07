/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2010-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  cjh102 Sep 2010

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
01/10/10   ckfm01                CCMPD01398027 Adding MotoLM Licensing
11/11/10   ckfm01                CCMPD01409592 Licensing Part II
22/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
24/2/12	   Zhan XueFeng-A23126	 CCMPD01617126 Various License Manager Issues
------------------------------------------------------------------------------*/

package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.List;

public class LicenseHeader implements java.io.Serializable
{
    public static final int SYSTEM_ID = 1;
    public static final int FEATURE_ID = 2;
    private static final long serialVersionUID = 242123452134234L;
    private int licenseType = 0;
    private String customerId = "";
    private String customerNameShort = "";
    private String customerNameLong = "";
    private String issueTime = "";
    private String iTMVersion = "";
    private int hostMatchRate = 0;
    private String hostId = "";

    /**
     * @return the LicenseType
     */
    public int getLicenseType()
    {
        return licenseType;
    }

    /**
     * @param LicenseType
     *            to set
     */
    public void setLicenseType(int licenseType)
    {
        this.licenseType = licenseType;
    }

    /**
     * @return the HostId
     */
    public String getHostId()
    {
        return hostId;
    }

    /**
     * @param HostId
     *            to set
     */
    public void setHostId(String hostId)
    {
        this.hostId = hostId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId()
    {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    /**
     * @return the customerNameShort
     */
    public String getCustomerNameShort()
    {
        return customerNameShort;
    }

    /**
     * @param customerNameShort
     *            the customerNameShort to set
     */
    public void setCustomerNameShort(String customerNameShort)
    {
        this.customerNameShort = customerNameShort;
    }

    /**
     * @return the customerNameLong
     */
    public String getCustomerNameLong()
    {
        return customerNameLong;
    }

    /**
     * @param customerNameLong
     *            the customerNameLong to set
     */
    public void setCustomerNameLong(String customerNameLong)
    {
        this.customerNameLong = customerNameLong;
    }

    /**
     * @return the iTMVersion
     */
    public String getITMVersion()
    {
        return iTMVersion;
    }

    /**
     * @param version
     *            the iTMVersion to set
     */
    public void setITMVersion(String version)
    {
        iTMVersion = version;
    }

    /**
     * @return the hostMatchRate
     */
    public int getHostMatchRate()
    {
        return hostMatchRate;
    }

    /**
     * @param hostMatchRate
     *            the hostMatchRate to set
     */
    public void setHostMatchRate(int hostMatchRate)
    {
        this.hostMatchRate = hostMatchRate;
    }

    /**
     * @return the issueTime
     */
    public String getIssueTime()
    {
        return issueTime;
    }

    /**
     * @param issueTime
     *            the issueTime to set
     */
    public void setIssueTime(String issueTime)
    {
        this.issueTime = issueTime;
    }

    /**
     * @return the hostIds
     */
    public List<String> getHostIds()
    {
        List<String> hostList = new ArrayList<String>();

        if (hostId == null || hostId.length() == 0)
        {
            return hostList;
        }

        if (hostId.equalsIgnoreCase("ANY"))
        {
            hostList.add(hostId);
            return hostList;
        }

        if (hostId != null && hostId.length() > 0)
        {
            String[] result = hostId.split(":");
            for (String nextSection : result)
            {
                if ((nextSection == null) || nextSection.isEmpty())
                {
                    continue;
                }
                hostList.add(nextSection);
            }
        }

        return hostList;
    }
}
