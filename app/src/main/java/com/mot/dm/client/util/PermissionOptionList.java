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
20/09/10   chad001               ccmpd01393090 4.0 licensing feature  
21/Sep/10  chad001               ccmpd01394081 Add policies manager  
------------------------------------------------------------------------------*/

package com.mot.dm.client.util;

import java.util.ArrayList;
import java.util.List;

import com.mot.dm.client.dmImpl.gui.DmsContext;

/*
 * class for keep tracking list items and the method related to the setter 
 * and getter of the user/group class
 */
// CHECKSTYLE:OFF
public final class PermissionOptionList
{
    /*
     * this is the list items for folder permissions. Those text must be
     * identical to the members of class FolderPermissions. The Setter and
     * Getter method name is calculated based on these text. The GUI text
     * mapping is also calculated through the strings!
     */
    public static final String[] FolderPermissionMember =
          { "viewFolderAndContents", "assignPermissions","enforceProfiles",
            "manageFolders", "createRadio", "modifyRadio", "moveRadio", "createTemplateFromDevice",
            "createTemplateFromFile", "importTemplate", "deleteRadios", "deleteTemplates",
            "getCodeplug", "cancelJob", "assignAndUnassignPolicies", "editCodeplugOfRadio",
            "editCodeplugOfTemplate", "restoreCodeplug", "recoverRadio", "programFirmware","programCodeplug",
            "exportCodeplug", "sendNotifications", "exportPolicyData","setPasswordPermission","revealPasswordPermission","limitedNumberOfTGPermission" };
    
    public static final String[] RootFolderPermissionMember = {"viewFolderAndContents", "assignPermissions", "manageFolders"};

    public static final String ObsoletedFolderPermissions[] = {"enforceNWtemplate", "enforceFleetTemplate"};
    
    
    /*
     * this is the list items for system permissions. Those text must be
     * identical to the members of class SystemPermissions. The Setter and
     * Getter method name is calculated based on these text.
     */
    public static final String[] OriginalSystemPermissionMember =
          { "logonToClient", "manageGroupsAndUsers", "manageProxies", "manageSoftware",
            "importPermission", "logonToProxy", "manageOfflineJobs", "configureProxy",
            "allowStatusView", "preventClearAccess", "manageLicenses", "managePolicies" };
    
    public static final String[] SystemPermissionMember =
            { "logonToClient", "manageGroupsAndUsers", "manageLicenses", "allowStatusView", "preventClearAccess" };
    
    public static final String[] AgencyPermissionMember =
    {
            "logonToClient", "manageGroupsAndUsers", "manageProxies", "manageSoftware",
            "importPermission", "logonToProxy", "manageOfflineJobs", "configureProxy",
            "manageLicenses", "managePolicies"
    };
    
    //Extended Agency Partitioning
    public static boolean eapOn = true;

    public static String[] folderPermissionText;
    public static String[] folderPermissionSetMethod;
    public static String[] folderPermissionGetMethod;
    
    public static String[] rootFolderPermissionText;
    public static String[] rootFolderPermissionSetMethod;
    public static String[] rootFolderPermissionGetMethod;
    
    public static String[] systemPermissionText;
    public static String[] systemPermissionSetMethod;
    public static String[] systemPermissionGetMethod;

    static
    {
        int size = FolderPermissionMember.length;
        folderPermissionText = new String[size];
        folderPermissionSetMethod = new String[size];
        folderPermissionGetMethod = new String[size];
        for (int ii = 0; ii < size; ii++)
        {
        	folderPermissionText[ii] = DmsContext.getString("FolderPropertyComposite." + FolderPermissionMember[ii] + "");
            String text = FolderPermissionMember[ii];
            String upCase = text.toUpperCase();
            text = upCase.substring(0, 1) + text.substring(1);
            folderPermissionSetMethod[ii] = "set" + text;
            folderPermissionGetMethod[ii] = "get" + text;
        }     
        
        size = RootFolderPermissionMember.length;
        rootFolderPermissionText = new String[size];
        rootFolderPermissionSetMethod = new String[size];
        rootFolderPermissionGetMethod = new String[size];
        for (int ii = 0; ii < size; ii++)
        {
            rootFolderPermissionText[ii] = DmsContext.getString("FolderPropertyComposite."+ RootFolderPermissionMember[ii] + "");
            String text = RootFolderPermissionMember[ii];
            String upCase = text.toUpperCase();
            text = upCase.substring(0, 1) + text.substring(1);
            rootFolderPermissionSetMethod[ii] = "set" + text;
            rootFolderPermissionGetMethod[ii] = "get" + text;
        }
    }
       


    /*
     * Get the folder option list
     * 
     * @return List<CustomOptionItem>
     */
    public static List<CustomOptionItem> getDefaultFolderOptionList()
    {
        List<CustomOptionItem> list = new ArrayList<CustomOptionItem>();
        for (int ii = 0; ii < folderPermissionText.length; ii++)
        {
            CustomOptionItem coi = new CustomOptionItem(folderPermissionText[ii],
                    folderPermissionSetMethod[ii], folderPermissionGetMethod[ii]);
            list.add(coi);
        }
        return list;
    }
    
    /*
     * Get the root folder option list
     * 
     * @return List<CustomOptionItem>
     */
    public static List<CustomOptionItem> getRootFolderOptionList()
    {
        List<CustomOptionItem> list = new ArrayList<CustomOptionItem>();

        for (int ii = 0; ii < rootFolderPermissionText.length; ii++)
        {
            CustomOptionItem coi =
                    new CustomOptionItem(rootFolderPermissionText[ii],
                            rootFolderPermissionSetMethod[ii], rootFolderPermissionGetMethod[ii]);
            list.add(coi);
        }

        return list;
    }
    
    /*
     * Get the system option list
     * 
     * @return List<CustomOptionItem>
     */
    public static List<CustomOptionItem> getSystemOptionList()
    {
        List<CustomOptionItem> list = new ArrayList<CustomOptionItem>();
        for (int ii = 0; ii < systemPermissionText.length; ii++)
        {
        	CustomOptionItem coi = new CustomOptionItem(systemPermissionText[ii],
                    systemPermissionSetMethod[ii], systemPermissionGetMethod[ii]);
            list.add(coi);
        }
        return list;
    }
}
//CHECKSTYLE:ON