package ru.testScandJavaCafee.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 15 on 19.04.2017.
 */
public class CoffeeOrderController extends Dispatcher  {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getParameter("NameAndFamily").isEmpty()
                && !request.getParameter("Address").isEmpty()
                ) {
            request.getRequestDispatcher("/successOrder.jsp").forward(request, response);
        }
        else  request.getRequestDispatcher("/coffeeType.jsp").forward(request, response);
    }

}
