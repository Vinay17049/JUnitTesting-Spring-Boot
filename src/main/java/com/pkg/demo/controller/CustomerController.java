package com.pkg.demo.controller;

import java.util.List;

import com.pkg.demo.dto.CustomerDto;
import com.pkg.demo.dto.CustomersDto;
import com.pkg.demo.mapper.CustomerMapper;
import com.pkg.demo.request.CreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pkg.demo.model.Customer;
import com.pkg.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerMapper customerMapper;
	
	@GetMapping("")
	public CustomersDto findAll() {
		List<Customer> customers = customerService.findAll();
		List<CustomerDto> customerDtos = customerMapper.map(customers);
		return new CustomersDto(customerDtos);
	}
	
	@GetMapping("/{id}")
	public CustomerDto findById(@PathVariable int id) {
		Customer customer = customerService.findById(id);
		if(customer == null) {
			return null;
		}
		return customerMapper.map(customer);
	}
	
	@PostMapping("")
	public CustomerDto save(@RequestBody CreateRequest createRequest) {
		Customer customer = customerMapper.map(createRequest);
		customer = customerService.save(customer);
		if(customer == null) {
			return null;
		}
		return customerMapper.map(customer);
	}
	
	@DeleteMapping("/{id}")
	public CustomerDto deleteById(@PathVariable int id) {
		Customer customer = customerService.deleteById(id);
		if(customer == null) {
			return null;
		}
		return customerMapper.map(customer);
	}

	@PutMapping("")
	public CustomerDto update(@RequestBody CustomerDto customerDto) {
		Customer customer = customerMapper.map(customerDto);
		customer = customerService.update(customer);
		if(customer == null) {
			return null;
		}
		return customerMapper.map(customer);
	}
}
