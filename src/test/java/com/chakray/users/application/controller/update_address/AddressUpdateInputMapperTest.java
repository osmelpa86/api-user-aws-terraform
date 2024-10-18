package com.chakray.users.application.controller.update_address;

import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;
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
	void test_when_address_request_is_mapped_then_address_input_is_mapped() {
		AddressUpdateInput response = this.mapper.map( new AddressUpdateRequest("::workaddress-street::", "UK"));

		assertEquals("::workaddress-street::", response.getStreet());
		assertEquals("UK", response.getCountryCode());
	}

}