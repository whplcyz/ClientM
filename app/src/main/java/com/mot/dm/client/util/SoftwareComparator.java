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
CODED     BY:  Michael Leismann    14-Jan-09

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
14/Jan/09  Michael Leismann      CCMPD01146532 Added header
                                               Modified compare method to be to
                                               handle CM5000 software
                                               Added method that ignores
                                               encryption strings
20/Jan/09  Michael Leismann      CCMPD01146532 Updated after FTR
07/Jul/09  Michael Leismann      CCMPD01234655 Added release IDs in comparison
26/Sep/09  Michael Leismann      CCMPD01259651 Supports the new radio type
                                               structure
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;

import java.util.Comparator;

import com.mot.dm.common.model.Software;

/**
 * @Version 1.0 Jun 13, 2007
 * @author q19266 Jack Qiu
 */
public class SoftwareComparator implements Comparator<Software>
{
    /**
     * Compare two software version strings: 1. First the version part of the
     * strings is taken from both software objects 2. These versions are then
     * compared 3. If they are identical then the encryption part of the strings
     * is taken from both software object and compared
     * 
     * The version part is sorted descending and the encryption part is sorted
     * ascending Example 1 (non-CM5000 software) - sorted: R070006211 R070006106
     * R070005848 R070005815 R070005620 Example 1 (CM5000 transceiver software)
     * - sorted: MR01_000_012 MR01_011_012 MR01_000_011 MR01_011_011
     * 
     * @see com.mot.dm.common.model.Software for details of version and
     *      encryption description
     */
    public int compare(Software p_software1, Software p_software2)
    {
        // Use the compareIgnoreEncryption method to extract and compare
        // version part of the strings
        int compareResult = compareIgnoreEncryption(p_software1, p_software2);

        // If the versions are identical then compare encryption strings
        if (compareResult == 0)
        {
            String encryptionMode1 = p_software1.getEncryptionMode();
            String encryptionMode2 = p_software2.getEncryptionMode();

            compareResult = encryptionMode1.compareTo(encryptionMode2);
        }

        return compareResult;
    }

    /**
     * Like compare method, but encryption strings are ignored
     */
    public int compareIgnoreEncryption(Software p_software1, Software p_software2)
    {
    	int compareResult;    

    	String softwareVersion1 = p_software1.getVersion();
        String softwareVersion2 = p_software2.getVersion();

        // If the versions have the same length, then compare 2 with 1
        // so that 5815 is lower than 6511
        // If the length are not the same, then compare 1 with 2
        // so that 6511d is lower than 6511
        if (softwareVersion1.length() == softwareVersion2.length())
        {
            compareResult = softwareVersion2.compareTo(softwareVersion1);
        }
        else
        {
            compareResult = softwareVersion1.compareTo(softwareVersion2);
        }

        return compareResult;
    }

}
