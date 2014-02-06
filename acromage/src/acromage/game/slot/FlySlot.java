/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.AppImpl;
import acromage.game.interfaсe.Actionable;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class FlySlot implements Rendereble, Actionable {

    private static float FLY_TIME = 0.30f;//количество секунд, зак оторые производится прохождение полного пути полёта

    Rectangle current, destination;
    float remainingTime;

    public FlySlot(Rectangle source, Rectangle destination) {
        this.current = source;
        this.destination = destination;
        remainingTime = FLY_TIME;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(AppImpl.resources.deckUndoTexture, current.x, current.y);
        spriteBatch.end();
    }

    @Override
    public void action(float delta) {

        if (remainingTime < 0.00001) {
            //анимация прошла
            
        } else if (delta > 0.00001) {
            float part = delta / remainingTime;
            float deltaX = (destination.x - current.x) * part;
            float deltaY = (destination.y - current.y) * part;

            remainingTime -= delta;

            if (remainingTime < 0.00001) {
                current.x = destination.x;
                current.y = destination.y;
            } else {
                current.x += deltaX;
                current.y += deltaY;
            }
        }

    }

}
