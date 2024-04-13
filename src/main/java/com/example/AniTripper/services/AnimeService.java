package com.example.AniTripper.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.AniTripper.dto.AddAnimeDto;
import com.example.AniTripper.dto.ShowAnimeInfoDto;
import com.example.AniTripper.dto.ShowDetailedAnimeInfoDto;
import com.example.AniTripper.models.entities.Anime;
import com.example.AniTripper.repositories.AnimeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final ModelMapper mapper;

    public AnimeService(AnimeRepository animeRepository, ModelMapper mapper) {
        this.animeRepository = animeRepository;
        this.mapper = mapper;
    }

    public void addAnime(AddAnimeDto animeDTO) {
        animeRepository.saveAndFlush(mapper.map(animeDTO, Anime.class));
    }

    public List<ShowAnimeInfoDto> allAnimes() {
        return animeRepository.findAll().stream().map(anime -> mapper.map(anime, ShowAnimeInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedAnimeInfoDto animeDetails(String animeName) {
        return mapper.map(animeRepository.findByName(animeName).orElse(null), ShowDetailedAnimeInfoDto.class);
    }

    public void removeAnime(String animeName) {
        animeRepository.deleteByName(animeName);
    }
}
