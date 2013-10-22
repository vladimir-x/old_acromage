/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import processmodel.Plant;
import processmodel.data.OrderPart;

/**
 *
 * @author Dude
 */
public class DeliveryBookPartMethod extends SimpleMethod {

    OrderPart orderPart;
    Integer day;
    Integer count;
    Integer deliverSpace;
    Integer deliverDay;
    Integer deliverDayBefore;
    float[] coeff = new float[]{1, 1, 1};

    /**
     *
     * @param orderPart
     * @param day - текущий день
     * @param deliverDay - день доставки ("к этому дню")
     * @param count
     */
    public DeliveryBookPartMethod(OrderPart orderPart, Integer day, Integer deliverDayBefore, Integer count) {
        this.orderPart = orderPart;
        this.day = day;
        this.deliverDayBefore = deliverDayBefore;
        this.count = count;
    }

    @Override
    public boolean isAllow() {

        deliverSpace = Plant.getPlant().delivery.getSpace(orderPart, count);
        deliverDay = Plant.getPlant().delivery.getDeliverDay(orderPart, count,day);
        
        boolean hasMoney = Plant.getPlant().counting.getBalance(day) >= orderPart.getCost() * count;
        boolean hasSpace = Plant.getPlant().delivery.getFreeSpace() >= deliverSpace;
        boolean hasInTime = deliverDayBefore >=  deliverDay;
        return hasMoney && hasSpace && hasInTime;
    }

    @Override
    public int getWeight() {
        Integer currCount = Plant.getPlant().delivery.getDailyPartCount(orderPart, day);
        return (int) ((count * coeff[0] + currCount * coeff[1]) / coeff[2] * deliverSpace);

    }

    @Override
    public void execute() {
        Plant.getPlant().counting.spendCash(orderPart.getCost() * count, day, orderPart.getIdent() + ":" + count);
        Plant.getPlant().delivery.incomeDeliver(orderPart,count,deliverDay);
        
    }
}
