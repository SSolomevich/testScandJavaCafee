package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 15 on 19.04.2017.
 */
public class CoffeeOrderController extends Dispatcher  {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/CoffeeTypeController").forward(req, resp);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!request.getParameter("Address").isEmpty()
                ) {

            insert(request);
            request.getRequestDispatcher("/successOrder.jsp").forward(request, response);

        }
        else  request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }

    public void insert(HttpServletRequest request)
            throws ServletException, IOException {

        String name = request.getParameter("NameAndFamily");
        String delivery_address = request.getParameter("Address");



        // JDBC URL, username и password of MySQL server
//        final String url = "jdbc:mysql://localhost:3306/test_scand_caffe";
        final String user = "root";
        final String password = "root";

//        Class.forName("org.gjt.mm.mysql.Driver").newInstance();

        String url = "jdbc:mysql://localhost:3306/test_scand_caffe";                      //вместо локалхост свой путь к базе
        System.out.println("Connect to driver");
        String createAccounts;
//        createAccounts = "create table coffeeorder (name VARCHAR(100), delivery_address VARCHAR(200))"; // СОЗДАНИЕ ПОЛЕЙ (СТОЛБЦЫ)

        try {

            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

//
//

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
