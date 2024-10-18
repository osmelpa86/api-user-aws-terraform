package com.chakray.users.infrastructure.repository;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.helper.SortHelper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final Mapper<UserCreateInput, User> userMapper;
	private final Mapper<AddressPartialUpdateInput, Address> addressMapper;
	private final JsonFileManager jsonFileManager;
	private final SortHelper sortHelper;

	@Override
	public List<User> getAllUsers(String sortByField) {
		return this.sortHelper.sort(
			this.jsonFileManager.read(),
			sortByField
		);
	}

	@Override
	public List<Map<String, Address>> getUserAddress(Long userId) throws ResourceNotFoundException {
		return this.jsonFileManager.read().stream()
			.filter(user -> user.getId().equals(userId))
			.findFirst()
			.map(User::getAddresses)
			.orElseThrow(() -> new ResourceNotFoundException(
				"exception.resource.not.found",
				new String[]{"User", "userId", userId.toString()}
			));
	}

	@Override
	public Address updateAddress(
		Long userId,
		Long addressId,
		AddressUpdateInput updateInput
	) throws ResourceNotFoundException {
		List<User> users = this.jsonFileManager.read();

		User user = users.stream()
			.filter(u -> u.getId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(
				"exception.resource.not.found",
				new String[]{"Users", "userId", userId.toString()}
			));

		Map<String, Address> addressMap = user.getAddresses().stream()
			.filter(map -> map.values().stream().anyMatch(address -> address.getId().equals(addressId)))
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(
				"exception.resource.not.found",
				new String[]{"Address", "addressId", addressId.toString()}
			));

		String addressKey = addressMap.entrySet().stream()
			.filter(entry -> entry.getValue().getId().equals(addressId))
			.map(Map.Entry::getKey)
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(
				"exception.resource.not.found",
				new String[]{"Address", "addressId", addressId.toString()}
			));

		Address updatedAddress = new Address(
			addressId,
			updateInput.getStreet(),
			updateInput.getCountryCode()
		);

		addressMap.put(addressKey, updatedAddress);

		this.jsonFileManager.write(users);

		return updatedAddress;
	}

	@Override
	public User save(UserCreateInput userCreateInput) {
		User user = this.userMapper.map(userCreateInput);
		List<User> users = this.jsonFileManager.read();
		users.add(user);
		this.jsonFileManager.write(users);
		return user;
	}

	@Override
	public User updatePartialUser(Long userId, UserPartialUpdateInput partialInput) throws ResourceNotFoundException {
		List<User> users = this.jsonFileManager.read();

		User existingUser = users.stream()
			.filter(u -> u.getId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(
				"exception.resource.not.found",
				new String[]{"Users", "userId", userId.toString()}
			));

		User updatedUser = new User(
			existingUser.getId(),
			partialInput.getEmail().orElse(existingUser.getEmail()),
			partialInput.getName().orElse(existingUser.getName()),
			existingUser.getCreatedAt(),
			partialInput.getAddresses()
				.map(newAddresses -> newAddresses.stream()
					.map(addressMap -> addressMap.entrySet().stream()
						.collect(Collectors.toMap(
							Map.Entry::getKey,
							entry -> this.addressMapper.map(entry.getValue())
						))
					)
					.collect(Collectors.toList()))
				.orElse(existingUser.getAddresses())
		).setPassword(partialInput.getPassword().orElse(existingUser.getPassword().orElse(null)));

		List<User> updatedUsers = users.stream()
			.map(u -> u.getId().equals(userId) ? updatedUser : u)
			.collect(Collectors.toList());

		this.jsonFileManager.write(updatedUsers);

		return updatedUser;
	}

	@Override
	public void deleteUser(Long userId) {
		List<User> users = this.jsonFileManager.read();
		users.removeIf(user -> user.getId().equals(userId));
		this.jsonFileManager.write(users);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.jsonFileManager.read()
			.stream()
			.filter(user -> user.getEmail().equals(email))
			.findFirst();
	}

}
