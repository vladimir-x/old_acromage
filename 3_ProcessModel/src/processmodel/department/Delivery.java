/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import processmodel.OutWorld;
import processmodel.data.DeliverData;
import processmodel.data.Detail;
import processmodel.data.Shop;
import processmodel.data.WorkshopOrder;
import processmodel.simplest.DeliveryBookPartMethod;
import processmodel.simplest.SimpleMethod;

/**
 * Склад и доставка
 *
 * @author Dude
 */
public class Delivery extends Department<Map<String, Integer>> {

    @Override
    public int getState() {
        return 0;
    }

    @Override
    protected Map<String, Integer> getZero() {
        return new HashMap<String, Integer>();
    }

    @Override
    protected Map<String, Integer> sum(Map<String, Integer> o1, Map<String, Integer> o2) {
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (String k1 : o1.keySet()) {
            if (o2.containsKey(k1)) {
                res.put(k1, o1.get(k1) + o2.get(k1));
            } else {
                res.put(k1, o1.get(k1));
            }
        }
        for (String k2 : o2.keySet()) {
            if (!o1.containsKey(k2)) {
                res.put(k2, o2.get(k2));
            }
        }
        return res;
    }

    /**
     * Количество деталей к текущему дню
     *
     * @param partIdent
     * @param day
     * @return
     */
    @JsonIgnore
    public Integer getDailyPartCount(String partIdent, Integer day) {
        int res = 0;
        for (int i = 0; i <= day; ++i) {
            Integer dailyCount = getShedule(i).get(partIdent);
            if (dailyCount != null) {
                res += dailyCount;
            }
        }
        return res;
    }

    /**
     * Потратить детали
     *
     * @param partIdent
     * @param value
     * @param day
     */
    public void decreasePartCount(String partIdent, Integer value, int day) {

        for (int i = 0; i <= day && value > 0; ++i) {
            Map<String, Integer> dailyMap = getShedule(day);
            Integer dailyCount = dailyMap.get(partIdent);
            if (dailyCount != null) {
                if (dailyCount <= value) {
                    dailyMap.remove(partIdent);
                } else {
                    dailyMap.put(partIdent, dailyCount - value);
                }
                value -= Math.min(dailyCount, value);
            }
        }

    }

    @JsonIgnore
    public SimpleMethod getBookPartMethod(String orderPart, Integer day, Integer count) {
        return new DeliveryBookPartMethod(orderPart, day, day, count);
    }

    @JsonIgnore
    public Integer getFreeSpace() {
        return 9999999;//пока что 
    }


    /**
     * Пришла посылка
     *
     * @param orderPartIdent
     * @param count
     * @param deliverDay
     */
    public void incomeDeliver(String orderPartIdent, Integer count, Integer deliverDay) {
        Map<String, Integer> dailyMap = getShedule(deliverDay);
        Integer dailyCount = dailyMap.get(orderPartIdent);
        if (dailyCount == null) {
            dailyCount = 0;
        }
        dailyMap.put(orderPartIdent, dailyCount + count);
        addShedule(deliverDay, dailyMap);

    }

    /**
     * Подготовить информацию для заказа
     *
     * @return
     */
    public DeliverData getDeliverData(String orderPartIdent, Integer count, Integer day,Integer deliverDayBefore) {

        Shop shop = OutWorld.getOutWorld().getShop(orderPartIdent, day,deliverDayBefore);
        Detail detail = OutWorld.getOutWorld().getDetail(orderPartIdent);
        DeliverData res = null;

        if (shop != null && detail!=null) {
            res = new DeliverData();
            
            res.bookDay = day + shop.bookTime;
            res.cost = count * shop.price.get(orderPartIdent);
            res.count = count;
            res.ident = orderPartIdent;
            res.storeSpace = count * detail.space;
        }
        return res;
    }
}
