/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import processmodel.Plant;
import processmodel.data.WorkshopOrder;
import processmodel.department.Workshop;
import processmodel.kimprocess.KimProcess;

/**
 *
 * @author Dude
 */
public class WorkshopMethod extends SimpleMethod {

    WorkshopOrder order;
    int day;
    int powerLimit;
    public static Float coeffSimp[] = new Float[]{4f, 3f, 1.5f, 0.2f};

    public WorkshopMethod(WorkshopOrder order, int day, int powerLimit) {
        this.order = order;
        this.day = day;
        this.powerLimit = powerLimit;
    }

    @Override
    public boolean isAllow() {
        Workshop workshop = Plant.getPlant().workshop;

        if (workshop.getFreePower(day) >= powerLimit) {
            int reservedPower = workshop.getFreePower(day + 1, order.getEndDay());
            int spendPower = Math.min(powerLimit, order.getLeftPower());

            // проверка на принципиальную исполнимость заказа после исполнения данного едйствия
            return reservedPower >= (order.getLeftPower() - spendPower);
        } else {
            return false;
        }

    }

    @Override
    public int getWeight() {
        return getWeightA(coeffSimp);
    }

    @Override
    public void execute() {
        Workshop workshop = Plant.getPlant().workshop;
        int spendPower = Math.min(powerLimit, order.getLeftPower());
        order.addSpendPower(spendPower);
        workshop.spendPower(day, spendPower,order.getIdentName());

        if (KimProcess.printDetail) {
            System.out.println("\tWorkshop  Order:" + order.getIdentName() + " spend_power:" + spendPower);
        }
        
        if (order.isComplete() && order.isSelled()){
            Plant.getPlant().counting.addCash(order.getSellCost());
            order.selled();
        }
    }

    // вариант функции оценки
    protected int getWeightA(Float coeff[]) {
        Float resF = (order.getPrioritet() * coeff[0]
                + order.getSellCost() * coeff[1])  / (order.getLeftPower() * coeff[2] + powerLimit *coeff[3]);

        return resF.intValue();
    }
    
}
