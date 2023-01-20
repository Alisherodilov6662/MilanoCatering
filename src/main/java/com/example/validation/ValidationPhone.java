package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPhone implements ConstraintValidator<ValidPhone,String> {

    private final String PHONE_PATTERN="(?:\\+[0-9]{12})";
    private final Pattern PATTERN=Pattern.compile(PHONE_PATTERN);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s==null) return false;
        Matcher matcher=PATTERN.matcher(s);
        return matcher.matches();
    }
}
