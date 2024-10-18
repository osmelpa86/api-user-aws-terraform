package com.chakray.users.application.controller.create_user;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;

public class AddressCreateInputMapper implements Mapper<AddressCreateRequest, AddressCreateInput> {

	@Override
	public AddressCreateInput map(AddressCreateRequest request) {
		return new AddressCreateInput(
			request.getStreet(),
			request.getCountryCode()
		);
	}

}