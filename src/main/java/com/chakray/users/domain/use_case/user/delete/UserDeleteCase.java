package com.chakray.users.domain.use_case.user.delete;

import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDeleteCase {

	private final UserRepository userRepository;

	public void execute(Long userId) {
		this.userRepository.deleteUser(userId);
	}

}
