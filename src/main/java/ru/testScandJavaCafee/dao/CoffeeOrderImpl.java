package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.model.CoffeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15 on 24.04.2017.
 */
public class CoffeeOrderImpl {

//    public static List<CoffeeType> list = createListInMysql(new ArrayList<>());
    // JDBC URL, username и password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con2;
    private static Statement stmt;
    private static ResultSet rs;

    public static List<CoffeeType> createListInMysql(
            List<CoffeeType> list
    ) {

        String query = "select * from coffeeorder";

        try {
// opening database connection to MySQL server
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            con2 = DriverManager.getConnection(url, user, password);

// getting Statement object to execute query
            stmt = con2.createStatement();

// executing SELECT query
            rs = stmt.executeQuery(query);

//            while (rs.next()) {
//                CoffeeType coffeeType = new CoffeeType();
//
//                coffeeType.setId(rs.getInt("id"));
//                coffeeType.setType_name(rs.getString("type_name"));
//                coffeeType.setPrice(rs.getDouble("price"));
//                coffeeType.setDisabled(rs.getString("disabled"));
//
//
//                list.add(coffeeType);
//            }

           stmt.addBatch("INSERT INTO coffeeorder(delivery_address, cost) VALUES ");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
//close connection ,stmt and resultset here
            try {
                con2.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return list;
    }


}
