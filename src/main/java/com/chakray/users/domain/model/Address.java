package com.chakray.users.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {

	private final Long id;
	private final String street;
	@JsonProperty("country_code")
	private final String countryCode;
}