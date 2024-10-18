package com.chakray.users.application.bean;

import com.chakray.users.domain.repository.UserRepository;
import com.chakray.users.domain.use_case.address.list.ListUserAddressByIdUseCase;
import com.chakray.users.domain.use_case.address.update.AddressUpdateUseCase;
import com.chakray.users.domain.use_case.user.create.UserCreateInputValidator;
import com.chakray.users.domain.use_case.user.create.UserCreateUseCase;
import com.chakray.users.domain.use_case.user.delete.UserDeleteCase;
import com.chakray.users.domain.use_case.user.list.UserListUseCase;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInputValidator;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCase {

	@Bean
	public UserListUseCase userListUseCase(UserRepository userRepository) {
		return new UserListUseCase(userRepository);
	}

	@Bean
	public ListUserAddressByIdUseCase listUserAddressByIdUseCase(UserRepository userRepository) {
		return new ListUserAddressByIdUseCase(userRepository);
	}

	@Bean
	public UserCreateUseCase userCreateUseCase(
		UserRepository userRepository,
		UserCreateInputValidator userCreateInputValidator
	) {
		return new UserCreateUseCase(userRepository, userCreateInputValidator);
	}

	@Bean
	public AddressUpdateUseCase addressUpdateUseCase(UserRepository userRepository) {
		return new AddressUpdateUseCase(userRepository);
	}

	@Bean
	public UserPartialUpdateUseCase userPartialUpdateUseCase(
		UserRepository userRepository,
		UserPartialUpdateInputValidator userPartialUpdateInputValidator
	) {
		return new UserPartialUpdateUseCase(userRepository, userPartialUpdateInputValidator);
	}

	@Bean
	public UserDeleteCase userDeleteCase(UserRepository userRepository) {
		return new UserDeleteCase(userRepository);
	}

}
