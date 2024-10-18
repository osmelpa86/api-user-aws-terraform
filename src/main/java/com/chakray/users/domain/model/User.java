package com.chakray.users.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class User {
	private final Long id;
	private final String email;
	private final String name;
	@JsonProperty("created_at")
	private final String createdAt;
	private final List<Map<String, Address>> addresses;
	private String password;

	public Optional<String> getPassword() {
		return Optional.ofNullable(password);
	}
}
