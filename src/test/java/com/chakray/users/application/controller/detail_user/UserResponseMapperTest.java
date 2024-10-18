package com.chakray.users.application.controller.detail_user;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserResponseMapperTest {
	@InjectMocks
	private UserResponseMapper mapper;

	@Mock
	private Mapper<Address, AddressResponse> addressResponseMapper;

	@Test
	void when_user_response_is_mapped_then_address_is_mapped() {
		when(this.addressResponseMapper.map(any()))
			.thenReturn(new AddressResponse(1L, "::workaddress-street::", "UK"))
			.thenReturn(new AddressResponse(2L, "::homeaddress-street::", "AU"));

		Map<String, Address> rawAddresses = new LinkedHashMap<>();
		rawAddresses.put("workaddress", new Address(1L, "::workaddress-street::", "UK"));
		rawAddresses.put("homeaddress", new Address(2L, "::homeaddress-street::", "AU"));
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		this.mapper.map(
			new User(
				1L,
				"::email::",
				"::name::",
				"::createdAt::",
				addressList
			)
		);

		verify(this.addressResponseMapper, times(1))
			.map(new Address(1L, "::workaddress-street::", "UK"));
		verify(this.addressResponseMapper, times(1))
			.map(new Address(2L, "::homeaddress-street::", "AU"));
	}

	@Test
	void when_address_is_mapped_then_user_response_is_retrieved() {
		when(this.addressResponseMapper.map(any()))
			.thenReturn(new AddressResponse(1L, "::workaddress-street::", "UK"))
			.thenReturn(new AddressResponse(2L, "::homeaddress-street::", "AU"));

		Map<String, Address> rawAddresses = new LinkedHashMap<>();
		rawAddresses.put("workaddress", new Address(1L, "::workaddress-street::", "UK"));
		rawAddresses.put("homeaddress", new Address(2L, "::homeaddress-street::", "AU"));
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		Map<String, AddressResponse> expectedAddress = new LinkedHashMap<>();
		expectedAddress.put("workaddress", new AddressResponse(1L, "::workaddress-street::", "UK"));
		expectedAddress.put("homeaddress", new AddressResponse(2L, "::homeaddress-street::", "AU"));
		List<Map<String, AddressResponse>> expectedAddressList = new ArrayList<>();
		expectedAddress.forEach((key, value) -> expectedAddressList.add(Map.of(key, value)));

		UserResponse response = this.mapper.map(
			new User(
				1L,
				"::email::",
				"::name::",
				"::createdAt::",
				addressList
			)
		);

		assertEquals(1L, response.getId());
		assertEquals("::email::", response.getEmail());
		assertEquals("::name::", response.getName());
		assertEquals("::createdAt::", response.getCreatedAt());
		assertEquals(expectedAddressList, response.getAddresses());
	}
}