package com.example.filmRental.Dto;
 
 
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
 
public class CustomerDto{
 
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;
 
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastName;
 
    @Email(message = "Email should be valid")
    private String email;
 
    // Getters and setters
}