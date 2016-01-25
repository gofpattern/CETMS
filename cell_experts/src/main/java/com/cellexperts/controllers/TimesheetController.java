package com.cellexperts.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.TimeSheetBean;
import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.DailyTimesheetDtlsId;
import com.cellexperts.db.hbm.EmployeeTimesheet;
import com.cellexperts.db.hbm.EmployeeTimesheetId;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.db.hbm.Store;

@Controller
public class TimesheetController
{
	@Autowired
	SessionFactory sessionFactory;
	static SessionFactory factory = getSessionFactory();

	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public ModelAndView testJsp()
	{

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

			System.out.println("testing testJsp for look and feel");
			model.addObject("username", userDetail.getUsername());
			System.out.println("creating emplooyee");
			// createEmployee();
			List<Employees> empList = searchEmployee("username");
			model.addObject("empList", empList);
		}

		model.setViewName("testJsp");
		return model;

	}

	/*************************************************************************
	 * author: abdulhafeez date: Dec 20, 2015 This page is only presented to
	 * admin.This method is invoked by spring security if a page is requested
	 * with ADMIN prefix and has ADMIN privileges. Only logged in ADMIN can
	 * access this resource. Spring security blocks this url but once logged in
	 * as admin,this page becomes accessible through GET request.
	 ************************************************************************/
	@RequestMapping(value =
	{ "/admin**", "/adminRegisterEmployee", "/adminTimesheetLandingPage" }, method = RequestMethod.GET)
	public ModelAndView adminPage(HttpServletRequest request)
	{

		ModelAndView model = new ModelAndView();
		System.out.println("admin page requested");
		model.addObject("title", "Welcome to Cell Expert Admin Dash Board");
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

			String mapping = request.getServletPath().replace("/", "");
			if ("admin".equals(mapping))
				model.setViewName("adminDashBoard");
			if ("adminRegisterEmployee".equals(mapping))
			{
				model.addObject("employee", new Employee());
				model.setViewName("adminRegisterEmployee");
			}
			if ("adminTimesheetLandingPage".equals(mapping))
			{
				List<Employees> empList = searchEmployee("%");
				model.addObject("empList", empList);
				model.setViewName("adminTimesheetLandingPage");
			}

		} else
			model.setViewName("403");

		return model;

	}
	
	@RequestMapping(value =
		{ "/adminSaveTime","/adminShowTimesheet" }, method = RequestMethod.POST)
		public ModelAndView saveTimeSheet(TimeSheetBean timesheetbean, BindingResult result, HttpServletRequest request)
		{
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

			String mapping = request.getServletPath().replace("/", "");
			
			if ("adminSaveTime".equals(mapping))
			{
				saveDailyTimesheet(timesheetbean);
			//	model.addObject("employee", emp);
				model.setViewName("adminTimesheetLandingPage");
			}
			if ("adminShowTimesheet".equals(mapping))
			{
				Employees emp = searchTimesheet("%");
				model.addObject("employee", emp);
				model.setViewName("adminTimesheetLandingPage");
			}
			else
				model.setViewName("403");//TODO: This shoud be no resource found page

		} else
			model.setViewName("403"); // 

		return model;
		
		}

	/*************************************************************************
	 * author: abdulhafeez
	 * date:   Dec 25, 2015
	 ************************************************************************/
	private Employees searchTimesheet(String string)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value =
	{ "/createEmployee" }, method = RequestMethod.POST)
	public ModelAndView createEmployee(Employee employee, BindingResult result, HttpServletRequest request)
	{
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView model = new ModelAndView();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			List<Employees> empList;
			// TODO Check for empty fields
			if (!employee.getEmail().isEmpty())
			{
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				model.addObject("username", userDetail.getUsername());

				createEmployee(employee);
				empList = searchEmployee(employee.getEmail());
				model.addObject("empList", empList);
				model.addObject("msg", "employee register successfull with username " + employee.getEmail());
			}

			model.addObject("msg", "Please do not leave fields blank ");
			model.setViewName("adminRegisterEmployee");

		} else
			model.setViewName("403");

		return model;

	}

	@RequestMapping(value =
	{ "/findEmployees" }, method = RequestMethod.GET)
	public ModelAndView findEmployees(ModelAndView model, HttpServletRequest request)
	{
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

			List<Employees> empList;
			String pattern = "%"; // TODO pattern for all string (common place)
			empList = searchEmployee(pattern);
			model.addObject("empList", empList);
			// model.addObject("msg", "List of All Employees ");

			model.addObject("username", userDetail.getUsername());

		} else
			model.setViewName("403");

		return model;

	}

	/*************************************************************************
	 * author: abdulhafeez date: Dec 20, 2015 default page shown to every user
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
	 * author: abdulhafeez date: Dec 20, 2015 Every User has to login with user
	 * role or admin role or both.
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

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request)
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
		model.setViewName("logout");

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
	 * author: abdulhafeez date: Dec 20, 2015 A non ADMIN_ROLE user will be
	 * denied admin resources and will be directed to this page.
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

	// TODO: Remove this after testing

	private List<Employees> searchEmployee(String username)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		Criteria criteria = session.createCriteria(Employees.class);
		criteria.add(Restrictions.like("email", username));
		return criteria.list();

	}

	public Integer saveDailyTimesheet(TimeSheetBean timesheetbean) // TODO Handle exception
														// for
														// duplicate Entry
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	
		
		EmployeeTimesheet timesheet = new EmployeeTimesheet();
		EmployeeTimesheetId empTimesheetId = new EmployeeTimesheetId();
		empTimesheetId.setWeekendDt(new Date("12/27/2015"));
		empTimesheetId.setEmployeeId(10025);
		timesheet.setId(empTimesheetId);
		
		DailyTimesheetDtlsId employeeTimeSheetDtlsId = new DailyTimesheetDtlsId();
		employeeTimeSheetDtlsId.setEmployeeId(10025);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2015, 12, 26);
		employeeTimeSheetDtlsId.setTodayDt(calendar.getTime());//timesheetbean.getToday() 12/26/2015
		calendar.set(2015, 12, 27);
		employeeTimeSheetDtlsId.setWeekendDt(calendar.getTime());//timesheetbean.getWeekend() 12/27/2015
		
		DailyTimesheetDtls timesheetDtls = new DailyTimesheetDtls();
		timesheetDtls.setDay("Saturday");//timesheetbean.getDay()
		timesheetDtls.setHours(new Long(3));
		timesheetDtls.setOvertime(new Long(0));
		timesheetDtls.setLeave(0);
		timesheetDtls.setId(employeeTimeSheetDtlsId);
		timesheetDtls.setEmployeeTimesheet(timesheet);
		Integer timesheetId =
		null;
		try
		{
			tx = session.beginTransaction();
//		 session.save(timesheet);
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
		return timesheetId;
	}

	public Integer createEmployee(Employee employee) // TODO Handle exception
														// for
	// duplicate Entry
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		Employees emp = new Employees();
		emp.setFirstName(employee.getFirstName());
		emp.setEmail(employee.getEmail());
		emp.setAddress(employee.getAddress());
		emp.setLastName(employee.getLastName());
		try
		{
			tx = session.beginTransaction();
			employeeID = (Integer) session.save(emp);
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
