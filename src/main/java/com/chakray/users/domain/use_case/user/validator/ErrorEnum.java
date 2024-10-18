package com.chakray.users.domain.use_case.user.validator;

import lombok.Getter;

@Getter
public enum ErrorEnum {

	INVALID_EMAIL("Email already exists");

	private final String code;

	ErrorEnum(String code) {
		this.code = code;
	}

}
