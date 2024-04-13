package com.example.AniTripper.models.entities;

import jakarta.persistence.*;
import com.example.AniTripper.models.enums.AccountRoles;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private AccountRoles name;

    public Role(AccountRoles name) {
        this.name = name;
    }

    public Role() {

    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public AccountRoles getName() {
        return name;
    }

    public void setName(AccountRoles name) {
        this.name = name;
    }
}
