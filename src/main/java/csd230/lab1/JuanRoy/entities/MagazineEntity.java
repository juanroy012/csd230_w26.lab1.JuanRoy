package csd230.lab1.JuanRoy.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {

    private int orderQty;

    private LocalDate currentIssue;

    public MagazineEntity() {}

    public MagazineEntity(String n, double p, int c, int o, LocalDate d) { super(n, p, c); this.orderQty = o; this.currentIssue = d; }

    public int getOrderQty() { return orderQty; }

    public void setOrderQty(int o) { this.orderQty = o; }

    public void setCurrentIssue(LocalDate d) { this.currentIssue = d; }

    public LocalDate getCurrentIssue() { return currentIssue; }

    @Override public String toString() { return "Mag{issue=" + currentIssue + ", " + super.toString() + "}"; }
}
