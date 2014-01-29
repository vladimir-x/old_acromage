/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Settings;
import com.badlogic.gdx.math.Rectangle;
import java.util.Arrays;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone {

    public static final int SLOT_SPACER = 10;
    
    Slot[] slots;

    public Hand(Settings settings, int zone) {
        super(settings, zone);
        slots = new Slot[5];
        Arrays.fill(slots, new Slot());
    }

    public Rectangle[] getRects() {

        Rectangle[] rects = new Rectangle[slots.length];
        
        Rectangle own = getRectangle();
        
        float currX = own.x + SLOT_SPACER ;
        float centerY = own.y + (own.height/2);
        
        for (int i=0;i<slots.length;++i){
            Slot slot = slots[i];
            float currY = centerY - slot.height/2;
            rects[i] = new Rectangle(currX, currY, slot.width, slot.height);
            currX +=  slot.width+ SLOT_SPACER;
        }
        
        return rects;
    }
}
