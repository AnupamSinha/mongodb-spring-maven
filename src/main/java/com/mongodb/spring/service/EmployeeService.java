package com.mongodb.spring.service;

import java.util.List;

import com.mongodb.spring.model.Employee;

/**
 * 
 *
 */
public interface EmployeeService {
	
	public Boolean addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(String empid);
	
	public Boolean deleteEmployee(String empid);
}
