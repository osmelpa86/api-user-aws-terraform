package com.chakray.users.domain.use_case.user.partial_update;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AddressPartialUpdateInput {

	private final String street;
	private final String countryCode;

}
