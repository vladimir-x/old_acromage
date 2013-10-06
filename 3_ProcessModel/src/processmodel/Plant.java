/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

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

    public void addByStatistic(String preload) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
