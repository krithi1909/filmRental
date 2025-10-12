package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
import lombok.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "staff")

public class Staff {
	
    public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Byte getStaffId() {
		return staffId;
	}

	public void setStaffId(Byte staffId) {
		this.staffId = staffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Staff(Byte staffId, String firstName, String lastName, Address address, byte[] picture, String email,
			Store store, Boolean active, String username, String password, Timestamp lastUpdate) {
		super();
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.picture = picture;
		this.email = email;
		this.store = store;
		this.active = active;
		this.username = username;
		this.password = password;
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte staffId;
 
    @Column(length = 45, nullable = false)
    private String firstName;
 
    @Column(length = 45, nullable = false)
    private String lastName;
 
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
 
    @Lob
    private byte[] picture;
 
    @Column(length = 50)
    private String email;
 
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
 
    @Column(nullable = false)
    private Boolean active;
 
    @Column(length = 16, nullable = false)
    private String username;
 
    @Column(length = 40)
    private String password;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}