package com.ram.demo.resurce;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ram.demo.exception.EmployeeNotFoundException;
import com.ram.demo.model.Employee;
import com.ram.demo.service.EmployeeService;

@RestController
public class HomeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employeeList = employeeService.getAllEmployee();
		ResponseEntity<List<Employee>> response = null;
		if (employeeList.isEmpty()) {
			response = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee employee = employeeService.getEmployeeById(id);
		ResponseEntity<Employee> response = null;
		if (null == employee) {
			response = new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
			throw new EmployeeNotFoundException("ID is : "+id);
		} else {
			response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/employee/hateos/{id}")
	public ResponseEntity<Resource<Employee>> getEmployeeByIdHateos(@PathVariable String id) {
		
		 Employee employee = employeeService.getEmployeeById(id);
		 ResponseEntity<Resource<Employee>> response = null;
	
		//Hateos -- send link to all users in response of this method 
		Resource<Employee> employeeResource = new Resource<Employee>(employee);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.
				methodOn(this.getClass()).getAllEmployees());
		employeeResource.add(linkTo.withRel("all-users"));
		response = new ResponseEntity<Resource<Employee>>(employeeResource, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Object> addEmployee(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeService.addEmployee(employee);
		ResponseEntity<Object> response = null;
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();
		response = ResponseEntity.created(location).build();

		return response;
	}
}
