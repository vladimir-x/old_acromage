/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Deskzone {

    private static float horizBound = 0.2f;
    private static float vertBound = 0.3f;

    public static final int CENTER = 0;
    //public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;
    public static final int EAST = 4;

    private int zone;

    private Settings settings;

    //
    private Color color;

    public Deskzone(Settings settings) {
        this.settings = settings;
        this.zone = 0;
    }

    public Deskzone(Settings settings, int zone) {
        this.settings = settings;
        this.zone = zone;
    }

    public Rectangle getRectangle() {
        int width = settings.cameraWidth;
        int height = settings.cameraHeight;

        float leftTopX = width * vertBound;
        float leftTopY = height * horizBound;
        float rightBotX = width - leftTopX;
        float rightBotY = height - leftTopY;

        Rectangle rect = null;

        switch (zone) {
            case CENTER:
                rect = new Rectangle(leftTopX, 0, rightBotX - leftTopX, rightBotY - leftTopY);
                break;
            case WEST:
                rect = new Rectangle(0, leftTopY, leftTopX, rightBotY - leftTopY);
                break;
            case SOUTH:
                rect = new Rectangle(0, rightBotY, width, leftTopY);
                break;
            case EAST:
                rect = new Rectangle(rightBotX, leftTopY, leftTopX, rightBotY - leftTopY);
                break;
            default:
                throw new AssertionError();
        }

        return rect;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
