package com.chakray.users.application.controller.partial_update;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserUpdateInputMapper implements Mapper<UserUpdateRequest, UserPartialUpdateInput> {

	private final Mapper<AddressUpdateRequest, AddressPartialUpdateInput>  addressCreateInputMapper;

	@Override
	public UserPartialUpdateInput map(UserUpdateRequest request) {

		UserPartialUpdateInput userPartialUpdateInput = new UserPartialUpdateInput();

		Optional.ofNullable(request.getName()).ifPresent(userPartialUpdateInput::setName);
		Optional.ofNullable(request.getEmail()).ifPresent(userPartialUpdateInput::setEmail);
		Optional.ofNullable(request.getPassword()).ifPresent(userPartialUpdateInput::setPassword);

		Optional.ofNullable(request.getAddresses()).ifPresent(addresses -> {
			List<Map<String, AddressPartialUpdateInput>> addressInputs = addresses.stream()
				.map(address -> address.entrySet().stream()
					.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> this.addressCreateInputMapper.map(entry.getValue())
					))
				).collect(Collectors.toList());

			userPartialUpdateInput.setAddresses(addressInputs);
		});

		return userPartialUpdateInput;
	}

}