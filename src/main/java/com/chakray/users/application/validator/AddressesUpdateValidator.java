package com.chakray.users.application.validator;

import com.chakray.users.application.controller.partial_update.AddressUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class AddressesUpdateValidator implements ConstraintValidator<AddressesUpdate, List<Map<String, AddressUpdateRequest>>> {

	@SneakyThrows
	@Override
	public boolean isValid(List<Map<String, AddressUpdateRequest>> addresses, ConstraintValidatorContext context) {
		if (addresses == null || addresses.isEmpty()) {
			return true;
		}

		for (Map<String, AddressUpdateRequest> addressMap : addresses) {
			for (Map.Entry<String, AddressUpdateRequest> entry : addressMap.entrySet()) {
				AddressUpdateRequest address = entry.getValue();
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