/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                    Copyright 2014 Motorola Solutions, Inc.                   |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  bfq463    May 27, 2014

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/Jun/14 bfq463              CCMPD01900799 active directory support 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class LdapNetWorkStatus implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 8385151692305474844L;
    
    public LdapNetWorkStatus()
    {
        certificates = new ArrayList<X509Certificate[]>();
    }
    
    private boolean connectSuccessful;
    public boolean isConnectSuccessful()
    {
        return connectSuccessful;
    }
    public void setConnectSuccessful(boolean connectSuccessful)
    {
        this.connectSuccessful = connectSuccessful;
    }
    public List<X509Certificate[]> getCertificates()
    {
        return certificates;
    }
    public void setCertificates(List<X509Certificate[]> certificates)
    {
        this.certificates = certificates;
    }
    private List<X509Certificate[]> certificates;

}
