package org.example.springboot_2.handler;

import org.example.springboot_2.Exceptions.BadRequestException;
import org.example.springboot_2.Exceptions.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException are) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder().
                        timeStand(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception")
                        .details(are.getMessage())
                        .devloperMessage(are.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
