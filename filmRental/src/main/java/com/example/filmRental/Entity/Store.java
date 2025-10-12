package com.example.filmRental.Entity;
 
import jakarta.persistence.*;

 
import java.sql.Timestamp;
 
@Entity
@Table(name = "store")
public class Store {
 
    public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(Long storeId, Staff manager, Address address, Timestamp lastUpdate) {
		super();
		this.storeId = storeId;
		this.manager = manager;
		this.address = address;
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
 
    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff manager;
 
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
 
}