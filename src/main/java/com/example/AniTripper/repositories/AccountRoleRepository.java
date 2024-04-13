package com.example.AniTripper.repositories;

import com.example.AniTripper.models.entities.Role;
import com.example.AniTripper.models.enums.AccountRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(AccountRoles role);
}
