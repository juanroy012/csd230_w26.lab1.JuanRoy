package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.HandheldConsoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class HandheldConsoleNotFoundAdvice {
    @ExceptionHandler(HandheldConsoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handheldConsoleNotFoundHandler(HandheldConsoleNotFoundException ex) {
        return ex.getMessage();
    }
}

