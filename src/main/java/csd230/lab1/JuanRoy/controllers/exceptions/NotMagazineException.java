package csd230.lab1.JuanRoy.controllers.exceptions;

public class NotMagazineException extends RuntimeException {
    public NotMagazineException(Long id) {
        super("Item with the id " + id + " is not a magazine!");
    }
}
