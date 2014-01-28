/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage.gdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author admin
 */
public class ApplicationImpl extends Game {

    private GameScreen screen;
    private GameInput input;
    
    @Override
    public void create() {
        screen = new GameScreen();
        setScreen(screen);
        Gdx.input.setInputProcessor(input);
    }

    
}
