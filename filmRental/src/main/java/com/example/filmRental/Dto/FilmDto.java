package com.example.filmRental.Dto;
 
import jakarta.validation.constraints.*;
import java.time.LocalDate;
 
public class FilmDto {
    @NotBlank
    private String title;
 
    @Size(max = 500)
    private String description;
 
    @Min(1900)
    private Integer releaseYear;
 
    @NotBlank
    private String language;
 
    @DecimalMin("0.0")
    private Double rentalRate;
 
    @NotBlank
    private String rating;
 
    @Min(1)
    private Integer length;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public FilmDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilmDto(@NotBlank String title, @Size(max = 500) String description, @Min(1900) Integer releaseYear,
			@NotBlank String language, @DecimalMin("0.0") Double rentalRate, @NotBlank String rating,
			@Min(1) Integer length) {
		super();
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalRate = rentalRate;
		this.rating = rating;
		this.length = length;
	}
 
    
    // Getters and setters
}