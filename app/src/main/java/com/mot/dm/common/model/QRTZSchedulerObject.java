/*
+------------------------------------------------------------------------------+
|                                                                              |
|               J A V A    C L A S S    S P E C I F I C A T I O N              |
|                                                                              |
|                 Copyright 2014 Motorola Solutions Inc.                  |
|                              All Rights Reserved.                            |
|                                                                              |
|                       Motorola Confidential Restricted                       |
+------------------------------------------------------------------------------+
--------------------------------------------------------------------------------

PRODUCT NAME:  iTM
CODED     BY:  Wei Huping(Qngv36)

------------------------------- REVISION HISTORY -------------------------------
dd/mmm/yy  <name>                <CR number>   <description>
09/July/14  Qngv36               CCMPD01394082 [iTM 7.0]Support scheduled on-line back up.
------------------------------------------------------------------------------*/
package com.mot.dm.common.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mot.dm.common.util.CommonConstants;

/**
 * 
 * @author qngv36 
 * Description The QRTZScheduleJob class contains all information for a Quartz schedule job. A
 *         Quartz job is a description of actions that will be auto be schedule by Quartz.
 */
public class QRTZSchedulerObject extends BaseObject 
{
	private static final long serialVersionUID = 649195200235607421L;
	
	private  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	protected int jobType;
	
	protected Calendar cal = Calendar.getInstance();
	protected int reccurPattern = CommonConstants.DAILY;
	protected boolean bEveryWeekDay = false;
	protected int[] dayOfWeek;
	protected int  dayOfMonth = 0; 
	protected String strCronExpression = "";
	protected Date lastModifiedTime = new Date();

	protected QRTZSchedulerObject()
	{
	}

	public int getJobType()
    {
        return jobType;
    }
	
	public String getTriggerKey()
    {
        return this.reccurPattern + CommonConstants.QUARTZ_SPLITTER + this.GetCronExpression()  + CommonConstants.QUARTZ_SPLITTER +  formatter.format(this.getLastModifiedTime());
    }
    
	public Calendar getCal() 
	{
		return cal;
	}

	public int getReccurPattern() 
	{
		return reccurPattern;
	}

	public boolean isbEveryWeekDay()
	{
		return bEveryWeekDay;
	}


	public int[] getDayOfWeek()
	{
		return dayOfWeek;
	}


	public int getDayOfMonth() 
	{
		return dayOfMonth;
	}

	//public Trigger getTrigger() 
	//{
	//	return trigger;
	//}

	/*
	 * Create a corn expression object whose date is the cal.date, and it reccur ever "increase" day.
	 * */
	public static QRTZSchedulerObject CreateNormalDailyInstance(int jobType, Calendar time) throws Exception
	{
		if(time == null)
		{
			throw new Exception("Invalidate parameter!");
		}
		
		QRTZSchedulerObject instance = new QRTZSchedulerObject();
		
		instance.reccurPattern = CommonConstants.DAILY;
		instance.cal = time;

		instance.jobType = jobType;
		return instance;
	}
	
	/*
	 * Create a corn expression object what happens all week day.
	 * */
	public static QRTZSchedulerObject CreateAllWeekDayDailyInstance(int jobType, Calendar time) throws Exception
    {
		if(time == null)
		{
			throw new Exception("Invalidate parameter!");
		}
		
		QRTZSchedulerObject instance = new QRTZSchedulerObject();
		
		instance.reccurPattern = CommonConstants.DAILY;
		instance.cal = time;
		
		instance.bEveryWeekDay = true;
		instance.jobType = jobType;
		return instance;
    }
	
	/*
	 * Create a corn expression object what happens in some specific day of week, reccur every "nIncrease" week.
	 * */
	public static  QRTZSchedulerObject CreateWeeklyInstance(int jobType, Calendar time,  int[] dayOfWeekArr) throws Exception
    {
		if(dayOfWeekArr == null || time == null)
		{
			throw new Exception("Invalidate parameter!");
		}
		
		QRTZSchedulerObject instance = new QRTZSchedulerObject();
		
		instance.reccurPattern = CommonConstants.WEEKLY;
		instance.cal = time;
		
		instance.dayOfWeek = dayOfWeekArr;
		instance.jobType = jobType;
		return instance;
    }

	/*
	 * Create a corn expression object what happens in some specific day of month, reccur every "nIncrease" moth.
	 * */
	public static QRTZSchedulerObject CreateMonthlyInstance(int jobType, Calendar time,  int dayOfMonth) throws Exception
    {
		if(dayOfMonth < 0 || dayOfMonth > 31 || time == null)
		{
			throw new Exception("Invalidate parameter!");
		}
		
		QRTZSchedulerObject instance = new QRTZSchedulerObject();
		instance.reccurPattern = CommonConstants.MONTHLY;
		instance.cal = time;
		
		instance.dayOfMonth = dayOfMonth;
		instance.jobType = jobType;
		return instance;
    }
	
	
	public static QRTZSchedulerObject CreateOnceInstance(int jobType, Calendar time)
    {
		QRTZSchedulerObject instance = new QRTZSchedulerObject();
		instance.reccurPattern = CommonConstants.ONCE;
		if(time != null)
		{
		    instance.cal = time;
		}
		
		instance.jobType = jobType;
		return instance;
    }
	
	/*get the cron-trigger expression*/
    public String GetCronExpression()
    {
    	String strTempCronExpression = "";
    	int second = cal.get(Calendar.SECOND); 
    	int minute = cal.get(Calendar.MINUTE); 
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	String timePart = String.format("%1$d %2$d %3$d", second, minute, hour);
    	
    	switch(reccurPattern)
    	{
    	case CommonConstants.DAILY:
    		if(bEveryWeekDay)
    		{
    			strTempCronExpression = String.format("%1$s ? * %2$s *", timePart, CommonConstants.QUARTZ_ALL_WEEK_DAY_FLAG); //every week day
    		}
    		else
    		{
    			strTempCronExpression = String.format("%1$s * * ? *",  timePart); //every day
    		}
    		break;
    		
    	case CommonConstants.WEEKLY:
    		if(dayOfWeek == null || dayOfWeek.length == 0)
    		{
    		    return "INVALID EXPRESSION";	
    		}
    		
    		StringBuilder strBuilder = new StringBuilder();
    		for(int day: dayOfWeek)
    		{
    			if(strBuilder.length() != 0)
    			{
    				strBuilder.append(",");
    			}
    			
    			strBuilder.append(day);
    		}
    		String strDayOfWeekPart = strBuilder.toString();
    		
    		strTempCronExpression = String.format("%1$s ? * %2$s *", timePart, strDayOfWeekPart); //every increase week with specify day of week
    		break;
    		
    	case CommonConstants.MONTHLY:
    		strTempCronExpression = String.format("%1$s %2$d * ? *", timePart, dayOfMonth); //every  moth on the special day of month.
    		break;
    		
    	case CommonConstants.ONCE:
    		int ndayOfMonth = this.cal.get(Calendar.DAY_OF_MONTH);
    		int nSpecialMonth = this.cal.get(Calendar.MONTH);
    		int nSpecialYear =  this.cal.get(Calendar.YEAR);
    		strTempCronExpression = String.format("%1$s %2$d %3$d ? %4$d", timePart, ndayOfMonth, nSpecialMonth, nSpecialYear); //every increase moth on the special day of month.
    		break;
    	}
    	
		strCronExpression = strTempCronExpression;
    	return strCronExpression;
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof QRTZSchedulerObject))
        {
            return false;
        }

        QRTZSchedulerObject other = (QRTZSchedulerObject)o;

        if(this.reccurPattern != other.reccurPattern)
        {
        	return false;
        }
        if(this.bEveryWeekDay != other.bEveryWeekDay)
        {
        	return false;
        }
        if(!this.lastModifiedTime.equals(other.lastModifiedTime))
        {
        	return false;
        }
        if(this.jobType != other.jobType)
        {
        	return false;
        }

        if(this.dayOfMonth != other.dayOfMonth)
        {
        	return false;
        }
        
        if(!Arrays.equals(this.dayOfWeek, other.dayOfWeek))
        {
        	return false;
        }
        
        if(!this.GetCronExpression().equals(other.GetCronExpression()))
		{
			return false;
		}
        
        return true;
    }

    public int hashCode()
    {
    	return new HashCodeBuilder().append(this.getTriggerKey()).hashCode();
    }
    
    public Date getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    
    public void setLastModifiedTime(Date time)
    {
        lastModifiedTime = time;
    }

	@Override
	public String toString() {
		ToStringBuilder sb;
		sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(
		        "reccurPattern", this.reccurPattern)
		        .append("job type", this.jobType)
		        .append("trigger key", this.getTriggerKey())
		        .append("Cron-Expressioin", this.GetCronExpression());
		return sb.toString();
	}
}
