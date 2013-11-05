/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import processmodel.OutWorld;
import processmodel.Plant;
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

    private static float stateCoeff[] = new float[]{100.f, 10.f};

    @Override
    public int getState() {
        int diff = 0;
        for (Detail detail : OutWorld.getOutWorld().detailList) {
            Integer currCnt = getDailyPartCount(detail.ident, Plant.getPlant().getDay());
            int cd = Math.abs(detail.normStore - currCnt);
            if (cd == 0) {
                diff += stateCoeff[0];
            } else {
                diff += stateCoeff[0] / cd;
            }
        }
        
        int freeLast = getFreeSpace(Plant.getPlant().getDay());
        int free = 0;
        if (freeLast == 0){
            free += stateCoeff[1];
        } else {
            free += stateCoeff[1]/freeLast;
        }
        return diff - free;
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

    //@JsonIgnore
    /**
     * Метод заказа детали.
     *
     * @param detailIdent
     * @param day
     * @param count
     * @return
     */
    public SimpleMethod getBookPartMethod(String detailIdent, Integer day, Integer count) {
        return new DeliveryBookPartMethod(detailIdent, count, day, day);
    }

    @JsonIgnore
    public Integer getFreeSpace(Integer day) {
        return 9999999;//пока что 
    }

    /**
     * Пришла посылка
     *
     * @param detailIdent
     * @param count
     * @param deliverDay
     */
    public void incomeDeliver(String detailIdent, Integer count, Integer deliverDay) {
        Map<String, Integer> dailyMap = getShedule(deliverDay);
        Integer dailyCount = dailyMap.get(detailIdent);
        if (dailyCount == null) {
            dailyCount = 0;
        }
        dailyMap.put(detailIdent, dailyCount + count);
        addShedule(deliverDay, dailyMap);

    }

    /**
     * Подготовить информацию для заказа
     *
     * @return
     */
    public DeliverData getDeliverData(String detailIdent, Integer count, Integer day, Integer deliverDayBefore) {

        Shop shop = OutWorld.getOutWorld().getShop(detailIdent, day, deliverDayBefore);
        Detail detail = OutWorld.getOutWorld().getDetail(detailIdent);
        DeliverData res = null;

        if (shop != null && detail != null) {
            res = new DeliverData();

            res.bookDay = day + shop.bookTime;
            res.cost = count * shop.price.get(detailIdent);
            res.count = count;
            res.ident = detailIdent;
            res.storeSpace = count * detail.space;
        }
        return res;
    }
}
