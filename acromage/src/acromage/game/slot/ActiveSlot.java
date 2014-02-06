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
public class ActiveSlot implements Rendereble, Actionable {

    Rectangle rect;
    Board owner;

    public ActiveSlot(Board owner) {
        this.owner = owner;
    }

    public Rectangle getRectangle() {
        return rect;
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
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.ORANGE);
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();
    }

    @Override
    public void action(float delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
