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
    public TextureRegion boardTexture;
    public TextureRegion deckUndoTexture;

    protected static Map<String, TextureRegion> textureMap;

    public Resource() {

        Texture texture;

        //фоны
        texture = new Texture(ApplicationImpl.settings.welcomeTexture);
        welcomeTexture = new TextureRegion(texture, ApplicationImpl.settings.welcomeTextureWidth, ApplicationImpl.settings.welcomeTextureHeight);

        texture = new Texture(ApplicationImpl.settings.boardTexture);
        boardTexture = new TextureRegion(texture, ApplicationImpl.settings.boardTextureWidth, ApplicationImpl.settings.boardTextureHeight);

        //куски
        texture = new Texture(ApplicationImpl.settings.itemsTexture);
        deckUndoTexture = new TextureRegion(texture,
                ApplicationImpl.settings.deckUndoTextureX,
                ApplicationImpl.settings.deckUndoTextureY,
                ApplicationImpl.settings.deckTextureWidth,
                ApplicationImpl.settings.deckTextureHeight);

        
        //колода
        textureMap = new HashMap<String, TextureRegion>();
        texture = new Texture(ApplicationImpl.settings.deckTexture);
        TextureRegion[][] regions = TextureRegion.split(texture,
                ApplicationImpl.settings.deckTextureWidth,
                ApplicationImpl.settings.deckTextureHeight
        );

        for (int i = 0; i < ApplicationImpl.settings.deckCountY; ++i) {
            for (int j = 0; j < ApplicationImpl.settings.deckCountX; ++j) {
                String name = i + "_" + j;
                textureMap.put(name, regions[i][j]);
            }
        }

    }

    public TextureRegion getGameTexture(String name) {
        return textureMap.get(name);
    }
}
