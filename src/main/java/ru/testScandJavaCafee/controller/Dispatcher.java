package ru.testScandJavaCafee.controller;

/**
 * Created by Sergey Solomevich on 13.04.2017.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Dispatcher extends HttpServlet  {
    protected void forward(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
