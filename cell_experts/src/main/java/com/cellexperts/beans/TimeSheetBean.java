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
	private int employeeId;
	private String firsname;
	private String lastname;
	private Date today;
	private Date pickedDate;
	private Date weekend;
	private String day;
	private double hours;
	private double minutes; //
	private double cash;
	private String notes;
	private String lastuser;
	
	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLastuser() {
		return lastuser;
	}

	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}

	public double getHours()
	{
		return hours;
	}

	public void setHours(double hours)
	{
		this.hours = hours;
	}

	public int getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(int empId)
	{
		this.employeeId = empId;
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

	public String getFirsname() {
		return firsname;
	}

	public void setFirsname(String firsname) {
		this.firsname = firsname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getPickedDate()
	{
		return pickedDate;
	}

	public void setPickedDate(Date pickedDate)
	{
		this.pickedDate = pickedDate;
	}

	

}
