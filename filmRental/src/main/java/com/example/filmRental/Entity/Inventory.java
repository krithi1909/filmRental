package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
import lombok.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "inventory")

public class Inventory {
	
	
    public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(Integer inventoryId, Film film, Store store, Timestamp lastUpdate) {
		super();
		this.inventoryId = inventoryId;
		this.film = film;
		this.store = store;
		this.lastUpdate = lastUpdate;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
 
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
 
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
 
    private Timestamp lastUpdate;
}