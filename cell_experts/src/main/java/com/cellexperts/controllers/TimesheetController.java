package com.cellexperts.controllers;

import java.util.HashSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.User;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.EmployeesHome;
import com.cellexperts.db.hbm.Store;

@Controller
public class TimesheetController {

	static SessionFactory factory = getSessionFactory();

	@RequestMapping(value = { "/timesheet", "/admin" }, method = RequestMethod.GET)
	public String helloWorld(Model model, @ModelAttribute("user") User user,
			BindingResult result) {
		System.out.println("admin page requested");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		//System.out.println(user.getPassword());

		return "adminLogin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String addStudent(Model model, @ModelAttribute("user") User user,
			BindingResult result) {
		Session session = factory.openSession();
		// Employees emp = (Employees)
		// session.get("com.cellexperts.db.hbm.Employees", new Integer(10002));
		System.out.println("logging in");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		// addEmployee(employee);
		return "adminLoginSuccess";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, @ModelAttribute("user") User user,
			BindingResult result) {
		Session session = factory.openSession();
		// Employees emp = (Employees)
		// session.get("com.cellexperts.db.hbm.Employees", new Integer(10002));
		System.out.println("logout"+user.getUsername());

		// addEmployee(employee);
		return "logout";
	}

	private static SessionFactory getSessionFactory()
			throws ExceptionInInitializerError {
		SessionFactory factory;
		try {
			// loads configuration and mappings
			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

			// builds a session factory from the service registry
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return factory;
	}

	public static Integer addEmployee(Employees employee) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		Integer storeID = null;
		try {
			tx = session.beginTransaction();

			// assign employees to store
			HashSet<Store> storeSet = new HashSet<Store>();

			Store store = new Store("Reading", "Cell Expert Systems", null);
			storeSet.add(store);
			// emp.setStores(storeSet);
			// indpendent tables
			employeeID = (Integer) session.save(employee);

			// storeID = (Integer) session.save(store);
			tx.commit();
			// /////////////////

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

}
