package com.cellexperts.dao;

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
import com.cellexperts.db.hbm.EmployeeTimesheet;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

@Component
public class CellExpertsDaoImpl implements CellExpertsDao
{
	@Autowired
	SessionFactory sessionFactory;

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
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#findAllEmployees()
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
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getDailyTimeSheet(int, int)
	 */
	public DailyTimesheetDtls getDailyTimeSheet(int empyeeId, int date)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getAllDailyTimeSheets(int, int, int)
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(int empyeeId, int dateFrom, int dateThru)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cellexperts.dao.CellexpertsDao#getAllDailyTimeSheets(int)
	 */
	public List<DailyTimesheetDtls> getAllDailyTimeSheets(int date)
	{
		return null;
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

	public void saveDailyTimeSheet(DailyTimesheetDtls timesheetDtls)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.saveOrUpdate(timesheetDtls.getEmployeeTimesheet());
			session.saveOrUpdate(timesheetDtls);
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
