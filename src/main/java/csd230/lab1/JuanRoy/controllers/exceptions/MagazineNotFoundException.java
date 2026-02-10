package csd230.lab1.JuanRoy.controllers.exceptions;

public class MagazineNotFoundException extends RuntimeException {
    public MagazineNotFoundException(Long id) {
        super("Could not find book with ID: " + id);
    }
}
