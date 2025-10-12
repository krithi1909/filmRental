package com.example.filmRental.Controller;

import com.example.filmRental.Entity.Inventory;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add one more Film to a Store
    @PostMapping("/add")
    public ResponseEntity<String> addOneMoreFilmToStore(@RequestParam Long filmId, @RequestParam Long storeId) {
        inventoryService.addFilmToStore(filmId, storeId);
        return ResponseEntity.ok("Record Created Successfully");
    }

    // Display inventory(count) of a Film in a Store
    @GetMapping("/film/{filmId}/store/{storeId}")
    public ResponseEntity<List<Inventory>> getFilmInventoryInStore(@PathVariable Long filmId, @PathVariable Long storeId) {
        List<Inventory> inventoryList = inventoryService.getFilmInventoryInStore(filmId, storeId);
        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new ResourceNotFoundException("No inventory found for film ID " + filmId + " in store ID " + storeId);
        }
        return ResponseEntity.ok(inventoryList);
    }

    // Display inventory(count) of all Films in all Stores
    @GetMapping("/films")
    public ResponseEntity<Object> getAllFilmInventoryInAllStores() {
        return ResponseEntity.ok(inventoryService.getAllFilmInventoryInAllStores());
    }

    // Display inventory of all Films by a Store
    @GetMapping("/store/{storeId}")
    public ResponseEntity<Object> getAllFilmInventoryByStore(@PathVariable Long storeId) {
        Object inventory = inventoryService.getInventoryByStore(storeId);
        if (inventory == null) {
            throw new ResourceNotFoundException("No inventory found for store ID: " + storeId);
        }
        return ResponseEntity.ok(inventory);
    }
    
    // Display inventory(count) of a Film in all Stores
    @GetMapping("/film/{filmId}")
    public ResponseEntity<Object> getFilmInventoryInAllStores(@PathVariable Long filmId) {
        Object inventory = inventoryService.getInventoryByFilm(filmId);
        if (inventory == null) {
            throw new ResourceNotFoundException("No inventory found for film ID: " + filmId);
        }
        return ResponseEntity.ok(inventory);
    }
}
