package com.chakray.users.application.configuration.exeption_handler.error.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorContainerResponse {

    @JsonProperty("errors")
    private final ErrorsResponse errors;

}

