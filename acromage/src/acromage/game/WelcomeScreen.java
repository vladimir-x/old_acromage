/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author admin
 */
public class WelcomeScreen extends BaseScreen {

    public SpriteBatch spriteBatch;

    WelcomeScreen(GameInput input) {
        super(input);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(ApplicationImpl.resources.welcomeTexture, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height); 
        spriteBatch.setProjectionMatrix(cam.combined);
    }
    
    

}
