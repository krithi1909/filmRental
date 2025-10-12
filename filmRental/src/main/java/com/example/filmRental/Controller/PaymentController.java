package com.example.filmRental.Controller;

import com.example.filmRental.Dto.PaymentDto;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/add")
    public ResponseEntity<String> makeOnePaymentEntry(@RequestBody PaymentDto dto) {
        if (dto.getAmount() == null || dto.getAmount().doubleValue() <= 0) {
            throw new CustomValidationException("Payment amount must be greater than zero.");
        }
        String result = paymentService.makeOnePaymentEntry(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/datewise")
    public ResponseEntity<List<Object[]>> calculateCumulativeRevenueOfAllStores() {
        List<Object[]> result = paymentService.getRevenueDatewiseAllStores();
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No revenue data found for all stores.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/datewise/store/{id}")
    public ResponseEntity<List<Object[]>> calculateCumulativeRevenueOfAStore(@PathVariable Long id) {
        List<Object[]> result = paymentService.getRevenueDatewiseByStore(id);
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No revenue data found for store ID: " + id);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/filmwise")
    public ResponseEntity<List<Object[]>> calculateCumulativeRevenueOfAllFilmsAcrossAllStores() {
        List<Object[]> result = paymentService.getRevenueAllFilmsAcrossStores();
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No revenue data found for films across stores.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/film/{id}")
    public ResponseEntity<List<Object[]>> calculateCumulativeRevenueOfAFilmStoreWise(@PathVariable Long id) {
        List<Object[]> result = paymentService.getRevenueOfFilmStoreWise(id);
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No revenue data found for film ID: " + id);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/films/store/{id}")
    public ResponseEntity<List<Object[]>> calculateCumulativeRevenueOfAllFilmsByStore(@PathVariable Long id) {
        List<Object[]> result = paymentService.getRevenueFilmsByStore(id);
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No revenue data found for films in store ID: " + id);
        }
        return ResponseEntity.ok(result);
    }
}
