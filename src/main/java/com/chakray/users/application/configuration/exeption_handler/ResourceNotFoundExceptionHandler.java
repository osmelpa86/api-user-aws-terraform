package com.chakray.users.application.configuration.exeption_handler;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorContainerResponse;
import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorsResponseCreator;
import com.chakray.users.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResourceNotFoundExceptionHandler {

    private final ErrorsResponseCreator errorsResponseCreator;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handle(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ErrorContainerResponse(this.errorsResponseCreator.create(ex.getMessage(), List.of(ex.getArgs()))),
                HttpStatus.NOT_FOUND
        );
    }

}
