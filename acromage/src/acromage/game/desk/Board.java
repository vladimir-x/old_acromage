/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.data.ActiveSlot;
import acromage.game.data.DeckSlot;
import acromage.game.data.PlayedSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Board extends Deskzone {

    DeckSlot deckSlot;
    ActiveSlot activeSlot;
    
    ArrayList<PlayedSlot> playedSlots;
    PlayedSlot lastSlot;
    
    public Board(int zone) {
        super(zone);
        playedSlots = new ArrayList<PlayedSlot>();
    }

    @Override
    public void update() {
        super.update(); 
        
        deckSlot.update();
        activeSlot.update();
        
        for (PlayedSlot playedSlot :playedSlots){
            playedSlot.update();
        }
        
    }
    
    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch); 
        
        for (PlayedSlot playedSlot :playedSlots){
            playedSlot.render(renderer, spriteBatch);
        }
        deckSlot.render(renderer, spriteBatch);
        activeSlot.render(renderer, spriteBatch);
        
    }

    private void makeEmptySlot(){
        
        PlayedSlot playedSlot = new PlayedSlot();
        
        lastSlot = playedSlot;
        
    }
}
