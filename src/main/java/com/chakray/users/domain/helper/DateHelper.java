package com.chakray.users.domain.helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	public String getCurrentTimeInUK() {
		return ZonedDateTime.now(ZoneId.of("Europe/London")).format(formatter);
	}

	public long getCurrentUnixTime() {
		return Instant.now().getEpochSecond();
	}
}