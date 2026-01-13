package csd230.lab1.pojos;

import java.util.Date;

public class Magazine extends Publication {
    private int orderQty = 0;
    private Date currentIssue = new Date();

    public Magazine() {
    }

    public Magazine(int orderQty, Date currentIssue, String title, double price, int copies) {
        super(title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    @Override
    public void sellItem() {
        setCopies(getCopies() - 1);
    }

    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int o) { this.orderQty = o; }
    public Date getCurrentIssue() { return currentIssue; }
    public void setCurrentIssue(Date d) { this.currentIssue = d; }

    @Override
    public String toString() {
        return "Magazine{orderQty=" + orderQty + ", issue=" + currentIssue + ", " + super.toString() + "}";
    }
}
