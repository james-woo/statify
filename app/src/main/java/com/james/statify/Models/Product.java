package com.james.statify.Models;

public class Product {
    int sold;
    String name;
    float revenue;

    public Product(int sold, String name, float revenue) {
        this.sold = sold;
        this.name = name;
        this.revenue = revenue;
    }

    public int getSold() {
        return sold;
    }

    public String getName() {
        return name;
    }

    public float getRevenue() {
        return revenue;
    }
}
