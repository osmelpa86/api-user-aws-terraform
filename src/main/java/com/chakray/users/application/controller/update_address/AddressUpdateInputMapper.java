package com.chakray.users.application.controller.update_address;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;

public class AddressUpdateInputMapper implements Mapper<AddressUpdateRequest, AddressUpdateInput> {

	@Override
	public AddressUpdateInput map(AddressUpdateRequest request) {
		return new AddressUpdateInput(
			request.getStreet(),
			request.getCountryCode()
		);
	}

}