package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "city")

public class City {
	
	
	
    public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(Short cityId, String city, Country country, Timestamp lastUpdate) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.country = country;
		this.lastUpdate = lastUpdate;
	}

	public Short getCityId() {
		return cityId;
	}

	public void setCityId(Short cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short cityId;
 
    @Column(length = 50, nullable = false)
    private String city;
 
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}