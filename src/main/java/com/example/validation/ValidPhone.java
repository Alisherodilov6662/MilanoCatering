package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidationPhone.class)
public @interface ValidPhone {
    String message() default "";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
