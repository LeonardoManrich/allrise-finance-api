package com.allrise.njord.exception.handler;

import com.allrise.njord.exception.BadRequestException;
import com.allrise.njord.exception.detail.GenericExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandlerResolver {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GenericExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                GenericExceptionDetails.builder()
                        .timestap(LocalDateTime.now())
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericExceptionDetails> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<Map<String, String>> listOfMapOfFieldErrors;

        listOfMapOfFieldErrors = methodArgumentNotValidException.getFieldErrors().stream()
                .map(fieldError ->
                        Map.of(fieldError.getField(), Objects.requireNonNull(fieldError.getDefaultMessage()))
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                GenericExceptionDetails.builder()
                        .timestap(LocalDateTime.now())
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(listOfMapOfFieldErrors)
                        .developerMessage(methodArgumentNotValidException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
