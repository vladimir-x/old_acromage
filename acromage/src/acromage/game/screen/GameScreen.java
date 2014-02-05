/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.screen;

import acromage.game.Arcomage;
import acromage.game.GameInput;
import acromage.game.GridRender;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author elduderino
 */
public class GameScreen extends BaseScreen {

    private GameInput input;

    private Arcomage acromage;
    public ShapeRenderer renderer;
    public SpriteBatch spriteBatch;

    GridRender gridRender;

    public GameScreen(Arcomage acromage, GameInput input) {
        super(input);
        this.acromage = acromage;

        spriteBatch = new SpriteBatch();
        renderer = new ShapeRenderer();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.enableBlending();
        renderer.setProjectionMatrix(cam.combined);

        gridRender = new GridRender();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        acromage.action(delta);
        
        acromage.render(spriteBatch);
        gridRender.render(spriteBatch);
        
        Gdx.gl.glEnable(GL10.GL_BLEND);
        
        acromage.render(renderer);
        gridRender.render(renderer);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        renderer.setProjectionMatrix(cam.combined);
        spriteBatch.setProjectionMatrix(cam.combined);
        acromage.update();
        gridRender.update();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        renderer.dispose();
    }
}
