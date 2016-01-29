package com.cellexperts.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.TimeSheetBean;
import com.cellexperts.dao.CellExpertsDao;
import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.DailyTimesheetDtlsId;
import com.cellexperts.db.hbm.EmployeeTimesheet;
import com.cellexperts.db.hbm.EmployeeTimesheetId;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.util.DateUtils;

/**
 * @author ahafeez
 * 
 */
@Component
public class CellExpertsServiceImpl implements CellExpertsService
{

	CellExpertsDao cellExpertsDao;

	public CellExpertsDao getCellExpertsDao()
	{
		return cellExpertsDao;
	}

	public void setCellExpertsDao(CellExpertsDao cellExpertsDao)
	{
		this.cellExpertsDao = cellExpertsDao;
	}

	public void registerEmployee()
	{
		// TODO Auto-generated method stub

	}

	/* ******************************************************
	 * 
	 * SECTION: CellExpertsService API methods
	 * 
	 * *******************************************************
	 */
	public Integer saveDailyTimesheet(TimeSheetBean timesheetbean)
	{
		EmployeeTimesheet timesheet = new EmployeeTimesheet();
		EmployeeTimesheetId empTimesheetId = new EmployeeTimesheetId();
		empTimesheetId.setWeekendDt(DateUtils.getWeekendDate());
		empTimesheetId.setEmployeeId(timesheetbean.getEmployeeId());
		timesheet.setId(empTimesheetId);

		DailyTimesheetDtlsId employeeTimeSheetDtlsId = new DailyTimesheetDtlsId();
		employeeTimeSheetDtlsId.setEmployeeId(timesheetbean.getEmployeeId());

		employeeTimeSheetDtlsId.setTodayDt(DateUtils.getTodaysDate());

		employeeTimeSheetDtlsId.setWeekendDt(empTimesheetId.getWeekendDt());

		DailyTimesheetDtls timesheetDtls = new DailyTimesheetDtls();
		timesheetDtls.setDay(DateUtils.getDayOfWeek());// TODO: timesheetbean.getDay()
		timesheetDtls.setHours(timesheetbean.getHours());
		// timesheetDtls.setOvertime(new Long(0));
		timesheetDtls.setDayOff(false);
		timesheetDtls.setCash(timesheetbean.getCash());
		timesheetDtls.setMinutes(timesheetbean.getMinutes());
		timesheetDtls.setNotes(timesheetbean.getNotes());
		timesheetDtls.setId(employeeTimeSheetDtlsId);
		timesheetDtls.setLastuser(timesheetbean.getLastuser());
		timesheetDtls.setEmployeeTimesheet(timesheet);

		cellExpertsDao.saveDailyTimeSheet(timesheetDtls);
		return null;
	}

	public List<Employees> searchEmployee(String pattern)
	{

		// TODO Auto-generated method stub
		return null;

	}

	public List<Employees> getAllEmployees()
	{
		// TODO Auto-generated method stub
		return cellExpertsDao.findAllEmployees();
	}

	public Employees findEmployee(int id)
	{
		return cellExpertsDao.findEmployeeById(id);

	}

	public int createEmployee(Employee employee)
	{
		Integer employeeID = null;
		Employees emp = new Employees();
		emp.setFirstName(employee.getFirstName());
		emp.setEmail(employee.getEmail());
		emp.setAddress(employee.getAddress());
		emp.setLastName(employee.getLastName());

		return cellExpertsDao.saveEmployee(emp);

	}

	/* (non-Javadoc)
	 * @see com.cellexperts.service.CellExpertsService#getAllTimeSheets(java.util.Date)
	 */
	public List<DailyTimesheetDtls> getAllTimeSheets(Date date)
	{
		return cellExpertsDao.getAllDailyTimeSheets(date);
		
	}

}
