package com.chakray.users.application.bean;

import com.chakray.users.domain.repository.UserRepository;
import com.chakray.users.domain.use_case.user.create.UserCreateInputValidator;
import com.chakray.users.domain.use_case.user.partial_update.UserPartialUpdateInputValidator;
import com.chakray.users.domain.use_case.user.validator.EmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validators {

	@Bean
	public EmailValidator emailValidator(UserRepository userRepository) {
		return new EmailValidator(userRepository);
	}

	@Bean
	public UserCreateInputValidator userCreateInputValidator(EmailValidator emailValidator) {
		return new UserCreateInputValidator(emailValidator);
	}

	@Bean
	public UserPartialUpdateInputValidator userPartialUpdateInputValidator(EmailValidator emailValidator) {
		return new UserPartialUpdateInputValidator(emailValidator);
	}

}
