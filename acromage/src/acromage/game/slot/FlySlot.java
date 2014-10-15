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
public class FlySlot extends Slot implements Actionable {

    private static float FLY_TIME = 0.30f;//количество секунд, зак оторые производится прохождение полного пути полёта

    Slot destination;
    float remainingTime;

    public FlySlot(Slot source, Slot destination) {
        this.rect = new Rectangle(source.getRect());
        this.destination = destination;
        this.remainingTime = FLY_TIME;

        setCard(source.getCard());
        setDroped(source.getDroped());
        setPlayedStep(source.getPlayedStep());
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void action(float delta) {

        if (remainingTime < 0.00001) {
            //анимация прошла
            onDest();

        } else if (delta > 0.00001) {
            float part = delta / remainingTime;
            float deltaX = (destination.getRect().x - rect.x) * part;
            float deltaY = (destination.getRect().y - rect.y) * part;

            remainingTime -= delta;

            if (remainingTime < 0.00001) {
                rect.x = destination.getRect().x;
                rect.y = destination.getRect().y;
            } else {
                rect.x += deltaX;
                rect.y += deltaY;
            }
        }
    }

    public void onDest() {
        destination.setCard(getCard());
        destination.setDroped(getDroped());
        destination.setPlayedStep(getPlayedStep());
        destination.onGetCard();
        setCard(null);
    }

}
