package com.ram.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ram.demo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,String> {

	public List<Employee> findByName(String name);
}
