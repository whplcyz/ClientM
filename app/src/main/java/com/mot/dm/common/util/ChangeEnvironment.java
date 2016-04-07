package com.mot.dm.common.util;

import java.util.logging.Level;

public class ChangeEnvironment
{
    private static java.util.logging.Logger fgLogger = SimpleLogUtil.getLogger();
    /**
     * @param args
     */
    static
    {
    	try
    	{
    	    String flexeraHome = CommonConstants.getFlexeraHome();
    	    
    	    System.loadLibrary("KERNEL32");
    	    System.load(flexeraHome + System.getProperty("file.separator") + "msvcr100.dll");
    	    System.load(flexeraHome + System.getProperty("file.separator") + "ChangeEnvironment.dll");
    	    fgLogger.info("Load ChangeEnvironment.dll successfully");
    	}
    	catch (UnsatisfiedLinkError e)
    	{
    	    try
    	    {
    		  System.loadLibrary("ChangeEnvironment");
    		  fgLogger.info("Load ChangeEnvironment.dll successfully");
    	    }
    	    catch (UnsatisfiedLinkError e1)
    	    {
    	        fgLogger.log(Level.SEVERE, "Can not load library " + e1.toString());
    	    }
    	}
    }

    private static ChangeEnvironment instance = null;

    public static ChangeEnvironment Instance()
    {
	if (instance == null)
	{
	    instance = new ChangeEnvironment();
	}

	return instance;
    }

    public native void setEnvironment(String name, String value, boolean append);
}
