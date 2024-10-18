package com.chakray.users.application.configuration.exeption_handler.error.response;

import java.util.List;

public class ErrorsResponseCreator {

    public ErrorsResponse create(List<FieldErrorResponse> fields) {
        return new ErrorsResponse(fields, List.of());
    }

    public ErrorsResponse create(String code, List<String> args) {
        return new ErrorsResponse(List.of(), List.of(new FormErrorResponse(code, args)));
    }

}
