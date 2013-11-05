/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import processmodel.data.DeliverBookOrder;
import processmodel.data.WorkshopOrder;
import processmodel.kimmethod.DeliveryDetailKimMethod;
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

        KimProcess process = new KimProcess();
        //KimProcess.printDetail = true;
        List<KimMethod> kimMethods = getKimMethods();
        saveMethodsList(kimMethods);
        for (KimMethod kimMethod : kimMethods) {
            process.modele(kimMethod, 1);
            process.fixBestStrategickPlan();
        }

    }

    private static List<KimMethod> getKimMethods() {

        DeliverBookOrder dOrder = new DeliverBookOrder(0, 0, "bolt", 50);
        DeliverBookOrder sOrder = new DeliverBookOrder(0, 0, "steel", 6);

        WorkshopOrder wOrder = WorkshopOrder.getNamedOrder("X");

        List<KimMethod> res = new ArrayList();
        res.add(new DeliveryDetailKimMethod(dOrder));
        res.add(new DeliveryDetailKimMethod(sOrder));
        res.add(new WorkshopKimMethod(wOrder));
        return res;

    }

    private static void saveMethodsList(List<KimMethod> kimMethods) {
        ObjectMapper om = new ObjectMapper();
        try {
            ObjectWriter writer = om.writer().withDefaultPrettyPrinter();

            writer.writeValue(new File("orders.json"), kimMethods);
        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
