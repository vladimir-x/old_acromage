/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.ApplicationImpl;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
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

        float leftBotX = width * vertBound;
        float leftBotY = height * horizBound;
        float rightTopX = width - leftBotX;
        float rightTopY = height - leftBotY;

        Rectangle rect = null;

        switch (zone) {
            case CENTER:
                rect = new Rectangle(leftBotX, leftBotY, rightTopX - leftBotX, rightTopY);
                break;
            case WEST://запад
                rect = new Rectangle(0, leftBotY, leftBotX, rightTopY);
                break;
            case SOUTH:
                rect = new Rectangle(0, 0, width, leftBotY);
                break;
            case EAST:
                rect = new Rectangle(rightTopX, leftBotY, leftBotX, rightTopY);
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
    public void render(ShapeRenderer renderer,SpriteBatch spriteBatch) {
                
        Gdx.gl.glEnable(GL10.GL_BLEND);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.rect(
                rect.x,
                rect.y,
                rect.width,
                rect.height
        );
        renderer.end();

    }

    //@Override
    public Rectangle getRectangle() {
        return rect;
    }

}
