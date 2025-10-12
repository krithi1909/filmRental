package com.example.filmRental.Entity;
 
 
import jakarta.persistence.*;
import lombok.*;
 
import java.sql.Timestamp;
 
@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte categoryId;
 
    @Column(length = 25, nullable = false)
    private String name;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
}