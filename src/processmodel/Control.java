/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс - с настройками приложения
 * @author elduderino
 */
public class Control {
    
    private static Control control;

    public static Control getControl() {
        if(control==null){
            loadFromJson();
        }
        return control;
    }
    
    public Integer iterationCount;
    
    
    public static void loadFromJson(){
        ObjectMapper om = new ObjectMapper();
        try {
            control = om.readValue(new File("control.json"), Control.class);
        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
