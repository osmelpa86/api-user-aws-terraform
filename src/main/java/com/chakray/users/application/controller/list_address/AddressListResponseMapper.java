package com.chakray.users.application.controller.list_address;

import com.chakray.users.application.contract.Mapper;
import com.chakray.users.application.controller.response.AddressResponse;
import com.chakray.users.domain.model.Address;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddressListResponseMapper implements Mapper<List<Map<String, Address>>, List<Map<String, AddressResponse>>> {

	private final Mapper<Address, AddressResponse> addressResponseMapper;

	@Override
	public List<Map<String, AddressResponse>> map(List<Map<String, Address>> input) {
		return input.stream()
			.map(addressMap -> addressMap.entrySet()
				.stream()
				.collect(Collectors.toMap(
					Map.Entry::getKey,
					entry -> addressResponseMapper.map(entry.getValue())
				))
			)
			.collect(Collectors.toList());
	}
}
