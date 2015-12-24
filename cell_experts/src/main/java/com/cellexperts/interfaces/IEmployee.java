package com.cellexperts.interfaces;

import java.util.List;

import com.cellexperts.beans.Employee;
import com.cellexperts.db.hbm.Employees;

/**
 * @author ahafeez
 *
 */
public interface IEmployee {
	
	public Employees searchEmployee(int empId);
	public List<Employees> searchEmployee(String pattern);
	public int createEmployee(Employee employee); //TODO return type can be Employees hb bean intead of just ID
	

}
