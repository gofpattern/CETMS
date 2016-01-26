/**
 * 
 */
package com.cellexperts.beans;

import java.util.Date;

/**
 * @author abdulhafeez
 *
 */
public class TimeSheetBean
{
	private int empId;
	private Date today;
	private Date weekend;
	private String day;
	private long hours;
	
	public long getHours()
	{
		return hours;
	}

	public void setHours(long hours)
	{
		this.hours = hours;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public Date getToday()
	{
		return today;
	}

	public void setToday(Date today)
	{
		this.today = today;
	}

	public Date getWeekend()
	{
		return weekend;
	}

	public void setWeekend(Date weekend)
	{
		this.weekend = weekend;
	}

	/**
	 * @return the day
	 */
	public String getDay()
	{
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day)
	{
		this.day = day;
	}

	

}
