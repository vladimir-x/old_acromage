/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimmethod;

import java.util.List;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public abstract class KimMethod {
    
    
    abstract public Integer getStartDate();
    
    abstract public Integer getEndDate();

    abstract public List<SimpleMethod> getDailySimpleMethods();

    // инициализация структурных едениц предпрятия
    abstract public void init();
    
    // тактическая(краткосрочная) оценка выполеннного плана
    abstract public Integer getResultTacticPoint();
    
    // стратегическя(долгосрочная) оценка выполеннного плана
    abstract public Integer getResultStrategicPoint();
    
    // статистическая выписка по выполенному плану
    //abstract public String getResultDetailedStat();

}
