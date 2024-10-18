package com.chakray.users.domain.use_case.address.update;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressUpdateInput {

	private final String street;
	private final String countryCode;

}