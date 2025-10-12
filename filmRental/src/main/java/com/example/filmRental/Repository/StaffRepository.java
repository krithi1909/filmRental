package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Byte> {

    List<Staff> findByFirstNameContaining(String firstName);

    List<Staff> findByLastNameContaining(String lastName);

    Optional<Staff> findByEmail(String email);

    List<Staff> findByAddressCityCity(String city);

    List<Staff> findByAddressCityCountryCountry(String country);

    Optional<Staff> findFirstByStore_StoreId(Long storeId);

    // ✅ Add this method to fetch all staff of a store
    List<Staff> findByStore_StoreId(Long storeId);
}