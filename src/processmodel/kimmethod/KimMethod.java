/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import processmodel.Plant;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public abstract class KimMethod {

    @JsonIgnore
    abstract public Integer getStartDate();

    @JsonIgnore
    abstract public Integer getEndDate();

    @JsonIgnore
    abstract public List<SimpleMethod> getDailySimpleMethods();

    // инициализация структурных едениц предпрятия
    abstract public void init();

    // тактическая(краткосрочная) оценка выполеннного плана
    @JsonIgnore
    public Integer getResultTacticPoint() {
        return Plant.getPlant().counting.getBalance();
    }

    // стратегическя(долгосрочная) оценка выполеннного плана
    @JsonIgnore
    public Integer getResultStrategicPoint() {
        return Plant.getPlant().calcState();
    }
    // статистическая выписка по выполенному плану
    //abstract public String getResultDetailedStat();

}
