package com.parameta.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.parameta.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvisor.class);

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> validateHandlerException(MethodArgumentNotValidException ex) {
        LOGGER.info("validateHandlerException");
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorDto error = ErrorDto.builder().message(message).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<ErrorDto> validateHandlerException(InvalidFormatException ex) {
        LOGGER.info("validateHandlerException");
        String message = "El formato debe ser yyyy-mm-dd";
        ErrorDto error = ErrorDto.builder().message(message).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
