/*
+------------------------------------------------------------------------------+
|                                                                              |
|              J A V A    C L A S S    S P E C I F I C A T I O N               |
|                                                                              |
|                       Copyright 2008-2011 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  ITM Client   
CODED     BY:  chad001    2008.01.11

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
19/Sep/08  Michael Leismann      CCMPD01087804 German Client: New class used to
                                               show message dialog where the
                                               text on the buttons can be
                                               customized
01/Dec/10  Michael Leismann      CCMPD01394081 iTM 4.0: Policies
19/Jan/11  cjh102                CCMPD01462097 Added new method, to handle
                                               multiple buttons
------------------------------------------------------------------------------*/
package com.mot.dm.client.util;
import com.mot.dm.client.dmImpl.gui.DmsContext;

/**
 * @author Michael Leismann This class contains methods used to show message
 *         dialogs where the text on the buttons are translated, i.e. language
 *         specific
 */
public final class ClientMessageDialog {
    private ClientMessageDialog() {

    }

    // String array containing the "OK" label
    private static String[] okLabel =
            {DmsContext.getString("JComposite.okButtonLabel")};

    // String array containing the "OK" and "Cancel" labels
    private static String[] okCancelLabels =
            {DmsContext.getString("JComposite.okButtonLabel"),
                    DmsContext.getString("JComposite.cancelButtonLabel")};

    // String array containing the "Yes" and "No" labels
    private static String[] yesNoLabels =
            {DmsContext.getString("JComposite.yesButtonLabel"),
                    DmsContext.getString("JComposite.noButtonLabel")};

}
