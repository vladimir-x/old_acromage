/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Slot {

    public static final int SLOT_SPACER = 10;

    
    private static Settings settings;
    public Slot() {

    }

    public Rectangle getRect(Rectangle handRect, int pos, int count) {

        float width = handRect.width/count;
        float height = handRect.height -SLOT_SPACER ;
        float halfSpacer = SLOT_SPACER/2;
        
        Rectangle rect = new Rectangle(
                handRect.x + width*pos+halfSpacer,
                handRect.y + halfSpacer,
                width - halfSpacer,
                height
        );
        return rect;
    }
}
