package com.example.AniTripper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.AniTripper.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT e FROM User AS e WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
    User findUserByFullName(String fullName);

    @Modifying
    @Transactional
    @Query("DELETE FROM User AS e WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
    void deleteUserByFullName(String fullName);
}
