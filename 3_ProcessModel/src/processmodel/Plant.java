/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processmodel.department.Counting;
import processmodel.department.Delivery;
import processmodel.department.Workshop;

/**
 *
 * @author Dude
 */
public class Plant {

    public Workshop workshop;
    public Delivery delivery;
    public Counting counting;
    private static Plant plant = new Plant();

    public Plant() {
        workshop = new Workshop();
        delivery = new Delivery();
        counting = new Counting();
    }

    public void init() {
        plant = new Plant();
    }

    public static Plant getPlant() {
        return plant;
    }

    public static String getStatistic() {

        ObjectMapper om = new ObjectMapper();
        try {
            ObjectWriter writer = om.writer().withDefaultPrettyPrinter();
            return writer.writeValueAsString(plant);

        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public static void loadByStatistic(String preload) {
        if (!preload.isEmpty()) {
            ObjectMapper om = new ObjectMapper();
            try {
                plant = om.readValue(preload, Plant.class);
            } catch (JsonProcessingException jpe) {
                System.err.println(jpe);
            } catch (IOException ex) {
                Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
