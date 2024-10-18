package com.chakray.users.domain.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SortHelper {

	public <T> List<T> sort(List<T> list, String fieldName) {
		List<T> sortedList = new ArrayList<>(list);
		Comparator<T> comparator;

		if ("created_at".equals(fieldName)) {
			fieldName = "createdAt";
		}

		try {
			Field field = sortedList.get(0).getClass().getDeclaredField(fieldName);
			field.setAccessible(true);

			Function<T, Comparable> fieldExtractor = o -> {
				try {
					return (Comparable) field.get(o);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			};

			comparator = Comparator.comparing(fieldExtractor, Comparator.nullsLast(Comparator.naturalOrder()));
			sortedList.sort(comparator);

		} catch (NoSuchFieldException e) {
			throw new IllegalArgumentException("Invalid field name: " + fieldName);
		}

		return sortedList;
	}

}
