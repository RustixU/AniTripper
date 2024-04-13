package com.example.AniTripper.models.entities;

import com.example.AniTripper.models.enums.EducationLevel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")

public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private EducationLevel educationLevel;
    private LocalDate birthDate;

    @ManyToOne
    private Anime anime;

    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "education_level",nullable = false)
    @Enumerated(EnumType.STRING)
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Column(name = "birth_date",nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToOne
    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
