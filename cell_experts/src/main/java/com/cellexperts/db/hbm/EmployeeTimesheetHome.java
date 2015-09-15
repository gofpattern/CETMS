package com.cellexperts.db.hbm;

// Generated Sep 9, 2015 10:52:46 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class EmployeeTimesheet.
 * @see com.cellexperts.db.hbm.EmployeeTimesheet
 * @author Hibernate Tools
 */
public class EmployeeTimesheetHome {

	private static final Log log = LogFactory
			.getLog(EmployeeTimesheetHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(EmployeeTimesheet transientInstance) {
		log.debug("persisting EmployeeTimesheet instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(EmployeeTimesheet instance) {
		log.debug("attaching dirty EmployeeTimesheet instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EmployeeTimesheet instance) {
		log.debug("attaching clean EmployeeTimesheet instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EmployeeTimesheet persistentInstance) {
		log.debug("deleting EmployeeTimesheet instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EmployeeTimesheet merge(EmployeeTimesheet detachedInstance) {
		log.debug("merging EmployeeTimesheet instance");
		try {
			EmployeeTimesheet result = (EmployeeTimesheet) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EmployeeTimesheet findById(
			com.cellexperts.db.hbm.EmployeeTimesheetId id) {
		log.debug("getting EmployeeTimesheet instance with id: " + id);
		try {
			EmployeeTimesheet instance = (EmployeeTimesheet) sessionFactory
					.getCurrentSession().get(
							"com.cellexperts.db.hbm.EmployeeTimesheet", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EmployeeTimesheet instance) {
		log.debug("finding EmployeeTimesheet instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.cellexperts.db.hbm.EmployeeTimesheet")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
