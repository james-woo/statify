package com.james.statify.Models;

public class Products {
    int sold;
    String name;
    float revenue;

    public Products(int s, String n, float r) {
        sold = s;
        name = n;
        revenue = r;
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
