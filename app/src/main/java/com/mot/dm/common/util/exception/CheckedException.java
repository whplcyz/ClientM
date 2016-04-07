/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                       Copyright 2007-2010 Motorola Inc.                      |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  w23021(Jason)

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
11/Jan/10  Michael Leismann      CCMPD01301894 Updated header
------------------------------------------------------------------------------*/
package com.mot.dm.common.util.exception;

/**
 * @Version 1.0 2007 April 12
 * @author w23021(Jason)
 */
import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class CheckedException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -7608447863972660355L;
    /**
     * the original exception which will be transformed.
     */
    private Throwable originalException;

    /**
     * the construction method with a detail message.
     * 
     * @param message
     *            the detail message
     */
    public CheckedException(String message)
    {
        super(message);
    }

    /**
     * the construction method with the detail message and original exception
     * 
     * @param message
     * @param originalException
     */
    public CheckedException(String message, Throwable originalException)
    {
        super(message);
        this.originalException = originalException;
    }

    /**
     * the method is used to get the detail message contain the original
     * exception's message
     */
    public String getMessage()
    {
        if (this.originalException == null || this.originalException == this)
            return super.getMessage();
        else
            return super.getMessage() + "\n'nest exception is': " + this.getClass().getName()
                    + ": " + this.originalException.getMessage();
    }

    /**
     * the method is just get the current message.not contain the original
     * message.
     */
    public String getLocalizedMessage()
    {
        return super.getMessage();
    }

    /**
     * this method is used to get the original exception
     */
    public Throwable getCause()
    {
        return this.originalException;
    }

    /**
     * this method is uset to print the composite message to the ps
     * 
     * @param ps
     *            the print stream
     */
    public void printStackTrace(PrintStream ps)
    {
        if (this.originalException == null || this.originalException == this)
        {
            super.printStackTrace(ps);
        }
        else
        {
            ps.print(this);
            this.originalException.printStackTrace(ps);
        }
    }

    /**
     * this method is used to print the composite message
     * 
     * @param pw
     *            the print writer
     */
    public void printStackTrace(PrintWriter pw)
    {
        if (this.originalException == null || this.originalException == this)
        {
            super.printStackTrace(pw);
        }
        else
        {
            pw.println(this);
            originalException.printStackTrace(pw);
        }

    }

}
