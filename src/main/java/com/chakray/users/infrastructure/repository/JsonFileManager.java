package com.chakray.users.infrastructure.repository;

import com.chakray.users.domain.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JsonFileManager {

	private final ObjectMapper objectMapper;
	private final String jsonFilePath;

	public List<User> read() {
		try {
			return objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<User>>() {
			});
		} catch(IOException e) {
			throw new RuntimeException("Error writing to JSON file", e);
		}
	}

	public void write(List<User> users) {
		try {
			objectMapper.writeValue(new File(jsonFilePath), users);
		} catch(IOException e) {
			throw new RuntimeException("Error writing to JSON file", e);
		}
	}

}
