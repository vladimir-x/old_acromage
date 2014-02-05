/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.ApplicationImpl;
import acromage.game.data.ResSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author admin
 */
public class ResPanel extends Deskzone {

    ResSlot brickSlot, gemSlot, beastSlot;

    ResSlot[] resSlots;

    public ResPanel(int zone) {
        super(zone);

        brickSlot = new ResSlot(this,0,ApplicationImpl.resources.brickTexture);
        gemSlot = new ResSlot(this,1,ApplicationImpl.resources.gemTexture);
        beastSlot = new ResSlot(this,2,ApplicationImpl.resources.beastTexture);

        resSlots = new ResSlot[]{brickSlot, gemSlot, beastSlot};
    }
    

    @Override
    public void update() {
        super.update();

        for (ResSlot resSlot:resSlots){
            resSlot.update();
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);

        for (ResSlot resSlot:resSlots){
            resSlot.render(renderer);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        for (ResSlot resSlot:resSlots){
            resSlot.render(spriteBatch);
        }
    }

}
