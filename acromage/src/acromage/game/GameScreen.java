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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class GameScreen implements Screen {

    private GameInput input;

    private Arcomage acromage;
    public OrthographicCamera cam;
    public ShapeRenderer renderer;

    public GameScreen(Arcomage acromage, GameInput input) {
        this.input = input;
        this.acromage = acromage;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Arcomage.settings.cameraWidth, Arcomage.settings.cameraHeight);
        cam.update();
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        acromage.action(delta);
        acromage.render(renderer);

    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(true, Arcomage.settings.cameraWidth, Arcomage.settings.cameraHeight);
        cam.update();
        renderer.setProjectionMatrix(cam.combined);
        acromage.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
