package ru.testScandJavaCafee.model;

/**
 * Created by 15 on 18.04.2017.
 */
public class CoffeeOrder {
    private int id;

    private String type_name;

    private double price;

    private int quantity;

    public CoffeeOrder() {
    }

    public CoffeeOrder(int id, String type_name, double price, int quantity) {
        this.id = id;
        this.type_name = type_name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
