/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2007-2012 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  Jeff Lu

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
22/02/08   cnij001               CCMPD00968807 Add new template
01/Apr/08  Tommy Thomadsen       CCMPD01013871 Cleanup database.
15/09/08   ckfm01                CCMPD01092340 Removing codeplugVersion table in DB.
                                               & Converting CPversion to Integer.
12/Sep/08  Sigurdur Jonsson      CCMPD01090964 Add user name for code-plug creator.
05/Jan/09  Hai Dong              CCMPD01145535 Take CM5000 feature.  
13/Apr/09   chad001              CCMPD01193805 recover radio
20/Apr/09 Kim Mortesnen          CCMPD01198547 Change Extention of CM5000 Codeplug 
                                               files from tps to tpx.   
05/Jul/09  Hai Dong              ccmpd01235320 Avoid deadlock by combining Codeplug
                                               and Flashpack operation into one 
                                               transaction. Assign codeplug uuid
                                               operation is skipped in the method
                                               setFlashPacks() as the codeplug uuid 
                                               hasn't been assigned during adding
                                               codeplug together with flashpacks.   
10/Nov/09  Kim Mortensen         CCMPD01284501 Moving Codeplug_Version from 
                                               Device to Codeplug.                                               
13/Jan/10 Jens Hansen            CCMPD01288783 Changed repository version to
                                               type Integer 
07/06/10   chad001               CCMPD01357654 Add language set    
30/06/10   Hai Dong              CCMPD01366593 combine text keys   
03/08/10   Hai Dong              CCMPD01377415 Show default language      
24/Aug/10  cjh102                CCMPD01385457 Updates for CPE codeplugs                                                                                        
24/Mar/11  Michael Leismann      CCMPD01483125 iTM 5.0: Code clean-up
02/May/11  Hai Dong              CCMPD01495824 iTM 5.0 Change language setting 
18/May/11   chad001              CCMPD01508612 correct language text  
10/Jun/11  Michael Leismann      CCMPD01516767 iTM 5.0: Added method to tell
                                               if a file is a codeplug file
01/Aug/11  Kim Mortensen         CCMPD01540391 Remove tokenizing on " "
03/Sep/12  a23126                CCMPD01692862 iTM6.0- Add flash pack support info in meta-data 
                                               - RadioModelInfo.xml
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Version 1.00
 * @author Jeff Lu
 */
public class Codeplug extends BaseObject
{
    public static final String CODEPLUG_FILE_EXTENSION = ".cpd";
    public static final String CPE_FILE_EXTENSION = ".cpe";
    public static final String PROFILE_FILE_EXTENSION = ".prl";

    private static final long serialVersionUID = 5007877007705295004L;

    private String userName;

    private String codeplugUuid;

    private Integer codeplugRepositoryVersion;

    private String codeplugFullPath;

    private String softwareVersion;
    
    //selected groups for profile
    //its format is "124;115;116"
    private String selectedGroups;

    // Note: Not available in the database initially.
    private String codeplugVersion;

    private Device device;

    private Timestamp createdDate;

    private List<FlashPack> FlashPacks;

    private List<String> flashPackLanguages = new ArrayList<String>();

    public Codeplug()
    {
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param inUserName
     *            the name of the user who adds this codeplugVersion
     */
    public void setUserName(String inUserName)
    {
        this.userName = inUserName;
    }

    /**
     * @return the codeplugId
     */
    public String getCodeplugUuid()
    {
        return codeplugUuid;
    }

    /**
     * @param codeplugId
     *            the codeplugId to set
     */
    public void setCodeplugUuid(String codeplugId)
    {
        this.codeplugUuid = codeplugId;
    }

    
    public String getSelectedGroups()
    {
        return this.selectedGroups;
    }

    public void setSelectedGroups(String selectedGrps)
    {
        this.selectedGroups = selectedGrps;
    }

    /**
     * @return the codeplugVersion
     */
    public Integer getCodeplugRepositoryVersion()
    {
        return codeplugRepositoryVersion;
    }

    /**
     * @param codeplugVersion
     *            the codeplugVersion to set
     */
    public void setCodeplugRepositoryVersion(Integer codeplugVersion)
    {
        this.codeplugRepositoryVersion = codeplugVersion;
    }

    /**
     * @return the softwareVersion
     */
    public String getSoftwareVersion()
    {
        return softwareVersion;
    }

    /**
     * @param softwareVersion
     *            the softwareVersion to set
     */
    public void setSoftwareVersion(String softwareVersion)
    {
        this.softwareVersion = softwareVersion;
    }

    /**
     * @return the fullPath
     */
    public String getCodeplugFullPath()
    {
        return codeplugFullPath;
    }

    /**
     * @param fullPath
     *            the fullPath to set
     */
    public void setCodeplugFullPath(String fullPath)
    {
        this.codeplugFullPath = fullPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(codeplugFullPath).append(device)
                .append(codeplugRepositoryVersion).hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Codeplug == false)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        Codeplug rhs = (Codeplug) obj;
        return new EqualsBuilder().append(codeplugFullPath, rhs.codeplugFullPath)
                .append(device, rhs.device)
                .append(getFlashPackLanguages(), rhs.getFlashPackLanguages())
                .append(device, rhs.device).isEquals();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("userName", this.userName).append("codeplugId", this.codeplugUuid)
                .append("fullPath", this.codeplugFullPath).append("device", this.device)
                .append("languages", this.getFlashPackLanguages())
                .append("codeplugVersion", this.codeplugRepositoryVersion);
        return sb.toString();
    }

    /**
     * @return the device
     */
    public Device getDevice()
    {
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(Device device)
    {
        this.device = device;
    }

    /**
     * Gets the Timestamp on device
     * 
     * @return Timestamp
     */
    public Timestamp getCreatedDate()
    {
        return createdDate;
    }

    /**
     * Sets the created date and time stamp on the device
     * 
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate)
    {
        this.createdDate = createdDate;
    }

    /**
     * Method to determine if the codeplug is of type CPD
     * 
     * @return boolean.
     */
    public boolean isCpdCodeplug()
    {
        return getCodeplugFullPath().toLowerCase().endsWith(CODEPLUG_FILE_EXTENSION);
    }

    /**
     * Method to determine if the codeplug is of type CPE
     * 
     * @return boolean.
     */
    public boolean isCpeCodeplug()
    {
        return getCodeplugFullPath().toLowerCase().endsWith(CPE_FILE_EXTENSION);
    }

    /**
     * Get the flashpacks
     * 
     * @return List<FlashPack>
     */
    public List<FlashPack> getFlashPacks()
    {
        return FlashPacks;
    }

    /**
     * Set the flashpacks
     * 
     * @param flashPacks
     *            List<FlashPack>
     */
    public void setFlashPacks(List<FlashPack> flashPacks)
    {
        FlashPacks = flashPacks;
    }

    /**
     * Gets the Codeplug structure version from the codeplug.
     * 
     * @return Codeplug structure version
     */
    public String getCodeplugVersion()
    {
        return codeplugVersion;
    }

    /**
     * Sets the codeplug structure version on the codeplug.
     * 
     * @param codeplugVersion
     */
    public void setCodeplugVersion(String codeplugVersion)
    {
        this.codeplugVersion = codeplugVersion;
    }

    /**
     * 
     * @return List
     */
    public List<String> getFlashPackLanguages()
    {
        flashPackLanguages = new ArrayList<String>();

        List<FlashPack> flashes = getFlashPacks();
        if (flashes != null && !flashes.isEmpty())
        {
            for (FlashPack fp : flashes)
            {
                String key = fp.getFlashPackKey();
                /*
                 * The key has format like:
                 * "packtype, packID, pack name, image name, platform, release number, revision, sub-revision, platform name"
                 * Languages can be fetched by filtering the flash packs for
                 * packType=1 ("1,") and fetching the packName information
                 * (token #3). PackName in this case would be Language name.
                 */

                if (key.startsWith("1,"))
                {
                    String [] token = key.split(",");
                    if (token != null && token.length >= 3)
                    {
                        String language = token[2].trim();
                        if (!flashPackLanguages.contains(language))
                            flashPackLanguages.add(language);
                    }
                }
            }
            Collections.sort(flashPackLanguages);
        }
        return flashPackLanguages;
    }

    /**
     * Checks - based on the extension - if the file is a codeplug file
     * 
     * @param fileName
     * @return
     */
    public static boolean isCodeplugFile(String fileName)
    {
        return fileName.endsWith(Codeplug.CPE_FILE_EXTENSION)
                || fileName.toLowerCase().endsWith(Codeplug.CODEPLUG_FILE_EXTENSION)
                || fileName.toLowerCase().endsWith(Codeplug.PROFILE_FILE_EXTENSION);
    }

}
