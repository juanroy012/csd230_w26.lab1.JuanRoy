package csd230.lab1.JuanRoy.controllers.advices;

import csd230.lab1.JuanRoy.controllers.exceptions.MagazineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MagazineNotFoundAdvice {
    @ExceptionHandler(MagazineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String magazineNotFoundHandler(MagazineNotFoundException ex) {
        return ex.getMessage();
    }
}
