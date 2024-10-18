package com.chakray.users.application.controller;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorContainerResponse;
import com.chakray.users.application.contract.Response;
import com.chakray.users.application.controller.create_user.UserCreateRequest;
import com.chakray.users.application.controller.detail_user.UserResponse;
import com.chakray.users.application.controller.list_address.AddressListResponse;
import com.chakray.users.application.controller.list_users.UserListResponse;
import com.chakray.users.application.controller.list_users.UserSort;
import com.chakray.users.application.controller.partial_update.UserUpdateRequest;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.application.controller.update_address.AddressUpdateRequest;
import com.chakray.users.domain.exception.ResourceNotFoundException;
import com.chakray.users.domain.exception.UserValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(
	name = "${api.user.tag.name}",
	description = "${api.user.tag.description}"
)
@Validated
public interface UserApi {
	@Operation(
		summary = "${api.user.op.list}",
		description = "${api.user.op.list.description}",
		operationId = "listUsers"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "${api.user.op.list.response.code.200.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserListResponse.class))),
		@ApiResponse(responseCode = "400", description = "${api.op.response.code.400.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Response<UserListResponse>> listUsers(
		@Parameter(in = ParameterIn.QUERY, name = "sortedBy", description = "${api.op.list.parameter.sortedBy.description}", example = "email") UserSort sortedBy
	);

	@Operation(
		summary = "${api.user.op.address.list}",
		description = "${api.user.op.address.list.description}",
		operationId = "listAddresses"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "${api.user.op.list.response.code.200.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressListResponse.class))),
		@ApiResponse(responseCode = "404", description = "${api.op.response.code.404.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Response<AddressListResponse>> listAddresses(
		@Parameter(in = ParameterIn.PATH, name = "user_id", description = "${api.op.address.list.parameter.userId.description}", example = "1234567", required = true) Long userId
	) throws ResourceNotFoundException;

	@Operation(
		summary = "${api.address.op.update.summary}",
		description = "${api.address.op.update.description}",
		operationId = "updateAddress"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "${api.user.op.create.response.code.200.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressResponse.class))),
		@ApiResponse(responseCode = "400", description = "${api.op.response.code.400.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "404", description = "${api.op.response.code.404.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Response<AddressResponse>> updateAddress(
		@Parameter(in = ParameterIn.PATH, name = "user_id", description = "${api.op.address.update.parameter.userId.description}", example = "1234567", required = true) Long userId,
		@Parameter(in = ParameterIn.PATH, name = "address_id", description = "${api.op.address.update.parameter.addressId.description}", example = "458989", required = true) Long addressId,
		@Valid @RequestBody(description = "Object request to update address.", required = true) AddressUpdateRequest addressUpdateRequest
	) throws ResourceNotFoundException;

	@Operation(
		summary = "${api.user.op.create.summary}",
		description = "${api.user.op.create.description}",
		operationId = "createUser"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "${api.user.op.create.response.code.201.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
		@ApiResponse(responseCode = "400", description = "${api.op.response.code.400.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Response<UserResponse>> createUser(
		@Valid @RequestBody(description = "Object request to create a user.", required = true) UserCreateRequest request
	) throws UserValidationException;

	@Operation(
		summary = "${api.user.op.update.partial.summary}",
		description = "${api.user.op.update.partial.description}",
		operationId = "partialUserUpdate"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "${api.user.op.partial.update.response.code.200.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
		@ApiResponse(responseCode = "400", description = "${api.op.response.code.400.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "404", description = "${api.op.response.code.404.description}",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorContainerResponse.class))),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Response<UserResponse>> partialUserUpdate(
		@Parameter(in = ParameterIn.PATH, name = "user_id", description = "${api.op.address.update.parameter.userId.description}", example = "1234567", required = true) Long userId,
		@Valid @RequestBody(description = "Object request to create a user.", required = true) UserUpdateRequest request
	) throws ResourceNotFoundException, UserValidationException;

	@Operation(
		summary = "${api.user.op.delete.summary}",
		description = "${api.user.op.delete.description}",
		operationId = "deleteUser"
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "${api.user.op.delete.response.code.204.description}"),
		@ApiResponse(responseCode = "500", description = "${api.op.response.code.500.description}",
			content = @Content)
	})
	ResponseEntity<Void> deleteUser(
		@Parameter(in = ParameterIn.PATH, name = "user_id", description = "${api.user.op.delete.parameter.userId}", example = "12345", required = true) Long userId
	);

}
