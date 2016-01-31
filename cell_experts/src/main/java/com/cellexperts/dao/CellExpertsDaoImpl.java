package com.cellexperts.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.DailyTimesheetDtlsId;
import com.cellexperts.db.hbm.EmployeeTimesheet;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;
import com.cellexperts.util.DateUtils;

@Component
public class CellExpertsDaoImpl implements CellExpertsDao
{
	@Autowired
	SessionFactory sessionFactory;

	/* ************************************************************
	 * save Employee
	 ***************************************************************/
	public Integer saveEmployee(Employees employee) // TODO: handle duplicate entry
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try
		{
			tx = session.beginTransaction();
			employeeID = (Integer) session.save(employee);
			tx.commit();

		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		return employeeID;
	}

	/*
	 * find All employees
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Employees> findAllEmployees()
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employees.class);
		List<Employees> employees = criteria.list();
		session.close();
		return employees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#findEmployeeById(int)
	 */
	public Employees findEmployeeById(int id)
	{

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Employees.class);
		criteria.add(Restrictions.like("employeeId", id));
		@SuppressWarnings("unchecked")
		List<Employees> employeesList = (List<Employees>) criteria.list();
		session.close(); // remember to close session
		if (employeesList.size() > 0)
			return employeesList.get(0);
		else
			return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#findEmployeeByLastName(java.lang.String )
	 */
	public List<Employees> findEmployeeByLastName(String lastname)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#findEmployeeByFirstName(java.lang. String)
	 */
	public List<Employees> findEmployeeByFirstName(String lastname)
	{
		return null;
	}

	/*
	 * 
	 * 
	 * Daily Timesheet for an employee for the given date
	 */
	public DailyTimesheetDtls getDailyTimeSheet(int employeeId, Date date)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(DailyTimesheetDtls.class);
		criteria.add(Restrictions.eq("id.todayDt", date));
		criteria.add(Restrictions.eq("id.employeeId", employeeId));
		List<DailyTimesheetDtls> timesheetdtlsList = (List<DailyTimesheetDtls>) criteria.list();
		session.close();
		if (timesheetdtlsList.size() > 0)
			return timesheetdtlsList.get(0);
		else
			return null; //TODO no time sheet found
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getAllDailyTimeSheets(int, int, int)
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(int employeeId, Date dateFrom, Date dateThru)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(DailyTimesheetDtls.class);
		criteria.add(Restrictions.ge("id.todayDt", dateFrom));
		criteria.add(Restrictions.le("id.todayDt", dateThru));
		criteria.add(Restrictions.eq("id.employeeId", employeeId));
		List<DailyTimesheetDtls> timesheetdtlsList = (List<DailyTimesheetDtls>) criteria.list();
		session.close();
		return timesheetdtlsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getAllDailyTimeSheets(int)
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(Date date)
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(DailyTimesheetDtls.class);
		criteria.add(Restrictions.eq("id.todayDt", date));
		List<DailyTimesheetDtls> timesheetdtlsList = (List<DailyTimesheetDtls>) criteria.list();
		session.close();
		return timesheetdtlsList;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getAllStores()
	 */
	public List<Store> getAllStores()
	{
		return null;
	}

	public DailyTimesheetDtls saveDailyTimeSheet(DailyTimesheetDtls timesheetDtls)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.saveOrUpdate(timesheetDtls.getEmployeeTimesheet());
			session.saveOrUpdate(timesheetDtls);
			tx.commit();
			return timesheetDtls;

		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally
		{
			session.close();
		}

	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
