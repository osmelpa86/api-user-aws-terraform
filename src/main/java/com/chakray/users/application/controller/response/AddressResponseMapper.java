package com.chakray.users.application.controller.response;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.model.Address;

public class AddressResponseMapper implements Mapper<Address, AddressResponse> {

	@Override
	public AddressResponse map(Address model) {
		return new AddressResponse(
			model.getId(),
			model.getStreet(),
			model.getCountryCode()
		);
	}
}