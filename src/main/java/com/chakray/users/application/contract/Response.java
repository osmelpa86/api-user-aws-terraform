package com.chakray.users.application.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Response<T> {

    @Valid
    @NotNull
    @JsonProperty("content")
    private final T content;

    @JsonCreator
    public Response(
            @JsonProperty("content") T content
    ) {
        this.content = content;
    }
}