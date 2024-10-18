package com.chakray.users.application.controller.detail_user;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserResponseMapper implements Mapper<User, UserResponse> {

	private final Mapper<Address, AddressResponse> addressResponseMapper;

	@Override
	public UserResponse map(User model) {
		List<Map<String, AddressResponse>> addressResponses = model.getAddresses()
			.stream()
			.map(address -> address.entrySet()
				.stream()
				.collect(Collectors.toMap(
					Map.Entry::getKey,
					entry -> this.addressResponseMapper.map(entry.getValue())
				))
			)
			.collect(Collectors.toList());

		return new UserResponse(
			model.getId(),
			model.getEmail(),
			model.getName(),
			model.getCreatedAt(),
			addressResponses
		);
	}

}