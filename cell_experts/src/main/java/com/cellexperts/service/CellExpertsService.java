package com.cellexperts.service;

import java.util.List;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.TimeSheetBean;
import com.cellexperts.db.hbm.Employees;

public interface CellExpertsService
{
	/**
	 * creates and register a new employee
	 * @return employee id
	 */
	public int createEmployee(Employee employee);
	/**
	 * @param timesheetbean
	 * @return saves or updates this timesheet
	 */
	public Integer saveDailyTimesheet(TimeSheetBean timesheetbean);
	
	
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

}
