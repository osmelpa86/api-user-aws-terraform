package com.chakray.users.domain.use_case.user.partial_update;

import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.use_case.user.validator.EmailValidator;
import com.chakray.users.domain.use_case.user.validator.Error;
import com.chakray.users.domain.use_case.user.validator.ErrorEnum;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserPartialUpdateInputValidator {

	private final EmailValidator emailValidator;

	public void validate(UserPartialUpdateInput user, Long userId) throws UserValidationException {
		List<Error> errors = new ArrayList<>();

		if(user.getEmail().isPresent() && this.emailValidator.alreadyExist(user.getEmail().get(), userId)) {
			List<String> codes = new ArrayList<>();
			codes.add(ErrorEnum.INVALID_EMAIL.getCode());
			errors.add(new Error("email", codes));
		}

		if(!errors.isEmpty()) {
			throw new UserValidationException("Invalid request", errors);
		}
	}
}
