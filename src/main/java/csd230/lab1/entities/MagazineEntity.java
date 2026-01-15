package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity @DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {

    private int orderQty;

    private LocalDate currentIssue;

    public MagazineEntity() {}

    public MagazineEntity(String t, double p, int c, int o, LocalDate d) { super(t, p, c); this.orderQty = o; this.currentIssue = d; }

    public int getOrderQty() { return orderQty; }

    public void setOrderQty(int o) { this.orderQty = o; }

    public void setCurrentIssue(LocalDate d) { this.currentIssue = d; }

    public LocalDate getCurrentIssue() { return currentIssue; }

    @Override public String toString() { return "Mag{issue=" + currentIssue + ", " + super.toString() + "}"; }
}
