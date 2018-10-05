package com.registration.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registration.employee.entity.Employee;

@Repository(value="employeedao")
public interface EmployeeDao  extends JpaRepository<Employee, Integer>{

}
