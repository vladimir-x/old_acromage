/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import processmodel.Plant;
import processmodel.data.WorkshopOrder;
import processmodel.kimprocess.KimProcess;

/**
 *
 * @author elduderino
 */
public class WorkshopStartMethod extends SimpleMethod {

    WorkshopOrder order;
    Integer day;
    List<SimpleMethod> dbpmList;

    public WorkshopStartMethod(WorkshopOrder order, Integer day) {
        this.order = order;
        this.day = day;
        dbpmList = new ArrayList<SimpleMethod>();
    }

    @Override
    public boolean isAllow() {
        boolean res = true;
        for (Map.Entry<String, Integer> en : order.getParts().entrySet()) {
            boolean currDetail = Plant.getPlant().delivery.getDailyPartCount(en.getKey(), day) >= en.getValue();
            /*
            if (!currDetail) {
                Integer lastDayForProduce = Plant.getPlant().workshop.getLastDayForStartProduce(order,day);
                List<SimpleMethod> currList = new ArrayList<SimpleMethod>();
                currList.add(new DeliveryBookPartMethod(en.getKey(), 0, day, lastDayForProduce));
                currList.add(new DeliveryBookPartMethod(en.getKey(), en.getValue(), day, lastDayForProduce));
                currList.add(new DeliveryBookPartMethod(en.getKey(), 2 * en.getValue(), day, lastDayForProduce));

                SimpleMethod sm = KimProcess.selectMethod(currList);
                dbpmList.add(sm);
            }
            */
            res &= currDetail;
        }
        return res;
    }

    @Override
    public float getWeight() {
        return 0;
    }

    @Override
    public void execute() {

        for (Map.Entry<String, Integer> en : order.getParts().entrySet()) {
            Plant.getPlant().delivery.decreasePartCount(en.getKey(), en.getValue(), day);
        }
        order.start();
    }

    /**
     * попытаться заказать недостающие материалы
     */
    public void bookMaterials() {
        for (SimpleMethod m : dbpmList) {
            if (m != null) {
                m.execute();
            }
        }
    }
}
