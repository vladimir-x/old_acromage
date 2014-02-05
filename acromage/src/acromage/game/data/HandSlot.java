/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.interfaсe.Rendereble;
import acromage.game.desk.Hand;
import acromage.game.ApplicationImpl;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class HandSlot implements Rendereble {

    public static final int SLOT_SPACER = 10;

    private Hand hand;
    private int pos;

    private Rectangle rect;

    public HandSlot(Hand hand, int pos) {
        this.hand = hand;
        this.pos = pos;
    }

    public Rectangle calcRect() {

        float width = hand.getRectangle().width / hand.getCount();
        float centrX = width / 2.f;

        float centrY = hand.getRectangle().height / 2.f;

        Rectangle rect = new Rectangle(
                hand.getRectangle().x + (width * pos + centrX - ApplicationImpl.settings.cardWidth / 2.f),
                hand.getRectangle().y + (centrY - ApplicationImpl.settings.cardHeight / 2.f),
                ApplicationImpl.settings.cardWidth,
                ApplicationImpl.settings.cardHeight
        );
        return rect;
    }

    @Override
    public void update() {
        rect = calcRect();
    }

    @Override
    public void render(ShapeRenderer renderer) {
        /*
        renderer.setColor(Color.GREEN);
        renderer.rect(
                rect.x,
                rect.y,
                rect.width,
                rect.height);
        */
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(ApplicationImpl.resources.deckUndoTexture, rect.x, rect.y);
        
    }

}
