/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2011-2014 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2010.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
      
12/01/10   chad001               CCMPD01290881 Add agency partitioning   
22/Feb/10  Hai Dong              CCMPD01318755 Clone job feature    
15/Mar/10   chad001              CCMPD01320819 Reschedule jobs     
25/03/10   chad001               CCMPD01324766  3.0 AP feature
26/Apr/10   hgkj73               CCMPD01327990  3.0 AP feature                                         
20/09/10   chad001               ccmpd01393090 4.0 licensing feature                                                                                       
21/Sep/10  chad001               ccmpd01394081 Add policies manager                                          
11/Nov/10   ckfm01               CCMPD01409592 Removing deprecated services, 
                                               adding systemLicenses   
20/12/10   chad001               ccmpd01456219 notify actions after license change
18/01/11   chad001               ccmpd01464314 Correct permission behavior. 
                                 If no AP license, only radio operations are
                                 enabled, policy and notification part are 
                                 determined by their license and permissions  
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
05/12/12   tqfn38                CCMPD01724687 License for Basic Programming Functions
21/Jun/13  tqfn38                CCMPD01783643 Change Request 2659 - Picture Push and Pull policies have to be licensed in iTM
28/3/14    wch378                CCMPD01889533 develop flexera license.
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.ArrayList;
import java.util.List;

import com.mot.dm.client.dmImpl.control.Controller;
import com.mot.dm.common.model.Agency;
import com.mot.dm.common.model.PermissionTemplate;
import com.mot.dm.common.model.User;
import com.mot.dm.common.service.FlexeraLicenseService;
import com.mot.dm.common.util.FlexeraFeatureConstants;

/*
 * provides security utilities for client
 */
public final class ClientSecurityUtil
{
    private ClientSecurityUtil()
    {
        
    }
    
    private static FlexeraLicenseService licenseService = Controller.getInstance().getFlexeraLicenseService();
    
    private static Object HAS_AP_LICENSE = null;
    private static boolean AP_LICENSE = false;
    
    private static Object HAS_NOTIFICATION_LICENSE = null;
    private static boolean NOTIFICATION_LICENSE = false;
    
    private static Object HAS_ENFORCE_PROFILE_LICENSE = null;
    private static boolean ENFORCE_PROFILE_LICENSE = false;

    private static Object HAS_ODBC_LICENSE = null;
    private static boolean ODBC_LICENSE = false;
    
    private static Object HAS_DISTRIBUTION_CENTER_LICENSE = null;
    private static boolean DISTRIBUTION_CENTER_LICENSE = false;
    
    private static Object HAS_RADIO_PROGRAMMING = null;
    private static boolean RP_LICENSE = false;
    
    private static Object HAS_RADIO_AUTHENTICATION_LICENSE = null;
    private static boolean RADIO_AUTHENTICATION_LICENSE = false;
    
    private static Object HAS_INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = null;
    private static boolean INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = false;
    
    private static Object HAS_EAP_LICENSE = null;
    private static boolean EAP_LICENSE = false;
    
    private static Agency currentAgency;


    /**
     * reset the object to reload license settings from server
     */
    public static void resetSystemFeatures()
    {
        HAS_AP_LICENSE = null;
        HAS_NOTIFICATION_LICENSE = null;
        HAS_ENFORCE_PROFILE_LICENSE = null;
        HAS_ODBC_LICENSE = null;        
        HAS_DISTRIBUTION_CENTER_LICENSE = null;
        HAS_RADIO_PROGRAMMING = null;
        HAS_RADIO_AUTHENTICATION_LICENSE = null;
        HAS_INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = null;
    }



    /*
     * check if the system has AP license
     * 
     * @return boolean
     */
    public static boolean hasApLicense()
    {
        if (HAS_AP_LICENSE == null)
        {
            try
            {
                AP_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.AGENCY_PARTIONING_LICENSE);
            }
            catch (Exception se)
            {
                AP_LICENSE = false;
            }
            HAS_AP_LICENSE = new Object();
        }
        return AP_LICENSE;
    }

    /*
     * check if the system has enforce template license
     * 
     * @return boolean
     */
    public static boolean hasEnforceProfileLicense()
    {
        if (HAS_ENFORCE_PROFILE_LICENSE == null)
        {
            try
            {
                ENFORCE_PROFILE_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.ENFORCE_PROFILE_LICENSE);
            }
            catch (Exception se)
            {
                ENFORCE_PROFILE_LICENSE = false;
            }
            HAS_ENFORCE_PROFILE_LICENSE = new Object();
        }
        return ENFORCE_PROFILE_LICENSE;
    }

    /*
     * check if the system has ODBC license
     * 
     * @return boolean
     */
    public static boolean hasODBCLicense()
    {
        if (HAS_ODBC_LICENSE == null)
        {
            try
            {
                ODBC_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.ODBC_VIEW_LICENSE);
            }
            catch (Exception se)
            {
                ODBC_LICENSE = false;
            }
            HAS_ODBC_LICENSE = new Object();
        }
        return ODBC_LICENSE;
    }

    /*
     * check if the system has notification license
     * 
     * @return boolean
     */
    public static boolean canAddNotificationIDToDevice()
    {
        boolean canAddNotificationIDToDevice = false;
        try
        {
            canAddNotificationIDToDevice = licenseService.canAddNotificationIDtoDevice();
        }
        catch (Exception se)
        {
            canAddNotificationIDToDevice = false;
        }        
        return canAddNotificationIDToDevice;
    }

    public static List<PermissionTemplate> filterApplicablePermTemplates(User loggedinUser,
            User selectedUser, List<PermissionTemplate> templates)
    {

        List<PermissionTemplate> filteredList = new ArrayList<PermissionTemplate>();

        // if we are modifying builtin superuser, then irrespective of
        // logged in user we just return only templates with super user flag set
        // that cannot be modified.
        if (selectedUser.isBuiltInUser() && selectedUser.isSuperUser())
        {
            for (PermissionTemplate template : templates)
            {
                if (template.isSuperUser())
                {
                    filteredList.add(template);
                }
            }
            return filteredList;
        }

        // if logged in user is superuser he can select any template for any
        // user except for builtin superuser which is handled already
        if (loggedinUser.isSuperUser())
        {
            filteredList.addAll(templates);
            return filteredList;
        }
        else
        { // logged in user is not super user
            for (PermissionTemplate template : templates)
            {
                if (template.isSuperUser() == false)
                {
                    if (selectedUser.isSuperUser() == false)
                    {
                        filteredList.add(template);
                    }
                }
                else
                {
                    if (selectedUser.isSuperUser())
                    {
                        filteredList.add(template);
                    }
                }
            }
            return filteredList;
        }
    }
        
    /**
     * Check if the system has a Policies license
     * 
     * @return boolean
     */
    public static boolean hasRadioProgrammingLicense()
    {
        if (HAS_RADIO_PROGRAMMING == null)
        {
            try
            {
                RP_LICENSE = licenseService.isSystemLicenceEnabled(FlexeraFeatureConstants.RADIO_PROGRAMMING);
            }
            catch (Exception se)
            {
                RP_LICENSE = false;
            }
            HAS_RADIO_PROGRAMMING = new Object();
        }

        return RP_LICENSE;
    }
    
    /**
     * Check if the system has a radio authentication license
     * 
     * @return boolean
     */
    public static boolean hasRadioAuthenticationLicense()
    {
        if (HAS_RADIO_AUTHENTICATION_LICENSE == null)
        {
            try
            {
            	RADIO_AUTHENTICATION_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.Radio_Authentication);
            }
            catch (Exception se)
            {
            	RADIO_AUTHENTICATION_LICENSE = false;
            }
            HAS_RADIO_AUTHENTICATION_LICENSE = new Object();
        }

        return RADIO_AUTHENTICATION_LICENSE;
    }
    
    /**
     * Check if the system has a distribution center license
     * 
     * @return boolean
     */
    public static boolean hasDistributionCenterLicense()
    {
        if (HAS_DISTRIBUTION_CENTER_LICENSE == null)
        {
            try
            {
            	DISTRIBUTION_CENTER_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.DISTRIBUTION_CENTER_LICENSE);
            }
            catch (Exception se)
            {
            	DISTRIBUTION_CENTER_LICENSE = false;
            }
            HAS_DISTRIBUTION_CENTER_LICENSE = new Object();
        }

        return DISTRIBUTION_CENTER_LICENSE;
    }
    
    /**
     * Check if the system has a individual identify control head license
     * 
     * @return boolean
     */
    public static boolean hasIndividualControlHeadsIDLicense()
    {
    	if (HAS_INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE == null)
        {
            try
            {
            	INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = licenseService
                        .isSystemLicenceEnabled(FlexeraFeatureConstants.INDIVIDUAL_IDENTIFY_CONTROL_HEAD);
            }
            catch (Exception se)
            {
            	INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = false;
            }
            HAS_INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE = new Object();
        }

        return INDIVIDUAL_IDENTIFY_CONTROL_HEAD_LICENSE;
    }
    
    /*
     * check if the system has notification license
     * 
     * @return boolean
     */
    public static boolean hasNotificationLicense()
    {
        if (HAS_NOTIFICATION_LICENSE == null)
        {
            try
            {
                NOTIFICATION_LICENSE = licenseService.isSystemCapacityLicenceEnabled(FlexeraFeatureConstants.NOTIFICATION_LICENSE);
            }
            catch (Exception se)
            {
                NOTIFICATION_LICENSE = false;
            }
            HAS_NOTIFICATION_LICENSE = new Object();
        }
        return NOTIFICATION_LICENSE;
    }

    public static void setCurrentAgency(Agency agency)
    {
        currentAgency = agency;
    }

    public static Agency getCurrentAgency()    
    {
        return currentAgency; 
    }
    
    public static boolean isSystemUser()
    {
        return true;
    }

}