package csd230.lab1.entities;

import csd230.lab1.pojos.HomeConsole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HOME CONSOLE")
public class HomeConsoleEntity extends GameConsoleEntity {
    @Column(name = "max_resolution")
    private String maxResolution;

    public HomeConsoleEntity() {}

    public HomeConsoleEntity(String manufacturer, double price, int quantity, String maxResolution) {
        super(manufacturer, price, quantity);
        this.maxResolution = maxResolution;
    }

    public String getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(String maxResolution) {
        this.maxResolution = maxResolution;
    }

}