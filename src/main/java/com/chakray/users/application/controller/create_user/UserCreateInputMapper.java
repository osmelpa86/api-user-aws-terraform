package com.chakray.users.application.controller.create_user;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserCreateInputMapper implements Mapper<UserCreateRequest, UserCreateInput> {

	private final Mapper<AddressCreateRequest, AddressCreateInput> addressCreateInputMapper;

	@Override
	public UserCreateInput map(UserCreateRequest request) {
		List<Map<String, AddressCreateInput>> addressResponses = request.getAddresses()
			.stream()
			.map(address -> address.entrySet()
				.stream()
				.collect(Collectors.toMap(
					Map.Entry::getKey,
					entry -> this.addressCreateInputMapper.map(entry.getValue())
				))
			)
			.collect(Collectors.toList());

		return new UserCreateInput(
			request.getEmail(),
			request.getName(),
			request.getPassword(),
			addressResponses
		);
	}

}