/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import processmodel.data.WorkshopOrder;
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
        //KimProcess.printDetail = true;
        WorkshopKimMethod kimMethod = new WorkshopKimMethod(order);
        //kimMethod.addPreload(getAPreload());

        process.modele(kimMethod, 50);
        process.printKimStatistic();


    }

    private static String getAPreload() {
        return ""
                + "Day: 0"
                + "    workshop: "
                + "     k1:6 lamda:2" //4
                + "    counting:"
                + "     k1:-60 k2:300 "
                + "Day: 1"
                + "	k1:11"
                + "Day: 2"
                + "	k1:6" //6
                + "Day: 3"
                + "	k1:8";  //4
    }
}
