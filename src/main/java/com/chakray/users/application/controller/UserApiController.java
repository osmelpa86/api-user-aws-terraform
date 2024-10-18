package com.chakray.users.application.controller;

import com.chakray.users.application.contract.ListMapper;
import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.contract.Response;
import com.chakray.users.application.controller.create_user.UserCreateRequest;
import com.chakray.users.application.controller.detail_user.UserResponse;
import com.chakray.users.application.controller.list_address.AddressListResponse;
import com.chakray.users.application.controller.list_users.UserListResponse;
import com.chakray.users.application.controller.list_users.UserSort;
import com.chakray.users.application.controller.partial_update.UserUpdateRequest;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.application.controller.update_address.AddressUpdateRequest;
import com.chakray.users.application.utils.ApiUrls;
import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.exception.UserValidationException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrls.PATH_OF_USER, produces = APPLICATION_JSON_VALUE)
@Validated
public class UserApiController implements UserApi {
	private final UserListUseCase userListUseCase;
	private final UserCreateUseCase userCreateUseCase;
	private final ListUserAddressByIdUseCase listUserAddressByIdUseCase;
	private final AddressUpdateUseCase addressUpdateUseCase;
	private final UserPartialUpdateUseCase userPartialUpdateUseCase;
	private final UserDeleteCase userDeleteCase;
	private final Mapper<User, UserResponse> userResponseMapper;
	private final Mapper<UserCreateRequest, UserCreateInput> userCreateInputMapper;
	private final Mapper<Address, AddressResponse> addressResponseMapper;
	private final Mapper<AddressUpdateRequest, AddressUpdateInput> addressUpdateInputMapper;
	private final Mapper<UserUpdateRequest, UserPartialUpdateInput> addresUpdateInputMapper;
	private final ListMapper<User, UserResponse> userResponseListMapper;
	private final Mapper<List<Map<String, Address>>, List<Map<String, AddressResponse>>> addressListResponseMapper;

	@Override
	@GetMapping
	public ResponseEntity<Response<UserListResponse>> listUsers(
		@RequestParam(value = "sortedBy", required = false) UserSort sortedBy
	) {
		return ResponseEntity.ok(
			new Response<>(
				new UserListResponse(
					this.userResponseListMapper.map(
						this.userListUseCase.execute(sortedBy.name())
					))
			));
	}

	@Override
	@GetMapping(value = "/{user_id}/addresses")
	public ResponseEntity<Response<AddressListResponse>> listAddresses(@PathVariable("user_id") Long userId) throws ResourceNotFoundException {
		return ResponseEntity.ok(
			new Response<>(
				new AddressListResponse(
					this.addressListResponseMapper.map(
						this.listUserAddressByIdUseCase.execute(userId)
					)
				)
			)
		);
	}

	@Override
	@PutMapping(value = "/{user_id}/addresses/{address_id}", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<AddressResponse>> updateAddress(
		@PathVariable("user_id") Long userId,
		@PathVariable("address_id") Long addressId,
		@RequestBody AddressUpdateRequest addressUpdateRequest
	) throws ResourceNotFoundException {
		return ResponseEntity.ok(
			new Response<>(
				this.addressResponseMapper.map(
					this.addressUpdateUseCase.execute(
						userId,
						addressId,
						this.addressUpdateInputMapper.map(addressUpdateRequest)
					)
				)
			)
		);
	}

	@Override
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserResponse>> createUser(
		@RequestBody UserCreateRequest userCreateRequest
	) throws UserValidationException {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new Response<>(
				this.userResponseMapper.map(
					this.userCreateUseCase.execute(
						this.userCreateInputMapper.map(userCreateRequest)
					)
				)
			));
	}

	@Override
	@PatchMapping(value = "/{user_id}", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserResponse>> partialUserUpdate(
		@PathVariable("user_id") Long userId,
		@RequestBody UserUpdateRequest request
	) throws ResourceNotFoundException, UserValidationException {
		return ResponseEntity.ok(
			new Response<>(
				this.userResponseMapper.map(
					this.userPartialUpdateUseCase.execute(
						userId,
						this.addresUpdateInputMapper.map(request)
					)
				)
			)
		);
	}

	@Override
	@DeleteMapping(value = "/{user_id}")
	public ResponseEntity<Void> deleteUser(	@PathVariable("user_id") Long userId) {
		this.userDeleteCase.execute(userId);
		return ResponseEntity.noContent().build();
	}

}
