package com.ram.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.demo.model.Employee;
import com.ram.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent())
				return employee.get();
		else 
				return null;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
		Employee insertedEMp = employeeRepository.insert(employee);
		return insertedEMp;
	}

}
