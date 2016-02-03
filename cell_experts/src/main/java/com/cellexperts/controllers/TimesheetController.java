package com.cellexperts.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cellexperts.beans.Employee;
import com.cellexperts.beans.TimeSheetBean;
import com.cellexperts.db.hbm.DailyTimesheetDtls;
import com.cellexperts.db.hbm.Employees;
import com.cellexperts.service.CellExpertsService;
import com.cellexperts.util.DateUtils;
//TODO make username one across the controller
//TODO process the page through only one method
//TODO change the name of the fields to be more sensible
//TODO Cache the list and dropdowns
//TODO make efficient use of the timesheetbean
//TODO cache needs to be updated when adding new employee
//TODO make separate application context later
//TODO handle exception for duplicate entry
//TODO experiment with autowored setter and getters by removing them
//TODO implement validators later

@Controller
public class TimesheetController
{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	CellExpertsService cellExpertService;
	private List<Employees> employeeListCache; // TODO make it dynamic

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

		model.setViewName("/user/testJsp");
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

			// /just for testing
			/*Calendar c = Calendar.getInstance();
			c.set(2016, 0, 26);
			Date dateFrom = c.getTime();
			c.set(2016, 0, 27);
			Date dateTo = c.getTime();
			List<DailyTimesheetDtls> timesheetsList = cellExpertService.getAllTimeSheets(10008, dateFrom, dateTo);
			*/

			String mapping = request.getServletPath().replace("/", "");
			if ("admin".equals(mapping))
				model.setViewName("/admin/adminDashBoard");
			if ("adminRegisterEmployee".equals(mapping))
			{
				Employee emp = new Employee();
				emp.setFirstName("Hafeez");
				model.addObject("employee", emp);
				model.setViewName("/admin/adminRegisterEmployee");
			}
			
			if ("adminDailyTimesheetReport".equals(mapping))
			{
				List<Employee> empList = new ArrayList<Employee>();
				Employee emp1 = new Employee();
				Employee emp2 = new Employee(); 
				emp1.setFirstName("Hafeez1");
				emp1.setLastName("Abdul1");
				emp2.setFirstName("AHafeez2");
				emp2.setLastName("Abdul2");
				empList.add(emp1);
				empList.add(emp2);
				model.addObject("employee", empList);
				model.setViewName("/admin/adminDailyTimesheetReport");
			}
			
			if ("adminTimesheetLandingPage".equals(mapping))
			{
				if (employeeListCache == null || employeeListCache.size() == 0)
				{
					List<Employees> empList = cellExpertService.getAllEmployees(); // get
																					// all
																					// employees
					// put the list in cache also for later uses
					employeeListCache = empList;
				}
				model.addObject("employee", new Employee());
				model.addObject("empList", employeeListCache);
				model.addObject("timesheetbean", new TimeSheetBean());
				model.setViewName("/admin/adminTimesheetLandingPage");
			}

		} else
			model.setViewName("403");

		return model;

	}

	@RequestMapping(value =
	{ "/adminSelectEmployee" }, method = RequestMethod.GET)
	public ModelAndView getEmployee(HttpServletRequest request, @ModelAttribute("timesheetbean") TimeSheetBean timesheetbean, BindingResult result,
			@RequestParam(value = "dateselected", required = false) String dateselected, @RequestParam(value = "id", required = false) int id)
	{

		ModelAndView model = new ModelAndView();
		System.out.println("adminSelectEmployee page requested");
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken))
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
			DailyTimesheetDtls timesheetDtls;
			if (dateselected != null && !dateselected.isEmpty())
			{
				timesheetDtls = cellExpertService.getTimeSheet(id, dateselected);
				timesheetbean.setPickedDate(dateselected);
			} else
			{
				timesheetbean.setPickedDate(DateUtils.getTodaysDate().toString());
				timesheetDtls = cellExpertService.getTodaysTimesheet(id);
			}

			Employees emp = cellExpertService.findEmployee(id);

			model.addObject("employee", emp);
			// populate the timesheetbean for UI page
			timesheetbean.setEmployeeId(emp.getEmployeeId());
			timesheetbean.setFirstname(emp.getFirstName());
			timesheetbean.setLastname(emp.getLastName());
			timesheetbean.setLastuser(userDetail.getUsername());
			if (timesheetDtls != null)
			{
				timesheetbean.setCash(timesheetDtls.getCash());
				timesheetbean.setDay(timesheetDtls.getDay());
				timesheetbean.setHours(timesheetDtls.getHours());
				timesheetbean.setMinutes(timesheetDtls.getMinutes());
				timesheetbean.setNotes(timesheetDtls.getNotes());
				// TODO set other properties as well and add to model
			}
			model.addObject("timesheetbean", timesheetbean);
			// add the employees list to model as well to keep populating
			// dropdown
			model.addObject("empList", employeeListCache);

			model.setViewName("/admin/adminTimesheetLandingPage");

		} else
			model.setViewName("403");

		return model;

	}

	@RequestMapping(value =
	{ "/adminSaveTime", "/adminShowTimesheet" }, method = RequestMethod.POST)
	public ModelAndView saveTimeSheet(@ModelAttribute("timesheetbean") TimeSheetBean timesheetbean, BindingResult result, HttpServletRequest request)
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
				try
				{
					cellExpertService.saveDailyTimesheet(timesheetbean);
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // TODO log the successful save message in logger

				Employees emp = new Employees();
				emp.setFirstName(timesheetbean.getFirstname());
				emp.setLastName(timesheetbean.getLastname());

				model.addObject("employee", emp); // make the previously
													// selected employee
													// available after saving
				model.addObject("empList", employeeListCache); // TODO add the
																// cache to the
																// request.may
																// wanna add it
																// to seesion
				model.setViewName("/admin/adminTimesheetLandingPage");
			} else if ("adminShowTimesheet".equals(mapping))
			{

				model.setViewName("/admin/adminTimesheetLandingPage");
			} else
				model.setViewName("403");// TODO: This should be no resource
											// found page

		} else
			model.setViewName("403"); //

		return model;

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

				cellExpertService.createEmployee(employee);
				// Check here if employee is created successfully otherwise
				// throw usefull error message
				empList = searchEmployee(employee.getEmail()); // This is not a
																// good way,
																// first make
																// sure its
																// created then
																// pull record
																// for newly
																// employee
				model.addObject("empList", empList);
				employeeListCache = cellExpertService.getAllEmployees();// get
																		// updated
																		// list
																		// after
																		// creation

				model.addObject("msg", "employee register successfull with username " + employee.getEmail());
			}

			model.addObject("msg", "Please do not leave fields blank ");
			model.setViewName("/admin/adminRegisterEmployee");

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
			model.setViewName("/admin/findEmployees");

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

	// TODO: Remove this after testing

	private List<Employees> searchEmployee(String username)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		Criteria criteria = session.createCriteria(Employees.class);
		criteria.add(Restrictions.like("email", username));
		return criteria.list();

	}
}
