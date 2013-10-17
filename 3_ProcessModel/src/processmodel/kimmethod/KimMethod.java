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
    
    
    abstract public int getStartDate();
    
    abstract public int getEndDate();

    abstract public List<SimpleMethod> getDailySimpleMethods();

    // инициализация структурных едениц предпрятия
    abstract public void init();
    
    // тактическая(краткосрочная) оценка выполеннного плана
    abstract public int getResultTacticPoint();
    
    // стратегическя(долгосрочная) оценка выполеннного плана
    abstract public int getResultStrategicPoint();
    
    // статистическая выписка по выполенному плану
    //abstract public String getResultDetailedStat();

}
