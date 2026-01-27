package csd230.lab1.JuanRoy.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {

    private String author;

    public BookEntity() {}

    public BookEntity(String n, double p, int c, String a) { super(n, p, c); this.author = a; }

    public String getAuthor() { return author; }

    public void setAuthor(String a) { this.author = a; }

    @Override public String toString() { return "Book{author='" + author + "', " + super.toString() + "}"; }
}
