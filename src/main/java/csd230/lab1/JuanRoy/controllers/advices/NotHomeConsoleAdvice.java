package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.NotHomeConsoleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class NotHomeConsoleAdvice {
    @ExceptionHandler(NotHomeConsoleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    String notHomeConsoleHandler(NotHomeConsoleException ex) {
        return ex.getMessage();
    }
}

