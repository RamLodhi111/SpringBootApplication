package com.ram.demo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "Employee")
public class Employee {

	@Size(min = 3)
	private String name;
	@Id
	@NotNull
	@ApiModelProperty(notes = "This attribute is treated as Id and should not be null")
	private String id;
	@Min(value = 21)
	@ApiModelProperty(notes = "This attribute is for age and should contains only numeric values and should be greater than 21")
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
