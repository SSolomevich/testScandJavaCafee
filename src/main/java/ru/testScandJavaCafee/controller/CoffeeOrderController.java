package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.service.CoffeeOrderServiceImpl;
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

    private CoffeeOrderServiceImpl coffeeOrderService = new CoffeeOrderServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/CoffeeTypeController").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("Address").isEmpty()) {
            String name = request.getParameter("NameAndFamily");
            String delivery_address = request.getParameter("Address");
            coffeeOrderService.insert(name, delivery_address);
            request.getRequestDispatcher("/successOrder.jsp").forward(request, response);
        }
        else  request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}
