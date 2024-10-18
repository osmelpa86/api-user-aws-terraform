package com.chakray.users.application.controller.list_users;

import com.chakray.users.application.controller.detail_user.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserListResponse {

	@JsonProperty("users")
	private final List<UserResponse> userListResponses;
}
