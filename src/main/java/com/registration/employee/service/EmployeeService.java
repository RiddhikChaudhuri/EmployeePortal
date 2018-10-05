package com.registration.employee.service;

import java.util.List;

import com.registration.employee.entity.Employee;

public interface EmployeeService {

	public String addEmployee(Employee employee);
	
	public List<Employee> getListOfEmployees();
}
