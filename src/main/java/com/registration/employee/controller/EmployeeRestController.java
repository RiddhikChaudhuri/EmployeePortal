package com.registration.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.registration.employee.entity.Employee;
import com.registration.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class EmployeeRestController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping(value="/addEmployee",produces = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployee(@RequestBody Employee employee) {
		String employeeName= employeeService.addEmployee(employee);
		return "Employee with Name"+employeeName+"Added to the Records";
	}
	
	@GetMapping(value="/listofEmployees")
	public List<Employee> listOfEmployees(){
		return employeeService.getListOfEmployees();
	}

}
