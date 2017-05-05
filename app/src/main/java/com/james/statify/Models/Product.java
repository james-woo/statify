package com.james.statify.Models;

public class Product {
    int quantity;
    String name;
    double revenue;

    public Product(int quantity, String name, double revenue) {
        this.quantity = quantity;
        this.name = name;
        this.revenue = revenue;
    }

    public int getQuantity() { return quantity; }

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public static int compare(Product o1, Product o2, int sortBy) {
        int result = 0;

        switch (sortBy) {
            case 0:
                result = (o1.quantity < o2.quantity) ? -1 : 1;
                if (o1.quantity == o2.quantity) result = 0;
                break;
            case 1:
                result = o1.name.compareTo(o2.name);
                break;
            case 2:
                result = (o1.revenue < o2.revenue) ? -1 : 1;
                if (o1.revenue == o2.revenue) result = 0;
                break;
        }

        return result;
    }
}
