package ru.testScandJavaCafee.service;

import ru.testScandJavaCafee.model.CoffeeType;

import java.util.List;

/**
 * Created by 15 on 25.04.2017.
 */
public interface CoffeeTypeService {
    public List<CoffeeType> getListCoffeeType(String[] count);
    public List<CoffeeType> getFilterListCoffeeType(List<CoffeeType> list);
    public List<CoffeeType> getListCoffeeOrder(List<CoffeeType> list, String[] checkbox);
}
