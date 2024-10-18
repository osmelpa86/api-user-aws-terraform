package com.chakray.users.application.configuration.exeption_handler;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorContainerResponse;
import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorsResponseCreator;
import com.chakray.users.application.configuration.exeption_handler.error.response.FieldErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MethodArgumentNotValidExceptionHandler {

    private final ErrorsResponseCreator errorsResponseCreator;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<FieldErrorResponse> fieldErrorList = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        List.of(Objects.requireNonNull(fieldError.getDefaultMessage()))
                ))
                .collect(Collectors.toList());


        List<FieldErrorResponse> globalErrorList = ex.getBindingResult().getGlobalErrors()
                .stream()
                .map(objectError -> new FieldErrorResponse(
                        objectError.getObjectName(),
                        List.of(Objects.requireNonNull(objectError.getDefaultMessage()))
                ))
                .collect(Collectors.toList());

        List<FieldErrorResponse> errors = Stream.of(fieldErrorList, globalErrorList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new ErrorContainerResponse(this.errorsResponseCreator.create(errors)),
                HttpStatus.BAD_REQUEST
        );
    }
}
