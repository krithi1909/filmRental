package com.example.filmRental.Entity;
 
import jakarta.persistence.*;
import java.sql.Timestamp;
 
@Entity
@Table(name = "language")
public class Language {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte languageId;
 
    @Column(length = 20, nullable = false)
    private String name;
 
    @Column(nullable = false)
    private Timestamp lastUpdate;
 
    // Constructors
    public Language() {}
 
    public Language(Byte languageId, String name, Timestamp lastUpdate) {
        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }
 
    // Getters
    public Byte getLanguageId() {
        return languageId;
    }
 
    public String getName() {
        return name;
    }
 
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
 
    // Setters
    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}