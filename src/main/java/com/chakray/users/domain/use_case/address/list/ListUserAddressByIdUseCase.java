package com.chakray.users.domain.use_case.address.list;

import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ListUserAddressByIdUseCase {

	private final UserRepository userRepository;

	public List<Map<String, Address>> execute(Long userId) throws ResourceNotFoundException {
		return this.userRepository.getUserAddress(userId);
	}
}
