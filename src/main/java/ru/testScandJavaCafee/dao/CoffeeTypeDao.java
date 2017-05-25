package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.dao.daoBase.dao_base;
import ru.testScandJavaCafee.model.CoffeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 15 on 13.04.2017.
 */
public interface CoffeeTypeDao {
//        List<CoffeeType> createListFromMysql(List<CoffeeType> list);
    void createConnecion (dao_base dao, final int idx) ;
    List<CoffeeType> createListCoffeeType(List<CoffeeType> list);
    List<CoffeeType> getList();

    }
