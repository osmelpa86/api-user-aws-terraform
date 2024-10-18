package com.chakray.users.domain.use_case.address.update;

import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressUpdateUseCase {

	private final UserRepository userRepository;

	public Address execute(Long userId, Long addressId, AddressUpdateInput addressUpdateInput) throws ResourceNotFoundException {
		return this.userRepository.updateAddress(userId, addressId, addressUpdateInput);
	}

}
