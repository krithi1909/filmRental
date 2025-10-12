package com.example.filmRental;



import com.example.filmRental.Controller.InventoryController;
import com.example.filmRental.Entity.Inventory;
import com.example.filmRental.Service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;

    @Mock
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOneMoreFilmToStore() {
        Long filmId = 1L;
        Long storeId = 2L;
        doNothing().when(inventoryService).addFilmToStore(filmId, storeId);

        ResponseEntity<?> response = inventoryController.addOneMoreFilmToStore(filmId, storeId);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testGetFilmInventoryInStore() {
        Long filmId = 1L;
        Long storeId = 2L;
        List<Inventory> mockList = List.of(new Inventory());
        when(inventoryService.getFilmInventoryInStore(filmId, storeId)).thenReturn(mockList);

        ResponseEntity<?> response = inventoryController.getFilmInventoryInStore(filmId, storeId);
        assertEquals(mockList, response.getBody());
    }

    @Test
    void testGetAllFilmInventoryInAllStores() {
        List<Inventory> mockList = List.of(new Inventory());
        when(inventoryService.getAllFilmInventoryInAllStores()).thenReturn(mockList);

        ResponseEntity<?> response = inventoryController.getAllFilmInventoryInAllStores();
        assertEquals(mockList, response.getBody());
    }

    @Test
    void testGetAllFilmInventoryByStore() {
        Long storeId = 2L;
        List<Inventory> mockList = List.of(new Inventory());
        when(inventoryService.getInventoryByStore(storeId)).thenReturn(mockList);

        ResponseEntity<?> response = inventoryController.getAllFilmInventoryByStore(storeId);
        assertEquals(mockList, response.getBody());
    }

    @Test
    void testGetFilmInventoryInAllStores() {
        Long filmId = 1L;
        List<Inventory> mockList = List.of(new Inventory());
        when(inventoryService.getInventoryByFilm(filmId)).thenReturn(mockList);

        ResponseEntity<?> response = inventoryController.getFilmInventoryInAllStores(filmId);
        assertEquals(mockList, response.getBody());
    }
}

