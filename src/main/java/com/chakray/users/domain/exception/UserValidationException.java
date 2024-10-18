package com.chakray.users.domain.exception;

import com.chakray.users.domain.use_case.user.validator.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class UserValidationException extends Exception {

	private final List<Error> errors;

	public UserValidationException(String message, List<Error> errors) {
		super(message);
		this.errors = errors;
	}

}

