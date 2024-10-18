package com.chakray.users.domain.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptedHelper {

	public String toSha1(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] result = md.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
