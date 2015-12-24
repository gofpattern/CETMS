package com.cellexperts.interfaces;

import org.joda.time.Period;

public interface ITimeReports {
	
	public void findTimesheet(int employeeId);
	public void findTimesheet(int employeeId,int date);
	public void findTimesheet(int employeeId,int dateFrom,int dateThru);
	
	/**
	 * @param employeeId
	 * @param period can be of type day, month, year.
	 */
	public void findTimesheet(int employeeId,Period period);
	public void editTimesheet(int employeeId,int date);
	

}
