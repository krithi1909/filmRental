package com.example.filmRental.Entity;
 
import jakarta.persistence.*;
import lombok.*;
 
import java.sql.Timestamp;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "rental")

public class Rental {
 
    public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rental(Integer rentalId, LocalDateTime rentalDate, Inventory inventory, Customer customer,
			LocalDateTime returnDate, Staff staff, Timestamp lastUpdate) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventory = inventory;
		this.customer = customer;
		this.returnDate = returnDate;
		this.staff = staff;
		this.lastUpdate = lastUpdate;
	}

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDateTime rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;
 
    @Column(nullable = false)
    private LocalDateTime rentalDate;
 
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;
 
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
 
    private LocalDateTime returnDate;
 
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}