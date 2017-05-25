package ru.testScandJavaCafee.service;

import ru.testScandJavaCafee.model.CoffeeType;

import java.util.List;

/**
 * Created by 15 on 25.04.2017.
 */
public interface CoffeeTypeService {
     List<CoffeeType> getListCoffeeType(String[] count);
     List<CoffeeType> getFilterListCoffeeType(List<CoffeeType> list);
     List<CoffeeType> getListCoffeeOrder(List<CoffeeType> list, String[] checkbox);
     Integer parser(String m, String n, String x, Integer c, double p) throws Exception;
}
