/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.ApplicationImpl;
import acromage.game.data.NameBlock;
import acromage.game.data.ResSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author admin
 */
public class ResPanel extends Deskzone {

    ResSlot brickSlot, gemSlot, beastSlot;

    public ResSlot[] resSlots;
    
    NameBlock nameBlock;

    public ResPanel(int zone) {
        super(zone);

        brickSlot = new ResSlot(this,0,ApplicationImpl.resources.brickTexture);
        gemSlot = new ResSlot(this,1,ApplicationImpl.resources.gemTexture);
        beastSlot = new ResSlot(this,2,ApplicationImpl.resources.beastTexture);

        resSlots = new ResSlot[]{brickSlot, gemSlot, beastSlot};
        
        nameBlock = new NameBlock(this);
        nameBlock.setText("text");
    }
    

    @Override
    public void update() {
        super.update();
        
        for (ResSlot resSlot:resSlots){
            resSlot.update();
        }
        nameBlock.update();
    }

    @Override
    public void render(ShapeRenderer renderer,SpriteBatch spriteBatch) {
        super.render(renderer,spriteBatch);

        for (ResSlot resSlot:resSlots){
            resSlot.render(renderer,spriteBatch);
        }
        nameBlock.render(renderer, spriteBatch);
    }


}