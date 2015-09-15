package com.cellexperts.controllers;

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

@Controller
public class TimesheetController {
	
	@RequestMapping(value = "/timesheet", method = RequestMethod.GET)
	public String helloWorld(Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {		
		return "Timesheet";
	}
	
	
	 @RequestMapping(value = "/Timesheet", method = RequestMethod.POST)
	   public String addStudent(Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {
		 System.out.println(employee.getUser());
	   System.out.println(employee.getPwd());
	    
	      
	      return "Timesheet";
	 }

}
