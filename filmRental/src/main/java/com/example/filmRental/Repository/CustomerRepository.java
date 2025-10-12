package com.example.filmRental.Repository;
 
import com.example.filmRental.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstNameContaining(String firstName);

    List<Customer> findByLastNameContaining(String lastName);

    List<Customer> findByEmail(String email);

    List<Customer> findByActive(Boolean active);

    List<Customer> findByAddressCityCity(String city);

    List<Customer> findByAddressCityCountryCountry(String country);
    List<Customer> findByStore_StoreId(Long storeId);
}