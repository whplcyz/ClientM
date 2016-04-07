/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright  2010 Motorola Inc.                          |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2010.03.25

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
25/03/10   chad001               CCMPD01324766  3.0 AP feature
------------------------------------------------------------------------------*/

package com.mot.dm.client.util;

import java.util.Comparator;

import com.mot.dm.common.model.ItmSecurityIdentity;

/*
 * A sorting class for sorting ViewUsers by user name
 */
public class SortByNameComparator implements Comparator<ItmSecurityIdentity>
{
    /*
     * (non-Javadoc)
     * 
     * @param o1 ViewUser
     * 
     * @param o2 ViewUser
     * 
     * @return int
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(ItmSecurityIdentity o1, ItmSecurityIdentity o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}