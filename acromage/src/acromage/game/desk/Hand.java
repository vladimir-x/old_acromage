/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.AppImpl;
import acromage.game.Arcomage;
import acromage.game.slot.HandSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.Arrays;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone {

    HandSlot[] slots;

    public Hand(int zone) {
        super(zone);
        slots = new HandSlot[AppImpl.settings.cardCount];
        for (int i=0;i<slots.length;++i){
            slots[i] = new HandSlot(this,i);
        }
    }

    public int getCount(){
        return slots.length;
    }

    
    @Override
    public void update() {
        super.update(); 
        
        for (HandSlot slot: slots){
            slot.update();
        }
    }
    
    @Override
    public void render(ShapeRenderer renderer,SpriteBatch spriteBatch) {
        super.render(renderer,spriteBatch); 
        
        for (HandSlot slot: slots){
            slot.render(renderer,spriteBatch);
        }
    }

}
