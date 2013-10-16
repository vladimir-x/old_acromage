/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Dude
 */
public class Counting extends Department<Integer> {

    public Counting() {
        //addCash(10000,0);
    }

    public void addCash(int add, int day,String comment) {
        addShedule(day, add);
        addStatistic(day, comment + ":"+add);
    }

    public void spendCash(int spend, int day,String comment) {
        addShedule(day, -spend);
        addStatistic(day, comment + ":" + (-spend));
    }

    @JsonIgnore
    public int getBalance() {
        return getBalance(getLastDay());
    }

    public int getBalance(int day) {
        int cash = 0;
        for (int i = 0; i <= day; ++i) {
            cash += getShedule(i);
        }
        return cash;
    }

    
    
    @Override
    public int getState() {
        return getBalance();
    }

    @Override
    protected Integer getZero() {
        return 0;
    }

    @Override
    protected Integer sum(Integer o1, Integer o2) {
        return o1+ o2;
    }
}
