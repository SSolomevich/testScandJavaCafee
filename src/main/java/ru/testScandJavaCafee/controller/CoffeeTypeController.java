package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.model.Configuration;
import ru.testScandJavaCafee.service.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.*;
import java.util.*;

/**
 * Created by Sergey Solomevich on 13.04.2017.
 */
public class CoffeeTypeController extends Dispatcher {

    private CoffeeTypeServiceImpl coffeeTypeService = new CoffeeTypeServiceImpl();

    private ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl();


    //  Создано 2 метода - гет и пост
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] count = req.getParameterValues("q");
        List<CoffeeType> list = coffeeTypeService.getListCoffeeType(count);
        list = coffeeTypeService.getFilterListCoffeeType(list);

        req.setAttribute("list", list);

        req.getRequestDispatcher("/coffeeType.jsp").forward(req, resp);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //   Создание листа с выбранными сортами кофе через методы сервиса СoffeeTypeService
        String[] count = request.getParameterValues("q");
        List<CoffeeType> list = coffeeTypeService.getListCoffeeType(count);
        String[] checkbox = request.getParameterValues("box");
        list = coffeeTypeService.getListCoffeeOrder(list, checkbox);
        request.setAttribute("list", list);
        //  Создание листа с параметрами подсчета стоимости через метод сервиса ConfigurationService
        List<Configuration> list2 = configurationService.getListConfiguration();

        Double sum = 0.0;

        try {

            for (CoffeeType aList : list) {
                String m = list2.get(0).getValue();
                String n = list2.get(1).getValue();
                String x = list2.get(2).getValue();
                Integer c = aList.getCount();
                Double price = aList.getPrice();
                sum = sum + coffeeTypeService.parser(m, n, x, c, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Double.parseDouble(list2.get(2).getValue()) < sum) {
            request.setAttribute("m", 0);
        } else {
            request.setAttribute("m", list2.get(0).getValue());
        }
        request.setAttribute("sum", sum);
        request.setAttribute("x", list2.get(2).getValue());
//        request.setAttribute("javax.servlet.jsp.jstl.fmt.fallbackLocale.request", "en-us");
//        request.setAttribute("javax.servlet.jsp.jstl.fmt.fallbackLocale.request", "en-EN");
        String countTest;

        for (int i=0; i<count.length;i++) {
            if (!count[i].equals("")) {
                if (Integer.parseInt(count[i]) > 20 || Integer.parseInt(count[i]) < 0) {
                    countTest = "f";
                    request.setAttribute("countTest", countTest);
                    String[] count2 = request.getParameterValues("q");
                    List<CoffeeType> list3 = coffeeTypeService.getListCoffeeType(count2);
                    list3 = coffeeTypeService.getFilterListCoffeeType(list3);

                    request.setAttribute("list", list3);
                    request.getRequestDispatcher("/coffeeType.jsp").forward(request, response);
                }
            }
        }
        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}



















