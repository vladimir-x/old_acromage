/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import processmodel.data.WorkshopOrder;
import processmodel.department.Counting;
import processmodel.department.Department;
import processmodel.department.Workshop;
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



        WorkshopOrder order = WorkshopOrder.getNamedOrder("Z");

        KimProcess process = new KimProcess();
        //KimProcess.printDetail = true;
        WorkshopKimMethod kimMethod = new WorkshopKimMethod(order);
        kimMethod.addPreload(getJSONPreload());

        process.modele(kimMethod, 50);
        process.printKimStatistic();


    }

    private static String getJSONPreload() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("plant.json"));
            StringBuilder sb = new StringBuilder();
            String r = "";
            do {
                r = reader.readLine();
                if (r != null) {
                    sb.append(r);
                }
            } while (r != null);

            reader.close();
            return sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }
}
