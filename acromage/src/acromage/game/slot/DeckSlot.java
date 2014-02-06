/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.AppImpl;
import acromage.game.desk.Board;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Колода
 *
 * @author elduderino
 */
public class DeckSlot implements Rendereble {

    Board owner;

    Rectangle rect;

    public DeckSlot(Board owner) {
        this.owner = owner;
    }

    @Override
    public void update() {
        float x = owner.getRectangle().x + owner.CARDS_SPACE_X;
        float y = owner.getRectangle().y +owner.getRectangle().height 
                -owner.CARDS_SPACE_Y - AppImpl.settings.cardHeight;
        rect = new Rectangle(
                x,
                y,
                AppImpl.settings.cardWidth,
                AppImpl.settings.cardHeight
        );
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(AppImpl.resources.deckUndoTexture, rect.x, rect.y);
        spriteBatch.end();
    }

}
