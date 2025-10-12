
package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Rental;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findByCustomer_CustomerId(Long customerId);

    List<Rental> findByReturnDateIsNull();

    @Query("SELECT DISTINCT r.customer FROM Rental r WHERE r.returnDate IS NULL AND r.inventory.store.storeId = :storeId")
    List<Customer> findDueCustomersByStore(Long storeId);

    @Query("SELECT r.inventory.film FROM Rental r WHERE r.inventory.store.storeId = :storeId GROUP BY r.inventory.film ORDER BY COUNT(r.inventory.film) DESC")
    List<Film> findTopTenFilmsByStore(Long storeId);

    @Query("SELECT r.inventory.film FROM Rental r WHERE r.customer.customerId = :customerId")
    List<Film> findFilmsByCustomerId(Long customerId);

    @Query("SELECT r.inventory.film FROM Rental r GROUP BY r.inventory.film ORDER BY COUNT(r.inventory.film) DESC")
    List<Film> findTopTenFilmsOverall();
}
