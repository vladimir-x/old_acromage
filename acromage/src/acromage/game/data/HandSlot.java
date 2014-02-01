/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Arcomage;
import acromage.game.Settings;
import com.badlogic.gdx.graphics.Color;
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
        float centrX = width / 2;

        float centrY = hand.getRectangle().height / 2;

        Rectangle rect = new Rectangle(
                hand.getRectangle().x + (width * pos + centrX - Arcomage.settings.cardWidth / 2),
                hand.getRectangle().y + (centrY - Arcomage.settings.cardHeight / 2),
                Arcomage.settings.cardWidth,
                Arcomage.settings.cardHeight
        );
        return rect;
    }

    @Override
    public void update() {
        rect = calcRect();
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.rect(
                rect.x,
                rect.y,
                rect.width,
                rect.height);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }

}
