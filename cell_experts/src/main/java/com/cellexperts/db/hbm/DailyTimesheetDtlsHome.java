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
 * Home object for domain model class DailyTimesheetDtls.
 * @see com.cellexperts.db.hbm.DailyTimesheetDtls
 * @author Hibernate Tools
 */
public class DailyTimesheetDtlsHome {

	private static final Log log = LogFactory
			.getLog(DailyTimesheetDtlsHome.class);

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

	public void persist(DailyTimesheetDtls transientInstance) {
		log.debug("persisting DailyTimesheetDtls instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DailyTimesheetDtls instance) {
		log.debug("attaching dirty DailyTimesheetDtls instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DailyTimesheetDtls instance) {
		log.debug("attaching clean DailyTimesheetDtls instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DailyTimesheetDtls persistentInstance) {
		log.debug("deleting DailyTimesheetDtls instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DailyTimesheetDtls merge(DailyTimesheetDtls detachedInstance) {
		log.debug("merging DailyTimesheetDtls instance");
		try {
			DailyTimesheetDtls result = (DailyTimesheetDtls) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DailyTimesheetDtls findById(
			com.cellexperts.db.hbm.DailyTimesheetDtlsId id) {
		log.debug("getting DailyTimesheetDtls instance with id: " + id);
		try {
			DailyTimesheetDtls instance = (DailyTimesheetDtls) sessionFactory
					.getCurrentSession().get(
							"com.cellexperts.db.hbm.DailyTimesheetDtls", id);
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

	public List findByExample(DailyTimesheetDtls instance) {
		log.debug("finding DailyTimesheetDtls instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria("com.cellexperts.db.hbm.DailyTimesheetDtls")
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
