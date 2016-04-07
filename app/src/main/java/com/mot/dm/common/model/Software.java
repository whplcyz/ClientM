/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                  Copyright 2007-2011 Motorola Solutions Inc.                 |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  thmt001 06/Feb/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
06/Feb/08  thmt001        ccmpd00977875 Load SW modifications
12/Jan/09  ckfm01         CCMPD01146563 Adding new file extentions.
14/Jan/09  Michael Leismann      CCMPD01146532 Updated to be able to handle the
                                               CM5000 device type
                                               Add version variable and updated
                                               related get method
21/Jan/09  Kim Mortensen         CCMPD01146532 New method for getting software 
                                               version from filename.
23/Feb/09  Michael Leismann      CCMPD01170040 Added encryption and release
                                               strings and methods
                                               Removed language string as it is
                                               not being used
 17/Mar/09 Jens Hansen           CCMPD01173870 Changes "_" to "." in CM5000 
                                               software version                                                   
20/Apr/09 Kim Mortesnen          CCMPD01198547 Change CTRL head software file 
                                               extention.                                                   
10/Jun/09  Sigurdur Jonsson      CCMPD01211479 Fix issue: Radio type is not validated
                                               when importing upgrade jobs.
07/Jul/09  Michael Leismann      CCMPD01234655 The file format of Motorola
                                               radios has been modified
                                               Regular expressions will be used
10/sep/09  Hai Dong              CCMPD01259651 New device type feature    
29/Sep/09  Jens Hansen           CCMPD01269158 Changed parsing of SW string
01/02/10  Hai Dong               CCMPD01309092 unnecessary log message in client 
                                               error log          
05/08/10   Hai Dong              ccmpd01378024 Add support software table.                                                                                 
26/May/11  Michael Leismann      CCMPD01505987 iTM 5.0: ICD changes
10/Jun/11  Michael Leismann      CCMPD01516767 iTM 5.0: New release package
                                               structure
10/Jun/11  cjh102                CCMPD01518983 Added reference to release package
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

/**
 * @Version 1.0 2007 April 15
 * @author w23021(Jason)
 */

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.mot.dm.common.util.CommonConstants;

public class Software extends BaseObject
{

    /***************************************************************************
     * constants part *
     ***************************************************************************/
    private static final long serialVersionUID = 5633344538392965097L;

    public static final String SOFTWARE_FILE_EXTENSION = ".Z19";

    public static final int BUILD_TYPE_FIELD = 0;
    public static final int BUILD_ID_FIELD = 1;
    public static final int ENC_MODE_FIELD = 2;
    public static final int RELEASE_NUM_FIELD = 3;
    public static final int E2E_NUM_FIELD = 4;
    public static final int TEA_NUM_FIELD = 5;

    // Regular expression for the software and the extension
    private static final String SOFTWARE_FILE_EXTENSION_REG_EX = "\\.(Z|z)19";
    private static final String VERSION_REG_EX = "[a-zA-Z]\\d{9}+([a-z]?+)";

    // The release package extension
    public static final String RELEASE_PACKAGE_EXTENSION = ".rpk";

    /***************************************************************************
     * constants part *
     ***************************************************************************/
    // sw_version_release (PK) in database, for example 5620
    private String swVersionRelease = "";
    // sw_version_build_ID (PK) in database, for example, 07
    private String swVersionBuildId = "";
    // sw_version_build_type, for example R, D, ...
    private String swVersionBuildType = "";

    /*
     * The path of the software in repository
     */
    private String swFilePathName;

    /*
     * Software creation time
     */
    private Timestamp softwareCreatedDateTime;
    /*
     * This is the cps codeplug version matching to the software
     */
    private String codeplugVersion;

    /**
     * The following is extracted from softwareVersion: Format for non-CM5000:
     * Rrref0vvvv where "e" indicates whether E2E encryption is enabled ("1") or
     * not ("0") "f" indicates the TEA mode: "0" for clear, "1" for TEA1, etc.
     * "vvvv" indicates the release Format (example) for non-CM5000: Entire
     * string: R071205620 Release: R07 Release id: R Device type id: 07
     * Encryption: 12 End2end: 1 TEA mode: 2 Version: 5620
     * 
     * New format for non-CM5000 (from iTM2.0.1): Any letter + 9 digits + one or
     * no letter, e.g. I13.000.6904a This format has been added to be able to
     * support development software
     * 
     * Format for CM5000 (transceiver): MR01_XXX_YYY where "XXX" indicates the
     * encryption mode "YYY" indicates the release Format (example) for CM5000
     * (transceiver): Entire string: MR01_012_011 Release: MR01 Release id: MR
     * Device type id: 01 Encryption: 012 Version: 011
     * 
     * Format for CM5000 (control head): MP01_000_YYY "YYY" is a release version
     * Encryption mode is always "000" Format (example) for CM5000 (control
     * head): Entire string: MP01_000_006 Release: MP01 Release id: MP Device
     * type id: 01 Encryption: 000 Version: 006
     */
    private String encryptionMode = "";
    private String end2end = "";
    private String teaMode = "";

    /*
     * The label like MR5.9
     */
    private String releaseLabel;

    /*
     * Note: This is not stored in the loaded software table, but is populated
     * by the SoftwareService when a software object is requested through the
     * service.
     */
    private List<ItmModelType> itmModelTypes;

    private static Logger fgLogger = Logger.getLogger("com.mot.dm");

    /*
     * Reference to the release package information. In case this software does
     * not require release package information this reference is null.
     * P6.1 removed it, replace with ReleasePacketInfo Service
     */

    /*
     * This method returns a string array that contains 4 elements build type,
     * build id, encryption mode, and release number,
     */
    public static String[] getSoftwareEntry(String softwareString)
    {
        String sVersion = softwareString;
        String bType = "";
        String bId = "";
        String release = "";
        String mode = "";
        String end2end = "";
        String teaMode = "";

        // It the software known?
        if (sVersion != null && !sVersion.equals(CommonConstants.UNKNOWN) && sVersion.length() != 0)
        {
            if (sVersion.matches(VERSION_REG_EX))
            {
                // e.g. SW R070006510, release is R07
                // "R07"
                String r = sVersion.substring(0, 3);
                // "R"
                bType = r.substring(0, 1);
                // "07"
                bId = r.substring(1);
                // "000"
                mode = sVersion.substring(3, 6);
                end2end = mode.substring(0, 1);
                teaMode = mode.substring(1, 2);
                // "6510" - including any ending letter, like 6510a
                release = sVersion.substring(6);
            }
            else
            {
                fgLogger.error("Could not parse software string: " + softwareString);
                return null;
            }

            return new String[]
            { bType, bId, mode, release, end2end, teaMode };
        }
        return null;
    }

    /*
     * Getter method.
     * 
     * @return Timestamp
     */
    public Timestamp getSoftwareCreatedDateTime()
    {
        return softwareCreatedDateTime;
    }

    /*
     * Setter method
     * 
     * @param createDateTime Timestamp
     */
    public void setSoftwareCreatedDateTime(Timestamp createDateTime)
    {
        this.softwareCreatedDateTime = createDateTime;
    }

    /**
     * @return the software version number (e.g.): R070005620 MR01_000_011
     *         MP01_000_006
     */
    public String getSoftwareVersion()
    {
        if (swVersionBuildType.length() == 1)
            return swVersionBuildType + swVersionBuildId + encryptionMode + swVersionRelease;
        else
            return swVersionBuildType + swVersionBuildId + "." + encryptionMode + "."
                    + swVersionRelease;
    }

    /**
     * @param entries
     *            The entries of a software
     * @return the software version number (e.g.): R070005620 MR01_000_011
     *         MP01_000_006
     */
    public static String getSoftwareVersion(String[] entries)
    {
        if (entries[0].length() == 1)
            return entries[0] + entries[1] + entries[2] + entries[3];
        else
            return entries[0] + entries[1] + "." + entries[2] + "." + entries[3];
    }

    /**
     * Set the software version and extract encryption type, language code and
     * version (see explanation for variable declaration section)
     * 
     * @param version
     */
    public void setSoftwareVersion(String p_version)
    {
        String[] versionEntries = getSoftwareEntry(p_version);
        if (versionEntries == null)
            return;

        swVersionBuildType = versionEntries[BUILD_TYPE_FIELD];
        swVersionBuildId = versionEntries[BUILD_ID_FIELD];
        encryptionMode = versionEntries[ENC_MODE_FIELD];
        swVersionRelease = versionEntries[RELEASE_NUM_FIELD];
        end2end = versionEntries[E2E_NUM_FIELD];
        teaMode = versionEntries[TEA_NUM_FIELD];
    }

    /**
     * Set the software version directly from filename, do special processing
     * depending on the actual software added.
     * 
     * @param p_filename
     */
    public boolean setSoftwareVersionFromFilename(String p_filename)
    {
        /*
         * We need to do special processing for each type of software filename
         * to build up the correct Software version number
         */
        if (p_filename.matches(VERSION_REG_EX + SOFTWARE_FILE_EXTENSION_REG_EX))
        {
            // Split the file name into the name and the extension
            String []parts = p_filename.split("\\.");
            if (parts.length > 0)
            {
                // The software version is the "name" part of the file name
                // (i.e. without extension)
                this.setSoftwareVersion(parts[0]);
                return true;
            }
            else
                return false;
        }
        else
        {
            return false;
        }
    }

    /*
     * 
     * Notice: This is used for keeping legacy client and proxy interface to
     * software object.
     * 
     * Get the path of the software
     * 
     * @return String
     */
    public String getSoftwareFullPath()
    {
        return swFilePathName;
    }

    /*
     * 
     * Notice: This is used for keeping legacy client and proxy interface to
     * software object.
     * 
     * Set path of the software
     * 
     * @param path String
     */
    public void setSoftwareFullPath(String path)
    {
        this.swFilePathName = path;
    }

    /**
     * 
     * Notice: This is used for keeping legacy client and proxy interface to
     * software object.
     * 
     * @return the version number (e.g.): 5620 (for R070005620) 011 (for
     *         MR01_000_011) 006 (for MP01_000_006)
     */
    public String getVersion()
    {
        return swVersionRelease;
    }

    /**
     * 
     * Notice: This is used for keeping legacy client and proxy interface to
     * software object.
     * 
     * Returns the release: MR01 (CM5000 transceiver) MP01 (CM5000 control head)
     * R07 (MTH800) etc.
     * 
     * @return release
     */
    public String getRelease()
    {
        return swVersionBuildType + swVersionBuildId;
    }

    /**
     * The encryption string, containing both End-2-End and TEA encryption
     * digits: For CM5000 transceiver, this is the "XXX" part of the version
     * string: MR01_XXX_YYY For CM5000 control head, this is always 000 For
     * non-CM5000, this is the "ef" part of the version string: Rrref0vvvv
     * 
     * @return the encryption string
     */
    public String getEncryptionMode()
    {
        return encryptionMode;
    }

    /**
     * Returns the End-to-end encryption mode: 0 (disabled) 1 (enabled) Empty
     * string for CM5000 software
     * 
     * @return End-to-end encryption mode
     */
    public String getEnd2end()
    {
        return end2end;
    }

    /**
     * Returns the TEA encryption algorithm: 0 (Clear) 1 (TEA1) 2 (TEA2) 3
     * (TEA3) Empty string for CM5000 software
     * 
     * @return TEA encryption algorithm
     */
    public String getTeaMode()
    {
        return teaMode;
    }

    /*
     * (non-Javadoc)
     * 
     * @return String
     * 
     * @see com.mot.dm.common.model.BaseObject#toString()
     */
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
        sb.append("swVersionBuildType", this.swVersionBuildType);
        sb.append("swVersionBuildId", this.swVersionBuildId);
        sb.append("encryptionMode", this.encryptionMode);
        sb.append("swVersionRelease", this.swVersionRelease);
        sb.append("swFilePathName", this.swFilePathName);
        sb.append("createDateTime", this.softwareCreatedDateTime);

        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @return boolean
     * 
     * @see com.mot.dm.common.model.BaseObject#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof Software))
            return false;

        Software software = (Software) o;
        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.swVersionBuildType, software.swVersionBuildType);
        builder.append(this.swVersionBuildId, software.swVersionBuildId);
        builder.append(this.encryptionMode, software.encryptionMode);
        builder.append(this.swVersionRelease, software.swVersionRelease);
        builder.append(this.swFilePathName, software.swFilePathName);
        builder.append(this.softwareCreatedDateTime, software.softwareCreatedDateTime);

        return builder.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @return int
     * 
     * @see com.mot.dm.common.model.BaseObject#hashCode()
     */
    public int hashCode()
    {
        return new HashCodeBuilder().append(swFilePathName).append(softwareCreatedDateTime)
                .hashCode();
    }

    /*
     * Get the cps codeplug version
     * 
     * @return String
     */
    public String getCodeplugVersion()
    {
        return codeplugVersion;
    }

    /*
     * Set the CPS codeplug version
     */
    public void setCodeplugVersion(String codeplugVersion)
    {
        this.codeplugVersion = codeplugVersion;
    }

    /*
     * Get the release
     * 
     * @return String
     */
    public String getSwVersionRelease()
    {
        return swVersionRelease;
    }

    /*
     * Get the build id
     * 
     * @return String
     */
    public String getSwVersionBuildId()
    {
        return swVersionBuildId;
    }

    /*
     * Get the build type: MR (CM5000 transceiver) MP (CM5000 control head) One
     * letter (a-z, A-Z) (MTH800, etc.)
     * 
     * @return String
     */
    public String getSwVersionBuildType()
    {
        return swVersionBuildType;
    }

    /*
     * Set the release
     */
    public void setSwVersionRelease(String swVersionRelease)
    {
        this.swVersionRelease = swVersionRelease;
    }

    /*
     * Set the build id
     * 
     * @param swVersionBuildId String
     */
    public void setSwVersionBuildId(String swVersionBuildId)
    {
        this.swVersionBuildId = swVersionBuildId;
    }

    /*
     * Set the build type
     * 
     * @param swVersionBuildType String
     */
    public void setSwVersionBuildType(String swVersionBuildType)
    {
        this.swVersionBuildType = swVersionBuildType;
    }

    /*
     * Get the path
     * 
     * @return String
     */
    public String getSwFilePathName()
    {
        return swFilePathName;
    }

    /*
     * Set the path
     * 
     * @param swFilePathName String
     */
    public void setSwFilePathName(String swFilePathName)
    {
        this.swFilePathName = swFilePathName;
    }

    /*
     * Set the encryption mode
     * 
     * @param encryptionMode String
     */
    public void setEncryptionMode(String encryptionMode)
    {
        this.encryptionMode = encryptionMode;
    }

    public Software()
    {
        super();
    }

    public Software(Software software)
    {
        this.codeplugVersion = software.getCodeplugVersion();
        this.softwareCreatedDateTime = software.getSoftwareCreatedDateTime();
        this.swFilePathName = software.getSwFilePathName();
        this.swVersionBuildId = software.getSwVersionBuildId();
        this.swVersionBuildType = software.getSwVersionBuildType();
        this.swVersionRelease = software.getSwVersionRelease();
        this.setSoftwareFullPath(software.getSwFilePathName());
        this.setEncryptionMode(software.getEncryptionMode());
    }

    /*
     * Getter
     * 
     * @return List<ItmModelType>
     */
    public List<ItmModelType> getItmModelTypes()
    {
        return itmModelTypes;
    }

    /*
     * Setter
     * 
     * @param itmModelTypes List<ItmModelType>
     */
    public void setItmModelTypes(List<ItmModelType> itmModelTypes)
    {
        this.itmModelTypes = itmModelTypes;
    }

    /*
     * Getter
     * 
     * @return String
     */
    public String getReleaseLabel()
    {
        return releaseLabel;
    }

    /*
     * Setter
     * 
     * @param releaseLabel String
     */
    public void setReleaseLabel(String releaseLabel)
    {
        this.releaseLabel = releaseLabel;
    }

    /**
     * Determines whether or not a file is a software file A software file is
     * either a firmware file (z19) or CM5000 software file
     * 
     * @param url
     * @return
     */
    public static boolean isSoftwareFile(String url)
    {
        File file = new File(url);
        String fileName = file.getName();
        if (fileName.matches(VERSION_REG_EX + SOFTWARE_FILE_EXTENSION_REG_EX))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Determines whether or not a file is a release package
     * 
     * @param url
     * @return
     */
    public static boolean isReleasePackage(String url)
    {
        File file = new File(url);
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(RELEASE_PACKAGE_EXTENSION);
    }
}