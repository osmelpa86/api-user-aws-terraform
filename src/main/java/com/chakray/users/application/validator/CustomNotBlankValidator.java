package com.chakray.users.application.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class CustomNotBlankValidator implements ConstraintValidator<CustomNotBlank, String> {

	@SneakyThrows
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || !value.trim().isEmpty();
	}

}