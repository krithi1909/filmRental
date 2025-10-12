package com.example.filmRental.Controller;

import com.example.filmRental.Entity.Store;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/post")
    public ResponseEntity<String> addNewStore(@RequestBody Store store) {
        storeService.saveStore(store);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @PutMapping("/{storeId}/address/{addressId}")
    public ResponseEntity<Store> assignAddressToStore(@PathVariable Long storeId, @PathVariable Long addressId) {
        return ResponseEntity.ok(storeService.assignAddressToStore(storeId, addressId));
    }

    @PutMapping("/{storeId}/manager/{managerStaffId}")
    public ResponseEntity<Store> assignManagerToStore(@PathVariable Long storeId, @PathVariable Long managerStaffId) {
        return ResponseEntity.ok(storeService.assignManagerToStore(storeId, managerStaffId));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Store>> searchStoreByCity(@PathVariable String city) {
        List<Store> stores = storeService.searchStoreByCity(city);
        if (stores == null || stores.isEmpty()) {
            throw new ResourceNotFoundException("No stores found in city: " + city);
        }
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Store>> searchStoreByCountry(@PathVariable String country) {
        List<Store> stores = storeService.searchStoreByCountry(country);
        if (stores == null || stores.isEmpty()) {
            throw new ResourceNotFoundException("No stores found in country: " + country);
        }
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/staff/{storeId}")
    public ResponseEntity<List<Staff>> getAllStaffOfStore(@PathVariable Long storeId) {
        List<Staff> staffList = storeService.getAllStaffOfStore(storeId);
        if (staffList == null || staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found for store ID: " + storeId);
        }
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/customer/{storeId}")
    public ResponseEntity<List<Customer>> getAllCustomersOfStore(@PathVariable Long storeId) {
        List<Customer> customers = storeService.getAllCustomersOfStore(storeId);
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found for store ID: " + storeId);
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/manager/{storeId}")
    public ResponseEntity<Staff> getManagerOfStore(@PathVariable Long storeId) {
        Staff manager = storeService.getManagerOfStore(storeId);
        if (manager == null) {
            throw new ResourceNotFoundException("No manager found for store ID: " + storeId);
        }
        return ResponseEntity.ok(manager);
    }

    @GetMapping("/managers")
    public ResponseEntity<List<Object>> getAllManagersWithStoreDetails() {
        List<Object> managers = storeService.getAllManagersWithStoreDetails();
        if (managers == null || managers.isEmpty()) {
            throw new ResourceNotFoundException("No managers found across stores.");
        }
        return ResponseEntity.ok(managers);
    }
}