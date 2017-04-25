package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.dao.CoffeeTypeDaoImpl;
import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.service.CoffeeTypeService;
import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Sergey Solomevich on 13.04.2017.
 */
public class CoffeeTypeController extends Dispatcher {

    private CoffeeTypeServiceImpl coffeeTypeService = new CoffeeTypeServiceImpl();

    //  Создано 2 метода - гет и пост
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] count = req.getParameterValues("q");
        List<CoffeeType> list = coffeeTypeService.getListCoffeeType(count);
        list = coffeeTypeService.getFilterListCoffeeType(list);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/coffeeType.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] count = request.getParameterValues("q");
        List<CoffeeType> list = coffeeTypeService.getListCoffeeType(count);
        String[] checkbox = request.getParameterValues("box");
        list = coffeeTypeService.getListCoffeeOrder(list, checkbox);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}