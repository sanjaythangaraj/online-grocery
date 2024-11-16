package com.example.online_grocery.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyIfPresentValidator implements ConstraintValidator<NotEmptyIfPresent, List<?>> {

  @Override
  public void initialize(NotEmptyIfPresent constraintAnnotation) {
  }

  @Override
  public boolean isValid(List<?> list, ConstraintValidatorContext context) {
    return list == null || !list.isEmpty();
  }

}
