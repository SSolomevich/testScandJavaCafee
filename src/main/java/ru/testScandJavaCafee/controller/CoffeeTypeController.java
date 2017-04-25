package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.dao.CoffeeTypeDaoImpl;
import ru.testScandJavaCafee.model.CoffeeType;

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

    private List<CoffeeType> getListCoffeeType(HttpServletRequest req){
        String[] count = req.getParameterValues("q");
        List<CoffeeType> list = CoffeeTypeDaoImpl.list;
        for (int i = 0; i< list.size(); i++) {
            CoffeeType item = list.get(i);
                if (count != null && count[i] != null && !count[i].equals("")) {
                    item.setCount(Integer.parseInt(count[i]));
            }
            else item.setCount(0);
        }
        return list;
    }

    private List<CoffeeType> getFilterListCoffeeType(List<CoffeeType> list){
        for (int i = 0; i< list.size(); i++)
        {
            if (list.get(i).getDisabled().equals("Y")){
                list.remove(list.get(i));
            }
        }
        return list;
    }
public static ArrayList<Double> c =new ArrayList<>();

    private List<CoffeeType> getListCoffeeOrder(List<CoffeeType> list, String[] checkbox){
        List<CoffeeType> list2=new ArrayList<>();
        if (checkbox!=null&&checkbox.length>0) {
            for (int i = 0; i < checkbox.length; i++) {
                for (int j = 0; j < CoffeeTypeDaoImpl.list.size(); j++) {
                    if (checkbox[i].equals(String.valueOf(CoffeeTypeDaoImpl.list.get(j).getId()))
                    && list.get(j).getCount()!=0
                            ) {
                        list2.add(list.get(j));
                        c.add(list.get(j).getPrice());
                    }
                }
            }
        }
        return list2;
    }

    //  Создано 2 метода - гет и пост
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/coffeeType.jsp");
        List<CoffeeType> list = this.getListCoffeeType(req);
        list = getFilterListCoffeeType(list);
        req.setAttribute("list", list);
        view.forward(req, resp);

//        List<CoffeeType> set1 = new LinkedList<>();
//        for (int i = 0; i< CoffeeTypeDao.list.size(); i++)
//        {
//            if (!CoffeeTypeDao.list.get(i).getDisabled().equals("Y"))
//            set1.add(CoffeeTypeDao.list.get(i));
//        }
//        req.setAttribute("list", set1);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  В listResult будем класть все соответствующие запросам объекты Database

        List<CoffeeType> list = this.getListCoffeeType(request);
        String[] checkbox = request.getParameterValues("box");
        list = getListCoffeeOrder(list, checkbox);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);




        //        Set<CoffeeOrder> listResult = new HashSet<>();
//
//        List<String> list1 = new LinkedList<>();
//        List<String> list2 = new LinkedList<>();
//        List<String> list3 = new LinkedList<>();
//        String[] coffeIds = request.getParameterValues("box");
//        String[] coffeQ = request.getParameterValues("q");

//        for (int a=0;a<coffeIds.length;a++) {
//
//            Integer x = a+1;
////                list1.add(coffeIds[a]);
//            list1.add(x.toString());
//
//        }
//
//
//
//
//        for (int a=0;a<coffeQ.length;a++) {
////            if (!CoffeeTypeDao.list.get(a).getDisabled().equals("Y")) {
//                list2.add(coffeQ[a]);
////            }
//        }
//
//        List<String> list3 = new LinkedList<>();
//        for (int i=0;i<list2.size();i++)
//        {
//            for (int j=0;j<list1.size();j++)
//            {
//                Integer x = i+1;
//                if (list1.get(j).equals(x.toString()))
//                {
//                   list3.add(list2.get(i));
//                }
//            }
//        }
//


        //        int len2 = list1.size()>list3.size()?list3.size():list1.size();
//        if (list3.size()>0) {
//            for (int j = 0; j < CoffeeTypeDao.list.size(); j++) {
//                for (int i = 0; i < len2; i++) {
//                   if (list1.get(i).equals(String.valueOf(CoffeeTypeDao.list.get(j).getId()))
//                    ) {
//
//                        if (!list2.get(Integer.parseInt(list1.get(i))-1).isEmpty())
//                         {
//                            listResult.add(new CoffeeOrder(CoffeeTypeDao.list.get(j).getId(),
//                            CoffeeTypeDao.list.get(j).getType_name(),
//                            CoffeeTypeDao.list.get(j).getPrice(),
//                            Integer.parseInt(list3.get(i))
//                              ));
//                           }
//                       }
//                  }
//             }
//
//        for (Integer a=0;a<coffeIds.length;a++) {
//            list1.add(a.toString());
//        }
//
//        for (int a=0;a<coffeQ.length;a++) {
//            list2.add(coffeQ[a]);
//        }
//
//
//        for (int i=0;i<list2.size();i++)
//        {
//            for (int j=0;j<list1.size();j++)
//            {
//                Integer x = i+1;
//                if (list1.get(j).equals(x.toString()))
//                {
//                    list3.add(list2.get(i));
//                }
//            }
//        }
//
//        for (int i = 0; i < list1.size(); i++) {
//            if (!list2.get(i).isEmpty())
//            {
//                for (int j = 0; j < CoffeeTypeDao.list.size(); j++) {
//                if (coffeIds[i].equals(String.valueOf(CoffeeTypeDao.list.get(j).getId())))
//                    {
//                        listResult.add(new CoffeeOrder(CoffeeTypeDao.list.get(j).getId(),
//                                CoffeeTypeDao.list.get(j).getType_name(),
//                                CoffeeTypeDao.list.get(j).getPrice(),
//                                Integer.parseInt(list2.get(i))
//                        ));
//                    }
//                }
//            }
//        }
//


//            int len2 = list1.size()>list3.size()?list3.size():list1.size();
//            if (list3.size()>0) {
//                for (int j = 0; j < list1.size(); j++) {
//                    for (int i = 0; i < len2; i++) {
//                        if (coffeIds[i].equals(String.valueOf(CoffeeTypeDao.list.get(j).getId()))
//                                ) {
//
//                            if (!list2.get(Integer.parseInt(coffeIds[i])-1).isEmpty())
//                            {
//                                listResult.add(new CoffeeOrder(CoffeeTypeDao.list.get(j).getId(),
//                                        CoffeeTypeDao.list.get(j).getType_name(),
//                                        CoffeeTypeDao.list.get(j).getPrice(),
//                                        Integer.parseInt(list3.get(i))
//                                ));
//                            }
//                        }
//                    }
//                }
//
//
//
//
//
//} request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
//  устанавливаем listResult для ответа на соответствующее обращение из jsp
//        request.setAttribute("type", listResult);
//
//        if (listResult.size() > 0) {
//
//        }

    }
}