/*
 +------------------------------------------------------------------------------+
 |                                                                              |
 |              J A V A    C L A S S    S P E C I F I C A T I O N               |
 |                                                                              |
 |                          Copyright 2009 Motorola Inc.                        |
 |                              All Rights Reserved.                            |
 |                                                                              |
 |                       Motorola Confidential Restricted                       |
 +------------------------------------------------------------------------------+
 --------------------------------------------------------------------------------

 PRODUCT NAME:  ITM
 CODED     BY:  Jens Hansen    4 Aug. 2009

 ------------------------------- REVISION HISTORY -------------------------------
 dd/mmm/yy  <name>                <CR number>   <description>
  4/Aug/09  Jens Hansen           ccmpd01247176 Initial version
 ------------------------------------------------------------------------------*/

package com.mot.dm.common.util;

public interface CommonMessagePresentaion
{
    /**
     * To set the display message text. It's up to the implementing class to
     * figure out how to display the message to the user.
     * 
     * @param displayHeaderText
     * @param displayBodyText
     */
    public void setDisplayMessage(String displayHeaderText, String displayBodyText);
}
