package com.chakray.users.domain.use_case.user.partial_update;

import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPartialUpdateUseCase {

	private final UserRepository userRepository;
	private final UserPartialUpdateInputValidator userPartialUpdateInputValidator;

	public User execute(Long userId, UserPartialUpdateInput userPartialUpdateInput) throws ResourceNotFoundException, UserValidationException {
		this.userPartialUpdateInputValidator.validate(userPartialUpdateInput, userId);
		return this.userRepository.updatePartialUser(userId, userPartialUpdateInput);
	}

}
