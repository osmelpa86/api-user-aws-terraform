package com.chakray.users.domain.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception {

	private final String[] args;

	public ResourceNotFoundException(String message, String[] args) {
		super(message);
		this.args = args;
	}

	public ResourceNotFoundException(String message, String[] args, Throwable cause) {
		super(message, cause);
		this.args = args;
	}

}
