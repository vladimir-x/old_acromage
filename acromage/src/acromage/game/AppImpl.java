/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.screen.GameScreen;
import acromage.game.screen.WelcomeScreen;
import com.badlogic.gdx.Game;

/**
 *
 * @author admin
 */
public class AppImpl extends Game {

    public static Settings settings;
    public static Resource resources;
    
    public Arcomage acromage;
    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private GameInput input;

    private boolean gameStart;

    public AppImpl(Settings settings) {
        AppImpl.settings = settings;
    }

    @Override
    public void create() {
        AppImpl.resources = new Resource();
        gameStart = false;
        acromage = new Arcomage();
        input = new GameInput(this);
        welcomeScreen = new WelcomeScreen(input);
        gameScreen = new GameScreen(this,input);

        setScreen(gameScreen);
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
