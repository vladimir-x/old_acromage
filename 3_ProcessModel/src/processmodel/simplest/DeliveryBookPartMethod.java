/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import processmodel.OutWorld;
import processmodel.data.DeliverData;
import processmodel.Plant;
import processmodel.department.Counting;
import processmodel.department.Delivery;

/**
 *  Доставка материалов для производства продукции.
 * @author Dude
 */
public class DeliveryBookPartMethod extends SimpleMethod {

    Delivery delivery;
    Counting counting;

    DeliverData deliverData;
    String detailIdent;
    Integer day;
    Integer count;
    Integer deliverDayBefore;
    float[] coeff = new float[]{5, 2, .2f, .5f};

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
    public DeliveryBookPartMethod(String detailIdent, Integer count, Integer day, Integer deliverDayBefore) {
        this();
        this.detailIdent = detailIdent;

        this.day = day;
        this.deliverDayBefore = deliverDayBefore;
        this.count = count;
    }

    @Override
    public boolean isAllow() {

        deliverData = delivery.getDeliverData(detailIdent, count, day,deliverDayBefore);

        if (deliverData != null) {
            boolean hasMoney = counting.getBalance(day) >= deliverData.cost;
            boolean hasSpace = delivery.getFreeSpace(day) >= deliverData.storeSpace;
            boolean hasInTime = deliverDayBefore >= deliverData.bookDay;
            return hasMoney && hasSpace && hasInTime;
        }
        return false;
    }

    @Override
    public float getWeight() {
        Integer currCount = delivery.getDailyPartCount(detailIdent, day);
        Integer normStore = OutWorld.getOutWorld().getDetail(detailIdent).normStore;
        return (count * coeff[0] + normStore * coeff[1]) / (coeff[2] * deliverData.storeSpace + coeff[3]*currCount+ 1);

    }

    @Override
    public void execute() {
        counting.spendCash(deliverData.cost, day, detailIdent + ":" + count);
        delivery.incomeDeliver(detailIdent, count, deliverData.bookDay);

    }

}
