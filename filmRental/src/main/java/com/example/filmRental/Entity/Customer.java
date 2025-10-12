package com.example.filmRental.Entity;
 
import jakarta.persistence.*;

 
import java.sql.Timestamp;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "customer")
public class Customer {
 
    public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long customerId, Store store, String firstName, String lastName, String email, Address address,
			Boolean active, LocalDateTime createDate, Timestamp lastUpdate) {
		super();
		this.customerId = customerId;
		this.store = store;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.active = active;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
 
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
 
    @Column(length = 45, nullable = false)
    private String firstName;
 
    @Column(length = 45, nullable = false)
    private String lastName;
 
    @Column(length = 50)
    private String email;
 
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
 
    @Column(nullable = false)
    private Boolean active;
 
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
 
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}