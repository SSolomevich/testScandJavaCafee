package ru.testScandJavaCafee.model;

/**
 * Created by 15 on 02.05.2017.
 */
public class Configuration {

    private String id;
    private String value;

    public Configuration(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Configuration() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
