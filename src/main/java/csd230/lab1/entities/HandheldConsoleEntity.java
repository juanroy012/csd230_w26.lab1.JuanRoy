package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HANDHELD CONSOLE")
public class HandheldConsoleEntity extends GameConsoleEntity {
    @Column(name = "battery_life_hours")
    private int batteryLifeHours;

    public HandheldConsoleEntity() {}

    public HandheldConsoleEntity(String manufacturer, double price, int quantity, int batteryLifeHours) {
        super(manufacturer, price, quantity);
        this.batteryLifeHours = batteryLifeHours;
    }

    public int getBatteryLifeHours() {
        return batteryLifeHours;
    }

    public void setBatteryLifeHours(int batteryLifeHours) {
        this.batteryLifeHours = batteryLifeHours;
    }

}