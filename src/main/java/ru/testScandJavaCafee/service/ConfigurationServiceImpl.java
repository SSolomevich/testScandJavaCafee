package ru.testScandJavaCafee.service;

import ru.testScandJavaCafee.dao.ConfigurationDaoImpl;
import ru.testScandJavaCafee.model.Configuration;

import java.util.List;

/**
 * Created by 15 on 02.05.2017.
 */
public class ConfigurationServiceImpl implements ConfigurationService{
    ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl();

    public List<Configuration> getListConfiguration()
    {
        return configurationDao.createListConfiguration();
    }

}
