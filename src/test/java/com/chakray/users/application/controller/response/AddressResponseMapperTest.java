package com.chakray.users.application.controller.response;

import com.chakray.users.domain.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddressResponseMapperTest {

	@InjectMocks
	private AddressResponseMapper mapper;

	@Test
	void test_when_address_is_mapped_then_address_response_is_mapped() {
		AddressResponse response = this.mapper.map( new Address(2L, "::workaddress-street::", "UK"));

		assertEquals(2L, response.getId());
		assertEquals("::workaddress-street::", response.getStreet());
		assertEquals("UK", response.getCountryCode());
	}

}