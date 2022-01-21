package com.pkg.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkg.demo.dao.CustomerRepository;
import com.pkg.demo.model.Customer;
import com.pkg.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@Override
	public Customer findById(int id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			return null;
		}
		return customer.get();
	}

	@Override
	public Customer save(Customer customer) {
		if(customer.getId() != 0) {
			return null;
		}
		return customerRepository.save(customer);
	}

	@Override
	public Customer deleteById(int id) {
		Customer customer = findById(id);
		if(customer == null) {
			return null;
		}
		customerRepository.deleteById(id);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		customer = findById(customer.getId());
		if(customer == null) {
			return null;
		}
		return customerRepository.save(customer);
	}
}
