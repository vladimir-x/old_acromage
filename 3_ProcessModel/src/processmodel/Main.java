/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import processmodel.data.WorkshopOrder;
import processmodel.kimmethod.KimMethod;
import processmodel.kimmethod.WorkshopKimMethod;
import processmodel.kimprocess.KimProcess;

/**
 *
 * @author Dude
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        WorkshopOrder order = WorkshopOrder.getXOrder();

        KimProcess process = new KimProcess();
        KimProcess.printDetail = true;
        WorkshopKimMethod kimMethod = new WorkshopKimMethod(order);
        kimMethod.addPreload(getAPreload());

        process.modele(kimMethod, 50);
        process.printStatistic();

    }

    private static String getAPreload() {
        return ""
                + "Day: 0"
                + "     k1:6 lamda:4"
                + "Day: 1"
                + "	k1:12"
                + "Day: 2"
                + "	k1:4"
                + "Day: 3"
                + "	k1:1";
    }
}
