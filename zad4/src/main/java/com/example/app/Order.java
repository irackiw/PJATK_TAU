package com.example.app;

import java.util.Date;

public class Order {

    private int id;
    private float price;
    private int qty;
    private Date date;

    public Order(int id, float price, int qty, Date date) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public Date getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }
}