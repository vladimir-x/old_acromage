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
    
    int cash;

    public Counting() {
        cash = 0;
    }
    
    
    public void addCash(int add){
        cash += add;
    }
    
    public void spendCash(int spend){
        cash -=spend;
    }
    
    
    public int getCash(){
        return cash;
    }
}
