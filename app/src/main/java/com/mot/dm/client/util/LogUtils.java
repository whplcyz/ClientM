/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2007-2008 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM
CODED     BY:  thmt001 07/Feb/08

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
12/Aug/08  chad001               CCMPD01079858 Close logger                                               
------------------------------------------------------------------------------*/

package com.mot.dm.client.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class LogUtils
{

    private static PrintWriter infoPW = null;

    public LogUtils(String sourcePath)
    {
        String[] strings = sourcePath.split("/");

        String path = "";
        for (int i = 0; i < strings.length - 1; i++)
        {
            path = path + strings[i] + "/";
        }

        String name = strings[strings.length - 1].substring(0,
                strings[strings.length - 1].length() - 4);

        String importLog = path + name + ".log";
        try
        {
            infoPW = new PrintWriter(new FileOutputStream(importLog, false), true);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void info(String message)
    {
        if (infoPW != null)
        {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            infoPW.println(ts.toString() + " " + " - " + message);
        }
    }

    public void close()
    {
        if (infoPW != null)
        {
            infoPW.close();
            infoPW = null;
        }
    }
}
