package com.chakray.users.domain.helper;

import java.util.Collection;
import java.util.function.Function;

public class GenerateIdHelper {
	public static <T> Long generate(Collection<T> collection, Function<T, Long> idExtractor) {
		return collection.stream()
			.map(idExtractor)
			.max(Long::compareTo)
			.orElse(0L) + 1;
	}
}
