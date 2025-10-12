package com.example.filmRental;



import com.example.filmRental.Controller.StoreController;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Entity.Store;
import com.example.filmRental.Service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreControllerTest {

    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewStore() {
        Store store = new Store();
        when(storeService.saveStore(store)).thenReturn(store); // ✅ correct for non-void method

        ResponseEntity<String> response = storeController.addNewStore(store);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testAssignAddressToStore() {
        Store store = new Store();
        when(storeService.assignAddressToStore(1L, 10L)).thenReturn(store);
        ResponseEntity<Store> response = storeController.assignAddressToStore(1L, 10L);
        assertEquals(store, response.getBody());
    }

    @Test
    void testAssignManagerToStore() {
        Store store = new Store();
        when(storeService.assignManagerToStore(1L, 2L)).thenReturn(store);
        ResponseEntity<Store> response = storeController.assignManagerToStore(1L, 2L);
        assertEquals(store, response.getBody());
    }

    

    @Test
    void testSearchStoreByCity() {
        List<Store> stores = List.of(new Store());
        when(storeService.searchStoreByCity("Chennai")).thenReturn(stores);
        ResponseEntity<List<Store>> response = storeController.searchStoreByCity("Chennai");
        assertEquals(stores, response.getBody());
    }

    @Test
    void testSearchStoreByCountry() {
        List<Store> stores = List.of(new Store());
        when(storeService.searchStoreByCountry("India")).thenReturn(stores);
        ResponseEntity<List<Store>> response = storeController.searchStoreByCountry("India");
        assertEquals(stores, response.getBody());
    }

   

    @Test
    void testGetAllStaffOfStore() {
        List<Staff> staffList = List.of(new Staff());
        when(storeService.getAllStaffOfStore(1L)).thenReturn(staffList);
        ResponseEntity<List<Staff>> response = storeController.getAllStaffOfStore(1L);
        assertEquals(staffList, response.getBody());
    }

    @Test
    void testGetAllCustomersOfStore() {
        List<Customer> customerList = List.of(new Customer());
        when(storeService.getAllCustomersOfStore(1L)).thenReturn(customerList);
        ResponseEntity<List<Customer>> response = storeController.getAllCustomersOfStore(1L);
        assertEquals(customerList, response.getBody());
    }

    @Test
    void testGetManagerOfStore() {
        Staff manager = new Staff();
        when(storeService.getManagerOfStore(1L)).thenReturn(manager);
        ResponseEntity<Staff> response = storeController.getManagerOfStore(1L);
        assertEquals(manager, response.getBody());
    }

    @Test
    void testGetAllManagersWithStoreDetails() {
        List<Object> managerDetails = List.of(Map.of("firstName", "John", "storeId", 1L));
        when(storeService.getAllManagersWithStoreDetails()).thenReturn(managerDetails);
        ResponseEntity<List<Object>> response = storeController.getAllManagersWithStoreDetails();
        assertEquals(managerDetails, response.getBody());
    }
}
