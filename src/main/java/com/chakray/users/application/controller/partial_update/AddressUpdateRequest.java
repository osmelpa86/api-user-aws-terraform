package com.chakray.users.application.controller.partial_update;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddressUpdateRequest {

	@Schema(description = "${user.address.street.description}")
	@JsonProperty("street")
	private final String street;

	@Schema(description = "${user.address.countryCode.description}")
	@JsonProperty("country_code")
	private final String countryCode;

}