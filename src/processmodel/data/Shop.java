/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;

/**
 * Магазин
 *
 * @author elduderino
 */
public class Shop {

    private static int firstDay = 0; //смещение первого дня недели. 0- пн, 1- вт, ... 6 - вс. 
    
    private enum WorkTime {
        EWERYDAY,
        WORKDAY,
    }

    public String name;              //Имя магазина/поставщика
    public WorkTime workTime;        //Режим работы
    public Map<String, Integer> price;//Прайс: товар-цена
    public Integer bookTime;         //Время доставки

    public Shop() {
        price = new HashMap<String, Integer>();
    }

    public boolean isWork(Integer day) {
        
        if (workTime.equals(WorkTime.WORKDAY)) {
            return (firstDay + day) % 7 < 5;
        }
        return true;
    }

    @JsonIgnore
    public static Shop getTATAShop() {
        Shop shop = new Shop();

        shop.name = "TATA";
        shop.workTime = WorkTime.EWERYDAY;
        shop.bookTime = 1;

        shop.price.put("AXE", 15);
        shop.price.put("POPCORN", 6);

        return shop;
    }

    @JsonIgnore
    public static Shop getMODESTShop() {
        Shop shop = new Shop();

        shop.name = "MODEST";
        shop.workTime = WorkTime.WORKDAY;
        shop.bookTime = 0;

        shop.price.put("BENTLEY", 800);
        shop.price.put("PASTA", 4);

        return shop;
    }
}
