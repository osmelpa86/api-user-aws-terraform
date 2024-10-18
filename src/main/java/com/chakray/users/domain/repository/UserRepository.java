package com.chakray.users.domain.repository;

import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {

	List<User> getAllUsers(String sortByField);

	User save(UserCreateInput userCreateInput);

	List<Map<String, Address>> getUserAddress(Long userId) throws ResourceNotFoundException;

	Address updateAddress(Long userId, Long addressId, AddressUpdateInput updateInput) throws ResourceNotFoundException;

	User updatePartialUser(Long userId, UserPartialUpdateInput userPartialUpdateInput) throws ResourceNotFoundException;

	void deleteUser(Long userId);

	Optional<User> findByEmail(String email);

}