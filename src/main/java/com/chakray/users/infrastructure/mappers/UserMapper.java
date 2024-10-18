package com.chakray.users.infrastructure.mappers;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.helper.DateHelper;
import com.chakray.users.domain.helper.EncryptedHelper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper implements Mapper<UserCreateInput, User> {
	private final Mapper<AddressCreateInput, Address> addressMapper;
	private final DateHelper dateHelper;
	private final EncryptedHelper encryptedHelper;

	@Override
	public User map(UserCreateInput input) {
		List<Map<String, Address>> mappedAddresses = input.getAddressesCreateInput()
			.stream()
			.map(address -> address.entrySet()
				.stream()
				.collect(Collectors.toMap(
					Map.Entry::getKey,
					entry -> this.addressMapper.map(entry.getValue())
				))
			)
			.collect(Collectors.toList());

		User user = new User(
			this.dateHelper.getCurrentUnixTime(),
			input.getEmail(),
			input.getName(),
			this.dateHelper.getCurrentTimeInUK(),
			mappedAddresses
		).setPassword(this.encryptedHelper.toSha1(input.getPassword()));

		return user;
	}
}
