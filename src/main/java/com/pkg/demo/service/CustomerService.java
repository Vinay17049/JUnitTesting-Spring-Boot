package com.pkg.demo.service;

import java.util.List;

import com.pkg.demo.model.Customer;

public interface CustomerService
{
	List<Customer> findAll();
	Customer findById(int id);
	Customer save(Customer theCustomer);
	Customer deleteById(int id);
	Customer update(Customer theCustomer);
}