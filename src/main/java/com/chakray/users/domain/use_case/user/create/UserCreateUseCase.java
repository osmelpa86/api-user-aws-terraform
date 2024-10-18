package com.chakray.users.domain.use_case.user.create;

import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateUseCase {

	private final UserRepository userRepository;
	private final UserCreateInputValidator userCreateInputValidator;

	public User execute(UserCreateInput userCreateInput) throws UserValidationException {
		this.userCreateInputValidator.validate(userCreateInput);
		return this.userRepository.save(userCreateInput);
	}

}
