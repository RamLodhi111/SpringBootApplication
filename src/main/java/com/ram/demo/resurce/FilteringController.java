package com.ram.demo.resurce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ram.demo.model.SomeBean;

@RestController
public class FilteringController {

	@GetMapping("/filter")
	public SomeBean getSomeBean() {
		SomeBean someBean = new SomeBean("Field1", "Field2",21);
		return someBean;
	}
	@GetMapping("/filter-list")
	public List<SomeBean> getSomeBeansList() {
		List<SomeBean> list = new ArrayList<SomeBean>();
		SomeBean someBean = new SomeBean("Field1", "Field2",21);
		SomeBean someBean2 = new SomeBean("Field2", "Field2",22);
		SomeBean someBean3 = new SomeBean("Field3", "Field3",23);
		list.add(someBean);
		list.add(someBean2);
		list.add(someBean3);
		return list;
	}
	@GetMapping("filter-dynamic")
	public MappingJacksonValue dynamicFiltering() {
		SomeBean someBean = new SomeBean("Field1", "Field2",21);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filterProvider);
		return mapping;
	}
	
	@GetMapping("filter-list-dynamic")
	public MappingJacksonValue dynamicFilteringList() {
		List<SomeBean> list = new ArrayList<SomeBean>();
		SomeBean someBean = new SomeBean("Field1", "Field2",21);
		SomeBean someBean2 = new SomeBean("Field2", "Field2",22);
		SomeBean someBean3 = new SomeBean("Field3", "Field3",23);
		list.add(someBean);
		list.add(someBean2);
		list.add(someBean3);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filterProvider);
		return mapping;
	}
}
