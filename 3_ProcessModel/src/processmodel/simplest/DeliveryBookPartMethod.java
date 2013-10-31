/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import processmodel.data.DeliverData;
import processmodel.Plant;
import processmodel.department.Counting;
import processmodel.department.Delivery;

/**
 *
 * @author Dude
 */
public class DeliveryBookPartMethod extends SimpleMethod {

    Delivery delivery;
    Counting counting;

    DeliverData deliverData;
    String orderPartIdent;
    Integer day;
    Integer count;
    Integer deliverDayBefore;
    float[] coeff = new float[]{5, 4, .1f};

    public DeliveryBookPartMethod() {
        delivery = Plant.getPlant().delivery;
        counting = Plant.getPlant().counting;
    }

    /**
     *
     * @param orderPart
     * @param count
     * @param day текущий день
     * @param deliverDay день доставки ("к этому дню")
     */
    public DeliveryBookPartMethod(String orderPartIdent, Integer count, Integer day, Integer deliverDayBefore) {
        this();
        this.orderPartIdent = orderPartIdent;

        this.day = day;
        this.deliverDayBefore = deliverDayBefore;
        this.count = count;
    }

    @Override
    public boolean isAllow() {

        deliverData = delivery.getDeliverData(orderPartIdent, count, day,deliverDayBefore);

        if (deliverData != null) {
            boolean hasMoney = counting.getBalance(day) >= deliverData.cost;
            boolean hasSpace = delivery.getFreeSpace() >= deliverData.storeSpace;
            boolean hasInTime = deliverDayBefore >= deliverData.bookDay;
            return hasMoney && hasSpace && hasInTime;
        }
        return false;
    }

    @Override
    public float getWeight() {
        Integer currCount = delivery.getDailyPartCount(orderPartIdent, day);
        return (count * coeff[0] + currCount * coeff[1]) / (coeff[2] * deliverData.storeSpace + 1);

    }

    @Override
    public void execute() {
        counting.spendCash(deliverData.cost, day, orderPartIdent + ":" + count);
        delivery.incomeDeliver(orderPartIdent, count, deliverData.bookDay);

    }

}
