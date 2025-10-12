package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    List<Payment> findByCustomerCustomerId(Short customerId);

    @Query("SELECT p.paymentDate, SUM(p.amount) FROM Payment p GROUP BY p.paymentDate")
    List<Object[]> getRevenueDatewiseAllStores();

    @Query("SELECT p.paymentDate, SUM(p.amount) FROM Payment p WHERE p.staff.store.storeId = :storeId GROUP BY p.paymentDate")
    List<Object[]> getRevenueDatewiseByStore(@Param("storeId") Long storeId);

    @Query("SELECT f.title, SUM(p.amount) FROM Payment p " +
           "JOIN p.rental r JOIN r.inventory i JOIN i.film f " +
           "WHERE r.staff.store.storeId = :storeId GROUP BY f.title")
    List<Object[]> getRevenueFilmsByStore(@Param("storeId") Long storeId);

    @Query("SELECT s.address, SUM(p.amount) FROM Payment p " +
           "JOIN p.rental r JOIN r.inventory i JOIN i.store s " +
           "WHERE i.film.filmId = :filmId GROUP BY s.address")
    List<Object[]> getRevenueOfFilmStoreWise(@Param("filmId") Long filmId);

    @Query("SELECT f.title, SUM(p.amount) FROM Payment p " +
           "JOIN p.rental r JOIN r.inventory i JOIN i.film f " +
           "GROUP BY f.title")
    List<Object[]> getRevenueAllFilmsAcrossStores();
}