package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.model.CoffeeOrder;
import ru.testScandJavaCafee.model.CoffeeType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Sergey Solomevich on 13.04.2017.
 */
public class CoffeeTypeController extends Dispatcher {

    //  Создано 2 метода - гет и пост
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/coffeeType.jsp");

//        Set<CoffeeType> set1 = new HashSet<>();
        List<CoffeeType> set1 = new LinkedList<>();
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

        Set<CoffeeOrder> listResult = new HashSet<>();

        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();

        String[] coffeIds = request.getParameterValues("box");
        String[] coffeQ = request.getParameterValues("q");

        for (int a=0;a<coffeIds.length;a++) {
        list1.add(coffeIds[a]);
        }
        for (int a=0;a<coffeQ.length;a++) {
            list2.add(coffeQ[a]);
        }

        List<String> list3 = new LinkedList<>();
        for (int i=0;i<list2.size();i++)
        {
            for (int j=0;j<list1.size();j++)
            {
                Integer x = i+1;
                if (list1.get(j).equals(x.toString()))
                {
                   list3.add(list2.get(i));
                }
            }
        }

        int len2 = list1.size()>list3.size()?list3.size():list1.size();
        if (list3.size()>0) {
            for (int j = 0; j < CoffeeTypeDao.list.size(); j++) {
                for (int i = 0; i < len2; i++) {
                   if (list1.get(i).equals(String.valueOf(CoffeeTypeDao.list.get(j).getId()))
                    ) {

                        if (!list2.get(Integer.parseInt(list1.get(i))-1).isEmpty())
                         {
                            listResult.add(new CoffeeOrder(CoffeeTypeDao.list.get(j).getId(),
                            CoffeeTypeDao.list.get(j).getType_name(),
                            CoffeeTypeDao.list.get(j).getPrice(),
                            Integer.parseInt(list3.get(i))
                              ));
                           }
                       }
                  }
             }
}
//  устанавливаем listResult для ответа на соответствующее обращение из jsp
        request.setAttribute("type", listResult);

        if (listResult.size() > 0) {
            request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
        }

    }
}