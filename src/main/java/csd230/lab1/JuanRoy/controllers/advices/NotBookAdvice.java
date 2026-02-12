package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.NotBookException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotBookAdvice {
    @ExceptionHandler(NotBookException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    String notBookHandler(NotBookException ex) { return ex.getMessage(); }

}
