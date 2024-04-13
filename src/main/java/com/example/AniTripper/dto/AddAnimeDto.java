package com.example.AniTripper.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.AniTripper.utils.validation.UniqueAnimeName;


public class AddAnimeDto {

    @UniqueAnimeName
    private String name;
    private String studio;
    private String description;
    private Double time;

    @NotEmpty(message = "Anime name must not be null or empty!")
    @Size(min = 2, max = 20, message = "Anime name must be between 2 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Studio name must not be null or empty!")
    @Size(min = 2, max = 20, message = "Studio name must be between 2 and 10 characters!")
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 10, message = "Description must be at least 10 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 5, message = "Time must be a positive number!")
    @NotNull(message = "Time must not be null or empty!")
    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
