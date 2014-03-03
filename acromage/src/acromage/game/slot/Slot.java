/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.data.Card;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public abstract class Slot implements Rendereble {

    public Card card;
    Rectangle rect;

    public Slot() {
        card = null;
    }
    

    public Rectangle getRect() {
        return rect;
    }

    void onGetCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        if (card != null) {
            spriteBatch.begin();
            spriteBatch.draw(card.getTexture(), rect.x, rect.y);
            spriteBatch.end();
        }
    }

}