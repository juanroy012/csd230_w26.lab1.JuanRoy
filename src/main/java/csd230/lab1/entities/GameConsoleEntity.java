package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class GameConsoleEntity extends ProductEntity {
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "console_price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    public GameConsoleEntity() {}

    public GameConsoleEntity(String manufacturer, double price, int quantity){
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public void sellItem() {
        setQuantity(getQuantity() - 1);
    }
}