package ru.testScandJavaCafee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.testScandJavaCafee.dao.CoffeeOrderDao;
import ru.testScandJavaCafee.dao.ConfigurationDao;
import ru.testScandJavaCafee.dao.ConfigurationDaoImpl;
import ru.testScandJavaCafee.model.Configuration;

import java.util.List;

/**
 * Created by 15 on 02.05.2017.
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService{

//    ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl();

    ConfigurationDao configurationDao;
    @Autowired
    public void setConfigurationDao(ConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
    }




    public List<Configuration> getListConfiguration()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        ConfigurationDao configurationDao = context.getBean(ConfigurationDao.class);
        return configurationDao.createListConfiguration();
    }

}
