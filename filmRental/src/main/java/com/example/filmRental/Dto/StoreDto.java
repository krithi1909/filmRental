package com.example.filmRental.Dto;
 
 
import jakarta.validation.constraints.*;
 
public class StoreDto {
    @NotBlank
    private String address;
 
    @NotBlank
    private String city;
 
 
    @NotBlank
    private String country;
 
    // Getters and setters
}