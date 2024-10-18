package com.chakray.users.infrastructure.mappers;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class AddressPartialUpdateInputToAddressMapper implements Mapper<AddressPartialUpdateInput, Address> {

	@Override
	public Address map(AddressPartialUpdateInput input) {
		return new Address(
			ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE),
			input.getStreet(),
			input.getCountryCode()
		);
	}

}
