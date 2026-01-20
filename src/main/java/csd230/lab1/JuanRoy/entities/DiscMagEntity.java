package csd230.lab1.JuanRoy.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity @DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {

    private boolean hasDisc;

    public DiscMagEntity() {}

    public DiscMagEntity(String t, double p, int c, int o, LocalDate d, boolean h) { super(t, p, c, o, d); this.hasDisc = h; }

    public boolean isHasDisc() { return hasDisc; }

    public void setHasDisc(boolean h) { this.hasDisc = h; }

    @Override public String toString() { return "DiscMag{disc=" + hasDisc + ", " + super.toString() + "}"; }
}
