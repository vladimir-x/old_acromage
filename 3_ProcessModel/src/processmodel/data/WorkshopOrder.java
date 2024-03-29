/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dude
 */
public class WorkshopOrder {

    String identName;
    int startDay;       //первый день в который возможно производство (день поступления заказа)
    int endDay;         //последний день для производства
    int powerAll;       //мощность для производсва заказа
    int spendPower;     //потраченная мощность
    int prioritet;
    int sellCost;
    Map<String, Integer> parts; //детали из которых состоит изделие: идентификатор - количество
    boolean selled; // продан ли заказ (получены ли деньги за исполненный заказ)
    boolean started;//начато ли производство заказа. при старте производства - со склада изымаются все необходимые для производства детали

    public WorkshopOrder() {
        parts = new HashMap<String, Integer>();
    }

    public static WorkshopOrder getNamedOrder(String name) {
        WorkshopOrder order = new WorkshopOrder();

        order.identName = name;
        order.startDay = 0;
        order.endDay = 3;
        order.powerAll = 15;
        order.spendPower = 0;
        order.prioritet = 1;
        order.sellCost = 3000;

        order.parts.put("bolt", 3);
        order.parts.put("steel", 2);

        return order;
    }

    public void init() {
        spendPower = 0;
        selled = false;
        started = false;
    }

    /**
     * Просрочено ?
     */
    public boolean isOverdue(int currentDay) {
        return currentDay > endDay;
    }

    /**
     * Завершено ?
     *
     * @return
     */
    @JsonIgnore
    public boolean isComplete() {
        return spendPower == powerAll;
    }

    /**
     * Сколько соталось до завершения
     *
     * @return
     */
    @JsonIgnore
    public int getLeftPower() {
        return powerAll - spendPower;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName = identName;
    }

    public int getPowerAll() {
        return powerAll;
    }

    public void setPowerAll(int powerAll) {
        this.powerAll = powerAll;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public int getSellCost() {
        return sellCost;
    }

    public void setSellCost(int sellCost) {
        this.sellCost = sellCost;
    }

    public int getSpendPower() {
        return spendPower;
    }

    public void setSpendPower(int spendPower) {
        this.spendPower = spendPower;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void addSpendPower(int spendPower) {
        this.spendPower += spendPower;
    }

    public boolean isSelled() {
        return selled;
    }

    public void selled() {
        this.selled = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        this.started = true;
    }

    public Map<String, Integer> getParts() {
        return parts;
    }

    
    public void setParts(Map<String, Integer> parts) {
        this.parts = parts;
    }
}
