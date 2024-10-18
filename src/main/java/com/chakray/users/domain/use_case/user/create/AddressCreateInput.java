package com.chakray.users.domain.use_case.user.create;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AddressCreateInput {

	private final String street;
	private final String countryCode;
}

