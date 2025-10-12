package com.example.filmRental.Controller;
 
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }

    @GetMapping("/search/firstName")
    public List<Customer> getCustomersByFirstName(@RequestParam String firstName) {
        return customerService.getCustomersByFirstName(firstName);
    }

    @GetMapping("/search/lastName")
    public List<Customer> getCustomersByLastName(@RequestParam String lastName) {
        return customerService.getCustomersByLastName(lastName);
    }

    @GetMapping("/search/email")
    public ResponseEntity<List<Customer>> getCustomersByEmail(@RequestParam String email) {
        List<Customer> customers = customerService.getCustomersByEmail(email);
        if (customers.isEmpty()) throw new ResourceNotFoundException("No customers found with email: " + email);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/active")
    public List<Customer> getActiveCustomers() {
        return customerService.getActiveCustomers();
    }

    @GetMapping("/inactive")
    public List<Customer> getInactiveCustomers() {
        return customerService.getInactiveCustomers();
    }

    @PostMapping("/post")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        if (customer.getFirstName() == null || customer.getLastName() == null) {
            throw new CustomValidationException("First name and last name are required.");
        }
        customerService.saveCustomerWithReferences(customer);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/{addressId}")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable Long id, @PathVariable Long addressId) {
        return ResponseEntity.ok(customerService.assignAddress(id, addressId));
    }

    @GetMapping("/city/{city}")
    public List<Customer> getCustomersByCity(@PathVariable String city) {
        return customerService.getCustomersByCity(city);
    }

    @GetMapping("/country/{country}")
    public List<Customer> getCustomersByCountry(@PathVariable String country) {
        return customerService.getCustomersByCountry(country);
    }

    @PutMapping("/update/{id}/fn")
    public ResponseEntity<Customer> updateFirstName(@PathVariable Long id, @RequestBody String firstName) {
        return ResponseEntity.ok(customerService.updateFirstName(id, firstName));
    }

    @PutMapping("/update/{id}/ln")
    public ResponseEntity<Customer> updateLastName(@PathVariable Long id, @RequestBody String lastName) {
        return ResponseEntity.ok(customerService.updateLastName(id, lastName));
    }

    @PutMapping("/update/{id}/email")
    public ResponseEntity<Customer> updateEmail(@PathVariable Long id, @RequestBody String email) {
        return ResponseEntity.ok(customerService.updateEmail(id, email));
    }

    @PutMapping("/update/{id}/store")
    public ResponseEntity<Customer> assignStoreToCustomer(@PathVariable Long id, @RequestBody Long storeId) {
        return ResponseEntity.ok(customerService.assignStore(id, storeId));
    }
}