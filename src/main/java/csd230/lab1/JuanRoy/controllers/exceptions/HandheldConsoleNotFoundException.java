package csd230.lab1.JuanRoy.controllers.exceptions;

public class HandheldConsoleNotFoundException extends RuntimeException {
    public HandheldConsoleNotFoundException(Long id) {
        super("Could not find handheld console with ID: " + id);
    }
}

