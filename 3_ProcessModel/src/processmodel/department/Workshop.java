/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import processmodel.data.WorkshopOrder;
import processmodel.simplest.SimpleMethod;
import processmodel.simplest.WorkshopMethod;

/**
 *
 * @author Dude
 */
public class Workshop extends Department {

    public static final Integer MAX_DAILY_POWER = 12;
    
    public Workshop() {
    }

    @JsonIgnore
    public Integer getMaxPower() {
        return MAX_DAILY_POWER;
    }

    public int getFreePower(int day) {
        if (day < SHEDULE_DEEP && day >= 0) {
            return getMaxPower() - getShedule(day);
        }
        return 0;
    }

    public int getFreePower(int start, int end) {
        int res = 0;
        for (int i = start; i <= end; ++i) {
            res += getMaxPower() - getShedule(i);
        }
        return res;
    }

    public SimpleMethod getProduceMethod(WorkshopOrder order, int day, Integer powerLimit) {
        return new WorkshopMethod(order, day, powerLimit);
    }

    public void spendPower(int day, int spendPower, String ident) {
        addShedule(day, spendPower);
        addStatistic(day,ident + ":" + spendPower);
    }

    public void addByStatistic(String stat) {
        for (String dayInfo : stat.split("Day:")) {
            Scanner sc = new Scanner(dayInfo);
            try {
                Integer dayNumber = sc.nextInt();
                while (sc.hasNext()) {
                    String[] data = sc.next().split(":");
                    String ident = data[0];
                    Integer power = Integer.valueOf(data[1]);

                    spendPower(dayNumber, power, ident);
                }
            } catch (NoSuchElementException nsee) {
                // пустая строка
            } catch (IllegalStateException ise) {
            }
        }
    }
    
    /* сколько потребуется денег на поддержание производства нужных мощностей.
     * Потом нужно усложнить и учесть детали со склада, з/п рабочим, может воду, элекстричество.
     */
    public int getMoneyForProduce(int power){
        return power*10;
    }


}
