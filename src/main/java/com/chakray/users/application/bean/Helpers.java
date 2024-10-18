package com.chakray.users.application.bean;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorsResponseCreator;
import com.chakray.users.domain.helper.DateHelper;
import com.chakray.users.domain.helper.EncryptedHelper;
import com.chakray.users.domain.helper.GenerateIdHelper;
import com.chakray.users.domain.helper.SortHelper;
import com.chakray.users.domain.helper.UserComparator;
import com.chakray.users.infrastructure.repository.JsonFileManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Helpers {

	@Bean
	public JsonFileManager jsonFile(
		ObjectMapper objectMapper,
		@Value("${json.file-path:src/main/resources/users.json}") String jsonFilePath
	) {
		return new JsonFileManager(objectMapper, jsonFilePath);
	}

	@Bean
	public DateHelper dateHelper() {
		return new DateHelper();
	}

	@Bean
	public EncryptedHelper encryptedHelper() {
		return new EncryptedHelper();
	}

	@Bean
	public SortHelper sortHelper() {
		return new SortHelper();
	}

	@Bean
	public GenerateIdHelper generateIdHelper() {
		return new GenerateIdHelper();
	}

	@Bean
	public UserComparator userComparator() {
		return new UserComparator();
	}

	@Bean
	public ErrorsResponseCreator errorsResponseCreator() {
		return new ErrorsResponseCreator();
	}

}
