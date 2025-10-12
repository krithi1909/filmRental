package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByAddressCityCity(String city);
    List<Store> findByAddressCityCountryCountry(String country);
    Store findByAddressPhone(String phone);
}
