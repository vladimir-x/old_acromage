/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Actionable;
import acromage.game.data.Deskzone;
import acromage.game.data.Hand;
import acromage.game.data.Rendereble;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 *
 * @author elduderino
 */
public class Arcomage implements Rendereble, Actionable {

    public static Settings settings;

    Deskzone left, right, center;
    Hand bottom;

    Deskzone zones[];
    Hand hands[];

    public Arcomage(Settings settings) {
        this.settings = settings;

        center = new Deskzone(Deskzone.CENTER);
        right = new Deskzone(Deskzone.EAST);
        left = new Deskzone(Deskzone.WEST);
        bottom = new Hand(Deskzone.SOUTH);

        center.setColor(Color.DARK_GRAY);
        right.setColor(Color.RED);
        left.setColor(Color.BLUE);
        bottom.setColor(Color.LIGHT_GRAY);

        zones = new Deskzone[]{center, right, left, bottom};
        hands = new Hand[]{bottom};
        
        update();
    }

    @Override
    public void render(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Deskzone zone : zones) {
            zone.render(renderer);
        }

        renderer.end();
    }

    

    @Override
    public void update() {
        for (Deskzone zone : zones) {
            zone.update();
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        for (Deskzone zone : zones) {
            zone.render(spriteBatch);
        }
    }

    @Override
    public void action(float delta) {
    }
}
