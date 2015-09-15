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
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

@Controller
public class TimesheetController {
	
	static SessionFactory factory = getSessionFactory();
	
	@RequestMapping(value = "/timesheet", method = RequestMethod.GET)
	public String helloWorld(Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {		
		return "Timesheet";
	}
	
	
	 @RequestMapping(value = "/timesheet", method = RequestMethod.POST)
	   public String addStudent(Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {
	   System.out.println(employee.getUser());
	   System.out.println(employee.getPwd());
	    
	   addEmployee(employee.getUser());
	      return "Timesheet";
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

		public static Integer addEmployee(String login) {
			Session session = factory.openSession();
			Transaction tx = null;
			Integer employeeID = null;
			Integer storeID = null;
			try {
				tx = session.beginTransaction();
				Employees emp = new Employees("aaqib", "rehman", null, null,
						login, null, "31 N Street, Allentown",
						null, null);
				//assign employees to store
				HashSet<Store> storeSet = new HashSet<Store>();
				
				Store store = new Store("Reading","Cell Expert Systems",null);
				storeSet.add(store);
				emp.setStores(storeSet);
				//indpendent tables
				employeeID = (Integer) session.save(emp);
				storeID = (Integer) session.save(store);
				tx.commit();
				///////////////////
				
				
				
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
