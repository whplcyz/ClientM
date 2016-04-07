/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                         Copyright 2010 Motorola Inc.                         |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Mar/10  hgkj73                CCMPD01317830 iTM 3.0 Folder permission 
                                               handling   
29/Mar/10   hgkj73               CCMPD01325308 iTM 3.0 AP requirements handled
21/Sep/10  chad001               ccmpd01394081 Add policies manager  
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * container system permissions in iTM
 * 
 * @author hgkj73
 * 
 */
public class FolderPermissions extends ItmPermissions {

	private static final long serialVersionUID = 1602308410346873829L;

	/**
	 * Permissions
	 */
	private boolean viewFolderAndContents = false;
	private boolean assignPermissions = false;
	private boolean enforceNWtemplate = false;
	private boolean enforceFleetTemplate = false;
	private boolean enforceProfiles = false;

	/**
	 * Folder management
	 */
	private boolean manageFolders = false;

	/**
	 * Radio and template management
	 */

	private boolean createRadio = false;
	private boolean modifyRadio = false;
	private boolean moveRadio = false;
	private boolean createTemplateFromDevice = false;
	private boolean createTemplateFromFile = false;
	private boolean importTemplate = false;
	private boolean deleteRadios = false;
	private boolean deleteTemplates = false;
	private boolean assignAndUnassignPolicies = false;
	private boolean exportPolicyData = false;

	/**
	 * Programming permissions
	 * 
	 */
	private boolean getCodeplug = false;
	private boolean cancelJob = false;
	private boolean editCodeplugOfRadio = false;
	private boolean editCodeplugOfTemplate = false;
	private boolean restoreCodeplug = false;
	private boolean recoverRadio = false;
	private boolean programRadio = false;
	private boolean exportCodeplug = false;
	private boolean sendNotifications = false;
	private boolean programFirmware = false;
	private boolean programCodeplug = false;

	/**
	 * Authentication permissions
	 * 
	 */
	private boolean setPasswordPermission = false;
	private boolean revealPasswordPermission = false;
	private boolean limitedNumberOfTGPermission = false;
	
    /**
     * @return the setProgramFirmware
     */
    public boolean getProgramFirmware()
    {
        return programFirmware;
    }

    /**
     * @return the setProgramFirmware
     */
    public void setProgramFirmware(boolean programFirmware)
    {
        this.programFirmware = programFirmware;
    }
    
    /**
     * @return the setProgramFirmware
     */
    public boolean getProgramCodeplug()
    {
        return programCodeplug;
    }

    /**
     * @return the setProgramFirmware
     */
    public void setProgramCodeplug(boolean programCodeplug)
    {
        this.programCodeplug = programCodeplug;
    }
	

	/**
	 * @return the setPasswordPermission
	 */
	public boolean getSetPasswordPermission() {
		return setPasswordPermission;
	}

	/**
	 * @param setPasswordPermission
	 *            the setPasswordPermission to set
	 */
	public void setSetPasswordPermission(boolean setPasswordPermission) {
		this.setPasswordPermission = setPasswordPermission;
	}

	/**
	 * @return the setPasswordPermission
	 */
	public boolean getRevealPasswordPermission() {
		return revealPasswordPermission;
	}

	/**
	 * @param viewFolderAndContents
	 *            the viewFolderAndContents to set
	 */
	public void setRevealPasswordPermission(boolean revealPasswordPermission) {
		this.revealPasswordPermission = revealPasswordPermission;
	}

	/**
	 * @return the viewFolderAndContents
	 */
	public boolean getViewFolderAndContents() {
		return viewFolderAndContents;
	}

	/**
	 * @param viewFolderAndContents
	 *            the viewFolderAndContents to set
	 */
	public void setViewFolderAndContents(boolean viewFolderAndContents) {
		this.viewFolderAndContents = viewFolderAndContents;
	}

	/**
	 * @return the assignPermissions
	 */
	public boolean getAssignPermissions() {
		return assignPermissions;
	}

	/**
	 * @param assignPermissions
	 *            the assignPermissions to set
	 */
	public void setAssignPermissions(boolean assignPermissions) {
		this.assignPermissions = assignPermissions;
	}

	/**
	 * @return the enforceNWtemplate
	 */
	public boolean getEnforceNWtemplate() {
		return enforceNWtemplate;
	}

	/**
	 * @param enforceNWtemplate
	 *            the enforceNWtemplate to set
	 */
	public void setEnforceNWtemplate(boolean enforceNWtemplate) {
		this.enforceNWtemplate = enforceNWtemplate;
	}

	/**
	 * @return the enforceFleetTemplate
	 */
	public boolean getEnforceFleetTemplate() {
		return enforceFleetTemplate;
	}

	/**
	 * @param enforceFleetTemplate
	 *            the enforceFleetTemplate to set
	 */
	public void setEnforceFleetTemplate(boolean enforceFleetTemplate) {
		this.enforceFleetTemplate = enforceFleetTemplate;
	}

	/**
	 * @return the manageFolders
	 */
	public boolean getManageFolders() {
		return manageFolders;
	}

	/**
	 * @param manageFolders
	 *            the manageFolders to set
	 */
	public void setManageFolders(boolean manageFolders) {
		this.manageFolders = manageFolders;
	}

	/**
	 * @return the createRadio
	 */
	public boolean getCreateRadio() {
		return createRadio;
	}

	/**
	 * @param createRadio
	 *            the createRadio to set
	 */
	public void setCreateRadio(boolean createRadio) {
		this.createRadio = createRadio;
	}

	/**
	 * @return the modifyRadio
	 */
	public boolean getModifyRadio() {
		return modifyRadio;
	}

	/**
	 * @param modifyRadio
	 *            the modifyRadio to set
	 */
	public void setModifyRadio(boolean modifyRadio) {
		this.modifyRadio = modifyRadio;
	}

	/**
	 * @return the moveRadio
	 */
	public boolean getMoveRadio() {
		return moveRadio;
	}

	/**
	 * @param moveRadio
	 *            the moveRadio to set
	 */
	public void setMoveRadio(boolean moveRadio) {
		this.moveRadio = moveRadio;
	}

	/**
	 * @return the createTemplateFromDevice
	 */
	public boolean getCreateTemplateFromDevice() {
		return createTemplateFromDevice;
	}

	/**
	 * @param createTemplateFromDevice
	 *            the createTemplateFromDevice to set
	 */
	public void setCreateTemplateFromDevice(boolean createTemplateFromDevice) {
		this.createTemplateFromDevice = createTemplateFromDevice;
	}

	/**
	 * @return the createTemplateFromFile
	 */
	public boolean getCreateTemplateFromFile() {
		return createTemplateFromFile;
	}

	/**
	 * @param createTemplateFromFile
	 *            the createTemplateFromFile to set
	 */
	public void setCreateTemplateFromFile(boolean createTemplateFromFile) {
		this.createTemplateFromFile = createTemplateFromFile;
	}

	/**
	 * @return the importTemplate
	 */
	public boolean getImportTemplate() {
		return importTemplate;
	}

	/**
	 * @param importTemplate
	 *            the importTemplate to set
	 */
	public void setImportTemplate(boolean importTemplate) {
		this.importTemplate = importTemplate;
	}

	/**
	 * @return the deleteRadios
	 */
	public boolean getDeleteRadios() {
		return deleteRadios;
	}

	/**
	 * @param deleteRadios
	 *            the deleteRadios to set
	 */
	public void setDeleteRadios(boolean deleteRadios) {
		this.deleteRadios = deleteRadios;
	}

	/**
	 * @return the deleteTemplates
	 */
	public boolean getDeleteTemplates() {
		return deleteTemplates;
	}

	/**
	 * @param deleteTemplates
	 *            the deleteTemplates to set
	 */
	public void setDeleteTemplates(boolean deleteTemplates) {
		this.deleteTemplates = deleteTemplates;
	}

	/**
	 * @return the getCodeplug
	 */
	public boolean getGetCodeplug() {
		return getCodeplug;
	}

	/**
	 * @param getCodeplug
	 *            the getCodeplug to set
	 */
	public void setGetCodeplug(boolean getCodeplug) {
		this.getCodeplug = getCodeplug;
	}

	/**
	 * @return the cancelJob
	 */
	public boolean getCancelJob() {
		return cancelJob;
	}

	/**
	 * @param cancelJob
	 *            the cancelJob to set
	 */
	public void setCancelJob(boolean cancelJob) {
		this.cancelJob = cancelJob;
	}

	/**
	 * @return the editCodeplugOfRadio
	 */
	public boolean getEditCodeplugOfRadio() {
		return editCodeplugOfRadio;
	}

	/**
	 * @param editCodeplugOfRadio
	 *            the editCodeplugOfRadio to set
	 */
	public void setEditCodeplugOfRadio(boolean editCodeplugOfRadio) {
		this.editCodeplugOfRadio = editCodeplugOfRadio;
	}

	/**
	 * @return the editCodeplugOfTemplate
	 */
	public boolean getEditCodeplugOfTemplate() {
		return editCodeplugOfTemplate;
	}

	/**
	 * @param editCodeplugOfTemplate
	 *            the editCodeplugOfTemplate to set
	 */
	public void setEditCodeplugOfTemplate(boolean editCodeplugOfTemplate) {
		this.editCodeplugOfTemplate = editCodeplugOfTemplate;
	}

	/**
	 * @return the restoreCodeplug
	 */
	public boolean getRestoreCodeplug() {
		return restoreCodeplug;
	}

	/**
	 * @param restoreCodeplug
	 *            the restoreCodeplug to set
	 */
	public void setRestoreCodeplug(boolean restoreCodeplug) {
		this.restoreCodeplug = restoreCodeplug;
	}

	/**
	 * @return the recoverRadio
	 */
	public boolean getRecoverRadio() {
		return recoverRadio;
	}

	/**
	 * @param recoverRadio
	 *            the recoverRadio to set
	 */
	public void setRecoverRadio(boolean recoverRadio) {
		this.recoverRadio = recoverRadio;
	}

	/**
	 * @return the programRadio
	 */
	public boolean getProgramRadio() {
		return programRadio;
	}

	/**
	 * @param programRadio
	 *            the programRadio to set
	 */
	public void setProgramRadio(boolean programRadio) {
		this.programRadio = programRadio;
	}

	/**
	 * @return the exportCodeplug
	 */
	public boolean getExportCodeplug() {
		return exportCodeplug;
	}

	/**
	 * @param exportCodeplug
	 *            the exportCodeplug to set
	 */
	public void setExportCodeplug(boolean exportCodeplug) {
		this.exportCodeplug = exportCodeplug;
	}

	/**
	 * @return the sendNotifications
	 */
	public boolean getSendNotifications() {
		return sendNotifications;
	}

	/**
	 * @param sendNotifications
	 *            the sendNotifications to set
	 */
	public void setSendNotifications(boolean sendNotifications) {
		this.sendNotifications = sendNotifications;
	}

	/**
	 * @return the assignAndUnassignPolicies
	 */
	public boolean getAssignAndUnassignPolicies() {
		return assignAndUnassignPolicies;
	}

	/**
	 * @param assignAndUnassignPolicies
	 *            the assignAndUnassignPolicies to set
	 */
	public void setAssignAndUnassignPolicies(boolean assignAndUnassignPolicies) {
		this.assignAndUnassignPolicies = assignAndUnassignPolicies;
	}

	/**
	 * @return the assignAndUnassignPolicies
	 */
	public boolean getExportPolicyData() {
		return exportPolicyData;
	}

	/**
	 * @param exportPolicyData
	 *            the exportPolicyData to set
	 */
	public void setExportPolicyData(boolean exportPolicyData) {
		this.exportPolicyData = exportPolicyData;
	}
	
	/**
	 * @return the enforceProfile
	 */
	public boolean getEnforceProfiles() {
		return enforceProfiles;
	}

	/**
	 * @param exportPolicyData
	 *            the exportPolicyData to set
	 */
	public void setEnforceProfiles(boolean enforceProfiles) {
		this.enforceProfiles = enforceProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (assignPermissions ? 1231 : 1237);
		result = prime * result + (cancelJob ? 1231 : 1237);
		result = prime * result + (createRadio ? 1231 : 1237);
		result = prime * result + (createTemplateFromDevice ? 1231 : 1237);
		result = prime * result + (createTemplateFromFile ? 1231 : 1237);
		result = prime * result + (deleteRadios ? 1231 : 1237);
		result = prime * result + (deleteTemplates ? 1231 : 1237);
		result = prime * result + (editCodeplugOfRadio ? 1231 : 1237);
		result = prime * result + (editCodeplugOfTemplate ? 1231 : 1237);
		result = prime * result + (enforceFleetTemplate ? 1231 : 1237);
		result = prime * result + (enforceNWtemplate ? 1231 : 1237);
		result = prime * result + (exportCodeplug ? 1231 : 1237);
		result = prime * result + (getCodeplug ? 1231 : 1237);
		result = prime * result + (importTemplate ? 1231 : 1237);
		result = prime * result + (manageFolders ? 1231 : 1237);
		result = prime * result + (modifyRadio ? 1231 : 1237);
		result = prime * result + (moveRadio ? 1231 : 1237);
		result = prime * result + (programRadio ? 1231 : 1237);
		result = prime * result + (recoverRadio ? 1231 : 1237);
		result = prime * result + (restoreCodeplug ? 1231 : 1237);
		result = prime * result + (sendNotifications ? 1231 : 1237);
		result = prime * result + (viewFolderAndContents ? 1231 : 1237);
		result = prime * result + (assignAndUnassignPolicies ? 1231 : 1237);
		result = prime * result + (exportPolicyData ? 1231 : 1237);
		result = prime * result + (setPasswordPermission ? 1231 : 1237);
		result = prime * result + (revealPasswordPermission ? 1231 : 1237);
		result = prime * result + (enforceProfiles ? 1231 : 1237);
		result = prime * result + (programFirmware ? 1231 : 1237);
        result = prime * result + (programCodeplug ? 1231 : 1237);
        result = prime * result + (limitedNumberOfTGPermission ? 1231 : 1237);

		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
	    if(obj == null)
	        return false;
	    
	    if (this == obj)
			return true;
	    
		if (getClass() != obj.getClass())
			return false;
		
		final FolderPermissions other = (FolderPermissions) obj;
		if (assignPermissions != other.assignPermissions)
			return false;
		if (cancelJob != other.cancelJob)
			return false;
		if (createRadio != other.createRadio)
			return false;
		if (createTemplateFromDevice != other.createTemplateFromDevice)
			return false;
		if (createTemplateFromFile != other.createTemplateFromFile)
			return false;
		if (deleteRadios != other.deleteRadios)
			return false;
		if (deleteTemplates != other.deleteTemplates)
			return false;
		if (editCodeplugOfRadio != other.editCodeplugOfRadio)
			return false;
		if (editCodeplugOfTemplate != other.editCodeplugOfTemplate)
			return false;
		if (enforceFleetTemplate != other.enforceFleetTemplate)
			return false;
		if (enforceNWtemplate != other.enforceNWtemplate)
			return false;
		if (exportCodeplug != other.exportCodeplug)
			return false;
		if (getCodeplug != other.getCodeplug)
			return false;
		if (importTemplate != other.importTemplate)
			return false;
		if (manageFolders != other.manageFolders)
			return false;
		if (modifyRadio != other.modifyRadio)
			return false;
		if (moveRadio != other.moveRadio)
			return false;
		if (programRadio != other.programRadio)
			return false;
		if (recoverRadio != other.recoverRadio)
			return false;
		if (restoreCodeplug != other.restoreCodeplug)
			return false;
		if (sendNotifications != other.sendNotifications)
			return false;
		if (viewFolderAndContents != other.viewFolderAndContents)
			return false;
		if (assignAndUnassignPolicies != other.assignAndUnassignPolicies)
			return false;
		if (exportPolicyData != other.exportPolicyData)
			return false;
		if (setPasswordPermission != other.setPasswordPermission)
			return false;
		if (revealPasswordPermission != other.revealPasswordPermission)
			return false;
		if(enforceProfiles != other.enforceProfiles)
		{
			return false;
		}
		if (programFirmware != other.programFirmware)
            return false;
        if(programCodeplug != other.programCodeplug)
        {
            return false;
        }
		if (limitedNumberOfTGPermission != other.limitedNumberOfTGPermission) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {

		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE)
				.append("assignPermissions", assignPermissions)
				.append("cancelJob", cancelJob)
				.append("createRadio", createRadio)
				.append("createTemplateFromDevice", createTemplateFromDevice)
				.append("createTemplateFromFile", createTemplateFromFile)
				.append("deleteRadios", deleteRadios)
				.append("deleteTemplates", deleteTemplates)
				.append("editCodeplugOfRadio", editCodeplugOfRadio)
				.append("editCodeplugOfTemplate", editCodeplugOfTemplate)
				.append("enforceFleetTemplate", enforceFleetTemplate)
				.append("enforceNWtemplate", enforceNWtemplate)
				.append("exportCodeplug", exportCodeplug)
				.append("getCodeplug", getCodeplug)
				.append("importTemplate", importTemplate)
				.append("manageFolders", manageFolders)
				.append("modifyRadio", modifyRadio)
				.append("moveRadio", moveRadio)
				.append("programRadio", programRadio)
				.append("recoverRadio", recoverRadio)
				.append("restoreCodeplug", restoreCodeplug)
				.append("sendNotifications", sendNotifications)
				.append("viewFolderAndContents", viewFolderAndContents)
				.append("assignAndUnassignPolicies", assignAndUnassignPolicies)
				.append("exportPolicyData", exportPolicyData)
				.append("setPasswordPermission", setPasswordPermission)
				.append("revealPasswordPermission", revealPasswordPermission)
		        .append("enforceProfiles", enforceProfiles)
        		.append("programFirmware", programFirmware)
                .append("programCodeplug", programCodeplug)
		        .append("limitedNumberOfTGPermission", limitedNumberOfTGPermission);
		return sb.toString();
	}

	@Override
	public boolean isEmpty() {
		if (assignPermissions || cancelJob || createRadio
				|| createTemplateFromDevice || createTemplateFromFile
				|| deleteRadios || deleteTemplates || editCodeplugOfRadio
				|| editCodeplugOfTemplate || enforceFleetTemplate
				|| enforceNWtemplate || exportCodeplug || getCodeplug
				|| importTemplate || manageFolders || modifyRadio || moveRadio
				|| programRadio || recoverRadio || restoreCodeplug
				|| sendNotifications || viewFolderAndContents
				|| assignAndUnassignPolicies || exportPolicyData
				|| setPasswordPermission || revealPasswordPermission
				|| enforceProfiles || limitedNumberOfTGPermission) {
			return false;
		}
		return true;
	}

	/**
	 * @return the limitedNumberOfTGPermission
	 */
	public boolean getLimitedNumberOfTGPermission() {
		return limitedNumberOfTGPermission;
	}

	/**
	 * @param limitedNumberOfTGPermission the limitedNumberOfTGPermission to set
	 */
	public void setLimitedNumberOfTGPermission(boolean limitedNumberOfTGPermission) {
		this.limitedNumberOfTGPermission = limitedNumberOfTGPermission;
	}

}
