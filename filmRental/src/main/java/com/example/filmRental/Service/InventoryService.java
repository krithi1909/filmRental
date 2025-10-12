package com.example.filmRental.Service;

import com.example.filmRental.Entity.Inventory;
import com.example.filmRental.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public void addFilmToStore(Long filmId, Long storeId) {
        // Implementation logic to add film to store
    }

    public List<Inventory> getInventoryByStore(Long storeId) {
        return inventoryRepository.findByStore_StoreId(storeId);
    }

    public List<Inventory> getInventoryByFilm(Long filmId) {
        return inventoryRepository.findByFilm_FilmId(filmId);
    }

    public List<Inventory> getFilmInventoryInStore(Long filmId, Long storeId) {
        return inventoryRepository.findByFilm_FilmIdAndStore_StoreId(filmId, storeId);
    }

    public List<Inventory> getAllFilmInventoryInAllStores() {
        return inventoryRepository.findAll();
    }
}