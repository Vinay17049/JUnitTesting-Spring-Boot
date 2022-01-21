package com.pkg.demo.mapper;

import com.pkg.demo.dto.CustomerDto;
import com.pkg.demo.model.Customer;
import com.pkg.demo.request.CreateRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CustomerMapperTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    @InjectMocks
    private CustomerMapper customerMapper;

    @Test
    public void should_map_customer_to_customerDto() {
        //Given
        Customer customer = givencustomer();
        //When
        CustomerDto customerDto = customerMapper.map(customer);
        //Then
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getName(), customerDto.getName());
    }

    @Test
    public void should_map_customerDto_to_customer() {
        //Given
        CustomerDto customerDto = givencustomerDto();
        //When
        Customer customer = customerMapper.map(customerDto);
        //Then
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getName(), customerDto.getName());
    }

    @Test
    public void should_map_createRequest_to_customer() {
        //Given
        CreateRequest createRequest = givenCreateRequest();
        //When
        Customer customer = customerMapper.map(createRequest);
        //Then
        assertEquals(customer.getName(), createRequest.getName());
    }

    @Test
    public void should_mao_customers_to_customerDtos() {
        //Given
        Customer customer = givencustomer();
        List<Customer> customers = Collections.singletonList(customer);
        //When
        List<CustomerDto> customerDtos = customerMapper.map(customers);
        //Then
        assertEquals(customers.size(), customerDtos.size());
        assertEquals(customerDtos.get(0).getId(), customers.get(0).getId());
        assertEquals(customerDtos.get(0).getName(), customers.get(0).getName());
    }

    private Customer givencustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        return customer;
    }

    private CustomerDto givencustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(ID);
        customerDto.setName(NAME);
        return customerDto;
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setName(NAME);
        return createRequest;
    }
}
