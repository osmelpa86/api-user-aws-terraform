package com.chakray.users.infrastructure.mappers;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class AddressCreateInputToAddressMapper implements Mapper<AddressCreateInput, Address> {

	@Override
	public Address map(AddressCreateInput input) {
		return new Address(
			ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE),
			input.getStreet(),
			input.getCountryCode()
		);
	}
}
