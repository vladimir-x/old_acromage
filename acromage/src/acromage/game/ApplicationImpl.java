/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author admin
 */
public class ApplicationImpl extends Game {

    private Settings settings;
    private Acromage acromage;
    private GameScreen screen;
    private GameInput input;

    public ApplicationImpl(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void create() {
        acromage = new Acromage(settings);
        input = new GameInput(settings, acromage);
        screen = new GameScreen(settings, acromage) {

            @Override
            void onShow() {
                Gdx.input.setInputProcessor(input);
            }

            @Override
            void onHide() {
                Gdx.input.setInputProcessor(null);
            }
        };

        setScreen(screen);
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

    public Settings getSettings() {
        return settings;
    }

}
