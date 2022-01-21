package com.pkg.demo.controller;

import com.pkg.demo.controller.helper.JsonHelper;
import com.pkg.demo.dto.CustomerDto;
import com.pkg.demo.dto.CustomersDto;
import com.pkg.demo.mapper.CustomerMapper;
import com.pkg.demo.model.Customer;
import com.pkg.demo.request.CreateRequest;
import com.pkg.demo.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerMvcTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    private static final String BASIC_URL = "/customers";
    private static final String BASIC_ID_URL = "/customers/{id}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    @Test
    public void should_find_all_customers_returns_200_nominal_case() throws Exception {
        //Given
        Customer customer = givencustomer();
        List<Customer> customers = Collections.singletonList(customer);
        when(customerService.findAll()).thenReturn(customers);
        CustomerDto customerDto = givencustomerDto();
        List<CustomerDto> customerDtos = Collections.singletonList(customerDto);
        when(customerMapper.map(customers)).thenReturn(customerDtos);
        CustomersDto customersDto = new CustomersDto(customerDtos);
        //When
        String expected = JsonHelper.toJson(customersDto).orElse("");
        //Then
        mockMvc.perform(get(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_find_basic_by_id_returns_200_nominal_case() throws Exception {
        //Given
        Customer customer = givencustomer();
        when(customerService.findById(ID)).thenReturn(customer);
        CustomerDto customerDto = givencustomerDto();
        when(customerMapper.map(customer)).thenReturn(customerDto);
        //When
        String expected = JsonHelper.toJson(customerDto).orElse("");
        //Then
        mockMvc.perform(get(BASIC_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_save_customer_returns_200_nominal_case() throws Exception {
        //Given
        CreateRequest createRequest = givenCreateRequest();
        Customer customer = givencustomer();
        CustomerDto customerDto = givencustomerDto();
        when(customerMapper.map(createRequest)).thenReturn(customer);
        when(customerService.save(customer)).thenReturn(customer);
        when(customerMapper.map(customer)).thenReturn(customerDto);
        //When
        String createRequestJson = JsonHelper.toJson(createRequest).orElse("");
        String expected = JsonHelper.toJson(customerDto).orElse("");
        //Then
        mockMvc.perform(post(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_customer_by_id_returns_200_nominal_case() throws Exception {
        //Given
        Customer customer = givencustomer();
        when(customerService.deleteById(ID)).thenReturn(customer);
        CustomerDto customerDto = givencustomerDto();
        when(customerMapper.map(customer)).thenReturn(customerDto);
        //When
        String expected = JsonHelper.toJson(customerDto).orElse("");
        //Then
        mockMvc.perform(delete(BASIC_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_update_customer_returns_200_nominal_case() throws Exception {
        //Given
        Customer customer = givencustomer();
        CustomerDto customerDto = givencustomerDto();
        when(customerMapper.map(customerDto)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);
        when(customerMapper.map(customer)).thenReturn(customerDto);
        //When
        String expected = JsonHelper.toJson(customerDto).orElse("");
        //Then
        mockMvc.perform(put(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setName(NAME);
        return createRequest;
    }

    private CustomerDto givencustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(ID);
        customerDto.setName(NAME);
        return customerDto;
    }

    private Customer givencustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        return customer;
    }
}
