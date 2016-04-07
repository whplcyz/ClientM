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
CODED     BY:   hgkj73/cjh102

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/11/09   hgkj73/cjh102         CCMPD01288783 Added for Agency Partitioning
19/01/10   hgkj73                CCMPD01304268 java doc added   
04/May/10  Michael Leismann      CCMPD01340657 Clone method is now in
                                               BaseObject class 
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

/**
 * container various permissions in iTM
 * 
 * @author hgkj73
 * 
 */
public abstract class ItmPermissions extends BaseObject implements Cloneable
{
    /**
     * 
     */
    private static final long serialVersionUID = -4041626284943574670L;

    public abstract boolean isEmpty();
}
