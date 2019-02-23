package com.ram.demo.service;

import java.util.List;

import com.ram.demo.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeById(String id);
	
	public Employee addEmployee(Employee employee);
}
