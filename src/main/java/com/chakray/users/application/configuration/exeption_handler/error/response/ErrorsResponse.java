package com.chakray.users.application.configuration.exeption_handler.error.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorsResponse {

    @JsonProperty("fields")
    private final List<FieldErrorResponse> fields;

    @JsonProperty("form")
    private final List<FormErrorResponse> form;
}
