package com.chakray.users.application.controller.partial_update;

import com.chakray.users.application.validator.AddressesUpdate;
import com.chakray.users.application.validator.CustomNotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserUpdateRequest {

	@Schema(description = "${user.email.description}")
	@JsonProperty("email")
	@Email(message = "Invalid email")
	private String email;

	@Schema(description = "${user.name.description}")
	@CustomNotBlank(message = "Cannot be blank")
	@JsonProperty("name")
	private String name;

	@Schema(description = "${user.password.description}")
	@JsonProperty("password")
	@CustomNotBlank(message = "Cannot be blank")
	private String password;

	@Schema(description = "${user.addresses.description}")
	@JsonProperty("addresses")
	@AddressesUpdate(message = "Invalid addresses format")
	private List<Map<String, AddressUpdateRequest>> addresses;

}
