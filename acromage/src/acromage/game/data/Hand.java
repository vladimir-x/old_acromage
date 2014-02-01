/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Arcomage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
        
        Rectangle own = calcRectangle();
        
        for (int i=0;i<slots.length;++i){
            Slot slot = slots[i];
            rects[i] = slot.calcRect(own, i, slots.length);
        }
        
        return rects;
    }

    
    @Override
    public void update(long delta) {
        super.update(delta); 
        
        for (Slot slot: slots){
            slot.update(delta);
        }
    }
    
    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer); 
        
        for (Slot slot: slots){
            slot.render(renderer);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        
        for (Slot slot: slots){
            slot.render(spriteBatch);
        }
    }

    
    
    
}
