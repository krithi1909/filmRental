package com.example.filmRental.Dto;

import jakarta.validation.constraints.NotBlank;

public class ActorDto {

    public ActorDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ActorDto(@NotBlank String firstName, @NotBlank String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}