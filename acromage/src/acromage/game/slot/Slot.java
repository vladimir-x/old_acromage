/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.data.Card;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author elduderino
 */
public abstract class Slot implements Rendereble {

    private Card card;
    Rectangle rect;
    private Boolean droped;
    Integer playedStep; // на каком ходу был разыгран

    public Slot() {
        card = null;
        droped = false;
    }

    public Rectangle getRect() {
        return rect;
    }

    void onGetCard() {
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        if (card != null) {
            spriteBatch.begin();
            spriteBatch.draw(card.getTexture(), rect.x, rect.y);

            spriteBatch.end();
            if (droped) {

                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                Color c = Color.GRAY.cpy().sub(0, 0, 0, 0.5f);

                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(c);
                renderer.rect(
                        rect.x,
                        rect.y,
                        rect.width,
                        rect.height
                );
                renderer.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
            }
        }
    }

    public Boolean getDroped() {
        return droped;
    }

    public void setDroped(Boolean droped) {
        this.droped = droped;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setPlayedStep(Integer playedStep) {
        this.playedStep = playedStep;
    }

    public Integer getPlayedStep() {
        return playedStep;
    }

}
