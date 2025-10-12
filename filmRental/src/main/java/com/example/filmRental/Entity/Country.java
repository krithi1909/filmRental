package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
import lombok.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short countryId;
 
    @Column(length = 50, nullable = false)
    private String country;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}