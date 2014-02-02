/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author admin
 */
public class WelcomeScreen implements Screen {

    private GameInput input;

    public OrthographicCamera cam;
    public SpriteBatch spriteBatch;

    Texture texture;

    WelcomeScreen(GameInput input) {
        this.input = input;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, ApplicationImpl.settings.cameraWidth, ApplicationImpl.settings.cameraHeight);
        cam.update();

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        texture = new Texture(ApplicationImpl.settings.welcomeTexture);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(texture, 0, 0);
        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {
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
