/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;


import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import processmodel.data.WorkshopOrder;
import processmodel.simplest.SimpleMethod;
import processmodel.simplest.WorkshopMethod;

/**
 *
 * @author Dude
 */
public class Workshop extends Department {

    private int[] shedule;
    private String[] staticstic;
    private static final Integer MAX_DAILY_POWER = 12;
    private static final int SHEDULE_DEEP = 1000;

    public Workshop() {
        shedule = new int[SHEDULE_DEEP];
        staticstic = new String[SHEDULE_DEEP];
    }

    public Integer getMaxPower() {
        return MAX_DAILY_POWER;
    }

    public int getFreePower(int day) {
        if (day < SHEDULE_DEEP && day >= 0) {
            return getMaxPower() - shedule[day];
        }
        return 0;
    }

    public int getFreePower(int start, int end) {
        int res = 0;
        for (int i = start; i <= end; ++i) {
            res += getMaxPower() - shedule[i];
        }
        return res;
    }

    public SimpleMethod getProduceMethod(WorkshopOrder order, int day, Integer powerLimit) {
        return new WorkshopMethod(order, day, powerLimit);
    }

    public void spendPower(int day, int spendPower, String ident) {
        shedule[day] += spendPower;
        if (staticstic[day] == null) {
            staticstic[day] = "";
        }
        staticstic[day] += "\t" + ident + ":" + spendPower + ";\n";
    }

    public String getStatistic() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHEDULE_DEEP; ++i) {
            if (staticstic[i] != null) {
                sb.append("Day: " + i + "\n");
                sb.append(staticstic[i]);
            }
        }
        return sb.toString();
    }

    public void addByStatistic(String stat) {
        for (String dayInfo : stat.split("Day:")) {
            Scanner sc = new Scanner(dayInfo);
            try {
                
                Integer dayNumber = sc.nextInt();
                while (sc.hasNext()){
                    String oneInfo = sc.next(Pattern.compile("[a-zA-Z]+:[0-9]+;"));
                    String[] data = oneInfo.split("[a-zA-Z0-9]+");
                    String ident = data[0];
                    Integer power = Integer.parseInt(data[1]);
                    
                    spendPower(dayNumber,power,ident);
                }
            } catch (NoSuchElementException nsee) {
                // пустая строка
            } catch (IllegalStateException ise){
            }
        }
    }
}
