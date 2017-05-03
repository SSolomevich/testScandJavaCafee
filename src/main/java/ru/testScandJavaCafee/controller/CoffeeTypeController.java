package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.dao.CoffeeTypeDaoImpl;
import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.model.Configuration;
import ru.testScandJavaCafee.service.CoffeeTypeService;
import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;
import ru.testScandJavaCafee.service.ConfigurationServiceImpl;

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

    //  Расчет суммы с учетом n-бесплатной чашки
        Double sum=0.0;
        for (int i=0;i<list.size();i++) {
             sum=list.get(i).getCount()*list.get(i).getPrice()-(list.get(i).getCount()/ Integer.parseInt(list2.get(1).getValue())*list.get(i).getPrice());
        }
        request.setAttribute("m", list2.get(0).getValue());
//        request.setAttribute("n", list2.get(1).getValue());
        request.setAttribute("sum", sum);
        request.setAttribute("x", list2.get(2).getValue());

        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}