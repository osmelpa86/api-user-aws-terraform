package com.chakray.users.application.controller.update_address;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressUpdateRequest {

	@Schema(description = "${user.address.street.description}")
	@JsonProperty("street")
	@NotBlank(message = "Cannot be blank")
	@NotNull(message = "Cannot be null")
	private final String street;

	@Schema(description = "${user.address.countryCode.description}")
	@JsonProperty("country_code")
	@NotBlank(message = "Cannot be blank")
	@NotNull(message = "Cannot be null")
	private final String countryCode;

}