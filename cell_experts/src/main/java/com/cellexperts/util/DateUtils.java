package com.cellexperts.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
/*************************************************************************
 * author: abdulhafeez
 * date:   Jan 30, 2016
 ************************************************************************/
public class DateUtils
{
	public static Date getWeekendDate()
	{
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date date = c.getTime(); // => Date of this coming Sunday.
		return date;
		// formatDate(date);
	}
	
	public static Date getWeekendDate(String pickedDate) throws ParseException
	{
		DateTime picked = new DateTime(formatDate(pickedDate)); //Using JOda
		
		if (picked==null)
			return getWeekendDate(); // returns current weekend date
		Calendar c = Calendar.getInstance();
		c.set(picked.getYear(), picked.getDayOfMonth()-2, picked.getDayOfWeek());
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date date = c.getTime(); // => Date on the coming Sunday of the given date
		return date;
		// formatDate(date);
	}

	public static String formatDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);

	}

	public static String getDayOfWeek()
	{
		Calendar c = Calendar.getInstance();
		String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
		return day;
	}
	
	@SuppressWarnings("deprecation")
	public static String getDayOfWeek(String date)
	{

		if (date != null)
		{
			DateTime thisDate = new DateTime(formatDate(date));
			Calendar c = Calendar.getInstance();
			c.set(thisDate.getYear(), thisDate.getDayOfMonth()-2, thisDate.getDayOfWeek());
			String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
			return day;
		} else
			return getDayOfWeek();

	}

	public static Date getTodaysDate()
	{
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	
	public static Date formatDate(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		if(date == null)
			return null;
		try
		{
			return formatter.parse(date);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
