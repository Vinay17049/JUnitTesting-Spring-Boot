package com.pkg.demo.initializer;

import com.pkg.demo.model.Customer;
import com.pkg.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerInitializer implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("pkg");
        customerService.save(customer);
    }
}
