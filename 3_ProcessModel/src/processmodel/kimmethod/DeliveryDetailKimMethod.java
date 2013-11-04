/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import java.util.ArrayList;
import java.util.List;
import processmodel.Plant;
import processmodel.data.DeliverBookOrder;
import processmodel.department.Delivery;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public class DeliveryDetailKimMethod extends KimMethod {

    DeliverBookOrder order;

    public DeliveryDetailKimMethod(DeliverBookOrder order) {
        this.order = order;
    }

    @Override
    public Integer getStartDate() {
        return order.startDay;
    }

    @Override
    public Integer getEndDate() {
        return order.endDay;
    }

    @Override
    public List<SimpleMethod> getDailySimpleMethods() {
        Delivery delivery = Plant.getPlant().delivery;
        Integer day = Plant.getPlant().getDay();
        List<SimpleMethod> res = new ArrayList<SimpleMethod>();

        res.add(delivery.getBookPartMethod(order.ident, day, 0));
        res.add(delivery.getBookPartMethod(order.ident, day, order.count));
        // здесь можно придумать стратегий 
        return res;
    }

    @Override
    public void init() {

    }

    @Override
    public Integer getResultTacticPoint() {
        return Plant.getPlant().counting.getBalance();
    }

    @Override
    public Integer getResultStrategicPoint() {
        return Plant.getPlant().calcState();
    }

}
