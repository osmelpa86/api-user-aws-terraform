package com.chakray.users.application.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CustomNotBlankValidatorTest {

	@InjectMocks
	private CustomNotBlankValidator validator;


	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	@Test
	void when_value_is_not_empty_then_its_valid() {
		boolean response = this.validator.isValid("::email::", constraintValidatorContext);

		assertTrue(response);
	}

	@Test
	void when_value_is_null_then_its_valid() {
		boolean response = this.validator.isValid(null, constraintValidatorContext);

		assertTrue(response);
	}

	@Test
	void when_value_is_blank_then_its_invalid() {
		boolean response = this.validator.isValid("     ", constraintValidatorContext);

		assertFalse(response);
	}

}