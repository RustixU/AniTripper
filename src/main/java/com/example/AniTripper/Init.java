package com.example.AniTripper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.AniTripper.models.entities.Role;
import com.example.AniTripper.models.entities.Account;
import com.example.AniTripper.models.enums.AccountRoles;
import com.example.AniTripper.repositories.AccountRepository;
import com.example.AniTripper.repositories.AccountRoleRepository;

import java.util.List;

@Component
public class Init implements CommandLineRunner {
    private final AccountRepository userRepository;

    private final AccountRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(AccountRepository userRepository, AccountRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initAccounts();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var moderatorRole = new Role(AccountRoles.MODERATOR);
            var adminRole = new Role(AccountRoles.ADMIN);
            var normalAccountRole = new Role(AccountRoles.USER);
            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalAccountRole);
        }
    }

    private void initAccounts() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initNormalAccount();
        }
    }

    private void initAdmin(){
       var adminRole = userRoleRepository.
                findRoleByName(AccountRoles.ADMIN).orElseThrow();

       var adminUser = new Account("admin", passwordEncoder.encode(defaultPassword), "admin@example.com", "Rustam Avezov", 22);
       adminUser.setRoles(List.of(adminRole));

       userRepository.save(adminUser);
    }

    private void initModerator(){

        var moderatorRole = userRoleRepository.
                findRoleByName(AccountRoles.MODERATOR).orElseThrow();

        var moderatorUser = new Account("moderator", passwordEncoder.encode(defaultPassword), "moderator@example.com", "Maxim Pavlicov", 24);
        moderatorUser.setRoles(List.of(moderatorRole));

        userRepository.save(moderatorUser);
    }

    private void initNormalAccount(){
        var userRole = userRoleRepository.
                findRoleByName(AccountRoles.USER).orElseThrow();

        var normalUser = new Account("user", passwordEncoder.encode(defaultPassword), "user@example.com", "Alexey Golovanov", 21);
        normalUser.setRoles(List.of(userRole));

        userRepository.save(normalUser);
    }
}
