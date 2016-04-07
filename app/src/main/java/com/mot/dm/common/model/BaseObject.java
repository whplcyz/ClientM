/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:  Kevin Fan    2007-4-18

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/09  cjh102                CCMPD01288783 Added for agency partitioning
12/Mar/10   hgkj73               CCMPD01317830 Added for handling System permissions 
                                               on user
18/Mar/10  hgkj73                CCMPD01325308 updated as per latest design and 
                                               added a todo for future refactoring
27/Apr/10  hgkj73                CCMPD01340657 updated copyright
04/May/10  Michael Leismann      CCMPD01340657 Added clone method
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.io.Serializable;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode();
 * 
 * @author Kevin Fan
 * 
 *         2007-4-18 added three common method
 * 
 */
public abstract class BaseObject implements Serializable, Cloneable
{
    /**
     * 
     */
    private static final long serialVersionUID = -7750123250718302616L;

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    /*
     * The id is used by Spring security with respect to ACL (Access Control
     * Lists) TODO: refer http://jira.springframework.org/browse/SEC-972 when
     * this feature is provided, we should roll back this id column and use uuid
     * column instead which is flexible and scalable.
     */
    private long id;

    /**
     * Clone method
     * 
     * @return the cloned object, or null
     */
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException ce)
        {
            return null;
        }
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
