/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.ApplicationImpl;
import acromage.game.Arcomage;
import acromage.game.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Deskzone implements Rendereble {

    private static float vertBound = 0.15f;
    private static float horizBound = 0.3f;

    public static final int CENTER = 0;
    //public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;
    public static final int EAST = 4;

    private int zone;

    private Rectangle rect;

    //
    private Color color;

    public Deskzone() {
        this.zone = 0;
    }

    public Deskzone(int zone) {
        this.zone = zone;
    }

    public Rectangle calcRectangle() {
        int width = ApplicationImpl.settings.cameraWidth;
        int height = ApplicationImpl.settings.cameraHeight;

        float leftTopX = width * vertBound;
        float leftTopY = height * horizBound;
        float rightBotX = width - leftTopX;
        float rightBotY = height - leftTopY;

        Rectangle rect = null;

        switch (zone) {
            case CENTER:
                rect = new Rectangle(leftTopX, 0, rightBotX - leftTopX, rightBotY);
                break;
            case WEST:
                rect = new Rectangle(0, 0, leftTopX, rightBotY);
                break;
            case SOUTH:
                rect = new Rectangle(0, rightBotY, width, leftTopY);
                break;
            case EAST:
                rect = new Rectangle(rightBotX, 0, leftTopX, rightBotY);
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

    @Override
    public void update() {
        rect = calcRectangle();
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.rect(
                rect.x,
                rect.y,
                rect.width,
                rect.height
        );

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }

    //@Override
    public Rectangle getRectangle() {
        return rect;
    }

}
