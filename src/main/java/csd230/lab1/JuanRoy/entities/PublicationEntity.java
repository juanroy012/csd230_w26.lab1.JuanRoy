package csd230.lab1.JuanRoy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("PUBLICATION")
public abstract class PublicationEntity extends ProductEntity {

    @Column(name = "pub_price") private double price;

    private int copies;

    public PublicationEntity() {}

    public PublicationEntity(String n, double p, int c) { this.setName(n); this.price = p; this.copies = c; }

    @Override public void sellItem() {
        if (copies > 0) { copies--; System.out.println("Sold '" + getName() + "'. Remaining copies: " + copies); }
        else { System.out.println("Cannot sell '" + getName() + "'. Out of stock."); }
    }

    @Override public double getPrice() { return price; }

    public void setPrice(double p) { this.price = p; }

    public int getCopies() { return copies; }

    public void setCopies(int c) { this.copies = c; }

    @Override public String toString() { return "Pub{title='" + getName() + "', price=" + price + ", copies=" + copies + "}"; }
}
