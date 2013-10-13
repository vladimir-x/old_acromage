/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dude
 */
public abstract class Department {

    public Map<Integer, Integer> shedule;
    protected static Integer lastDay = 0;
    public Map<Integer, List<String>> staticstic;

    public Department() {
        shedule = new HashMap<Integer, Integer>();
        staticstic = new HashMap<Integer, List<String>>();
    }

    protected Integer getShedule(int day) {
        Integer res = shedule.get(day);
        if (res == null) {
            return 0;
        }
        lastDay = Math.max(lastDay, day);
        return res;
    }

    protected void setShedule(int day, int value) {
        shedule.put(day, value);
        lastDay = Math.max(lastDay, day);
    }

    protected void addShedule(int day, int value) {
        Integer res = shedule.get(day);
        if (res == null) {
            shedule.put(day, value);
        } else {
            shedule.put(day, res + value);
        }
    }

    protected void addStatistic(int day, String StatStr) {
        List<String> list = staticstic.get(day);
        if (list == null) {
            list = new ArrayList<String>();
            staticstic.put(day, list);
        }
        list.add(StatStr);
    }
    
    protected Integer getLastDay(){
        return lastDay;
    }

    /**
     * Оценка состояния производственной единицы
     * @return 
     */
    public abstract int getState();
}
