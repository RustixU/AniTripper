package com.example.AniTripper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.AniTripper.models.entities.Anime;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, String> {
    Optional<Anime> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
