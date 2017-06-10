package ru.testScandJavaCafee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.testScandJavaCafee.dao.CoffeeTypeDao;
import ru.testScandJavaCafee.dao.CoffeeTypeDaoImpl;
import ru.testScandJavaCafee.model.CoffeeType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 15 on 25.04.2017.
 */
@Service
public class CoffeeTypeServiceImpl implements CoffeeTypeService {

    CoffeeTypeDaoImpl coffeeTypeDao1 = new CoffeeTypeDaoImpl();
//    @Resource(name = "coffeeTypeDao")

//   CoffeeTypeDao coffeeTypeDao ;
// = (CoffeeTypeDao) getServletContext().getAttribute("userDao");
////        = new CoffeeTypeDaoImpl();
//WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//    UserDao userDao =(UserDao)springContext.getBean("userDao");

//    @Autowired
//    public void setCoffeeTypeDao(CoffeeTypeDao coffeeTypeDao) {
//        this.coffeeTypeDao = coffeeTypeDao;
//    }




    @Override
    public List<CoffeeType> getListCoffeeType(String[] count) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/spring-context.xml");
//        CoffeeTypeDao coffeeTypeDao = context.getBean(CoffeeTypeDao.class);
        List<CoffeeType> list = coffeeTypeDao1.getList();
        for (int i = 0; i< list.size(); i++) {
            CoffeeType item = list.get(i);
            if (
                    count != null
                    &&
 count[i] != null
                    && !count[i].equals("")
                    ) {
                item.setCount(Integer.parseInt(count[i]));
            }
            else item.setCount(0);
        }
        return list;
    }

    @Override
    public List<CoffeeType> getFilterListCoffeeType(List<CoffeeType> list) {
        for (int i = 0; i< list.size(); i++)
        {
            if (list.get(i).getDisabled().equals("Y")){
                list.remove(list.get(i));
            }
        }
        return list;
    }


    public static ArrayList<Double> c = new ArrayList<>();

    @Override
    public List<CoffeeType> getListCoffeeOrder(List<CoffeeType> list, String[] checkbox) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring-context.xml");
//        CoffeeTypeDao coffeeTypeDao = context.getBean(CoffeeTypeDao.class);
        List<CoffeeType> list2=new ArrayList<>();
        if (checkbox!=null&&checkbox.length>0) {
            for (int i = 0; i < checkbox.length; i++) {
                for (int j = 0; j < coffeeTypeDao1.getList().size(); j++) {
                    if (checkbox[i].equals(String.valueOf(coffeeTypeDao1.getList().get(j).getId()))
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


    private MathParser parser = new MathParser();
    public Integer parser(String m, String n, String x, Integer c, double p) throws Exception {
        FileInputStream fis;
        Properties property = new Properties();
        String sum = "";
        try {
            fis = new FileInputStream("C:\\Java\\IdeaProjects\\testScandJavaCafee\\src\\main\\resources\\config.properties");
            property.load(fis);
            sum = property.getProperty("sum");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        Integer result = parser.Parse(sum, m, n, x, c, p);
        return result;
    }


}
