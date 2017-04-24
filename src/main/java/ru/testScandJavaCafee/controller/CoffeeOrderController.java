package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 15 on 19.04.2017.
 */
public class CoffeeOrderController extends Dispatcher  {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher view = req.getRequestDispatcher("/coffeeType.jsp");
        req.getRequestDispatcher("/CoffeeTypeController").forward(req, resp);
//        List<CoffeeType> set1 = new LinkedList<>();
//        for (int i = 0; i< CoffeeTypeDao.list.size(); i++)
//        {
//            if (CoffeeTypeDao.list.get(i).getDisabled()!='Y')
//                set1.add(CoffeeTypeDao.list.get(i));
//        }
//        req.setAttribute("list", set1);
//
//        view.forward(req, resp);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getParameter("Address").isEmpty()
                ) {
//            response.sendRedirect("/successOrder.jsp");
            request.getRequestDispatcher("/successOrder.jsp").forward(request, response);
        }
        else  request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }

}
