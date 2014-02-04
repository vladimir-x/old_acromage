/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elduderino
 */
public class Resource {

    public TextureRegion welcomeTexture;
    public TextureRegion deckTexture;
    
    protected static Map<String, TextureRegion> textureMap;


    public Resource() {

        Texture texture;

        texture = new Texture(ApplicationImpl.settings.welcomeTexture);
        welcomeTexture = new TextureRegion(texture, ApplicationImpl.settings.welcomeTextureWidth, ApplicationImpl.settings.welcomeTextureHeight);

        texture = new Texture(ApplicationImpl.settings.welcomeTexture);
        deckTexture = new TextureRegion(texture, ApplicationImpl.settings.deckTextureWidth, ApplicationImpl.settings.deckTextureHeight);

        textureMap = new  HashMap<String, TextureRegion>();
    }

    
    public TextureRegion getGameTexture(String name){
        return textureMap.get(name);
    }
}
