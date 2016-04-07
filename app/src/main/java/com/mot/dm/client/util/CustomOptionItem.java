/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2010 Motorola Inc.                           |
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
------------------------------------------------------------------------------*/

package com.mot.dm.client.util;

/*
 * class for a custom option item in a table
 */
public class CustomOptionItem
{
    private String title;
    private String actionMethod;
    private String getMethod;

    /*
     * Constructor.
     * 
     * @param title String
     * 
     * @param actionMethod String
     * 
     * @param getMethod String
     */
    public CustomOptionItem(String title, String actionMethod, String getMethod)
    {
        this.title = title;
        this.actionMethod = actionMethod;
        this.getMethod = getMethod;
    }

    /*
     * get the title
     * 
     * @return String
     */
    public String getTitle()
    {
        return title;
    }

    /*
     * set the title
     * 
     * @param title String
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /*
     * Get the method name matching to this option item
     * 
     * @return String
     */
    public String getActionMethod()
    {
        return actionMethod;
    }

    /*
     * Set the method name matching to this option item
     * 
     * @param actionMethod String
     */
    public void setActionMethod(String actionMethod)
    {
        this.actionMethod = actionMethod;
    }

    /*
     * Get the Getter method name matching to this option item
     * 
     * @return String
     */
    public String getGetMethod()
    {
        return getMethod;
    }

    /*
     * Set the Setter method name matching to this option item
     * 
     * @param getMethod String
     */
    public void setGetMethod(String getMethod)
    {
        this.getMethod = getMethod;
    }

}