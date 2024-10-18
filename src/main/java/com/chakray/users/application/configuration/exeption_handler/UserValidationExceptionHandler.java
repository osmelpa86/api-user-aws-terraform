package com.chakray.users.application.configuration.exeption_handler;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorContainerResponse;
import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorsResponseCreator;
import com.chakray.users.application.configuration.exeption_handler.error.response.FieldErrorResponse;
import com.chakray.users.application.contract.ListMapper;
import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.use_case.user.validator.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserValidationExceptionHandler {

	private final ListMapper<Error, FieldErrorResponse> errorFieldErrorResponseListMapper;
	private final ErrorsResponseCreator errorsResponseCreator;

	@ExceptionHandler(UserValidationException.class)
	public ResponseEntity<Object> handle(UserValidationException ex) {
		log.error(ex.getMessage(), ex);

		return new ResponseEntity<>(
			new ErrorContainerResponse(
				this.errorsResponseCreator.create(
					this.errorFieldErrorResponseListMapper.map(ex.getErrors())
				)
			),
			HttpStatus.BAD_REQUEST
		);
	}

}
