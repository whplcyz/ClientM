/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                 Copyright 2011-2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  Jens Hansen 4/Jul/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
29/Okt/08  ckfm01               NA             Updating interface version number
                                               Last minute change.
16/Feb/09  Michael Leismann      CCMPD01166212 Updated INTERFACE_VERSION to
                                               1.2.0 for the 1.9.3 release
14/May/09  Michael Leismann      CCMPD01210760 Updated INTERFACE_VERSION to
                                               2.0.0 for the 2.0 release
18/Aug/09  Michael Leismann                    Updated INTERFACE_VERSION to
                                               2.1.0 for the 2.1 release
11/Dec/09  Michael Leismann                    Updated INTERFACE_VERSION to
                                               3.0.0B for the 3.0 Demo release
25/Feb/10  Michael Leismann      CCMPD01308747 Updated INTERFACE_VERSION to
                                               3.0.0 for the 3.0 release
23/Jun/10  Michael Leismann      CCMPD01357734 Updated INTERFACE_VERSION to
                                               3.1.0 for the 3.1 release
24/Jun/10  Sigurdur Jonsson      CCMPD01357127 Automatic application update.
03/Sep/10  Michael Leismann      CCMPD01387283 Updated INTERFACE_VERSION to
                                               4.0.0 for the 4.0 release
28/Oct/10  cjh102                CCMPD01399192 Added version number of file
                                               transmissions                                               
16/Feb/11  Michael Leismann      CCMPD01474261 Updated INTERFACE_VERSION to
                                               5.0.0 for the 5.0 release
25/Feb/11  Michael Leismann      CCMPD01478134 iTM 5.0: Ensure that the version
                                               number is only defined in one
                                               class
05/Jul/12  a23126                CCMPD01673281 Update Version to 6.0.0
10/Apr/14  qngv36                CCMPD01881985 Update Version to 7.0.0
28/3/14    wch378               CCMPD01889533 develop flexera license.
------------------------------------------------------------------------------*/
package com.mot.dm.common.util;

/**
 * This class contains the version number which is used to identify if client
 * and proxy can communicate with the server
 * 
 * @author cjh102
 * 
 */
public final class VersionNumber
{
    private VersionNumber()
    {
        
    }
    // TODO: when to change version number
    /**
     * The version number is divided into three parts:
     * \<major\>.<minor>.<service>
     */
    public final static String INTERFACE_VERSION = "7.2.0";
    public final static String ITM_SYSTEM_VERSION = INTERFACE_VERSION;
    public final static String SERVER_ID = "119281bd28b5c00c0129354962210012";
    public final static String CLIENT_ID = "119281bd28b5c00c0129354982e80013";
    public final static String PROXY_ID = "119281bd28b00d120128b03549bd0028";
    public final static String UPDATER_ID = "119281bd28b00d120128b03549fb0029";
    public final static String EMAIL_TRANSMITTER_ID = "119281bd28b00d120128b033cc210021";
    public final static String FILE_TRANSMITTER_ID = "119281bd28b00d120128b033cc210027";
    public final static String NEED_UPGRADE_LICENSE_VERSION = "6.0.2";
    public final static String UPGRADE_LICENSE_FROM_VERSION = "%6.%";
    
}
