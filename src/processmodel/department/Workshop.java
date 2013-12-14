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
import processmodel.simplest.WorkshopProduceMethod;

/**
 *
 * @author Dude
 */
public class Workshop extends Department<Integer> {

    public static final Integer MAX_DAILY_POWER = 12;

    public Workshop() {
    }

    @JsonIgnore
    public Integer getMaxPower() {
        return MAX_DAILY_POWER;
    }

    public int getFreePower(Integer day) {
        if (day >= 0) {
            return getMaxPower() - getShedule(day);
        }
        return 0;
    }

    public int getFreePower(Integer start, Integer end) {
        int res = 0;
        for (int i = start; i <= end; ++i) {
            res += getMaxPower() - getShedule(i);
        }
        return res;
    }

    public SimpleMethod getProduceMethod(WorkshopOrder order, Integer day, Integer powerLimit) {
        return new WorkshopProduceMethod(order, day, powerLimit);
    }

    public void spendPower(Integer day, Integer spendPower, String ident) {
        addShedule(day, spendPower);
        addStatistic(day, ident + ":" + spendPower);
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

    @Override
    public int getState() {
        // будем считать что чем ближе ежедневная нагрузка к указанному проказателю (к сердей загрузке например),
        // тем лучше состояние производства

        int res = 0;
        int goodFreePart = (int) (MAX_DAILY_POWER * 0.6f);
        for (int i = 0; i <= getLastDay(); ++i) {
            int delta = Math.abs(goodFreePart - getFreePower(i));
            res += (MAX_DAILY_POWER - delta) * 10;
        }
        return res;
    }

    @Override
    protected Integer getZero() {
        return new Integer(0);
    }

    @Override
    protected Integer sum(Integer o1, Integer o2) {
        return o1 + o2;
    }

    /**
     * Возвращает последний день, в который возможно начать производство, чтобы успеть его в срок.
     * @param order
     * @param day
     * @return № дня с которого начнётся производсво, или -1 - произвести не хватает мощностей
     */
    public Integer getLastDayForStartProduce(WorkshopOrder order, Integer day) {
        int freePower = 0;
        int cday = order.getEndDay();
        while (cday >= day && freePower < order.getPowerAll()){
            freePower += getFreePower(cday);
            cday--;
        }
        if (freePower >= order.getPowerAll()){
            return cday;
        } else {
            return -1;
        }
    }
}
