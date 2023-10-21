package com.example.TomTomIntegration.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, String> {

    private static final String PHONE_REGEX = "^(\\+\\d{1,3}\\s)?\\(?\\d{1,3}\\)?[\\s.-]?\\d{3,4}[\\s.-]?\\d{4}$";

    @Override
    public void initialize(PhoneConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext cxt) {
        return Objects.nonNull(phone) && phone.matches(PHONE_REGEX);
    }
}
