/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                   Copyright 2011 Motorola Solutions Inc.                     |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  cjh102 11/Jan/11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Jan/11  cjh102                CCMPD01461502 Moved methods to OsUtils from 
                                               CommonUtils, so they can become 
                                               part of the itmUpdater
02/Mar/11  Kim Mortensen         CCMPD01471488 fixing klocwork findings.
25/May/11  cjh102                CCMPD01484229 Adding a description to each
                                               of the returned MAC addresses
9/06/11    cjh102                CCMPD01476570 Updates for license exceptions
16/Dec/11  cmdg37                CCMPD01953493 Change the method for executing commands 

------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mot.dm.common.model.NetworkAdapterInfo;

public final class OsUtils
{
    private OsUtils()
    {
        
    }
    private static Logger c_fgLogger = Logger.getLogger("com.mot.dm");

    private static String c_classname = "OsUtils";

    private static ArrayList<NetworkAdapterInfo> macAddresses = null;

    private static String macAddressString = null;

    /**
     * Return available free disk space for a directory as a long.
     * 
     * @param dirName
     *            name of the directory.
     * @return free disk space in bytes or -1 if unknown
     */
    public static long getFreeDiskSpace(String dirName)
    {
        // Prepend and append " if the directory contains spaces
        if (dirName.contains(" "))
        {
            dirName = "\"" + dirName + "\"";
        }

        BufferedReader in = null;
        try
        {
            // run the dir command on the argument directory name
			String[] commands = new String[] { "cmd.exe", "/c", "dir", dirName };
            Runtime runtime = Runtime.getRuntime();
            Process process = null;
            process = runtime.exec(commands);
            if (process == null)
            {
                c_fgLogger.info(c_classname + " - getFreeDiskSpace()"
                        + " - Cannot create process - folder: " + dirName);
                return -1;
            }
            // read the output of the dir command
            // only the last line is of interest
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String freeSpace = null;
            while ((line = in.readLine()) != null)
            {
                freeSpace = line;
            }
            if (freeSpace == null)
            {
                c_fgLogger.info(c_classname + " - getFreeDiskSpace()"
                        + " - freeSpace is null - folder: " + dirName);
                in.close();
                return -1;
            }

            process.destroy();

            // Remove leading and trailing whitespaces
            freeSpace = freeSpace.trim();
            String[] items = freeSpace.split(" ");
            // the first valid numeric value in items after(!) index 0
            // is probably the free disk space
            int index = 1;
            while (index < items.length)
            {
                try
                {
                    String item = items[index++];

                    // Remove all the non-digits from the string
                    String itemWithOnlyDigits = item.replaceAll("\\D", "");

                    // And try to parse it
                    long bytes = Long.parseLong(itemWithOnlyDigits);

                    in.close();
                    return bytes;
                }
                catch (NumberFormatException nfe)
                {
                }
            }
            c_fgLogger.info(c_classname + " - getFreeDiskSpace()"
                    + " - Could not calculate free disk space - folder: " + dirName);
            in.close();
            return -1;
        }
        catch (Exception exception)
        {
            c_fgLogger.info(c_classname + " - getFreeDiskSpace()" + " - folder: " + dirName
                    + " - Exception: " + exception.getMessage());
            try
            {
                if (in != null)
                    in.close();
            }
            catch (Exception ee)
            {
                // we can do nothing now
            }
            return -1;
        }
    }

    /**
     * This method returns information about the network adapters installed in
     * the server. If no network adapters are found, then an empty list is
     * returned.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<NetworkAdapterInfo> getNetworkAdapterInfo()
    {
        synchronized(c_fgLogger)
        {
            if (macAddresses == null)
            {        
                ArrayList<NetworkAdapterInfo> nodeIds = new ArrayList<NetworkAdapterInfo>();
                String command = "cmd.exe /C ipconfig /all";
        		String[] commands = new String[] { "cmd.exe", "/C", "ipconfig", "/all" };
                Process pid1 = null;
                try
                {
                    pid1 = Runtime.getRuntime().exec(commands);
                }
                catch (IOException e)
                {
                    c_fgLogger.error("Command: " + command + " could not be executed", e);
                }
        
                BufferedReader inn = null;
                Pattern pp = null;
                InputStream is = null;
                InputStreamReader isr = null;
                if (pid1 != null)
                {
                    is = pid1.getInputStream();
                    isr = new InputStreamReader(is);
                    inn = new BufferedReader(isr);
                    pp = Pattern
                            .compile("([0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2})");
                }
        
                String currentLine = "";
                String previousSplittedLine = "";
                while (true && inn != null)
                {
                    try
                    {
                        currentLine = inn.readLine();
                        if (currentLine == null)
                            break;
        
                        // Split the line based on a number of ". "-combinations ending
                        // with one ": " combination. In general this means that the
                        // array contains "key","value" pairs
                        String[] splittedLines = currentLine.split("([. ]+?:[ ]{1})");
        
                        // Getting the last "split" in the line
                        String lastSplit = (String) splittedLines[splittedLines.length - 1];
                        // Try if they match the expression for the MAC-address.
                        Matcher mm = pp.matcher(lastSplit);
                        if (mm.matches())
                        {
                            // Add if they match
                            NetworkAdapterInfo nai = new NetworkAdapterInfo();
                            nai.setDescription(previousSplittedLine);
                            nai.setMacAddress(lastSplit);
                            nodeIds.add(nai);
                        }
        
                        // Assuming that the description of the network card is the list
                        // above the line with the MAC-address, the second part of the
                        // split line is stored for use in the following iteration
                        if (splittedLines.length >= 2)
                            previousSplittedLine = splittedLines[1];
        
                    }
                    catch (IOException e)
                    {
                        try
                        {
                            if (inn != null)
                                inn.close();
                        }
                        catch (Exception ex)
                        {
                        }
        
                        try
                        {
                            if (is != null)
                                is.close();
                        }
                        catch (Exception ex)
                        {
                        }
        
                        try
                        {
                            if (isr != null)
                                isr.close();
                        }
                        catch (Exception ex)
                        {
                        }
        
                        e.printStackTrace();
                    }
                    if (currentLine == null)
                        break;
                }
        
                // Sort the list of strings, so we are sure its ordered
                // in the same way every time.
                Collections.sort(nodeIds);
                macAddresses = new ArrayList<NetworkAdapterInfo>();
                macAddresses.addAll(nodeIds);
        
                try
                {
                    if (inn != null)
                        inn.close();
                }
                catch (Exception ex)
                {
                }
        
                try
                {
                    if (is != null)
                        is.close();
                }
                catch (Exception ex)
                {
                }
        
                try
                {
                    if (isr != null)
                        isr.close();
                }
                catch (Exception ex)
                {
                }
        
                c_fgLogger.debug("Found network adapters: " + macAddresses);
            }
        }

        return macAddresses;
    }

    /**
     * Returns a string containing the MAC-addresses in on the computer
     * delimited by ":".
     * 
     * @return
     */
    public static String getMacAddressString()
    {
        if (macAddressString != null)
            return macAddressString;

        ArrayList<NetworkAdapterInfo> networkAdapterInfos = getNetworkAdapterInfo();
        StringBuffer sb = new StringBuffer();

        if (networkAdapterInfos != null)
        {
            for (NetworkAdapterInfo nai : networkAdapterInfos)
            {
                sb.append(nai.getMacAddress());
                sb.append(" ");
            }

            macAddressString = sb.toString().trim();
            macAddressString = macAddressString.replaceAll(" ", ":");
        }
        return macAddressString;
    }

}
