/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2008-2013 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  w22796    14 June 2007

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
15/Nov/07   q19376               Add support for "Apply template"
20/Nov/07   q19376               Add a new Message for Radio not existed
19/Mar/08   ckfm01               minor changes from FTR.
25/Mar/08 Tommy Thomadsen        CCMPD01006519 Updated job states.
31/Mar/08 Sigurdur Jonsson       CCMPD00993893 Off-line programming feature improvements.
9/Oct/08  Jens Hansen            CCMPD01095091 Added maps for deviceStatus, 
                                               jobStatus and proxyStatus. 
18/Nov/08 Sigurdur Jonsson       CCMPD01127256 clear template when up/downgrading radio
12/Jan/09  Kim Mortensen         CCMPD01146532 Adding new SW Upgrade for Cleartone
13/Apr/09   chad001              CCMPD01193805 recover radio
14/Apr/09  Michael Leismann      CCMPD01189094 Added new task types 
26/Nov/09  Michael Leismann      CCMPD01287031 Jobs on memory stick feature:
                                               Removed offline job status
26/11/09   hgkj73/cjh102         CCMPD01288783 Added new task types for nw/fleet 
                                               templates 
13/Jan/10 Sigurdur Jonsson       CCMPD01302541 iTM 3.0 Feature, job scheduling and
                                               expired scheduling cleanup.
08/Jun/10  Michael Leismann      CCMPD01357734 Added new task types 
22/Sep/10  Sigurdur Jonsson      CCMPD01394082 Policy feature, network logging
11/Oct/10  Michael Leismann      CCMPD01387289 iTM 4.0: Network Logging
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Add upgrade codeplug 
26/May/11  Michael Leismann      CCMPD01505987 iTM 5.0: ICD changes
22/May/12  wch378                wch378_hotfix iTM6.0 Hot Fix Feature
19/July/12 tqfn38	         CCMPD01665910 proxy back compatibility 
1/Aug/12   wch378                wch378_CCMPD01680624 iTM6.0 Hot Fix Feature
26/Nov/12  ZhaoWei wch378        CCMPD01713576 Convert Radio Model
27/Nov/12  a21944                CCMPD01832741 iTM6.2, added for profile feature
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class StatusConstants
{
    private StatusConstants()
    {
        
    }
    // ICD section 2.24.1 - POLICY_EXECUTION_STATUS
    public static final int POLICY_EXECUTION_STATUS_PERFORMED = 1;
    public static final int POLICY_EXECUTION_STATUS_FAILED = 2;

    // FR requirement FR-ITM-4102 - ICD section 2.23.1 - POLICY_STATUS
    public static final int POLICY_STATUS_RESERVED = 1;
    public static final int POLICY_STATUS_RELEASED = 2;
    public static final int POLICY_STATUS_EXECUTED = 3;
    public static final int POLICY_STATUS_PARTIAL = 4;

    public static final int DEVICE_NEW = 1;
    public static final int DEVICE_READY = 2;
    public static final int DEVICE_PENDING = 3;
    public static final int DEVICE_RUNNING = 4;
    public static final int DEVICE_FAILED = 5;
    public static final int DEVICE_OFFLINE = 6;
    public static final int DEVICE_CPMODIFIED = 7;
    public static final int DEVICE_WAITING = 8;
    public static final int DEVICE_EXPIRED = 9;
    public static final int DEVICE_PRLMODIFIED = 10;
    public static final int DEVICE_PENDING_LICENSE = 11;

    // ICD section 2.9.1 - JOB_STATUS
    public static final int JOB_STATUS_PENDING = 1;
    public static final int JOB_STATUS_RUNNING = 2;
    public static final int JOB_STATUS_PERFORMED = 3;
    public static final int JOB_STATUS_PARTIAL = 4;
    public static final int JOB_STATUS_FAILED = 5;
    public static final int JOB_STATUS_REPLACED = 6;
    public static final int JOB_STATUS_WAITING = 7;
    public static final int JOB_STATUS_EXPIRED = 8;
    public static final int JOB_STATUS_CANCELED = 9;
    public static final int JOB_STATUS_RELEASED = 11;
    public static final int JOB_STATUS_TERMINATED = 20;
    public static final int JOB_STATUS_PENDING_LICENSE = 21;

    // ICD section 2.6.3 - TASK_TYPE
    public static final int TASK_TYPE_SW_UPGRADE = 1;
    public static final int TASK_TYPE_CP_OVERRIDE = 3;
    public static final int TASK_TYPE_CP_UPLOAD = 4;
    public static final int TASK_TYPE_APPLY_TEMPLATE = 7;
    public static final int TASK_TYPE_PARAMETERS = 8;
    public static final int TASK_TYPE_SW_UPGRADE_CH = 10;
    public static final int TASK_TYPE_RECOVERY = 11;
    public static final int TASK_TYPE_RECOVERY_FP = 12;
    public static final int TASK_TYPE_SPECIAL_PARAMETERS = 13;
    public static final int TASK_TYPE_SPECIAL_CWG = 14;
    public static final int TASK_TYPE_APPLY_FLEETMAP_TEMPLATE = 15;
    public static final int TASK_TYPE_APPLY_NETWORK_TEMPLATE = 16;
    public static final int TASK_TYPE_SET_LANGUAGES = 17;
    public static final int TASK_TYPE_BACKUP_CP = 18;
    public static final int TASK_TYPE_APPLY_USER_DATA = 19;
    public static final int TASK_TYPE_APPLY_HOT_FIX = 20;
    public static final int TASK_TYPE_Convert_Model_Type = 21;
    public static final int TASK_TYPE_APPLY_PROFILE = 22;
    public static final int TASK_TYPE_APPLY_ENFORCE_PROFILE = 23;
    public static final int TASK_TYPE_FEATURES = 24;

    // ICD section 2.22.2 - POLICY_TYPE
    public static final int POLICY_TYPE_MS_MOBILITY_LOGGING = 1;
    public static final int POLICY_TYPE_PICTURE_COLLECTION = 2;
    public static final int POLICY_TYPE_PICTURE_PUSH = 3;
    
    public static final int PROXY_OFFLINE = 1;
    public static final int PROXY_ONLINE = 2;
    public static final int PROXY_DISCOVE = 3;

    public static final int TEMPL_NO_OP = 0;
    public static final int TEMPL_SET_NAME = 1;
    public static final int TEMPL_RESET_NAME = 2;

    public static HashMap<Integer, String> deviceStatusMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> jobStatusMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> proxyStatusMap = new HashMap<Integer, String>();
    public static HashMap<Integer, String> executedPolicyStatusMap = new HashMap<Integer, String>();
    public static List<Integer> TaskTypeMap = new ArrayList<Integer>();
    public static List<Integer> PolicyTypeMap = new ArrayList<Integer>();

    static
    {
        deviceStatusMap.put(DEVICE_NEW, "StatusConstants.device.new");
        deviceStatusMap.put(DEVICE_READY, "StatusConstants.device.ready");
        deviceStatusMap.put(DEVICE_PENDING, "StatusConstants.device.pending");
        deviceStatusMap.put(DEVICE_RUNNING, "StatusConstants.device.running");
        deviceStatusMap.put(DEVICE_FAILED, "StatusConstants.device.failed");
        deviceStatusMap.put(DEVICE_OFFLINE, "StatusConstants.device.offline");
        deviceStatusMap.put(DEVICE_CPMODIFIED, "StatusConstants.device.cpmodified");
        deviceStatusMap.put(DEVICE_WAITING, "StatusConstants.device.waiting");
        deviceStatusMap.put(DEVICE_EXPIRED, "StatusConstants.device.expired");
        deviceStatusMap.put(DEVICE_PRLMODIFIED, "StatusConstants.device.prlmodified");
        deviceStatusMap.put(DEVICE_PENDING_LICENSE, "StatusConstants.device.pending.license");

        jobStatusMap.put(JOB_STATUS_PENDING, "StatusConstants.job.pending");
        jobStatusMap.put(JOB_STATUS_RUNNING, "StatusConstants.job.running");
        jobStatusMap.put(JOB_STATUS_PERFORMED, "StatusConstants.job.performed");
        jobStatusMap.put(JOB_STATUS_PARTIAL, "StatusConstants.job.partial");
        jobStatusMap.put(JOB_STATUS_FAILED, "StatusConstants.job.failed");
        jobStatusMap.put(JOB_STATUS_REPLACED, "StatusConstants.job.replaced");
        jobStatusMap.put(JOB_STATUS_WAITING, "StatusConstants.job.waiting");
        jobStatusMap.put(JOB_STATUS_EXPIRED, "StatusConstants.job.expired");
        jobStatusMap.put(JOB_STATUS_CANCELED, "StatusConstants.job.canceled");
        jobStatusMap.put(JOB_STATUS_TERMINATED, "StatusConstants.job.terminated");
        jobStatusMap.put(JOB_STATUS_PENDING_LICENSE, "StatusConstants.job.pending.license");

        proxyStatusMap.put(PROXY_OFFLINE, "StatusConstants.proxy.offline");
        proxyStatusMap.put(PROXY_ONLINE, "StatusConstants.proxy.online");
        proxyStatusMap.put(PROXY_DISCOVE, "StatusConstants.proxy.discovered");

        executedPolicyStatusMap.put(POLICY_EXECUTION_STATUS_PERFORMED,
                "StatusConstants.job.performed");
        executedPolicyStatusMap.put(POLICY_EXECUTION_STATUS_FAILED, "StatusConstants.job.failed");
        
        TaskTypeMap.add(TASK_TYPE_SW_UPGRADE);
        TaskTypeMap.add(TASK_TYPE_CP_OVERRIDE);
        TaskTypeMap.add(TASK_TYPE_CP_UPLOAD);
        TaskTypeMap.add(TASK_TYPE_APPLY_TEMPLATE);
        TaskTypeMap.add(TASK_TYPE_PARAMETERS);
        TaskTypeMap.add(TASK_TYPE_FEATURES);
        TaskTypeMap.add(TASK_TYPE_SW_UPGRADE_CH);
        TaskTypeMap.add(TASK_TYPE_RECOVERY);
        TaskTypeMap.add(TASK_TYPE_RECOVERY_FP);
        TaskTypeMap.add(TASK_TYPE_SPECIAL_PARAMETERS);
        TaskTypeMap.add(TASK_TYPE_SPECIAL_CWG);
        TaskTypeMap.add(TASK_TYPE_APPLY_FLEETMAP_TEMPLATE);
        TaskTypeMap.add(TASK_TYPE_APPLY_NETWORK_TEMPLATE);
        TaskTypeMap.add(TASK_TYPE_SET_LANGUAGES);
        TaskTypeMap.add(TASK_TYPE_BACKUP_CP);
        TaskTypeMap.add(TASK_TYPE_APPLY_USER_DATA);
        TaskTypeMap.add(TASK_TYPE_APPLY_HOT_FIX);
        TaskTypeMap.add(TASK_TYPE_Convert_Model_Type);
        TaskTypeMap.add(TASK_TYPE_APPLY_PROFILE);
        TaskTypeMap.add(TASK_TYPE_APPLY_ENFORCE_PROFILE);
        
        PolicyTypeMap.add(POLICY_TYPE_MS_MOBILITY_LOGGING);
        PolicyTypeMap.add(POLICY_TYPE_PICTURE_COLLECTION);
        PolicyTypeMap.add(POLICY_TYPE_PICTURE_PUSH);
     
    }

}