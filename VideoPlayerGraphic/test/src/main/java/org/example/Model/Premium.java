package org.example.Model;

public enum Premium {
    BRONZE(30 , 5),
    SILVER(60 , 9),
    GOLD(180 , 14);

    private final int days;
    private final int price;

    Premium(int days , int price) {
        this.days = days;
        this.price = price;
    }

    public int getDays() { return days; }

    public int getPrice() { return price; }
}
