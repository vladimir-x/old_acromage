/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

/**
 *
 * @author Dude
 */
public class Counting extends Department{
    
    public Counting() {
    }
    
    public void addCash(int add, int day){
        shedule[day] += add;
    }
    
    public void spendCash(int spend, int day){
        shedule[day] -= spend;
    }
    
    
    public int getBalance(){
        int cash = 0;
        for (int i=0;i<SHEDULE_DEEP;++i){
            cash += shedule[i];
        }
        return cash;
    }
    
    public int getBalance(int day){
        int cash = 0;
        for (int i=0;i<=day;++i){
            cash += shedule[i];
        }
        return cash;
    }
}
