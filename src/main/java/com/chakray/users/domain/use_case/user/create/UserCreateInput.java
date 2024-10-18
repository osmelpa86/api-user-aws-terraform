package com.chakray.users.domain.use_case.user.create;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Data
public class UserCreateInput {

	private final String email;
	private final String name;
	private final String password;
	private final List<Map<String, AddressCreateInput>> addressesCreateInput;

}
