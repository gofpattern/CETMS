package com.cellexperts.dao;

import java.util.Date;
import java.util.List;

import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

public interface CellExpertsDao
{
	/**
	 * @return id of the newly created or registered employee
	 */
	public Integer saveEmployee(Employees employee);

	/**
	 * @return
	 */
	public List<Employees> findAllEmployees();

	/**
	 * @param id
	 *            of the employee
	 * @return employee with employeeId id
	 */
	public Employees findEmployeeById(int id);

	/**
	 * @param lastname
	 *            of the employee
	 * @return list of emplyees
	 */
	public List<Employees> findEmployeeByLastName(String lastname);

	/**
	 * @param lastname
	 * @return
	 */
	public List<Employees> findEmployeeByFirstName(String lastname);

	/**
	 * @param empyeeId
	 * @param date
	 * @return timesheet for the date enter for the employee with employee id
	 */
	public DailyTimesheetDtls getDailyTimeSheet(int empyeeId, Date date);

	/**
	 * @param empyeeId
	 * @param dateFrom
	 * @param dateThru
	 * @return all time sheets of the emplyeeId from dateFrom to dateThru
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(int empyeeId, Date dateFrom, Date dateThru);

	/**
	 * @param date
	 * @return time sheet for each emplyee at the given date
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(Date date);

	/**
	 * @return
	 */
	public List<Store> getAllStores();

	/**
	 * @param timesheetDtls
	 * @return 
	 */
	public DailyTimesheetDtls saveDailyTimeSheet(DailyTimesheetDtls timesheetDtls);

}