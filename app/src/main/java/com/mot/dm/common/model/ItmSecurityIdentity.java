/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2009-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/09  hgkj73                CCMPD01288783 Added for agency partitioning
10/Mar/10  hgkj73                CCMPD01317830 Updated for AP.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

/**
 * Created for an abstraction level for representing subclasses that can act is
 * security Identities which can hold permissions on any given Controllable
 * Object
 * 
 * @author hgkj73
 * 
 */
public abstract class ItmSecurityIdentity extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 320797813643984422L;

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    /**
     * sub classes override and return their identity say user - user name Group
     * - groupName
     * 
     * @return String
     */
    public abstract String getName();

    /**
     * sub classes override and return additional info say user - Full name
     * group - group notes
     * 
     * @return String
     */
    public abstract String getDetails();

    /**
     * sub classes override and return uuid say user - user uuid group - group
     * uuid
     * 
     * @return String
     */
    public abstract String getUuid();
}