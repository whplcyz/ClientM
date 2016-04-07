/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                   Copyright 2011-2014 Motorola Solutions Inc.                |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  q19266 Jack Qiu    Apr 20, 2007

------------------------------- REVISION HISTORY -------------------------------
11/Mar/2008  Michael Leismann     CCMPD01002089    getSellableLicensesService()
22/Apr/2008  Jens Hansen          CCMPD01013422    Added getParameterService()
17/Jun/2008  Hai Dong             CCMPD01051225    Add CodeplugVersionService
                                                   for keeping it consistent  
14/Jul/2008  Jens Hansen          CCMPD01061654    Added method to getting
                                                   VersionService           
15/09/08     ckfm01               CCMPD01092340    Removing codeplugVersion 
                                                   table in DB. 
12/01/08     chad001              CCMPD01290881 Add agency partitioning 
01/10/10     ckfm01               CCMPD01398027    Adding MotoLM Licensing
28/10/10    cjh102                CCMPD01399192    Added LogoutService
11/11/10     ckfm01               CCMPD01409592    Removing depricated services
25/11/10    chad001               ccmpd01394081    4.0 MS Mobility logging
22/Jul/13  a21944                CCMPD01796269 iTM6.1 support RPK w/o FW  
28/3/14    wch378                CCMPD01889533 develop flexera license.
25/Jun/14 bfq463              CCMPD01900799 active directory support 
------------------------------------------------------------------------------*/
package com.mot.dm.client.dmImpl.control;

import com.mot.dm.client.delegation.ServiceDelegation;
import com.mot.dm.common.service.AccessControlService;
import com.mot.dm.common.service.AgencyService;
import com.mot.dm.common.service.CodeplugService;
import com.mot.dm.common.service.DeviceService;
import com.mot.dm.common.service.DeviceTypeService;
import com.mot.dm.common.service.FlexeraLicenseService;
import com.mot.dm.common.service.GroupService;
import com.mot.dm.common.service.JobInfoService;
import com.mot.dm.common.service.LdapService;
import com.mot.dm.common.service.LogoutService;
import com.mot.dm.common.service.ParameterService;
import com.mot.dm.common.service.PermissionTemplateService;
import com.mot.dm.common.service.PolicyService;
import com.mot.dm.common.service.ProxyService;
import com.mot.dm.common.service.SoftwareService;
import com.mot.dm.common.service.UserService;
import com.mot.dm.common.service.VersionService;

/**
 * @Version 1.0 Apr 20, 2007
 * @author q19266 Jack Qiu
 */
public final class Controller
{
    private static Controller instance = null;
    private UserService userService;
    private AgencyService agencyService;
    private DeviceService deviceService;
    private DeviceTypeService deviceTypeService;
    private SoftwareService softwareService;
    private ProxyService proxyService;
    private PolicyService policyService;
    private ParameterService parameterService;
    private FlexeraLicenseService flexeraLicenseService;

    private JobInfoService jobService;
    private CodeplugService codeplugService;

    private VersionService versionService;
    private AccessControlService accessControlService;
    private GroupService groupService;
    private PermissionTemplateService permissionTemplateService;
    private LogoutService logoutService;
    private LdapService ldapService;

    private Controller()
    {
    }

    /**
     * Get a singleton instance of this class.
     * 
     * @param
     * @return a singleton instance of this class.
     */
    public static Controller getInstance()
    {

        if (null == instance)
        {
            instance = new Controller();
        }
        return instance;
    }
        
    public FlexeraLicenseService getFlexeraLicenseService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            flexeraLicenseService = (FlexeraLicenseService) ServiceDelegation.getInstance().getService("flexeraLicenseService");
        }
        else if (flexeraLicenseService == null)
        {
            flexeraLicenseService = (FlexeraLicenseService) ServiceDelegation.getInstance().getService("flexeraLicenseService");
        }
        return flexeraLicenseService;
    }

    public UserService getUserService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            userService = (UserService) ServiceDelegation.getInstance().getService("userService");
        }
        else if (userService == null)
        {
            userService = (UserService) ServiceDelegation.getInstance().getService("userService");
        }
        return userService;
    }

    public AgencyService getAgencyService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            agencyService = (AgencyService) ServiceDelegation.getInstance().getService(
                    "agencyService");
        }
        else if (agencyService == null)
        {
            agencyService = (AgencyService) ServiceDelegation.getInstance().getService(
                    "agencyService");
        }
        return agencyService;
    }

    public DeviceService getDeviceService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            deviceService = (DeviceService) ServiceDelegation.getInstance().getService(
                    "deviceService");
        }
        else if (deviceService == null)
        {
            deviceService = (DeviceService) ServiceDelegation.getInstance().getService(
                    "deviceService");
        }
        return deviceService;
    }

    public DeviceTypeService getDevceTypeService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            deviceTypeService = (DeviceTypeService) ServiceDelegation.getInstance().getService(
                    "deviceTypeService");
        }
        else if (deviceTypeService == null)
        {
            deviceTypeService = (DeviceTypeService) ServiceDelegation.getInstance().getService(
                    "deviceTypeService");
        }
        return deviceTypeService;
    }

    public SoftwareService getSoftwareService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            softwareService = (SoftwareService) ServiceDelegation.getInstance().getService(
                    "softwareService");
        }
        else if (softwareService == null)
        {
            softwareService = (SoftwareService) ServiceDelegation.getInstance().getService(
                    "softwareService");
        }
        return softwareService;
    }


    public JobInfoService getJobService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            jobService = (JobInfoService) ServiceDelegation.getInstance().getService(
                    "jobInfoService");
        }
        else if (jobService == null)
        {
            jobService = (JobInfoService) ServiceDelegation.getInstance().getService(
                    "jobInfoService");
        }
        return jobService;
    }

    public ProxyService getProxyService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            proxyService = (ProxyService) ServiceDelegation.getInstance()
                    .getService("proxyService");
        }
        else if (proxyService == null)
        {
            proxyService = (ProxyService) ServiceDelegation.getInstance()
                    .getService("proxyService");
        }
        return proxyService;
    }

    public CodeplugService getCodeplugService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            codeplugService = (CodeplugService) ServiceDelegation.getInstance().getService(
                    "codeplugService");
        }
        else if (codeplugService == null)
        {
            codeplugService = (CodeplugService) ServiceDelegation.getInstance().getService(
                    "codeplugService");
        }
        return codeplugService;
    }


    /**
     * Returns a ParameterService object
     * 
     * @return
     */
    public ParameterService getParameterService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            parameterService = (ParameterService) ServiceDelegation.getInstance().getService(
                    "parameterService");
        }
        else if (parameterService == null)
        {
            parameterService = (ParameterService) ServiceDelegation.getInstance().getService(
                    "parameterService");
        }
        return parameterService;
    }

    /**
     * Returns a VersionService object
     * 
     * @return
     */
    public VersionService getVersionService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            versionService = (VersionService) ServiceDelegation.getInstance().getService(
                    "versionService");
        }
        else if (versionService == null)
        {
            versionService = (VersionService) ServiceDelegation.getInstance().getService(
                    "versionService");
        }
        return versionService;
    }

    /*
     * Get access control service
     * 
     * @return AccessControlService
     */
    public AccessControlService getAccessControlService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            accessControlService = (AccessControlService) ServiceDelegation.getInstance()
                    .getService("accessControlService");
        }
        else if (accessControlService == null)
        {
            accessControlService = (AccessControlService) ServiceDelegation.getInstance()
                    .getService("accessControlService");
        }
        return accessControlService;
    }

    /*
     * Get the GroupService
     * 
     * @return GroupServicee
     */
    public GroupService getGroupService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            groupService = (GroupService) ServiceDelegation.getInstance()
                    .getService("groupService");
        }
        else if (groupService == null)
        {
            groupService = (GroupService) ServiceDelegation.getInstance()
                    .getService("groupService");
        }
        return groupService;
    }

    /*
     * Get the PermissionTemplateService
     * 
     * @return PermissionTemplateService
     */
    public PermissionTemplateService getPermissionTemplateService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            permissionTemplateService = (PermissionTemplateService) ServiceDelegation.getInstance()
                    .getService("permissionTemplateService");
        }
        else if (permissionTemplateService == null)
        {
            permissionTemplateService = (PermissionTemplateService) ServiceDelegation.getInstance()
                    .getService("permissionTemplateService");
        }
        return permissionTemplateService;
    }

    public LogoutService getLogoutService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            logoutService = (LogoutService) ServiceDelegation.getInstance().getService(
                    "logoutService");
        }
        else if (logoutService == null)
        {
            logoutService = (LogoutService) ServiceDelegation.getInstance().getService(
                    "logoutService");
        }
        return logoutService;
    }

    /*
     * Get the PolicyService
     * 
     * @return PolicyService
     */
    public PolicyService getPolicyService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            policyService = (PolicyService) ServiceDelegation.getInstance().getService(
                    "policyService");
        }
        else if (policyService == null)
        {
            policyService = (PolicyService) ServiceDelegation.getInstance().getService(
                    "policyService");
        }
        return policyService;
    }
       


    public LdapService getLdapService()
    {
        if (ServiceDelegation.getIsLoadAgain())
        {
            ldapService = (LdapService) ServiceDelegation.getInstance()
                    .getService("ldapService");
        }
        else if (ldapService == null)
        {
            ldapService = (LdapService) ServiceDelegation.getInstance()
                    .getService("ldapService");
        }
        return ldapService;
    }
}
