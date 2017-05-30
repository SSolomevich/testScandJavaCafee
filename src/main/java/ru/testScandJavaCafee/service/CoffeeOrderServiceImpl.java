package ru.testScandJavaCafee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.testScandJavaCafee.dao.CoffeeOrderDao;
import ru.testScandJavaCafee.dao.CoffeeOrderDaoImpl;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 15 on 26.04.2017.
 */

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService{





//    CoffeeOrderDaoImpl coffeeOrderDao = new CoffeeOrderDaoImpl();

//

    CoffeeOrderDao coffeeOrderDao ;
    @Autowired
    public void setCoffeeOrderDao(CoffeeOrderDao coffeeOrderDao) {
        this.coffeeOrderDao = coffeeOrderDao;
    }

    @Override
    public void insert(String name, String delivery_address) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
       CoffeeOrderDao coffeeOrderDao = context.getBean(CoffeeOrderDao.class);
        coffeeOrderDao.insert(name, delivery_address);
    }


}
//    @Override
//    public void insert(String name, String delivery_address) {
//        // JDBC URL, username и password of MySQL server
//        final String user = "root";
//        final String password = "root";
//        String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
//
//        System.out.println("Connect to driver");
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            Statement stmt = con.createStatement();
//            for (int i = 0; i< CoffeeTypeServiceImpl.c.size(); i++) {
//                stmt.executeUpdate("INSERT INTO coffeeorder(order_date,name,delivery_address,cost) VALUES (NOW(),'" + name + "', '" + delivery_address + "','" + CoffeeTypeServiceImpl.c.get(i) + "' )");
//            }
//            stmt.close();
//            con.close();
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }
