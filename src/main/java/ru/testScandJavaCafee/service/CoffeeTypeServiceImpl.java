package ru.testScandJavaCafee.service;

import ru.testScandJavaCafee.dao.CoffeeTypeDaoImpl;
import ru.testScandJavaCafee.model.CoffeeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15 on 25.04.2017.
 */
public class CoffeeTypeServiceImpl implements CoffeeTypeService {

    CoffeeTypeDaoImpl coffeeTypeDao = new CoffeeTypeDaoImpl();

    @Override
    public List<CoffeeType> getListCoffeeType(String[] count) {
        List<CoffeeType> list = coffeeTypeDao.list;
        for (int i = 0; i< list.size(); i++) {
            CoffeeType item = list.get(i);
//            if(req.getParameterValues("box["+item.getId()+"]")){
            if (count != null && count[i] != null && !count[i].equals("")) {
                item.setCount(Integer.parseInt(count[i]));
            }
//            }


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
        List<CoffeeType> list2=new ArrayList<>();
        if (checkbox!=null&&checkbox.length>0) {
            for (int i = 0; i < checkbox.length; i++) {
                for (int j = 0; j < coffeeTypeDao.list.size(); j++) {
                    if (checkbox[i].equals(String.valueOf(coffeeTypeDao.list.get(j).getId()))
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
}
