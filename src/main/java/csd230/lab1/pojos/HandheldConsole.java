package csd230.lab1.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.HandheldConsoleEntity}
 */
/**
 * DTO for {@link csd230.lab1.entities.HandheldConsoleEntity}
 */
public class HandheldConsole extends GameConsole {
    private int batteryLifeHours;

    public int getBatteryLifeHours() {
        return batteryLifeHours;
    }

    public void setBatteryLifeHours(int batteryLifeHours) {
        this.batteryLifeHours = batteryLifeHours;
    }

    @Override
    public String toString() {
        return "HandheldConsole{" +
                "batteryLifeHours=" + batteryLifeHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HandheldConsole that)) return false;
        return batteryLifeHours == that.batteryLifeHours;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(batteryLifeHours);
    }
}
