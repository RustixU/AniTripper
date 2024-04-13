package com.example.AniTripper.utils.validation;

import com.example.AniTripper.repositories.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private AccountRepository accountRepository;

    public UniqueEmailValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !accountRepository.findByEmail(email).isPresent();
    }
}
