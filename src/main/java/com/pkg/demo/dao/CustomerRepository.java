package com.pkg.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkg.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

}
