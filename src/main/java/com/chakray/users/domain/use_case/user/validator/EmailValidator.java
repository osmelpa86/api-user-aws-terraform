package com.chakray.users.domain.use_case.user.validator;

import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidator {

	private final UserRepository userRepository;

	public boolean isValid(String email) {
		return this.userRepository.findByEmail(email).isEmpty();
	}

	public boolean alreadyExist(String email, Long userId) {
		return this.userRepository.findByEmail(email)
			.filter(user -> !user.getId().equals(userId))
			.isPresent();
	}

}
