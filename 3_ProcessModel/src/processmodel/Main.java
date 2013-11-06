/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.ArrayType;
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
        List<KimMethod> kimMethods = readKimMethods();
        //saveMethodsList(kimMethods);
        for (KimMethod kimMethod : kimMethods) {
            process.modele(kimMethod, Control.getControl().iterationCount);
            process.fixBestStrategickPlan();
        }

    }

    private static List<KimMethod> readKimMethods() {
        try {
            ObjectMapper om = new ObjectMapper();
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY); // Adds type info

            List<KimMethod> res = (List<KimMethod>) om.readValue(new File("orders.json"), Object.class);
            return res;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
