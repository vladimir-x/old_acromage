/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dude
 */
public class Department {

    public HashMap<Integer, Integer> shedule;
    protected static final int SHEDULE_DEEP = 1000;
    protected String[] staticstic;

    public Department() {
        shedule = new HashMap<Integer, Integer>();
        staticstic = new String[SHEDULE_DEEP];
    }

    protected Integer getShedule(int day) {
        Integer res = shedule.get(day);
        if (res == null) {
            return 0;
        }
        return res;
    }

    protected void setShedule(int day, int value) {
        shedule.put(day, value);
    }

    protected void addShedule(int day, int value) {
        Integer res = shedule.get(String.valueOf(day));
        if (res == null) {
            shedule.put(day, value);
        } else {
            shedule.put(day, res + value);
        }
    }

    public String getStatistic() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHEDULE_DEEP; ++i) {
            if (staticstic[i] != null) {
                sb.append("Day: " + i + " ");
                sb.append(staticstic[i]);
                sb.append(";");
            }
        }
        return sb.toString();
    }

    Map getShedule() {
        return shedule;
    }
}
