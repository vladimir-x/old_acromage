/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.AppImpl;
import acromage.game.desk.ResPanel;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class NameBlock extends TextBlock {

    ResPanel owner;

    public NameBlock(ResPanel owner) {
        this.owner = owner;
    }

    @Override
    public void update() {
        float topSlotY = owner.resSlots[0].getRectangle().y + owner.resSlots[0].getRectangle().height;
        float topResPanelY = owner.getRectangle().y + owner.getRectangle().height;
        
        float centrX = owner.getRectangle().x + owner.getRectangle().width / 2.f;
        float centrY = topSlotY + (topResPanelY - topSlotY) / 2.f;
        
        
        BitmapFont.TextBounds tb = AppImpl.resources.font.getBounds(text);
        
        rect = new Rectangle(centrX -tb.width/2.f, centrY - tb.height/2.f, tb.width, tb.height);
    }

}
