package com.example.filmRental.Dto;
 
import jakarta.validation.constraints.*;
import java.time.LocalDate;
 
public class RentalDto {
    @NotNull
    private Long customerId;
 
    @NotNull
    private Long filmId;
 
    @NotNull
    private LocalDate rentalDate;
 
    @NotNull
    private LocalDate returnDate;
 
    @NotNull
    private Long storeId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
 
    // Getters and setters
    
}