package com.example.AniTripper.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.AniTripper.dto.AccountRegistrationDto;
import com.example.AniTripper.models.entities.Account;
import com.example.AniTripper.models.enums.AccountRoles;
import com.example.AniTripper.repositories.AccountRepository;
import com.example.AniTripper.repositories.AccountRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private AccountRepository accountRepository;

    private AccountRoleRepository accountRoleRepository;


    private PasswordEncoder passwordEncoder;

    public AuthService(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRoleRepository = accountRoleRepository;
    }

    public void register(AccountRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<Account> byEmail = this.accountRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var accountRole = accountRoleRepository.
                findRoleByName(AccountRoles.USER).orElseThrow();

        Account account = new Account(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFullname(),
                registrationDTO.getAge()
        );

        account.setRoles(List.of(accountRole));

        this.accountRepository.save(account);
    }

    public Account getAccount(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
