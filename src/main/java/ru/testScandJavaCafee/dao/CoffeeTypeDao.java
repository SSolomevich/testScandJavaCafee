package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.model.CoffeeType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 15 on 13.04.2017.
 */
public class CoffeeTypeDao {
    public static List<CoffeeType> list = Arrays.asList(
            new CoffeeType(1, "Очень крепкий и горячий кофе.", 20, 'X'),
            new CoffeeType(2, "Вкусный кофе со сливками.", 15, 'A'),
            new CoffeeType(3, "Просто кофе.", 10, 'B'),
            new CoffeeType(4, "Не важно, его всё равно не видно в меню.", 120, 'Y'));

}
