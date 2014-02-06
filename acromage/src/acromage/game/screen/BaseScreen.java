/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.screen;

import acromage.game.ApplicationImpl;
import acromage.game.GameInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 *
 * @author elduderino
 */
public class BaseScreen implements Screen {

    protected GameInput input;
    protected OrthographicCamera cam;

    public BaseScreen(GameInput input) {
        this.input = input;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, ApplicationImpl.settings.cameraWidth, ApplicationImpl.settings.cameraHeight);
        cam.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, ApplicationImpl.settings.cameraWidth, ApplicationImpl.settings.cameraHeight);
        cam.update();
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
    }
}
