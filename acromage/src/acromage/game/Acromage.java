/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Deskzone;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author elduderino
 */
public class Acromage {

    private Settings settings;

    Deskzone left, right, top, bottom, center;
    Deskzone zones[];

    public Acromage(Settings settings) {
        this.settings = settings;

        center = new Deskzone(settings, Deskzone.CENTER);
        right = new Deskzone(settings, Deskzone.EAST);
        left = new Deskzone(settings, Deskzone.WEST);
        top = new Deskzone(settings, Deskzone.NORTH);
        bottom = new Deskzone(settings, Deskzone.SOUTH);

        center.setColor(Color.YELLOW);
        right.setColor(Color.RED);
        left.setColor(Color.GREEN);
        top.setColor(Color.CYAN);
        bottom.setColor(Color.BLACK);

        zones = new Deskzone[]{center, right, left, bottom, top};

    }

    public void render(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Deskzone zone : zones) {
            renderer.setColor(zone.getColor());
            renderer.rect(
                    zone.getRectangle().x,
                    zone.getRectangle().y,
                    zone.getRectangle().width,
                    zone.getRectangle().height
            );
        }
        renderer.end();
    }

}
