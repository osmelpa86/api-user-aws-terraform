package com.chakray.users.application.validator;

import com.chakray.users.application.controller.create_user.AddressCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class AddressesCreateValidator implements ConstraintValidator<AddressesCreate, List<Map<String, AddressCreateRequest>>> {

	@SneakyThrows
	@Override
	public boolean isValid(List<Map<String, AddressCreateRequest>> addresses, ConstraintValidatorContext context) {
		if (addresses == null || addresses.isEmpty()) {
			return false;
		}

		for (Map<String, AddressCreateRequest> addressMap : addresses) {
			for (Map.Entry<String, AddressCreateRequest> entry : addressMap.entrySet()) {
				AddressCreateRequest address = entry.getValue();
				if (address.getStreet() == null || address.getStreet().isBlank()) {
					return false;
				}
				if (address.getCountryCode() == null || address.getCountryCode().isBlank()) {
					return false;
				}
			}
		}
		return true;
	}
}