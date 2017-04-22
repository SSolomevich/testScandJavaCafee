package ru.testScandJavaCafee.dao;

import ru.testScandJavaCafee.model.CoffeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 15 on 13.04.2017.
 */
public class CoffeeTypeDao {
//    public static List<CoffeeType> list = Arrays.asList(
//            new CoffeeType(1, "Очень крепкий и горячий кофе.", 20, 'X'),
//            new CoffeeType(2, "Вкусный кофе со сливками.", 15, 'A'),
//            new CoffeeType(3, "Просто кофе.", 10, 'B'),
//            new CoffeeType(4, "Не важно, его всё равно не видно в меню.", 120, 'Y'));


    public static  List<CoffeeType>  list = createListFromMysql(new ArrayList<>());
    // JDBC URL, username и password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static List<CoffeeType> createListFromMysql(
            List<CoffeeType> list
    ) {

                String query = "select * from coffeetype";

        try {
// opening database connection to MySQL server
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            con = DriverManager.getConnection(url, user, password);

// getting Statement object to execute query
            stmt = con.createStatement();

// executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                CoffeeType coffeeType = new CoffeeType();

                coffeeType.setId(rs.getInt("id"));
                coffeeType.setType_name(rs.getString("type_name"));
                coffeeType.setPrice(rs.getDouble("price"));
                coffeeType.setDisabled(rs.getString("disabled"));


                list.add(coffeeType);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
//close connection ,stmt and resultset here
            try {
                con.close();
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
