package ru.testScandJavaCafee.model;

/**
 * Created by 15 on 13.04.2017.
 */
public class CoffeeType {

    private int id;

    private String type_name;

    private double price;

    private char disabled;

    public CoffeeType() {
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

    public char getDisabled() {
        return disabled;
    }

    public void setDisabled(char disabled) {
        this.disabled = disabled;
    }

    public CoffeeType(int id, String type_name, double price, char disabled) {
        this.id = id;
        this.type_name = type_name;
        this.price = price;
        this.disabled = disabled;
    }
}
