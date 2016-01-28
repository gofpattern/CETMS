package com.cellexperts.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils
{
	public static Date getWeekendDate()
	{
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date date = c.getTime(); // => Date of this coming Saturday.
		return date;
		// formatDate(date);
	}

	public String formatDate(Date date)
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

	public static Date getTodaysDate()
	{
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

}
