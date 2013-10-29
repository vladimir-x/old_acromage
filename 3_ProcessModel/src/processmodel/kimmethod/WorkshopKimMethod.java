/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import java.util.ArrayList;
import java.util.List;
import processmodel.Plant;
import processmodel.data.WorkshopOrder;
import processmodel.department.Workshop;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public class WorkshopKimMethod extends KimMethod {

    WorkshopOrder order;

    public WorkshopKimMethod(WorkshopOrder order) {
        this.order = order;
    }

    @Override
    public int getStartDate() {
        return order.getStartDay();
    }

    @Override
    public int getEndDate() {
        return order.getEndDay();
    }

    @Override
    public List<SimpleMethod> getDailySimpleMethods() {
        Workshop workshop = Plant.getPlant().workshop;
        Integer day = Plant.getPlant().getDay();
        Integer maxPower = workshop.getMaxPower();

        List<SimpleMethod> res = new ArrayList<SimpleMethod>();

        res.add(workshop.getProduceMethod(order, day, maxPower));
        res.add(workshop.getProduceMethod(order, day, maxPower / 2));
        res.add(workshop.getProduceMethod(order, day, 1));
        res.add(workshop.getProduceMethod(order, day, 0));

        return res;
    }

    @Override
    public void init() {
        order.init();
    }

    @Override
    public int getResultTacticPoint() {
        return Plant.getPlant().counting.getBalance();
    }

    @Override
    public int getResultStrategicPoint() {
        return Plant.getPlant().calcState();
    }
}
