/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2009 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  thmt001 07/Feb/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
17/06/08   chad001              CCMPD01051225  Use FileUtils for codeplug
16/July/09 Kalyan Pushpala      CCMPD01237099  Updated the getService method as 
                                     		   interface is changed
15/09/09   Kalyan Pushpala      CCMPD01261809  Copy right updated - review comment 
------------------------------------------------------------------------------*/

package com.mot.dm.common.delegation;


/*
 * This class is a wrapper for the ServiceDelegation in client package and proxy
 * package. This class is introduced during cleaning up repository services so 
 * that all repository task can be done in FileUtils class.
 * In the future, the two ServiceDelegation classes should be combined into one
 * class in common package.    
 */
public final class ServiceDelegationWrapper
{
    private ServiceDelegationWrapper()
    {
        
    }
    private static IServiceDelegation m_ServiceDelegation;

    public static IServiceDelegation setServiceDelegation(IServiceDelegation iServiceDelegation)
    {
        m_ServiceDelegation = iServiceDelegation;
        return m_ServiceDelegation;
    }

    public static IServiceDelegation getServiceDelegation()
    {
        return m_ServiceDelegation;
    }

    public static Object getService(String servicename)
    {
        return m_ServiceDelegation.getService(servicename);
    }

}
