package com.example.AniTripper.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import com.example.AniTripper.models.enums.AccountRoles;
import com.example.AniTripper.repositories.AccountRepository;
import com.example.AniTripper.services.AppAccountDetailsService;

@Configuration
public class AppSecurityConfiguration {
    private AccountRepository accountRepository;

    public AppSecurityConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests.
                                        requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                        .permitAll().
                                        requestMatchers("/", "/accounts/login", "/accounts/register", "/accounts/login-error")
                                        .permitAll().
                                        requestMatchers("/accounts/profile").authenticated().
                                        requestMatchers("/users/add", "/users/user-delete/").hasRole(AccountRoles.MODERATOR.name()).
                                        requestMatchers("/animes/add","/animes/anime-delete/","/users/add", "/users/user-delete/").hasRole(AccountRoles.ADMIN.name()).
                                        anyRequest().authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin.
                                        loginPage("/accounts/login").
                                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                                        defaultSuccessUrl("/").
                                        failureForwardUrl("/accounts/login-error")
                )
                .logout((logout) ->
                        logout.logoutUrl("/accounts/logout").
                                logoutSuccessUrl("/").
                                invalidateHttpSession(true)
                ).securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                );

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() { return new AppAccountDetailsService(accountRepository); }
}
