package com.chakray.users.application.controller.create_user;

import com.chakray.users.application.validator.AddressesCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserCreateRequest {

	@Schema(description = "${user.email.description}")
	@JsonProperty("email")
	@NotBlank(message = "Cannot be blank")
	@NotNull(message = "Cannot be null")
	@Email(message = "Invalid email")
	private final String email;

	@Schema(description = "${user.name.description}")
	@JsonProperty("name")
	@NotBlank(message = "Cannot be blank")
	@NotNull(message = "Cannot be null")
	private final String name;

	@Schema(description = "${user.password.description}")
	@JsonProperty("password")
	@NotBlank(message = "Cannot be blank")
	@NotNull(message = "Cannot be null")
	private final String password;

	@Schema(description = "${user.addresses.description}")
	@JsonProperty("addresses")
	@AddressesCreate(message = "Invalid addresses format")
	private final List<Map<String, AddressCreateRequest>> addresses;

}
