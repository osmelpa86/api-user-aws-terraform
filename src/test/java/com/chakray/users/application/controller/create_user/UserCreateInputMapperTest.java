package com.chakray.users.application.controller.create_user;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
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
class UserCreateInputMapperTest {

	@InjectMocks
	private UserCreateInputMapper mapper;

	@Mock
	private Mapper<AddressCreateRequest, AddressCreateInput> addressCreateInputMapper;

	@Test
	void when_user_create_input_is_mapped_then_address_is_mapped() {
		when(this.addressCreateInputMapper.map(any()))
			.thenReturn(new AddressCreateInput("::workaddress-street::", "UK"))
			.thenReturn(new AddressCreateInput("::homeaddress-street::", "AU"));
		Map<String, AddressCreateRequest> rawAddresses = Map.of(
			"workaddress", new AddressCreateRequest("::workaddress-street::", "UK"),
			"homeaddress", new AddressCreateRequest("::homeaddress-street::", "AU")
		);
		List<Map<String, AddressCreateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		this.mapper.map(
			new UserCreateRequest(
				"::email::",
				"::name::",
				"::createdAt::",
				addressList
			)
		);

		verify(this.addressCreateInputMapper, times(1))
			.map(new AddressCreateRequest("::workaddress-street::", "UK"));
		verify(this.addressCreateInputMapper, times(1))
			.map(new AddressCreateRequest("::homeaddress-street::", "AU"));
	}

	@Test
	void when_address_is_mapped_then_user_create_input_is_retrieved() {
		when(this.addressCreateInputMapper.map(any()))
			.thenReturn(new AddressCreateInput("::workaddress-street::", "UK"))
			.thenReturn(new AddressCreateInput("::homeaddress-street::", "AU"));

		Map<String, AddressCreateRequest> rawAddresses = new LinkedHashMap<>();
		rawAddresses.put("workaddress", new AddressCreateRequest("::workaddress-street::", "UK"));
		rawAddresses.put("homeaddress", new AddressCreateRequest("::homeaddress-street::", "AU"));
		List<Map<String, AddressCreateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		Map<String, AddressCreateInput> expectedAddress = new LinkedHashMap<>();
		expectedAddress.put("workaddress", new AddressCreateInput("::workaddress-street::", "UK"));
		expectedAddress.put("homeaddress", new AddressCreateInput("::homeaddress-street::", "AU"));
		List<Map<String, AddressCreateInput>> expectedAddressList = new ArrayList<>();
		expectedAddress.forEach((key, value) -> expectedAddressList.add(Map.of(key, value)));

		UserCreateInput input = this.mapper.map(
			new UserCreateRequest(
				"::email::",
				"::name::",
				"::password::",
				addressList
			)
		);

		assertEquals("::email::", input.getEmail());
		assertEquals("::name::", input.getName());
		assertEquals("::password::", input.getPassword());
		assertEquals(expectedAddressList, input.getAddressesCreateInput());
	}

}
