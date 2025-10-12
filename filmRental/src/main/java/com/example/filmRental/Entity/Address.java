package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "address")

public class Address {
	
	
    public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Long addressId, String address, String address2, String district, City city, String postalCode,
			String phone, Timestamp lastUpdate) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
 
    @Column(length = 50, nullable = false)
    private String address;
 
    @Column(length = 50)
    private String address2;
 
    @Column(length = 20, nullable = false)
    private String district;
 
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
 
    @Column(length = 10)
    private String postalCode;
 
    @Column(length = 20, nullable = false)
    private String phone;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}