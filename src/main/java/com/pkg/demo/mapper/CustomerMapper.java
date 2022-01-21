package com.pkg.demo.mapper;

import com.pkg.demo.dto.CustomerDto;
import com.pkg.demo.model.Customer;
import com.pkg.demo.request.CreateRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public Customer map(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        return customer;
    }

    public Customer map(CreateRequest createRequest) {
        Customer customer = new Customer();
        customer.setName(createRequest.getName());
        return customer;
    }

    public List<CustomerDto> map(List<Customer> customers) {
        return customers
                .stream()
                .map(customer -> map(customer))
                .collect(Collectors.toList());
    }
}
