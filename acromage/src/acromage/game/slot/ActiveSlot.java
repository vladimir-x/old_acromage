/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.AppImpl;
import acromage.game.desk.Board;
import acromage.game.interfaсe.Actionable;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Играемая карта
 *
 * @author elduderino
 */
public class ActiveSlot extends Slot implements Actionable {

    private static final float CARD_ACTIVE_TIME = 0.5f;

    Board owner;
    float remainingTime;

    FlySlot playedCard;

    public ActiveSlot(Board owner) {
        this.owner = owner;
        remainingTime = 0f;
        card = null;
    }

    @Override
    public void update() {
        float centrX = owner.getRectangle().x + owner.getRectangle().width / 2.f;
        float botY = owner.getRectangle().y;

        rect = new Rectangle(
                centrX - AppImpl.settings.cardWidth / 2.f,
                botY,
                AppImpl.settings.cardWidth,
                AppImpl.settings.cardHeight);
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.ORANGE);
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();

        if (playedCard != null) {
            playedCard.render(renderer, spriteBatch);
        }
    }

    @Override
    void onGetCard() {
        remainingTime = CARD_ACTIVE_TIME;
    }

    @Override
    public void action(float delta) {
        if (remainingTime > 0.000001f) {

            remainingTime -= delta;

            if (remainingTime < 0.000001f) {
                playedCard = new FlySlot(this, owner.getLastPlayedSlot());
                executeCard();
                card = null;
            }
        }
        if (playedCard != null) {
            playedCard.action(delta);
            if (playedCard.card == null) {
                playedCard = null;
            }
        }
    }
    
    private void executeCard(){
        card.play();
    }

}
