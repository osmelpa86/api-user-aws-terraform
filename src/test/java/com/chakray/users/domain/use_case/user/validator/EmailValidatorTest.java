package com.chakray.users.domain.use_case.user.validator;

import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailValidatorTest {

	@InjectMocks
	private EmailValidator validator;

	@Mock
	private UserRepository userRepository;

	@Test
	void when_email_is_alreadyExist_then_its_searched() {
		this.validator.alreadyExist("::email::", 3L);

		verify(this.userRepository).findByEmail("::email::");
	}

	@Test
	void when_email_already_exist_then_true_is_retrieved() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(2L, "::workaddress-street::", "UK"),
			"homeaddress", new Address( 3L,"::homeaddress-street::", "AU")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		when(this.userRepository.findByEmail("::email::")).thenReturn(
			Optional.of(new User(9L, "::email::", "::name::", "::created-at::", addressList))
		);

		boolean response = this.validator.alreadyExist("::email::", 3L);

		assertTrue(response);
	}

	@Test
	void when_email_not_already_exist_then_its_false() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(2L, "::workaddress-street::", "UK"),
			"homeaddress", new Address( 3L,"::homeaddress-street::", "AU")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		when(this.userRepository.findByEmail("::email::")).thenReturn(Optional.empty());

		boolean response = this.validator.alreadyExist("::email::", 3L);

		assertFalse(response);
	}

	@Test
	void when_email_already_exist_but_is_same_user_then_its_false() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(2L, "::workaddress-street::", "UK"),
			"homeaddress", new Address( 3L,"::homeaddress-street::", "AU")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		when(this.userRepository.findByEmail("::email::")).thenReturn(
			Optional.of(new User(3L, "::email::", "::name::", "::created-at::", addressList))
		);

		boolean response = this.validator.alreadyExist("::email::", 3L);

		assertFalse(response);
	}

}