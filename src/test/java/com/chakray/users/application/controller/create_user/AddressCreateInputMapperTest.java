package com.chakray.users.application.controller.create_user;

import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AddressCreateInputMapperTest {

	@InjectMocks
	private AddressCreateInputMapper mapper;

	@Test
	void test_when_request_is_mapped_then_object_is_retrieved() {
		AddressCreateRequest addressCreateRequest = new AddressCreateRequest("::workaddress-street::", "UK");

		AddressCreateInput input = this.mapper.map(addressCreateRequest);

		assertNotNull(input);
		assertEquals("::workaddress-street::", input.getStreet());
		assertEquals("UK", input.getCountryCode());
	}

}
