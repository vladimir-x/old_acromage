/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Arcomage;
import acromage.game.Settings;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Slot {

    public static final int SLOT_SPACER = 10;

    public Slot() {

    }

    public Rectangle getRect(Rectangle handRect, int pos, int count) {

        float width = handRect.width/count;
        float centrX = width/2;
        
        
        float height = handRect.height;
        float centrY = height/2;
        float halfSpacer = SLOT_SPACER/2;
        
        Rectangle rect = new Rectangle(
                handRect.x + (width*pos+centrX - Arcomage.settings.cardWidth/2),
                handRect.y + (centrY - Arcomage.settings.cardHeight/2),
                Arcomage.settings.cardWidth,
                Arcomage.settings.cardHeight
        );
        return rect;
    }
}
