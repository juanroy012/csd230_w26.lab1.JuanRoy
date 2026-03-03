package csd230.lab1.JuanRoy.controllers.exceptions;

public class HomeConsoleNotFoundException extends RuntimeException {
    public HomeConsoleNotFoundException(Long id) {
        super("Could not find home console with ID: " + id);
    }
}

