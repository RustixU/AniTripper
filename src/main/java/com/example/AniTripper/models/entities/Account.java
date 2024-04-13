package com.example.AniTripper.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity implements Serializable {

    private String username;

    private String password;

    private String email;

    private String fullName;

    private int age;

    private List<Role> roles;

    public Account(String username, String password, String email, String fullName, int age) {
        this.roles = new ArrayList<>();

        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    public Account() {
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
