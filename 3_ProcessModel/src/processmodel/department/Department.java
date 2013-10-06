/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.department;

/**
 *
 * @author Dude
 */
public class Department {

    protected  int[] shedule;
    protected static final int SHEDULE_DEEP = 1000;
    protected String[] staticstic;

    public Department() {
        shedule = new int[SHEDULE_DEEP];
        staticstic = new String[SHEDULE_DEEP];
    }
    
     public String getStatistic() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHEDULE_DEEP; ++i) {
            if (staticstic[i] != null) {
                sb.append("Day: " + i + "\n");
                sb.append(staticstic[i]);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
     
}
