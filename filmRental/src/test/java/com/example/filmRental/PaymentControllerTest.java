package com.example.filmRental;



import com.example.filmRental.Controller.PaymentController;
import com.example.filmRental.Dto.PaymentDto;
import com.example.filmRental.Service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testMakeOnePaymentEntry() {
        PaymentDto dto = new PaymentDto();
        dto.setAmount(100.0);
        dto.setCustomerId(1L);
        dto.setStaffId((byte) 1); 
        dto.setPaymentDate(LocalDate.now()); 

        when(paymentService.makeOnePaymentEntry(dto)).thenReturn("Payment successful");

        ResponseEntity<String> response = paymentController.makeOnePaymentEntry(dto);
        assertEquals("Payment successful", response.getBody());
    }


    @Test
    void testCalculateCumulativeRevenueOfAllStores() {
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"2025-10-10", 100.0});
        when(paymentService.getRevenueDatewiseAllStores()).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = paymentController.calculateCumulativeRevenueOfAllStores();
        assertEquals(mockResult, response.getBody());
    }

    @Test
    void testCalculateCumulativeRevenueOfAStore() {
        Long storeId = 1L;
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"2025-10-10", 50.0});
        when(paymentService.getRevenueDatewiseByStore(storeId)).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = paymentController.calculateCumulativeRevenueOfAStore(storeId);
        assertEquals(mockResult, response.getBody());
    }

    @Test
    void testCalculateCumulativeRevenueOfAllFilmsAcrossAllStores() {
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Inception", 200.0});
        when(paymentService.getRevenueAllFilmsAcrossStores()).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = paymentController.calculateCumulativeRevenueOfAllFilmsAcrossAllStores();
        assertEquals(mockResult, response.getBody());
    }

    @Test
    void testCalculateCumulativeRevenueOfAFilmStoreWise() {
        Long filmId = 10L;
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Store A", 120.0});
        when(paymentService.getRevenueOfFilmStoreWise(filmId)).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = paymentController.calculateCumulativeRevenueOfAFilmStoreWise(filmId);
        assertEquals(mockResult, response.getBody());
    }

    @Test
    void testCalculateCumulativeRevenueOfAllFilmsByStore() {
        Long storeId = 2L;
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Matrix", 80.0});
        when(paymentService.getRevenueFilmsByStore(storeId)).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = paymentController.calculateCumulativeRevenueOfAllFilmsByStore(storeId);
        assertEquals(mockResult, response.getBody());
    }
}
