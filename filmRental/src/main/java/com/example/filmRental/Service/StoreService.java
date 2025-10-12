
package com.example.filmRental.Service;

import com.example.filmRental.Entity.Store;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Address;
import com.example.filmRental.Repository.StoreRepository;
import com.example.filmRental.Repository.StaffRepository;
import com.example.filmRental.Repository.CustomerRepository;
import com.example.filmRental.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Store saveStore(Store store) {
        store.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return storeRepository.save(store);
    }

    public Store assignAddressToStore(Long storeId, Long addressId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Address address = addressRepository.findById(addressId).orElseThrow();
        store.setAddress(address);
        store.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return storeRepository.save(store);
    }

    public Store assignManagerToStore(Long storeId, Long managerStaffId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Staff manager = staffRepository.findById(managerStaffId.byteValue()).orElseThrow();
        store.setManager(manager);
        store.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return storeRepository.save(store);
    }

    public Store updateStorePhone(Long storeId, String phone) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Address address = store.getAddress();
        address.setPhone(phone);
        addressRepository.save(address);
        store.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return storeRepository.save(store);
    }

    public List<Store> searchStoreByCity(String city) {
        return storeRepository.findByAddressCityCity(city);
    }

    public List<Store> searchStoreByCountry(String country) {
        return storeRepository.findByAddressCityCountryCountry(country);
    }

    public Store searchStoreByPhone(String phone) {
        return storeRepository.findByAddressPhone(phone);
    }

    public List<Staff> getAllStaffOfStore(Long storeId) {
        return staffRepository.findByStore_StoreId(storeId);
    }

    public List<Customer> getAllCustomersOfStore(Long storeId) {
        return customerRepository.findByStore_StoreId(storeId);
    }

    public Staff getManagerOfStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        return store.getManager();
    }

    public List<Object> getAllManagersWithStoreDetails() {
        List<Store> stores = storeRepository.findAll();
        List<Object> result = new ArrayList<>();
        for (Store store : stores) {
            Map<String, Object> map = new HashMap<>();
            Staff manager = store.getManager();
            Address address = store.getAddress();
            map.put("firstName", manager.getFirstName());
            map.put("lastName", manager.getLastName());
            map.put("email", manager.getEmail());
            map.put("phone", address.getPhone());
            map.put("address", address.getAddress());
            map.put("city", address.getCity().getCity());
            result.add(map);
        }
        return result;
    }
}
