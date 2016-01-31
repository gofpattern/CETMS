package com.cellexperts.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.TimeSheetBean;
import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.Employees;

public interface CellExpertsService
{
	/**
	 * creates and register a new employee
	 * 
	 * @return employee id
	 */
	public int createEmployee(Employee employee);

	/**
	 * @param timesheetbean
	 * @return saves or updates this timesheet
	 * @throws ParseException 
	 */
	public DailyTimesheetDtls saveDailyTimesheet(TimeSheetBean timesheetbean) throws ParseException;

	/**
	 * @param pattern
	 * @return list of employees matching the pattern
	 */
	public List<Employees> searchEmployee(String pattern);

	/**
	 * @return the cached list of all current emplyees
	 */
	public List<Employees> getAllEmployees();

	/**
	 * @param id
	 * @return a single employee with a given id
	 */
	public Employees findEmployee(int id);

	/**
	 * @param time
	 * @return
	 */
	public List<DailyTimesheetDtls> getAllTimeSheets(Date time);

	/**
	 * @param employeeId
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public List<DailyTimesheetDtls> getAllTimeSheets(int employeeId, Date dateFrom, Date dateTo);

	/**
	 * @param employeeId
	 * @returnd timesheeet detail with employee detail if exits in database
	 */
	public DailyTimesheetDtls getTodaysTimesheet(int employeeId);
	
	/**
	 * @param employeeId
	 * @param pickedDate 
	 * @return time sheet for the picked date for the selected employee
	 */	
	public DailyTimesheetDtls getTimeSheet(int employeeId, String pickedDate);

}
