package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey Solomevich on 13.04.2017.
 */
public class MyController extends Dispatcher {

    //  Создано 2 метода - гет и пост
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/coffeeType.jsp");

        Set<CoffeeType> set1 = new HashSet<>();
        for (int i = 0; i< CoffeeTypeDao.list.size(); i++)
        {
            if (CoffeeTypeDao.list.get(i).getDisabled()!='Y')
            set1.add(CoffeeTypeDao.list.get(i));
        }
        req.setAttribute("list", set1);

        view.forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  В listResult будем класть все соответствующие запросам объекты Database
        ArrayList<CoffeeType> listResult = new ArrayList<>();
        for (int i = 0; i < CoffeeTypeDao.list.size(); i++) {
            if (!request.getParameter("quv").equals(null))

                listResult.add(CoffeeTypeDao.list.get(i));
        }
//  устанавливаем listResult для ответа на соответствующее обращение из jsp
        request.setAttribute("tupe", listResult);

        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }
}