package com.example.online_grocery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyIfPresentValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyIfPresent {

  String message() default "If provided, the list cannot be empty and should contain at least one item";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
