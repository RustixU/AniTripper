package com.example.AniTripper.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.AniTripper.dto.AddUserDto;
import com.example.AniTripper.dto.ShowDetailedUserInfoDto;
import com.example.AniTripper.dto.ShowUserInfoDto;
import com.example.AniTripper.models.entities.User;
import com.example.AniTripper.repositories.AnimeRepository;
import com.example.AniTripper.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserService {
    private final UserRepository userRepository;

    private final AnimeRepository animeRepository;

    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, AnimeRepository animeRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.animeRepository = animeRepository;
        this.mapper = mapper;
    }

    public void addUser(AddUserDto userDTO) {
        User user = mapper.map(userDTO, User.class);
        user.setAnime(animeRepository.findByName(userDTO.getAnimeName()).orElse(null));

        userRepository.saveAndFlush(user);
    }

    public List<ShowUserInfoDto> allUsers() {
        return userRepository.findAll().stream().map(user -> mapper.map(user, ShowUserInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedUserInfoDto userInfo(String userFullName) {
        return mapper.map(userRepository.findUserByFullName(userFullName), ShowDetailedUserInfoDto.class);
    }

    public void fireUser(String userFullName) {
        userRepository.deleteUserByFullName(userFullName);
    }
}
