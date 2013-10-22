/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.simplest;

import processmodel.Plant;
import processmodel.department.Counting;

/**
 *
 * @author Dude
 */
public class CountingMethod extends SimpleMethod {

    Integer profit;
    Integer cash;
    Integer day;
    String comment;
    public static Float coeffSimp[] = new Float[]{1f, 0.3f};

    public CountingMethod(Integer cashSpend , Integer profit, Integer day,String comment) {
        this.cash = cashSpend;
        this.day = day;
        this.profit = profit;
        this.comment = comment;
    }

    @Override
    public boolean isAllow() {
        Counting counting = Plant.getPlant().counting;
        return counting.getBalance(day) >= cash;
    }

    @Override
    public int getWeight() {
        return getWeightA(coeffSimp);
    }

    @Override
    public void execute() {
        Counting counting = Plant.getPlant().counting;
        counting.spendCash(cash, day,comment);
    }

    // вариант функции оценки
    protected int getWeightA(Float coeff[]) {
        Float resF = (coeff[0] * profit ) / ((cash+1) * coeff[1]);
        return resF.intValue();
    }
}
