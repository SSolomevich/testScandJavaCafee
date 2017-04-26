package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 15 on 26.04.2017.
 */
public class CoffeeOrderDaoImpl implements CoffeeOrderDao{
    public void insert(String name, String delivery_address) {
        // JDBC URL, username и password of MySQL server
        final String user = "root";
        final String password = "root";
        String url = "jdbc:mysql://localhost:3306/test_scand_caffe";

        System.out.println("Connect to driver");
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            for (int i = 0; i< CoffeeTypeServiceImpl.c.size(); i++) {
                stmt.executeUpdate("INSERT INTO coffeeorder(order_date,name,delivery_address,cost) VALUES (NOW(),'" + name + "', '" + delivery_address + "','" + CoffeeTypeServiceImpl.c.get(i) + "' )");
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
