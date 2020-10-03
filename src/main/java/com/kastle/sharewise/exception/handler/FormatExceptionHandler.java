package com.kastle.sharewise.exception.handler;

import com.kastle.sharewise.exception.FormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class FormatExceptionHandler {
    @ExceptionHandler(value = FormatException.class)
    public ResponseEntity<Object> exception(FormatException exception) {
        if (exception.getMessage() == null)
            return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
