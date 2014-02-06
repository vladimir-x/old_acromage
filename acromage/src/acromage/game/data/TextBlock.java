/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.ApplicationImpl;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public abstract class TextBlock implements Rendereble {

    protected Rectangle rect;

    protected String text;

    public TextBlock() {
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.ORANGE);
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();
        
        spriteBatch.begin();
        ApplicationImpl.resources.font.draw(spriteBatch, text, rect.x, rect.y +rect.height);
        spriteBatch.end();

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
