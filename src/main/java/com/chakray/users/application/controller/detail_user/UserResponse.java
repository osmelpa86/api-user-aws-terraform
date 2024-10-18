package com.chakray.users.application.controller.detail_user;

import com.chakray.users.application.controller.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {

	@Schema(description = "${user.userId.description}")
	@JsonProperty("id")
	private final Long id;

	@Schema(description = "${user.email.description}")
	@JsonProperty("email")
	private final String email;

	@Schema(description = "${user.name.description}")
	@JsonProperty("name")
	private final String name;

	@Schema(description = "${user.password.description}")
	@JsonProperty("password")
	private String password;

	@Schema(description = "${user.createdAt.description}")
	@JsonProperty("created_at")
	private final String createdAt;

	@Schema(description = "${user.addresses.description}")
	@JsonProperty("addresses")
	private final List<Map<String, AddressResponse>> addresses;

}
