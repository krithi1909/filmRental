package com.example.filmRental.Service;

import com.example.filmRental.Dto.StaffDto;
import com.example.filmRental.Entity.Address;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Entity.Store;
import com.example.filmRental.Repository.AddressRepository;
import com.example.filmRental.Repository.StaffRepository;
import com.example.filmRental.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StoreRepository storeRepository;

    public void addStaff(StaffDto dto) {
        Staff staff = new Staff();
        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setEmail(dto.getEmail());
        staff.setUsername(dto.getFirstName().toLowerCase() + "." + dto.getLastName().toLowerCase());
        staff.setActive(true);
        staff.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        staff.setAddress(addressRepository.findById(dto.getAddressId()).orElseThrow());
        staff.setStore(storeRepository.findById(dto.getStoreId()).orElseThrow());
        staffRepository.save(staff);
    }

    public List<Staff> getStaffByFirstName(String firstName) {
        return staffRepository.findByFirstNameContaining(firstName);
    }

    public List<Staff> getStaffByLastName(String lastName) {
        return staffRepository.findByLastNameContaining(lastName);
    }

    public Staff getStaffByEmail(String email) {
        return staffRepository.findByEmail(email).orElseThrow();
    }

    public Staff assignAddress(Byte id, Long addressId) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        Address address = addressRepository.findById(addressId).orElseThrow();
        staff.setAddress(address);
        return staffRepository.save(staff);
    }

    public List<Staff> getStaffByCity(String city) {
        return staffRepository.findByAddressCityCity(city);
    }

    public List<Staff> getStaffByCountry(String country) {
        return staffRepository.findByAddressCityCountryCountry(country);
    }

    public Staff updateFirstName(Byte id, String firstName) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staff.setFirstName(firstName);
        return staffRepository.save(staff);
    }

    public Staff updateLastName(Byte id, String lastName) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staff.setLastName(lastName);
        return staffRepository.save(staff);
    }

    public Staff updateEmail(Byte id, String email) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staff.setEmail(email);
        return staffRepository.save(staff);
    }

    public Staff assignStore(Byte id, Long storeId) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();
        staff.setStore(store);
        return staffRepository.save(staff);
    }
}