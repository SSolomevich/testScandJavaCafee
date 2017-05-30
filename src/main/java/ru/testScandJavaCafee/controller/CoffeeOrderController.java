package ru.testScandJavaCafee.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.testScandJavaCafee.dao.CoffeeOrderDao;
import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.service.CoffeeOrderServiceImpl;
import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        webApplicationContext.getBean("coffeeOrderDao");
//    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/CoffeeTypeController").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CoffeeOrderDao coffeeOrderDao = (CoffeeOrderDao)getServletContext().getAttribute("coffeeOrderDao");
//        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        CoffeeOrderDao coffeeOrderDao = (CoffeeOrderDao)springContext.getBean("coffeeOrderDao");
        if (!request.getParameter("Address").isEmpty()) {
            String name = request.getParameter("NameAndFamily");
            String delivery_address = request.getParameter("Address");
            coffeeOrderService.insert(name, delivery_address);
            request.getRequestDispatcher("/successOrder.jsp").forward(request, response);
        }
        else  request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}
