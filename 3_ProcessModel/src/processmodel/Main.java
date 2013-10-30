/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        WorkshopOrder order = WorkshopOrder.getNamedOrder("X");

        KimProcess process = new KimProcess();
        //KimProcess.printDetail = true;
        WorkshopKimMethod kimMethod = new WorkshopKimMethod(order);

        process.modele(kimMethod, 1);
        process.printKimStatistic();
    }
}
