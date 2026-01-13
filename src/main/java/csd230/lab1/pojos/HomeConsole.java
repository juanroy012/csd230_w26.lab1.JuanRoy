package csd230.lab1.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.entities.HomeConsoleEntity}
 */
public class HomeConsole extends GameConsole {

    private String maxResolution;

    public String getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(String maxResolution) {
        this.maxResolution = maxResolution;
    }

    @Override
    public String toString() {
        return "HomeConsole{" +
                "maxResolution='" + maxResolution + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HomeConsole that)) return false;
        return Objects.equals(maxResolution, that.maxResolution);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maxResolution);
    }
}
