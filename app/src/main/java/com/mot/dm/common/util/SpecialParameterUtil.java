package com.mot.dm.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mot.dm.client.dmImpl.gui.DmsContext;

public class SpecialParameterUtil
{
    public static final int startIndexOfLimitNumberOfTMOTg = 2000;
    public static final int startIndexOfLimitNumberOfDMOTg = 1000;
    public static final int TMOTalkgroupType = 0;
    public static final int DMOTalkgroupType = 1;
    public static final Map<String,List<String>> firmwareVerion2TMOTgRangeCache = new HashMap<String,List<String>>();
    public static final Map<String,List<String>> firmwareVerion2DMOTgRangeCache = new HashMap<String,List<String>>();
    public static final String MAXFLAG = "_MAX";
                    
    public static List<String> computeRange(int startIndex, String actualMaxValue)
    {
        int step = startIndex; //step is equal to startIndex as FR
        List<String> range = new ArrayList<String>();
        range.add("");//default value
        if (actualMaxValue != null && !actualMaxValue.isEmpty())
        {
            int iMaxValue = Integer.parseInt(actualMaxValue);
            while (startIndex < iMaxValue)
            {
                range.add(String.valueOf(startIndex));
                startIndex = startIndex + step;
            }
        }
        range.add(DmsContext.getString("General.Unlimited")); //range does not include actual max value
        return range;
    }
    
    //For server side
    public static String convertBackToLogString(String valueInTask)
    {
        String value = valueInTask;
        if (value != null && value.contains(SpecialParameterUtil.MAXFLAG))
        {
            value = value.replace(SpecialParameterUtil.MAXFLAG, "");
        }
        return value;
    }
    
    //For client side
    public static String convertBackToGUIString(String valueInTask)
    {
        String value = valueInTask;
        if (value != null && value.contains(SpecialParameterUtil.MAXFLAG))
        {
            value = value.replace(value, DmsContext.getString("General.Unlimited"));
        }
        return value;
    }
    
     
}