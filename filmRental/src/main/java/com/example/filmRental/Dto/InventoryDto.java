package com.example.filmRental.Dto;
 
import jakarta.validation.constraints.*;
import java.time.LocalDate;
 
public class InventoryDto {
    @NotNull
    private Long filmId;
 
    @NotNull
    private Long storeId;
 
    @Min(1)
    private Integer quantity;
 
    // Getters and setters
}