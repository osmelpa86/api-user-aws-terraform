package com.chakray.users.domain.use_case.user.list;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserListUseCaseTest {

	@InjectMocks
	private UserListUseCase useCase;

	@Mock
	private UserRepository userRepository;

	@Test
	void when_use_case_is_invoked_then_users_are_searched() {
		this.useCase.execute("name");

		verify(this.userRepository).getAllUsers("name");
	}

	@Test
	void when_users_are_found_then_are_retrieved() {
		Map<String, Address> rawAddresses = Map.of(
			"workaddress", new Address(6L, "::workaddress-street::", "UK"),
			"homeaddress", new Address(7L, "::homeaddress-street::", "AU")
		);
		List<Map<String, Address>> addressList = new ArrayList<>();
		rawAddresses.forEach((key, value) -> {
			addressList.add(Map.of(key, value));
		});

		when(this.userRepository.getAllUsers("name")).thenReturn(
			List.of(new User(3L, "::email::", "::name::", "::created_at::", addressList))
		);

		List<User> response = this.useCase.execute("name");

		assertEquals(List.of(new User(3L, "::email::", "::name::", "::created_at::", addressList)), response);
	}

}