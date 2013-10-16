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
public abstract class Department<T> {

    public Map<Integer, T >shedule;
    protected static Integer lastDay = 0;
    public Map<Integer, List<String>> staticstic;
    
    protected T register;

    public Department() {
        shedule = new HashMap<Integer, T>();
        staticstic = new HashMap<Integer, List<String>>();
    }
    
    
    protected T getRegister(){
        return register;
    }
    
    protected void addRegister(T value){
        
    }

    protected T getShedule(int day) {
        T res = shedule.get(day);
        if (res == null) {
            return getZero();
        }
        lastDay = Math.max(lastDay, day);
        return res;
    }

    protected void setShedule(int day, T value) {
        shedule.put(day, value);
        lastDay = Math.max(lastDay, day);
    }

    protected void addShedule(int day, T value) {
        T res = shedule.get(day);
        if (res == null) {
            shedule.put(day, value);
        } else {
            
            shedule.put(day, sum(res, value));
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
    
    protected abstract T getZero();
    
    protected abstract T sum(T o1,T o2);

    /**
     * Оценка состояния производственной единицы
     * @return 
     */
    @JsonIgnore
    public abstract int getState();
}
