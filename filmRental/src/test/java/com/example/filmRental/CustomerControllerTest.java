package com.example.filmRental;


import com.example.filmRental.Controller.CustomerController;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        when(customerService.getAllCustomers()).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getAllCustomers();
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));
        ResponseEntity<Customer> response = customerController.getCustomerById(1L);
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetCustomersByFirstName() {
        when(customerService.getCustomersByFirstName("John")).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getCustomersByFirstName("John");
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomersByLastName() {
        when(customerService.getCustomersByLastName("Doe")).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getCustomersByLastName("Doe");
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomersByEmail() {
        when(customerService.getCustomersByEmail("john@example.com")).thenReturn(List.of(new Customer()));
        ResponseEntity<List<Customer>> response = customerController.getCustomersByEmail("john@example.com");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetActiveCustomers() {
        when(customerService.getActiveCustomers()).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getActiveCustomers();
        assertEquals(1, result.size());
    }

    @Test
    void testGetInactiveCustomers() {
        when(customerService.getInactiveCustomers()).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getInactiveCustomers();
        assertEquals(1, result.size());
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerService.saveCustomerWithReferences(customer)).thenReturn(customer);

        ResponseEntity<String> response = customerController.saveCustomer(customer);

        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerService).deactivateCustomer(1L);
        ResponseEntity<Void> response = customerController.deleteCustomer(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testAssignAddressToCustomer() {
        Customer customer = new Customer();
        when(customerService.assignAddress(1L, 10L)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.assignAddressToCustomer(1L, 10L);
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetCustomersByCity() {
        when(customerService.getCustomersByCity("Chennai")).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getCustomersByCity("Chennai");
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomersByCountry() {
        when(customerService.getCustomersByCountry("India")).thenReturn(List.of(new Customer()));
        List<Customer> result = customerController.getCustomersByCountry("India");
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateFirstName() {
        Customer customer = new Customer();
        when(customerService.updateFirstName(1L, "Jane")).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.updateFirstName(1L, "Jane");
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateLastName() {
        Customer customer = new Customer();
        when(customerService.updateLastName(1L, "Smith")).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.updateLastName(1L, "Smith");
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateEmail() {
        Customer customer = new Customer();
        when(customerService.updateEmail(1L, "jane@example.com")).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.updateEmail(1L, "jane@example.com");
        assertEquals(customer, response.getBody());
    }

    @Test
    void testAssignStoreToCustomer() {
        Customer customer = new Customer();
        when(customerService.assignStore(1L, 2L)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.assignStoreToCustomer(1L, 2L);
        assertEquals(customer, response.getBody());
    }
}

