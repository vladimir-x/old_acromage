/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.interfaсe.Rendereble;
import acromage.game.desk.Hand;
import acromage.game.AppImpl;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class HandSlot extends Slot {

    public static final int SLOT_SPACER = 10;

    private Hand hand;
    private int pos;

    public HandSlot(Hand hand, int pos) {
        this.hand = hand;
        this.pos = pos;
    }

    public Rectangle calcRect() {

        float width = hand.getRectangle().width / AppImpl.settings.cardCount;
        float centrX = width / 2.f;

        float centrY = hand.getRectangle().height / 2.f;

        Rectangle rect = new Rectangle(
                hand.getRectangle().x + (width * pos + centrX - AppImpl.settings.cardWidth / 2.f),
                hand.getRectangle().y + (centrY - AppImpl.settings.cardHeight / 2.f),
                AppImpl.settings.cardWidth,
                AppImpl.settings.cardHeight
        );
        return rect;
    }

    public boolean contains(float propX, float propY) {
        return rect.contains(propX, propY);
    }

    @Override
    public void update() {
        rect = calcRect();
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN.cpy());
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
