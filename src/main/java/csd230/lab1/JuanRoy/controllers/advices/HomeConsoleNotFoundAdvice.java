package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.HomeConsoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class HomeConsoleNotFoundAdvice {
    @ExceptionHandler(HomeConsoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String homeConsoleNotFoundHandler(HomeConsoleNotFoundException ex) {
        return ex.getMessage();
    }
}

