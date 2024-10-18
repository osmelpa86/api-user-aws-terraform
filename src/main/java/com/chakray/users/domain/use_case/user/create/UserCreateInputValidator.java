package com.chakray.users.domain.use_case.user.create;

import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.use_case.user.validator.EmailValidator;
import com.chakray.users.domain.use_case.user.validator.Error;
import com.chakray.users.domain.use_case.user.validator.ErrorEnum;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserCreateInputValidator {

	private final EmailValidator emailValidator;

	public void validate(UserCreateInput user) throws UserValidationException {
		List<Error> errors = new ArrayList<>();

		if(!this.emailValidator.isValid(user.getEmail())) {
			List<String> codes = new ArrayList<>();
			codes.add(ErrorEnum.INVALID_EMAIL.getCode());
			errors.add(new Error("email", codes));
		}

		if(!errors.isEmpty()) {
			throw new UserValidationException("Invalid request", errors);
		}
	}
}
