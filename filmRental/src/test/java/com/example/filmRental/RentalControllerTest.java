package com.example.filmRental;



import com.example.filmRental.Controller.RentalController;
import com.example.filmRental.Dto.RentalDto;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Rental;
import com.example.filmRental.Service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalControllerTest {

    @InjectMocks
    private RentalController rentalController;

    @Mock
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRentFilm() {
        RentalDto dto = new RentalDto();
        dto.setCustomerId(1L); // ✅ Required
        dto.setFilmId(2L);     // ✅ Required
        dto.setStoreId(3L);    // ✅ Required

        Rental rental = new Rental(); // or mock a Rental object

        when(rentalService.saveRental(dto)).thenReturn(rental);

        ResponseEntity<String> response = rentalController.rentFilm(dto);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testUpdateReturnDate() {
        Rental rental = new Rental();
        when(rentalService.updateReturnDate(1)).thenReturn(rental);
        ResponseEntity<Rental> response = rentalController.updateReturnDate(1);
        assertEquals(rental, response.getBody());
    }

    @Test
    void testGetDueCustomersByStore() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        when(rentalService.getDueCustomersByStore(1L)).thenReturn(customers);
        ResponseEntity<List<Customer>> response = rentalController.getDueCustomersByStore(1L);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTopTenFilmsByStore() {
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        when(rentalService.getTopTenFilmsByStore(1L)).thenReturn(films);
        ResponseEntity<List<Film>> response = rentalController.getTopTenFilmsByStore(1L);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetFilmsRentedToCustomer() {
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        when(rentalService.getFilmsRentedToCustomer(1L)).thenReturn(films);
        ResponseEntity<List<Film>> response = rentalController.getFilmsRentedToCustomer(1L);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTopTenFilmsOverall() {
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        when(rentalService.getTopTenFilmsOverall()).thenReturn(films);
        ResponseEntity<List<Film>> response = rentalController.getTopTenFilmsOverall();
        assertEquals(1, response.getBody().size());
    }
}

