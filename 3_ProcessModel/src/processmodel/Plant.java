/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    private int day;

    public Plant() {
        workshop = new Workshop();
        delivery = new Delivery();
        counting = new Counting();
    }

    public void init() {
        loadByStatistic();
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

    private static void loadByStatistic() {
        ObjectMapper om = new ObjectMapper();
        try {
            plant = om.readValue(new File("plant.json"), Plant.class);
        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Оценка состояния всех производственных единиц
     *
     * @return
     */
    public int calcState() {

        float[] coeff = new float[]{1f, 1f, 1f};
        float res = workshop.getState() * coeff[0] + delivery.getState() * coeff[1] + counting.getState() * coeff[2];

        return (int) res;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @JsonIgnore
    public int getDay() {
        return day;
    }

}
