/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
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
            return om.writeValueAsString(plant);

        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public void addByStatistic(String preload) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
