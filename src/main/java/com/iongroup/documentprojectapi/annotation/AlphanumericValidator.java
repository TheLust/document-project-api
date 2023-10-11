package com.iongroup.documentprojectapi.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {
    @Override
    public void initialize(Alphanumeric constraintAnnotation) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string == null) {
            return true;
        }

        return string.matches("^[a-zA-Z0-9\\s]+$");
    }
}