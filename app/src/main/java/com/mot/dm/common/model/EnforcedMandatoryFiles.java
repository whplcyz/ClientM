/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2009-2011 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM    
CODED     BY:   hgkj73

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
26/Nov/09  hgkj73                CCMPD01288783 initial version
19/01/10   hgkj73                CCMPD01304268 maintains a unique list
18/Mar/10  hgkj73                CCMPD01317830 iTM 3.0 Enforced Template 
                                               requirements     
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EnforcedMandatoryFiles extends BaseObject
{
    /**
     * Mandatory files priority settings
     */
    public static final int NETWORK_TEMPLATE_PRIORITY = 1;

    public static final int FLEET_TEMPLATE_PRIORITY = 2;
    
    public static final int PROFILE_PRIORITY = 3;

    private static final long serialVersionUID = 82765203748623L;

    private String agencyUuid;

    private List<MandatoryFile> mandatoryFiles;

    public EnforcedMandatoryFiles()
    {
        mandatoryFiles = new ArrayList<MandatoryFile>();
    }

    /**
     * set MandatoryTemplates - This is only for hibernate not to use by
     * client/proxy
     * 
     * @param mandatoryFiles
     */
    public void setMandatoryFiles(List<MandatoryFile> mandatoryFiles)
    {
        for (MandatoryFile mandatoryTemplate : mandatoryFiles)
        {
            if (mandatoryTemplate != null)
            {
                this.mandatoryFiles.add(mandatoryTemplate);
            }
        }
    }

    public List<MandatoryFile> getMandatoryFiles()
    {
        List<MandatoryFile> files = new ArrayList<MandatoryFile>();
        files.addAll(mandatoryFiles);
        return files;
    }

    /**
     * setAgencyUuid
     * 
     * @param agencyUuid
     */
    public void setAgencyUuid(String agencyUuid)
    {
        this.agencyUuid = agencyUuid;
    }

    public String getAgencyUuid()
    {
        return agencyUuid;
    }

    /**
     * getMandatoryNetworkTemplates - returns list of mandatory network
     * templates configured for the agency
     * 
     * @return List<MandatoryTemplate> - list of mandatoryNetworktemplates
     */
    public List<MandatoryFile> getMandatoryProfiles()
    {
        List<MandatoryFile> widthFirstOrder = getMandatoryFilesByPriority(PROFILE_PRIORITY);
        List<MandatoryFile> depthFirstProfiles = new ArrayList<MandatoryFile>();
        for(int i = widthFirstOrder.size() - 1; i >= 0; i--)
        {
        	depthFirstProfiles.add(widthFirstOrder.get(i));
        }
        
        return depthFirstProfiles;
    }
    
    /**
     * getMandatoryNetworkTemplates - returns list of mandatory network
     * templates configured for the agency
     * 
     * @return List<MandatoryTemplate> - list of mandatoryNetworktemplates
     */
    public List<MandatoryFile> getMandatoryNetworkTemplates()
    {
        return getMandatoryFilesByPriority(NETWORK_TEMPLATE_PRIORITY);
    }

    /**
     * getMandatoryNetworkTemplates - returns list of mandatory fleet templates
     * configured for the agency
     * 
     * @return List<MandatoryTemplate> - list of mandatoryFleetTmplates
     */
    public List<MandatoryFile> getMandatoryFleetTemplates()
    {
        return getMandatoryFilesByPriority(FLEET_TEMPLATE_PRIORITY);
    }

    /**
     * adds the passed mandatory template to the agency network templates
     * 
     * @param MandatoryFile
     *            - Need to set template field using set method
     */
    public void addMandatoryNetworkTemplate(MandatoryFile networkTemplate)
    {
        addMandatoryFile(networkTemplate, NETWORK_TEMPLATE_PRIORITY);
    }

    /**
     * adds the passed mandatory template to the agency network templates
     * 
     * @param MandatoryFile
     *            - Need to set template field using set method
     */
    public void addMandatoryProfile(MandatoryFile profile)
    {
        addMandatoryFile(profile, PROFILE_PRIORITY);
    }
    
    
    /**
     * adds the passed mandatory Template to the agency fleet templates
     * 
     * @param MandatoryFile
     *            - Need to set template field using set method
     */
    public void addMandatoryFleetTemplate(MandatoryFile fleetTemplate)
    {
        addMandatoryFile(fleetTemplate, FLEET_TEMPLATE_PRIORITY);
    }

    /**
     * deletes the passed mandatory Template from the agency network templates
     * 
     * @param MandatoryFile
     *            - Must it be the one retried from the getmethod
     */
    public void deleteMandatoryNetworkTemplates(MandatoryFile networkTemplate)
    {
        mandatoryFiles.remove(networkTemplate);
    }

    /**
     * deletes the passed mandatory Template from the agency fleet templates
     * 
     * @param MandatoryFile
     *            - Must it be the one retried from the getmethod
     */
    public void deleteMandatoryFleetTemplates(MandatoryFile fleetTemplate)
    {
        mandatoryFiles.remove(fleetTemplate);
    }
    
    
    /**
     * deletes the passed mandatory Template from the agency fleet templates
     * 
     * @param MandatoryFile
     *            - Must it be the one retried from the getmethod
     */
    public void deleteMandatoryProfiles(MandatoryFile enforceProfile)
    {
        mandatoryFiles.remove(enforceProfile);
    }
    


    private List<MandatoryFile> getMandatoryFilesByPriority(int priority)
    {
        List<MandatoryFile> result = new ArrayList<MandatoryFile>();
        for (MandatoryFile mandatoryTemplate : mandatoryFiles)
        {
            if (priority == mandatoryTemplate.getPriority())
            {
                result.add(mandatoryTemplate);
            }
        }
        return result;
    }

    private void addMandatoryFile(MandatoryFile mandatoryFile, int filePriority)
    {
        if (null == mandatoryFile)
        {
            return;
        }
        mandatoryFile.setAgencyUuid(agencyUuid);
        mandatoryFile.setPriority(filePriority);
        mandatoryFiles.add(mandatoryFile);

    }

    /**
     * Used to update the mandatory template with a new codeplug version
     * 
     * @param file
     * @param cp
     * @return true / if mandatory template found and updated false / if
     *         mandatory template not found
     */
    public boolean updateMandatoryFileCPversion(MandatoryFile file, Codeplug cp)
    {
        Iterator<MandatoryFile> iterator = mandatoryFiles.iterator();
        while (iterator.hasNext())
        {
            MandatoryFile mandatoryFile = iterator.next();
            if (mandatoryFile.equals(file))
            {
                mandatoryFile.setCodeplug(cp);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if ((this == o))
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof EnforcedMandatoryFiles))
            return false;
        EnforcedMandatoryFiles rhs = (EnforcedMandatoryFiles) o;
        return new EqualsBuilder().append(agencyUuid, rhs.getAgencyUuid())
                .append(mandatoryFiles, rhs.mandatoryFiles).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(agencyUuid).append(mandatoryFiles).hashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
                "agencyUuid", this.agencyUuid)
                .append("mandatoryTemplates", this.mandatoryFiles);
        return sb.toString();
    }

}
