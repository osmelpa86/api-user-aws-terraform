package com.chakray.users.application.configuration.exeption_handler.error.response;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.use_case.user.validator.Error;

public class FielErrorResponseMapper implements Mapper<Error, FieldErrorResponse> {

	@Override
	public FieldErrorResponse map(Error error) {
		return new FieldErrorResponse(error.getField(), error.getCodes());
	}

}
