/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
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
    abstract public Integer getResultTacticPoint();
    
    // стратегическя(долгосрочная) оценка выполеннного плана
    @JsonIgnore
    abstract public Integer getResultStrategicPoint();
    
    // статистическая выписка по выполенному плану
    //abstract public String getResultDetailedStat();

}
