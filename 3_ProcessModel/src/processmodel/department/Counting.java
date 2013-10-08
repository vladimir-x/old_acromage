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
public class Counting extends Department {

    public Counting() {
        //addCash(10000,0);
    }

    public void addCash(int add, int day,String comment) {
        addShedule(day, add);
        addStatistic(day, comment);
    }

    public void spendCash(int spend, int day,String comment) {
        addShedule(day, -spend);
        addStatistic(day, comment + ":" + (-spend));
    }

    @JsonIgnore
    public int getBalance() {
        return getBalance(SHEDULE_DEEP-1);
    }

    public int getBalance(int day) {
        int cash = 0;
        for (int i = 0; i <= day; ++i) {
            cash += getShedule(i);
        }
        return cash;
    }
}
