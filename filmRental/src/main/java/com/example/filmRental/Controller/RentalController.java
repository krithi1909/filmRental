package com.example.filmRental.Controller;

import com.example.filmRental.Dto.RentalDto;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Rental;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/add")
    public ResponseEntity<String> rentFilm(@RequestBody RentalDto dto) {
        if (dto.getCustomerId() == null || dto.getFilmId() == null || dto.getStoreId() == null) {
            throw new CustomValidationException("Customer ID, Film ID, and Store ID must be provided.");
        }
        rentalService.saveRental(dto);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @PostMapping("/update/returndate/{id}")
    public ResponseEntity<Rental> updateReturnDate(@PathVariable Integer id) {
        Rental updatedRental = rentalService.updateReturnDate(id);
        if (updatedRental == null) {
            throw new ResourceNotFoundException("Rental not found with ID: " + id);
        }
        return ResponseEntity.ok(updatedRental);
    }

    @GetMapping("/due/store/{id}")
    public ResponseEntity<List<Customer>> getDueCustomersByStore(@PathVariable Long id) {
        List<Customer> customers = rentalService.getDueCustomersByStore(id);
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No due customers found for store ID: " + id);
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<Film>> getTopTenFilmsByStore(@PathVariable Long id) {
        List<Film> films = rentalService.getTopTenFilmsByStore(id);
        if (films == null || films.isEmpty()) {
            throw new ResourceNotFoundException("No top films found for store ID: " + id);
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Film>> getFilmsRentedToCustomer(@PathVariable Long id) {
        List<Film> films = rentalService.getFilmsRentedToCustomer(id);
        if (films == null || films.isEmpty()) {
            throw new ResourceNotFoundException("No films found rented to customer ID: " + id);
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping("/toptenfilms")
    public ResponseEntity<List<Film>> getTopTenFilmsOverall() {
        List<Film> films = rentalService.getTopTenFilmsOverall();
        if (films == null || films.isEmpty()) {
            throw new ResourceNotFoundException("No top films found overall.");
        }
        return ResponseEntity.ok(films);
    }
}