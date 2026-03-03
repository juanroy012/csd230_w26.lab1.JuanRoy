package csd230.lab1.JuanRoy.controllers.exceptions;

public class NotHomeConsoleException extends RuntimeException {
    public NotHomeConsoleException(Long id) {
        super("Item with the id " + id + " is not a home console!");
    }
}

