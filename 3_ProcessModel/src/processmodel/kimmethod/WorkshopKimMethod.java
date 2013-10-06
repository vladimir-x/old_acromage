/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
    List<String> preloadList;

    public WorkshopKimMethod(WorkshopOrder order) {
        this.order = order;
        preloadList = new ArrayList<String>();
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
    public List<SimpleMethod> getDailySimpleMethods(int day) {
        Workshop workshop = Plant.getPlant().workshop;
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
        for (String preload :preloadList){
            Plant.getPlant().addByStatistic(preload);
        }
    }

    @Override
    public int getResultPoint() {
        return Plant.getPlant().counting.getBalance();
    }

    @Override
    public String getResultDetailedStat() {
        return Plant.getPlant().workshop.getStatistic();
    }

    @Override
    public long getResultHash() {
        return Plant.getPlant().workshop.getStatistic().hashCode();
    }

    public void addPreload(String string) {
        preloadList.add(string);
    }
}
