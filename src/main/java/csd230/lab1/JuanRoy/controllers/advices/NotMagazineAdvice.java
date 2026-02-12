package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.NotBookException;
import csd230.lab1.JuanRoy.controllers.exceptions.NotMagazineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotMagazineAdvice {
    @ExceptionHandler(NotMagazineException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    String notMagazineHandler(NotMagazineException ex) { return ex.getMessage(); }
}
