package com.chakray.users.application.configuration.exeption_handler.error.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FieldErrorResponse {

    @Schema(description = "El nombre del campo con error.", example = "name")
    @JsonProperty("field")
    private final String field;

    @Schema(description = "El listado de los códigos de errores.", example = "[\"user.name.required\"]")
    @JsonProperty("codes")
    private final List<String> codes;
}