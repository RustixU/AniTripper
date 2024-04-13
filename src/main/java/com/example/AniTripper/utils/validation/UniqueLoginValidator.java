package com.example.AniTripper.utils.validation;

import com.example.AniTripper.repositories.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private AccountRepository accountRepository;

    public UniqueLoginValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        return login != null && !accountRepository.findByUsername(login).isPresent();
    }

}
