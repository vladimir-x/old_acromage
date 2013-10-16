/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import java.util.Map;
import processmodel.Plant;
import processmodel.data.OrderPart;
import processmodel.data.WorkshopOrder;

/**
 *
 * @author elduderino
 */
public class WorkshopStartMethod extends SimpleMethod {

    WorkshopOrder order;
    int day;

    public WorkshopStartMethod(WorkshopOrder order, int day) {
        this.order = order;
        this.day = day;
    }

    @Override
    public boolean isAllow() {
        boolean res = true;
        for (Map.Entry<OrderPart, Integer> en : order.getParts().entrySet()) {
            res &= Plant.getPlant().delivery.getDailyPartCount(en.getKey(), day) >= en.getValue();
        }
        return res;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public void execute() {
        for (Map.Entry<OrderPart, Integer> en : order.getParts().entrySet()) {
            Plant.getPlant().delivery.decreasePartCount(en.getKey(), en.getValue(), day);
        }
        order.start();
    }
}
