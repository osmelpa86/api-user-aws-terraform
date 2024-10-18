package com.chakray.users.application.controller.list_address;

import com.chakray.users.application.controller.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class AddressListResponse {

	@JsonProperty("addresses")
	private final List<Map<String, AddressResponse>> addressListResponses;
}
