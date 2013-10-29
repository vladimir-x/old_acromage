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
public class WorkshopProduceMethod extends SimpleMethod {

    WorkshopOrder order;
    Integer day;
    Integer powerLimit;
    Integer spendPower;
    public static Float coeffSimp[] = new Float[]{4f, 3f, 1.5f, 0.2f};
    WorkshopStartMethod wsm;

    public WorkshopProduceMethod(WorkshopOrder order, Integer day, Integer powerLimit) {
        this.order = order;
        this.day = day;
        this.powerLimit = powerLimit;
    }
    
    @Override
    public boolean isAllow() {
        Workshop workshop = Plant.getPlant().workshop;

        int reservedPower = workshop.getFreePower(day + 1, order.getEndDay());
        spendPower = Math.min(Math.min(powerLimit, order.getLeftPower()), workshop.getFreePower(day));

        // проверка на принципиальную исполнимость заказа после исполнения данного действия
        boolean hasPower = reservedPower >= (order.getLeftPower() - spendPower);

        if (!order.isStarted()) {
            wsm = new WorkshopStartMethod(order, day);
            
            if (!wsm.isAllow()){
                wsm.bookMaterials();
            }
        }

        return hasPower && (order.isStarted() || wsm.isAllow());// && cm.isAllow() && cmAll.isAllow();
    }

    @Override
    public float getWeight() {
        return getWeightA(coeffSimp);
    }

    @Override
    public void execute() {
        if (!order.isStarted()) {
            wsm.execute();
        }
        
        Workshop workshop = Plant.getPlant().workshop;
        order.addSpendPower(spendPower);
        workshop.spendPower(day, spendPower, order.getIdentName());

        if (KimProcess.printDetail) {
            System.out.println("\tWorkshop  Order:" + order.getIdentName() + " spend_power:" + spendPower);
        }

        if (order.isComplete() && !order.isSelled()) {
            Plant.getPlant().counting.addCash(order.getSellCost(), day, order.getIdentName());
            order.selled();
        }
    }

    // вариант функции оценки
    protected int getWeightA(Float coeff[]) {
        Float resF = (order.getPrioritet() * coeff[0]
                + order.getSellCost() * coeff[1]) / (order.getLeftPower() * coeff[2] + powerLimit * coeff[3]);

        return resF.intValue();
    }
}
