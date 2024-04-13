package com.example.AniTripper.dto;

import jakarta.validation.constraints.*;
import com.example.AniTripper.models.enums.EducationLevel;

import java.time.LocalDate;

public class AddUserDto {

    private String firstName;

    private String lastName;
    private EducationLevel educationLevel;
    private LocalDate birthDate;
    private String animeName;

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 2, message = "First name should be at least 2 characters long!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 2, message = "Last name should be at least 2 characters long!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotNull(message = "Please choose an education level!")
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }
    @NotNull(message = "Birth date cannot be null or empty!")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @NotEmpty(message = "Please choose a anime!")
    public String getAnimeName() {
        return animeName;
    }
    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }
}
