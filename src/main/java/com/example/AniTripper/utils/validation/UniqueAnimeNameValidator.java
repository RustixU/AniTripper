package com.example.AniTripper.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.AniTripper.repositories.AnimeRepository;

public class UniqueAnimeNameValidator implements ConstraintValidator<UniqueAnimeName, String> {
    private final AnimeRepository animeRepository;

    public UniqueAnimeNameValidator(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return animeRepository.findByName(value).isEmpty();
    }
}
