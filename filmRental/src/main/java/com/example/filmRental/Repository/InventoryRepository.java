package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByStore_StoreId(Long storeId);

    List<Inventory> findByFilm_FilmId(Long filmId);

    List<Inventory> findByFilm_FilmIdAndStore_StoreId(Long filmId, Long storeId);
}