/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.screen;

import acromage.game.AppImpl;
import acromage.game.Arcomage;
import acromage.game.GameInput;
import acromage.game.GridRender;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author elduderino
 */
public class GameScreen extends BaseScreen {

    private GameInput input;

    private AppImpl appImpl;
    public ShapeRenderer renderer;
    public SpriteBatch spriteBatch;

    GridRender gridRender;

    public GameScreen(AppImpl appImpl, GameInput input) {
        super(input);
        this.appImpl = appImpl;

        spriteBatch = new SpriteBatch();
        renderer = new ShapeRenderer();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.enableBlending();
        renderer.setProjectionMatrix(cam.combined);

        gridRender = new GridRender();

    }

    @Override
    public void render(float delta) {
        //super.render(delta);

        appImpl.acromage.action(delta);
        appImpl.acromage.render(renderer, spriteBatch);
        //gridRender.render(renderer, spriteBatch);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        renderer.setProjectionMatrix(cam.combined);
        spriteBatch.setProjectionMatrix(cam.combined);
        appImpl.acromage.update();
        gridRender.update();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        renderer.dispose();
    }
}
