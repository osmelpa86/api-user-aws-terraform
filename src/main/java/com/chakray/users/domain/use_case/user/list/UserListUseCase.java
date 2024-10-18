package com.chakray.users.domain.use_case.user.list;

import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserListUseCase {

	private final UserRepository userRepository;

	public List<User> execute(String sortByField) {
		return this.userRepository.getAllUsers(sortByField);
	}

}