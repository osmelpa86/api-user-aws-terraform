package com.chakray.users.application.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddressResponse {

	@Schema(description = "${user.address.addressId.description}")
	@JsonProperty("id")
	private final Long id;

	@Schema(description = "${user.address.street.description}")
	@JsonProperty("street")
	private final String street;

	@Schema(description = "${user.address.countryCode.description}")
	@JsonProperty("country_code")
	private final String countryCode;
}