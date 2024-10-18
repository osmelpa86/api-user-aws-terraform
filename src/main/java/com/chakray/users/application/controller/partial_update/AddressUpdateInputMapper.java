package com.chakray.users.application.controller.partial_update;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;

public class AddressUpdateInputMapper implements Mapper<AddressUpdateRequest, AddressPartialUpdateInput> {

	@Override
	public AddressPartialUpdateInput map(AddressUpdateRequest request) {
		return new AddressPartialUpdateInput(
			request.getStreet(),
			request.getCountryCode()
		);
	}

}