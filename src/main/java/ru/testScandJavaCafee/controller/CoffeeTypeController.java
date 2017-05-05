package ru.testScandJavaCafee.controller;

import ru.testScandJavaCafee.model.CoffeeType;
import ru.testScandJavaCafee.model.Configuration;
import ru.testScandJavaCafee.service.CoffeeTypeServiceImpl;
import ru.testScandJavaCafee.service.ConfigurationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
             sum=sum+list.get(i).getCount()*list.get(i).getPrice()-(list.get(i).getCount()/ Integer.parseInt(list2.get(1).getValue())*list.get(i).getPrice());
        }

        if (Double.parseDouble(list2.get(2).getValue())<sum)
        {
            request.setAttribute("m", 0);
        }
        else {
            request.setAttribute("m", list2.get(0).getValue());
        }
//        a(sum,list2.get(0).getValue(),list2.get(2).getValue());

//        request.setAttribute("n", list2.get(1).getValue());
        request.setAttribute("sum", sum);
//        String b = null;
//        try {
//           b = b();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("sum", b);
        request.setAttribute("x", list2.get(2).getValue());

        request.getRequestDispatcher("/coffeeOrder.jsp").forward(request, response);
    }

//    public void a(Double sum, String m, String x) throws IOException {
//        Properties prop = new Properties();
//        String s = sum.toString();
//        prop.setProperty("sum", s);
//        prop.setProperty("m", m);
//        prop.setProperty("x", x);
//        FileOutputStream fos = new FileOutputStream("C:\\Java\\IdeaProjects\\testScandJavaCafee\\src\\props.xml");
//        prop.storeToXML(fos, "Здесь может быть комментарий.");
//        fos.close();
//    }
//
//    public String b() throws Exception {
//        Properties prop = new Properties();
//        FileInputStream fis = new FileInputStream("C:\\Java\\IdeaProjects\\testScandJavaCafee\\src\\props.xml");
//        prop.loadFromXML(fis);
//        prop.list(System.out);
//        System.out.println("\nПараметр sum: " + prop.getProperty("sum"));
//        System.out.println("\nПараметр sum2: " + prop.getProperty("sum2"));
//        System.out.println("\nПараметр sum3: " + prop.getProperty("sum3"));
//
//        return prop.getProperty("sum2");
//    }

}