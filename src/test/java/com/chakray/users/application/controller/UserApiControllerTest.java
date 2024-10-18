package com.chakray.users.application.controller;

import com.chakray.users.application.contract.ListMapper;
import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.contract.Response;
import com.chakray.users.application.controller.create_user.UserCreateRequest;
import com.chakray.users.application.controller.detail_user.UserResponse;
import com.chakray.users.application.controller.list_users.UserListResponse;
import com.chakray.users.application.controller.list_users.UserSort;
import com.chakray.users.application.controller.partial_update.UserUpdateRequest;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.application.controller.update_address.AddressUpdateRequest;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.use_case.address.list.ListUserAddressByIdUseCase;
import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;
import com.chakray.users.domain.use_case.address.update.AddressUpdateUseCase;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateUseCase;
import com.chakray.users.domain.use_case.user.delete.UserDeleteCase;
import com.chakray.users.domain.use_case.user.list.UserListUseCase;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {

	@InjectMocks
	private UserApiController controller;

	@Mock
	private UserListUseCase userListUseCase;

	@Mock
	private UserCreateUseCase userCreateUseCase;

	@Mock
	private ListUserAddressByIdUseCase listUserAddressByIdUseCase;

	@Mock
	private AddressUpdateUseCase addressUpdateUseCase;

	@Mock
	private UserPartialUpdateUseCase userPartialUpdateUseCase;

	@Mock
	private UserDeleteCase userDeleteCase;

	@Mock
	private Mapper<User, UserResponse> userResponseMapper;

	@Mock
	private Mapper<UserCreateRequest, UserCreateInput> userCreateInputMapper;

	@Mock
	private Mapper<Address, AddressResponse> addressResponseMapper;

	@Mock
	private Mapper<AddressUpdateRequest, AddressUpdateInput> addressUpdateInputMapper;

	@Mock
	private Mapper<UserUpdateRequest, UserPartialUpdateInput> addresUpdateInputMapper;

	@Mock
	private ListMapper<User, UserResponse> userResponseListMapper;

	@Mock
	private Mapper<List<Map<String, Address>>, List<Map<String, AddressResponse>>> addressListResponseMapper;

	@Test
	void when_get_all_users_is_called_then_use_case_is_invoked() {
		this.controller.listUsers(UserSort.name);

		verify(this.userListUseCase).execute("name");
	}

	@Test
	void when_get_all_users_is_called_and_users_are_retrieved_then_users_are_mapped() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(6L, "::workaddress-street::", "UK")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});
		Map<String, AddressResponse> expectedAddresses = Map.of(
			"workaddress", new AddressResponse(6L, "::workaddress-street::", "UK")
		);
		List<Map<String, AddressResponse>> expectedAddressList = new ArrayList<>();
		expectedAddresses.forEach((key, value) -> {
			expectedAddressList.add(Map.of(key, value));
		});
		when(this.userListUseCase.execute("name"))
			.thenReturn(List.of(new User(3L, "::email::", "::name::", "::created_at::", addressList)));

		this.controller.listUsers(UserSort.name);

		verify(this.userResponseListMapper).map(List.of(new User(
			3L,
			"::email::",
			"::name::",
			"::created_at::",
			addressList
		)));
	}

	@Test
	void when_get_all_users_is_called_and_user_are_mapped_then_users_are_retrieved() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(6L, "::workaddress-street::", "UK")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});
		Map<String, AddressResponse> expectedAddresses = Map.of(
			"workaddress", new AddressResponse(6L, "::workaddress-street::", "UK")
		);
		List<Map<String, AddressResponse>> expectedAddressList = new ArrayList<>();
		expectedAddresses.forEach((key, value) -> {
			expectedAddressList.add(Map.of(key, value));
		});
		List<UserResponse> expectedList = List.of(new UserResponse(
			3L,
			"::email::",
			"::name::",
			"::created_at::",
			expectedAddressList
		));
		when(this.userListUseCase.execute("name"))
			.thenReturn(List.of(new User(3L, "::email::", "::name::", "::created_at::", addressList)));
		when(this.userResponseListMapper.map(anyList())).thenReturn(expectedList);

		ResponseEntity<Response<UserListResponse>> response = this.controller.listUsers(UserSort.name);

		assertEquals(response.getStatusCodeValue(), 200);
		assertEquals(
			new Response<>(
				new UserListResponse(
					expectedList
				)
			),
			response.getBody()
		);
	}

}
