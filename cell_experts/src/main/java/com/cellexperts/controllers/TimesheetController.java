package com.cellexperts.controllers;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

@Controller
public class TimesheetController
{

	static SessionFactory factory = getSessionFactory();

	/*************************************************************************
	 * author: abdulhafeez
	 *  date: Dec 20, 2015 
	 * This page is only presented to
	 * admin.This method is invoked by spring security if a page is requested
	 * with ADMIN prefix and has ADMIN privileges. Only logged in ADMIN can
	 * access this resource. Spring security blocks this url but once logged in
	 * as admin,this page becomes accessible through GET request.
	 ************************************************************************/
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage()
	{

		ModelAndView model = new ModelAndView();
		System.out.println("admin page requested");
		model.addObject("title", "Welcome to Cell Expert Admin Dash Board");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("adminDashBoard");

		return model;

	}

	/*************************************************************************
	 * author: abdulhafeez date: Dec 20, 2015 
	 * default page shown to every user
	 * who accesses this application
	 ************************************************************************/
	@RequestMapping(value =
	{ "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage()
	{

		System.out.println("default page opening");
		ModelAndView model = new ModelAndView();

		model.addObject("title", "Welcome to Cell Experts");
		model.addObject("message", "This is default page!");
		model.setViewName("welcome");
		return model;

	}

	/*
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET) public
	 * String logout(Model model, @ModelAttribute("user") User user,
	 * BindingResult result) { //Session session = factory.openSession(); //
	 * Employees emp = (Employees) //
	 * session.get("com.cellexperts.db.hbm.Employees", new Integer(10002));
	 * System.out.println("logout"+user.getUsername());
	 * 
	 * // addEmployee(employee); return "logout"; }
	 */

	/*************************************************************************
	 * author: abdulhafeez
	 * date:   Dec 20, 2015
	 * Every User has to login with user role or admin role or both.
	 ************************************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request)
	{

		ModelAndView model = new ModelAndView();
		if (error != null)
		{
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null)
		{
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key)
	{

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException)
		{
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException)
		{
			error = exception.getMessage();
		} else
		{
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	/*************************************************************************
	 * author: abdulhafeez
	 * date:   Dec 20, 2015
	 * A non ADMIN_ROLE user will be denied admin resources and will be directed
	 * to this page.
	 ************************************************************************/
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied()
	{

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/testJsp", method = RequestMethod.GET)
	public ModelAndView testJsp()
	{

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
			System.out.println("testing testJsp for look and feel");
			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("testJsp");
		return model;

	}

	// TODO sessionfactory method, may be not needed
	private static SessionFactory getSessionFactory() throws ExceptionInInitializerError
	{
		SessionFactory factory;
		try
		{
			// loads configuration and mappings
			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

			// builds a session factory from the service registry
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return factory;
	}

	public static Integer addEmployee(Employees employee)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		Integer storeID = null;
		try
		{
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

}
