package com.chakray.users.application.controller.partial_update;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUpdateInputMapperTest {

	@InjectMocks
	private UserUpdateInputMapper mapper;

	@Mock
	private Mapper<AddressUpdateRequest, AddressPartialUpdateInput> addressCreateInputMapper;

	@Test
	void when_user_response_is_mapped_then_address_is_mapped() {
		when(this.addressCreateInputMapper.map(any()))
			.thenReturn(new AddressPartialUpdateInput( "::workaddress-street::", "UK"))
			.thenReturn(new AddressPartialUpdateInput( "::homeaddress-street::", "AU"));

		Map<String, AddressUpdateRequest> rawAddresses = new LinkedHashMap<>();
		rawAddresses.put("workaddress", new AddressUpdateRequest("::workaddress-street::", "UK"));
		rawAddresses.put("homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "AU"));
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		this.mapper.map(
			new UserUpdateRequest()
				.setName("::name::")
				.setEmail("::email::")
				.setPassword("::password::")
				.setAddresses(addressList)
		);

		verify(this.addressCreateInputMapper, times(1))
			.map(new AddressUpdateRequest("::workaddress-street::", "UK"));
		verify(this.addressCreateInputMapper, times(1))
			.map(new AddressUpdateRequest("::homeaddress-street::", "AU"));
	}


	@Test
	void when_address_is_mapped_then_user_partial_update_is_retrieved() {
		when(this.addressCreateInputMapper.map(any()))
			.thenReturn(new AddressPartialUpdateInput( "::workaddress-street::", "UK"))
			.thenReturn(new AddressPartialUpdateInput( "::homeaddress-street::", "AU"));

		Map<String, AddressUpdateRequest> rawAddresses = new LinkedHashMap<>();
		rawAddresses.put("workaddress", new AddressUpdateRequest("::workaddress-street::", "UK"));
		rawAddresses.put("homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "AU"));
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> addressList.add(Map.of(key, value)));

		Map<String, AddressPartialUpdateInput> expectedAddress = new LinkedHashMap<>();
		expectedAddress.put("workaddress", new AddressPartialUpdateInput("::workaddress-street::", "UK"));
		expectedAddress.put("homeaddress", new AddressPartialUpdateInput("::homeaddress-street::", "AU"));
		List<Map<String, AddressPartialUpdateInput>> expectedAddressList = new ArrayList<>();
		expectedAddress.forEach((key, value) -> expectedAddressList.add(Map.of(key, value)));

		UserPartialUpdateInput response = this.mapper.map(
			new UserUpdateRequest()
				.setName("::name::")
				.setEmail("::email::")
				.setPassword("::password::")
				.setAddresses(addressList)
		);

		assertEquals(Optional.of("::email::"), response.getEmail());
		assertEquals(Optional.of("::name::"), response.getName());
		assertEquals(Optional.of("::password::"), response.getPassword());
		assertEquals(Optional.of(expectedAddressList), response.getAddresses());
	}

	@Test
	void when_address_is_mapped_but_values_are_not_given_then_user_partial_update_is_retrieved() {
		UserPartialUpdateInput response = this.mapper.map(
			new UserUpdateRequest()
				.setName(null)
				.setEmail(null)
				.setPassword(null)
				.setAddresses(null)
		);

		assertEquals(Optional.empty(), response.getEmail());
		assertEquals(Optional.empty(), response.getName());
		assertEquals(Optional.empty(), response.getPassword());
		assertEquals(Optional.empty(), response.getAddresses());
	}


}