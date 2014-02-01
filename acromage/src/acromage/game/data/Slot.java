/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Arcomage;
import acromage.game.Settings;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Slot implements Rendereble{

    public static final int SLOT_SPACER = 10;

    private Rendereble owner;
    
    private Rectangle rect;
    
    public Slot() {

    }

    public Rectangle calcRect(Rectangle handRect, int pos, int count) {

        float width = handRect.width/count;
        float centrX = width/2;
        
        float centrY = handRect.height/2;
        
        Rectangle rect = new Rectangle(
                handRect.x + (width*pos+centrX - Arcomage.settings.cardWidth/2),
                handRect.y + (centrY - Arcomage.settings.cardHeight/2),
                Arcomage.settings.cardWidth,
                Arcomage.settings.cardHeight
        );
        return rect;
    }

    @Override
    public void update(long delta) {
        rect = calcRect
    }

    @Override
    public void render(ShapeRenderer renderer) {
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }
    
    @Override
    public Rectangle getRectangle() {
        return rect;
    }
}
