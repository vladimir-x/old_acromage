/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import java.util.HashMap;
import java.util.Map;
import processmodel.data.OrderPart;

/**
 *
 * @author Dude
 */
public class Delivery extends Department<Map<OrderPart,Integer>> {

   
    @Override
    public int getState() {
        return 0;
    }

    @Override
    protected Map<OrderPart,Integer> getZero() {
        return new HashMap<OrderPart, Integer>();
    }

    @Override
    protected Map<OrderPart,Integer> sum(Map<OrderPart,Integer> o1, Map<OrderPart,Integer> o2) {
        Map<OrderPart,Integer> res = new HashMap<OrderPart, Integer>();
        res.putAll(o1);
        res.putAll(o2);
        return res;
    }

    public Integer getDailyPartCount(OrderPart key, int day) {
        Integer dailyCount = getShedule(day).get(key);
        if (dailyCount ==null){
            return 0;
        }
        return dailyCount;
    }

    public void decreasePartCount(OrderPart key, Integer value, int day) {
        Map<OrderPart,Integer>  dailyMap = getShedule(day);
        Integer dailyCount = dailyMap.get(key);
        if (dailyCount !=null){
            dailyMap.put(key, dailyCount - value);
        }
    }
}
