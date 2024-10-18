package com.chakray.users.application.controller.partial_update;

import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddressUpdateInputMapperTest {

	@InjectMocks
	private AddressUpdateInputMapper mapper;

	@Test
	void when_address_input_is_givev_then_its_mapped() {
		AddressPartialUpdateInput response = this.mapper.map(
			new AddressUpdateRequest(
				"::street::",
				"UK"
			)
		);

		assertEquals("::street::", response.getStreet());
		assertEquals("UK", response.getCountryCode());
	}

}