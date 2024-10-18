package com.chakray.users.application.validator;

import com.chakray.users.application.controller.partial_update.AddressUpdateRequest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AddressesUpdateValidatorTest {

	@InjectMocks
	private AddressesUpdateValidator validator;

	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	@Test
	void when_address_is_correct_then_address_is_valid() {
		Map<String, AddressUpdateRequest> rawAddresses = Map.of(
			"workaddress", new AddressUpdateRequest("::workaddress-street::", "UK"),
			"homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		boolean response = this.validator.isValid(addressList, constraintValidatorContext);

		assertTrue(response);
	}

	@Test
	void when_address_is_null_then_address_is_valid_because_is_optional_edit() {
		boolean response = this.validator.isValid(null, constraintValidatorContext);

		assertTrue(response);
	}

	@Test
	void when_address_is_empty_then_address_is_invalid() {
		boolean response = this.validator.isValid(new ArrayList<>(), constraintValidatorContext);

		assertTrue(response);
	}

	@Test
	void when_address_street_is_blank_then_address_is_invalid() {
		Map<String, AddressUpdateRequest> rawAddresses = Map.of(
			"workaddress", new AddressUpdateRequest("      ", "UK"),
			"homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		boolean response = this.validator.isValid(addressList, constraintValidatorContext);

		assertFalse(response);
	}

	@Test
	void when_address_street_is_null_then_address_is_invalid() {
		Map<String, AddressUpdateRequest> rawAddresses = Map.of(
			"workaddress", new AddressUpdateRequest(null, "UK"),
			"homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "AU")
		);
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		boolean response = this.validator.isValid(addressList, constraintValidatorContext);

		assertFalse(response);
	}

	@Test
	void when_address_country_code_is_null_then_address_is_invalid() {
		Map<String, AddressUpdateRequest> rawAddresses = Map.of(
			"workaddress", new AddressUpdateRequest("::workaddress-street::", "UK"),
			"homeaddress", new AddressUpdateRequest( "::homeaddress-street::", null)
		);
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		boolean response = this.validator.isValid(addressList, constraintValidatorContext);

		assertFalse(response);
	}

	@Test
	void when_address_country_code_is_blank_then_address_is_invalid() {
		Map<String, AddressUpdateRequest> rawAddresses = Map.of(
			"workaddress", new AddressUpdateRequest("::workaddress-street::", "UK"),
			"homeaddress", new AddressUpdateRequest( "::homeaddress-street::", "   ")
		);
		List<Map<String, AddressUpdateRequest>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		boolean response = this.validator.isValid(addressList, constraintValidatorContext);

		assertFalse(response);
	}

}