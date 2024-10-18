package com.chakray.users.domain.use_case.user.create;

import com.chakray.users.domain.exception.UserValidationException;
import com.chakray.users.domain.use_case.user.validator.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCreateInputValidatorTest {

	@InjectMocks
	private UserCreateInputValidator validator;

	@Mock
	private EmailValidator emailValidator;

	@Test
	void when_user_is_given_then_email_is_validate() throws UserValidationException {
		Map<String, AddressCreateInput> rawAddresses = Map.of(
			"workaddress", new AddressCreateInput("::workaddress-street::", "UK"),
			"homeaddress", new AddressCreateInput( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressCreateInput>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});
		UserCreateInput userCreateInput = new UserCreateInput("::email::", "::name::", "::password::", addressList);
		when(this.emailValidator.isValid(anyString())).thenReturn(Boolean.TRUE);
		this.validator.validate(userCreateInput);

		verify(this.emailValidator).isValid("::email::");
	}

	@Test
	void when_user_is_given_and_email_is_valid_then_user_create_input_is_valid() throws UserValidationException {
		when(this.emailValidator.isValid("::email::")).thenReturn(Boolean.TRUE);

		Map<String, AddressCreateInput> rawAddresses = Map.of(
			"workaddress", new AddressCreateInput("::workaddress-street::", "UK"),
			"homeaddress", new AddressCreateInput( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressCreateInput>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});
		UserCreateInput userCreateInput = new UserCreateInput("::email::", "::name::", "::password::", addressList);

		assertDoesNotThrow(() -> this.validator.validate(userCreateInput));
	}
	@Test
	void when_user_is_given_and_email_is_invalid_then_error_is_thrown() throws UserValidationException {
		when(this.emailValidator.isValid("::email::")).thenReturn(Boolean.FALSE);

		Map<String, AddressCreateInput> rawAddresses = Map.of(
			"workaddress", new AddressCreateInput("::workaddress-street::", "UK"),
			"homeaddress", new AddressCreateInput( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressCreateInput>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});
		UserCreateInput userCreateInput = new UserCreateInput("::email::", "::name::", "::password::", addressList);

		assertThrows(
			UserValidationException.class,
			() -> this.validator.validate(userCreateInput)
		);
	}

}