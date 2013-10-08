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

    public void addCash(int add, int day) {
        addShedule(day, add);
    }

    public void spendCash(int spend, int day) {
        addShedule(day, -spend);
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
