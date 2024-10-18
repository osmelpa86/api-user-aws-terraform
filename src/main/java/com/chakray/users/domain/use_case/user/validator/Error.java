package com.chakray.users.domain.use_case.user.validator;

import lombok.Data;

import java.util.List;

@Data
public class Error {

	private final String field;
	private final List<String> codes;

}
