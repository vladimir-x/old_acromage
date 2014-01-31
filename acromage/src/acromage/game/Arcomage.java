/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Deskzone;
import acromage.game.data.Hand;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Arcomage {

    public static Settings settings;

    Deskzone left, right, center;
    Hand bottom;
    
    Deskzone zones[];
    Hand hands[];

    public Arcomage(Settings settings) {
        this.settings = settings;

        center = new Deskzone( Deskzone.CENTER);
        right = new Deskzone( Deskzone.EAST);
        left = new Deskzone( Deskzone.WEST);
        bottom = new Hand( Deskzone.SOUTH);

        center.setColor(Color.DARK_GRAY);
        right.setColor(Color.RED);
        left.setColor(Color.BLUE);
        bottom.setColor(Color.LIGHT_GRAY);

        zones = new Deskzone[]{center, right, left, bottom};
        hands = new Hand[]{bottom};

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

        for (Hand hand : hands) {
            for (Rectangle rect : hand.getRects()) {
                renderer.setColor(Color.GREEN);
                renderer.rect(
                        rect.x,
                        rect.y,
                        rect.width,
                        rect.height
                );
            }
        }

        renderer.end();
    }

}
