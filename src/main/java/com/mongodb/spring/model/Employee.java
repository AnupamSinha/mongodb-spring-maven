package com.mongodb.spring.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * A simple POJO representing a Employee
 * 
 *
 */
@Document(collection="employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	private String empId;
	private String empName;
	private String empAddress;
	private String salary;
	private String empAge;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}

}
