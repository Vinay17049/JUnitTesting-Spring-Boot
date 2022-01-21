package com.pkg.demo.dao;

import com.pkg.demo.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    private static final String NAME = "NAME";
    private static final String UPDATED_NAME = "UPDATED_NAME";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_save_customer() {
        //Given
        Customer customer = givencustomer();
        customer.setId(0);
        //When
        customer = customerRepository.save(customer);
        //Then
        Customer actual = testEntityManager.find(Customer.class, customer.getId());
        assertEquals(actual, customer);
    }

    @Test
    public void should_find_customer_by_id() {
        //Given
        Customer customer = givencustomer();
        customer = testEntityManager.persist(customer);
        //When
        Optional<Customer> actual = customerRepository.findById(customer.getId());
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), customer);
    }

    @Test
    public void should_find_all_customers() {
        //Given
        Customer customer = givencustomer();
        customer = testEntityManager.persist(customer);
        //When
        List<Customer> customers = customerRepository.findAll();
        //Then
        assertThat(customers).contains(customer);
    }

    @Test
    public void should_delete_customer_by_id() {
        //Given
        Customer customer = givencustomer();
        customer = testEntityManager.persist(customer);
        //When
        customerRepository.deleteById(customer.getId());
        //Then
        Customer actual = testEntityManager.find(Customer.class, customer.getId());
        assertNull(actual);
    }

    @Test
    public void should_update_customer() {
        //Given
        Customer customer = givencustomer();
        customer = testEntityManager.persist(customer);
        Customer updatedCustomer = givenUpdatedcustomer();
        updatedCustomer.setId(customer.getId());
        //When
        updatedCustomer = customerRepository.save(updatedCustomer);
        //Then
        Customer actual = testEntityManager.find(Customer.class, customer.getId());
        assertEquals(actual, updatedCustomer);

    }

    private Customer givencustomer() {
        Customer customer = new Customer();
        customer.setId(0);
        customer.setName(NAME);
        return customer;
    }

    private Customer givenUpdatedcustomer() {
        Customer customer = new Customer();
        customer.setName(UPDATED_NAME);
        return customer;
    }
}
