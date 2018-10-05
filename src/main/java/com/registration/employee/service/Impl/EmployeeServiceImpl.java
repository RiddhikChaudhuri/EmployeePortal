package com.registration.employee.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.employee.dao.EmployeeDao;
import com.registration.employee.entity.Employee;
import com.registration.employee.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public String addEmployee(Employee employee) {
		employeeDao.save(employee);
		return employee.getFirstName();
	}

	@Override
	public List<Employee> getListOfEmployees() {
		return employeeDao.findAll();
	}
	
	
}
