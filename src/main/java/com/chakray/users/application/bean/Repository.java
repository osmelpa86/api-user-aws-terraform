package com.chakray.users.application.bean;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.domain.helper.SortHelper;
import com.chakray.users.domain.model.Address;
import com.chakray.users.domain.model.User;
import com.chakray.users.domain.repository.UserRepository;
import com.chakray.users.domain.use_case.user.create.UserCreateInput;
import com.chakray.users.domain.use_case.user.partial_update.AddressPartialUpdateInput;
import com.chakray.users.infrastructure.repository.JsonFileManager;
import com.chakray.users.infrastructure.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repository {

	@Bean
	public UserRepository userRepository(
		Mapper<UserCreateInput, User> userMapper,
		JsonFileManager jsonFileManager,
		SortHelper sortHelper,
		Mapper<AddressPartialUpdateInput, Address> addressMapper
	) {
		return new UserRepositoryImpl(userMapper, addressMapper, jsonFileManager,sortHelper);
	}
}
