package com.james.statify.Models;

public class Sale {
    int quantity;
    double price;

    public Sale(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
