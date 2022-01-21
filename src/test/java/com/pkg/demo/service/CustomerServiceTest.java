package com.pkg.demo.service;

import com.pkg.demo.dao.CustomerRepository;
import com.pkg.demo.model.Customer;
import com.pkg.demo.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @Test
    public void should_find_all_customers() {
        //Given
        Customer customer = givencustomer();
        List<Customer> customers = Collections.singletonList(customer);
        when(customerRepositoryMock.findAll()).thenReturn(customers);
        //When
        //Then
        assertEquals(customer, customerService.findAll().get(0));
    }

    @Test
    public void should_find_customer_by_id() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.findById(ID)).thenReturn(Optional.of(customer));
        //When
        //Then
        assertEquals(customer, customerService.findById(ID));
    }

    @Test
    public void should_return_null_find_customer_by_id() {
        //Given
        when(customerRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(customerService.findById(ID));
    }

    @Test
    public void should_save_customer() {
        //Given
        Customer customer = givencustomer();
        customer.setId(0);
        when(customerRepositoryMock.save(customer)).thenReturn(customer);
        //When
        //Then
        assertEquals(customer, customerService.save(customer));
    }

    @Test
    public void should_return_null_save_customer() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.save(customer)).thenReturn(customer);
        //When
        //Then
        assertNull(customerService.save(customer));
    }

    @Test
    public void should_update_customer() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepositoryMock.save(customer)).thenReturn(customer);
        //When
        //Then
        assertEquals(customer, customerService.update(customer));
    }

    @Test
    public void should_return_null_update_basic() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(Optional.empty());
        when(customerRepositoryMock.save(customer)).thenReturn(customer);
        //When
        //Then
        assertNull(customerService.update(customer));
    }

    @Test
    public void should_delete_basic_by_id() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(Optional.of(customer));
        //When
        doNothing().when(customerRepositoryMock).deleteById(ID);
        //Then
        assertEquals(customer, customerService.deleteById(ID));
    }

    @Test
    public void should_return_null_delete_basic_by_id() {
        //Given
        Customer customer = givencustomer();
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(customerRepositoryMock).deleteById(ID);
        //Then
        assertNull(customerService.deleteById(ID));
    }

    private Customer givencustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        return customer;
    }
}
