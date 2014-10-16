/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.interfaсe.Actionable;
import acromage.game.interfaсe.Rendereble;
import acromage.game.slot.FlySlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elduderino
 */
public class AnimPool implements Actionable, Rendereble {

    Map<FlySlot, Runnable> fSlots;

    public AnimPool() {
        fSlots = new HashMap<FlySlot, Runnable>();
    }

    public void putSlot(FlySlot slot, Runnable onFlyOver) {
        fSlots.put(slot, onFlyOver);
    }

    @Override
    public void action(float delta) {
        for (FlySlot slot : fSlots.keySet()) {
            slot.action(delta);
            if (slot.isOwer()) {
                if (fSlots.get(slot) != null) {
                    fSlots.get(slot).run();
                }
                fSlots.remove(slot);
            }
        }
    }

    @Override
    public void update() {
        for (FlySlot slot : fSlots.keySet()) {
            slot.update();

        }
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        for (FlySlot slot : fSlots.keySet()) {
            slot.render(renderer, spriteBatch);
        }
    }

}
