package com.chakray.users.domain.use_case.user.partial_update;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Accessors(chain = true)
@Data
public class UserPartialUpdateInput {

	private String email;
	private String name;
	private String password;
	private List<Map<String, AddressPartialUpdateInput>> addresses;

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public Optional<String> getPassword() {
		return Optional.ofNullable(password);
	}

	public Optional<List<Map<String, AddressPartialUpdateInput>>> getAddresses() {
		return Optional.ofNullable(addresses);
	}

}
