package com.example.AniTripper.models.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "anime")
public class Anime extends BaseEntity {

    private String name;
    private String studio;
    private String description;
    private Double time;
    private Set<User> users;

    @Column(name = "time",columnDefinition = "NUMERIC", nullable = false)
    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    @Column(name = "descriprion",columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name",unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "studio",nullable = false)
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @OneToMany(mappedBy = "anime", targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
