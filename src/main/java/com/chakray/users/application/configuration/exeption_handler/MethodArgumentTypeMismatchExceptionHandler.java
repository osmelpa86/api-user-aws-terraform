package com.chakray.users.application.configuration.exeption_handler;

import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorContainerResponse;
import com.chakray.users.application.configuration.exeption_handler.error.response.ErrorsResponseCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MethodArgumentTypeMismatchExceptionHandler {

    private final ErrorsResponseCreator errorsResponseCreator;

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handle(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorContainerResponse(
                        this.errorsResponseCreator.create(
                                "exception.argument.type.mismatch",
                                List.of(new String[]{
                                        ex.getValue().toString(),
                                        ex.getRequiredType().getSimpleName(),
                                        ex.getName()}
                                )
                        )),
                HttpStatus.BAD_REQUEST
        );
    }

}
