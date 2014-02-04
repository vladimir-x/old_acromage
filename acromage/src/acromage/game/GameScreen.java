/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elduderino
 */
public class GameScreen extends BaseScreen {

    private GameInput input;

    private Arcomage acromage;
    public ShapeRenderer renderer;
    public SpriteBatch spriteBatch;

    public GameScreen(Arcomage acromage, GameInput input) {
        super(input);
        this.acromage = acromage;

        spriteBatch = new SpriteBatch();
        renderer = new ShapeRenderer();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.enableBlending();
        renderer.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        acromage.action(delta);
        acromage.render(spriteBatch);

        Gdx.gl.glEnable(GL10.GL_BLEND);
        acromage.render(renderer);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        renderer.setProjectionMatrix(cam.combined);
        spriteBatch.setProjectionMatrix(cam.combined);
        acromage.update();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        renderer.dispose();
    }
}
