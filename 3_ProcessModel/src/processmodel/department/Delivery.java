/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import processmodel.data.OrderPart;
import processmodel.data.WorkshopOrder;
import processmodel.simplest.DeliveryBookPartMethod;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public class Delivery extends Department<Map<OrderPart, Integer>> {

    @Override
    public int getState() {
        return 0;
    }

    @Override
    protected Map<OrderPart, Integer> getZero() {
        return new HashMap<OrderPart, Integer>();
    }

    @Override
    protected Map<OrderPart, Integer> sum(Map<OrderPart, Integer> o1, Map<OrderPart, Integer> o2) {
        Map<OrderPart, Integer> res = new HashMap<OrderPart, Integer>();
        res.putAll(o1);
        res.putAll(o2);
        return res;
    }

    /**
     * Количество деталей к текущему дню
     *
     * @param key
     * @param day
     * @return
     */
    @JsonIgnore
    public Integer getDailyPartCount(OrderPart key, Integer day) {
        int res = 0;
        for (int i = 0; i <= day ; ++i) {
            Integer dailyCount = getShedule(i).get(key);
            if (dailyCount != null) {
                res +=dailyCount;
            }
        }
        return res;
    }

    /**
     * Потратить детали
     *
     * @param key
     * @param value
     * @param day
     */
    public void decreasePartCount(OrderPart key, Integer value, int day) {

        for (int i = 0; i <= day && value > 0; ++i) {
            Map<OrderPart, Integer> dailyMap = getShedule(day);
            Integer dailyCount = dailyMap.get(key);
            if (dailyCount != null) {
                if (dailyCount <= value) {
                    dailyMap.remove(key);
                } else {
                    dailyMap.put(key, dailyCount - value);
                }
                value -= Math.min(dailyCount, value);
            }
        }

    }

    @JsonIgnore
    public SimpleMethod getBookPartMethod(OrderPart orderPart, Integer day, Integer count) {
        return new DeliveryBookPartMethod(orderPart, day, day, count);
    }

    @JsonIgnore
    public Integer getFreeSpace() {
        return 1;//пока что 
    }

    @JsonIgnore
    public Integer getSpace(OrderPart orderPart, Integer count) {
        return 0;//безразмерные детали пока что
    }

    @JsonIgnore
    public Integer getDeliverDay(OrderPart orderPart, Integer count, Integer day) {
        return day;// пока что моментальная доставка
    }

    /**
     * Пришла посылка
     *
     * @param orderPart
     * @param count
     * @param deliverDay
     */
    public void incomeDeliver(OrderPart orderPart, Integer count, Integer deliverDay) {
        Map<OrderPart, Integer> dailyMap = getShedule(deliverDay);
        Integer dailyCount = dailyMap.get(orderPart);
        if (dailyCount == null) {
            dailyCount = 0;
        }
        dailyMap.put(orderPart, dailyCount + count);
        addShedule(deliverDay, dailyMap);
        
    }
}
