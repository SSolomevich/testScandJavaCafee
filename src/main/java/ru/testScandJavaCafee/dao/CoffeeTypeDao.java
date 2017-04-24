package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.model.CoffeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 15 on 13.04.2017.
 */
public interface CoffeeTypeDao {
        public List<CoffeeType> createListFromMysql(List<CoffeeType> list);
    }
