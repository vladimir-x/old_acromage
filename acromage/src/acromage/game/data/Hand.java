/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Arcomage;
import com.badlogic.gdx.math.Rectangle;
import java.util.Arrays;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone {

    
    Slot[] slots;

    public Hand(int zone) {
        super( zone);
        slots = new Slot[Arcomage.settings.cardCount];
        Arrays.fill(slots, new Slot());
    }

    public Rectangle[] getRects() {

        Rectangle[] rects = new Rectangle[slots.length];
        
        Rectangle own = getRectangle();
        
        
        for (int i=0;i<slots.length;++i){
            Slot slot = slots[i];
            rects[i] = slot.getRect(own, i, slots.length);
        }
        
        return rects;
    }
}
