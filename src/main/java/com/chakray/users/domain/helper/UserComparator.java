package com.chakray.users.domain.helper;

import com.chakray.users.domain.model.User;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserComparator {
	public Map<String, Comparator<User>> sortFieldMap = new HashMap<>() {{
		put("id", Comparator.comparing(User::getId));
		put("email", Comparator.comparing(User::getEmail));
		put("name", Comparator.comparing(User::getName));
		put("createdAt", Comparator.comparing(User::getCreatedAt));
	}};
}
