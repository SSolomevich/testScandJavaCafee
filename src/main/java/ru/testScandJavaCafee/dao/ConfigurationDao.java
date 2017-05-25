package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.dao.daoBase.dao_base;
import ru.testScandJavaCafee.model.Configuration;

import java.util.List;

/**
 * Created by 15 on 25.05.2017.
 */
public interface ConfigurationDao {
    void createConnecion (dao_base dao, final int idx);
    List<Configuration> createListConfiguration();

}
