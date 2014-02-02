/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author admin
 */
public class ApplicationImpl extends Game {

    public static Settings settings;
    
    private Arcomage acromage;
    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private GameInput input;

    private boolean gameStart;

    public ApplicationImpl(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void create() {
        gameStart = false;
        acromage = new Arcomage();
        input = new GameInput(acromage);
        welcomeScreen = new WelcomeScreen(input);
        gameScreen = new GameScreen(acromage,input);

        setScreen(welcomeScreen);
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
    }

    public Settings getSettings() {
        return settings;
    }

    public void restart() {
        gameStart = true;
        acromage = new Arcomage();
        setScreen(gameScreen);
    }

    public void end() {
        gameStart = false;
        setScreen(welcomeScreen);
    }

    public boolean isGameStart() {
        return gameStart;
    }
}
