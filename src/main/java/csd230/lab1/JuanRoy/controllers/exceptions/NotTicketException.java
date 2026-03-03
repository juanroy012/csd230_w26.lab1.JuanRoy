package csd230.lab1.JuanRoy.controllers.exceptions;

public class NotTicketException extends RuntimeException {
    public NotTicketException(Long id) {
        super("Item with the id " + id + " is not a ticket!");
    }
}

