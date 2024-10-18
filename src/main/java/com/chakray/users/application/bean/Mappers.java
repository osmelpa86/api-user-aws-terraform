package com.chakray.users.application.bean;

import com.chakray.users.application.configuration.exeption_handler.error.response.FielErrorResponseMapper;
import com.chakray.users.application.configuration.exeption_handler.error.response.FieldErrorResponse;
import com.chakray.users.application.contract.ListMapper;
import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.controller.create_user.AddressCreateInputMapper;
import com.chakray.users.application.controller.create_user.AddressCreateRequest;
import com.chakray.users.application.controller.create_user.UserCreateInputMapper;
import com.chakray.users.application.controller.create_user.UserCreateRequest;
import com.chakray.users.application.controller.detail_user.UserResponse;
import com.chakray.users.application.controller.detail_user.UserResponseMapper;
import com.chakray.users.application.controller.list_address.AddressListResponseMapper;
import com.chakray.users.application.controller.partial_update.UserUpdateInputMapper;
import com.chakray.users.application.controller.partial_update.UserUpdateRequest;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.application.controller.response.AddressResponseMapper;
import com.chakray.users.application.controller.update_address.AddressUpdateInputMapper;
import com.chakray.users.application.controller.update_address.AddressUpdateRequest;
import com.chakray.users.domain.helper.DateHelper;
import com.chakray.users.domain.helper.EncryptedHelper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.use_case.address.update.AddressUpdateInput;
import com.chakray.users.domain.use_case.user.create.AddressCreateInput;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInput;
import com.chakray.users.domain.use_case.user.validator.Error;
import com.chakray.users.infrastructure.mappers.AddressCreateInputToAddressMapper;
import com.chakray.users.infrastructure.mappers.AddressPartialUpdateInputToAddressMapper;
import com.chakray.users.infrastructure.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class Mappers {

	@Bean
	public Mapper<AddressCreateInput, Address> addressMapper() {
		return new AddressCreateInputToAddressMapper();
	}

	@Bean
	public Mapper<UserCreateInput, User> userMapper(
		Mapper<AddressCreateInput, Address> addressMapper,
		DateHelper dateHelper,
		EncryptedHelper encryptedHelper
	) {
		return new UserMapper(addressMapper, dateHelper, encryptedHelper);
	}

	@Bean
	public Mapper<User, UserResponse> userResponseMapper(Mapper<Address, AddressResponse> addressResponseMapper) {
		return new UserResponseMapper(addressResponseMapper);
	}

	@Bean
	public ListMapper<User, UserResponse> userResponseListMapper(Mapper<User, UserResponse> userResponseMapper) {
		return new ListMapper<>(userResponseMapper);
	}

	@Bean
	public Mapper<Address, AddressResponse> addressResponseMapper() {
		return new AddressResponseMapper();
	}

	@Bean
	public Mapper<AddressCreateRequest, AddressCreateInput> addressCreateInputMapper() {
		return new AddressCreateInputMapper();
	}

	@Bean
	public Mapper<UserCreateRequest, UserCreateInput> userCreateRequestUserCreateInputMapper(Mapper<AddressCreateRequest, AddressCreateInput> addressCreateInputMapper) {
		return new UserCreateInputMapper(addressCreateInputMapper);
	}

	@Bean
	public Mapper<List<Map<String, Address>>, List<Map<String, AddressResponse>>> addressListResponseMapper(Mapper<Address, AddressResponse> addressResponseMapper) {
		return new AddressListResponseMapper(addressResponseMapper);
	}

	@Bean
	public Mapper<AddressUpdateRequest, AddressUpdateInput> addressUpdateInputMapper() {
		return new AddressUpdateInputMapper();
	}

	@Bean
	public Mapper<com.chakray.users.application.controller.partial_update.AddressUpdateRequest, AddressPartialUpdateInput> addressPartialUpdateInputMapper() {
		return new com.chakray.users.application.controller.partial_update.AddressUpdateInputMapper();
	}

	@Bean
	public Mapper<UserUpdateRequest, UserPartialUpdateInput> userUpdateRequestUserPartialUpdateInputMapper(
		Mapper<com.chakray.users.application.controller.partial_update.AddressUpdateRequest, AddressPartialUpdateInput> addressPartialUpdateInputMapper
	) {
		return new UserUpdateInputMapper(addressPartialUpdateInputMapper);
	}

	@Bean
	public Mapper<AddressPartialUpdateInput, Address> addressUpdateInputAddressMapper() {
		return new AddressPartialUpdateInputToAddressMapper();
	}

	@Bean
	public Mapper<Error, FieldErrorResponse> errorFieldErrorResponseMapper() {
		return new FielErrorResponseMapper();
	}

	@Bean
	public ListMapper<Error, FieldErrorResponse> errorFieldErrorResponseListMapper(
		Mapper<Error, FieldErrorResponse> errorFieldErrorResponseMapper
	) {
		return new ListMapper<>(errorFieldErrorResponseMapper);
	}

}
