package com.example.filmRental.Service;
 
import com.example.filmRental.Entity.Address;
import com.example.filmRental.Repository.AddressRepository;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Store;
import com.example.filmRental.Repository.CustomerRepository;
import com.example.filmRental.Repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContaining(firstName);
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContaining(lastName);
    }

    public List<Customer> getCustomersByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(true);
    }

    public List<Customer> getInactiveCustomers() {
        return customerRepository.findByActive(false);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deactivateCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setActive(false);
        customerRepository.save(customer);
    }

    public Customer assignAddress(Long customerId, Long addressId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        customer.setAddress(address);
        return customerRepository.save(customer);
    }
    
    public Customer saveCustomerWithReferences(Customer customer) {
        // Fetch and assign existing Store
        if (customer.getStore() != null && customer.getStore().getStoreId() != null) {
            Store store = storeRepository.findById(customer.getStore().getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            customer.setStore(store);
        } else {
            throw new RuntimeException("Store ID must be provided");
        }

        // Fetch and assign existing Address
        if (customer.getAddress() != null && customer.getAddress().getAddressId() != null) {
            Address address = addressRepository.findById(customer.getAddress().getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            customer.setAddress(address);
        } else {
            throw new RuntimeException("Address ID must be provided");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByAddressCityCity(city);
    }

    public List<Customer> getCustomersByCountry(String country) {
        return customerRepository.findByAddressCityCountryCountry(country);
    }

    public Customer updateFirstName(Long id, String firstName) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstName(firstName);
        return customerRepository.save(customer);
    }

    public Customer updateLastName(Long id, String lastName) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setLastName(lastName);
        return customerRepository.save(customer);
    }

    public Customer updateEmail(Long id, String email) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setEmail(email);
        return customerRepository.save(customer);
    }

    public Customer assignStore(Long id, Long storeId) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        customer.setStore(store);
        return customerRepository.save(customer);
    }
}