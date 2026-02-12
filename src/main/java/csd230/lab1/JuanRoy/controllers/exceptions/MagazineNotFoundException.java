package csd230.lab1.JuanRoy.controllers.exceptions;

public class MagazineNotFoundException extends RuntimeException {
    public MagazineNotFoundException(Long id) {
        super("Could not find magazine with ID: " + id);
    }
}
