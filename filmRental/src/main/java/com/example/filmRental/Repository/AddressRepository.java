package com.example.filmRental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filmRental.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
