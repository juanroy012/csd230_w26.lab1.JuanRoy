package csd230.lab1.JuanRoy.controllers.exceptions;

public class DiscMagNotFoundException extends RuntimeException {
    public DiscMagNotFoundException(Long id) {
        super("Could not find disc magazine with ID: " + id);
    }
}

