/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.File;
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

    public TextureRegion brickTexture;
    public TextureRegion gemTexture;
    public TextureRegion beastTexture;

    protected static Map<String, TextureRegion> cardsTextureMap;
    protected static TextureRegion[][] cardsTextureArray;

    public BitmapFont font;

    public Resource() {

        Texture texture;

        //фоны
        texture = new Texture(AppImpl.settings.welcomeTexture);
        welcomeTexture = new TextureRegion(texture, AppImpl.settings.welcomeTextureWidth, AppImpl.settings.welcomeTextureHeight);

        texture = new Texture(AppImpl.settings.boardTexture);
        boardTexture = new TextureRegion(texture, AppImpl.settings.boardTextureWidth, AppImpl.settings.boardTextureHeight);

        //куски
        texture = new Texture(AppImpl.settings.itemsTexture);

        deckUndoTexture = new TextureRegion(texture,
                AppImpl.settings.deckUndoTextureX,
                AppImpl.settings.deckUndoTextureY,
                AppImpl.settings.deckTextureWidth,
                AppImpl.settings.deckTextureHeight);

        brickTexture = new TextureRegion(texture,
                AppImpl.settings.brickTextureX,
                AppImpl.settings.brickTextureY,
                AppImpl.settings.resTextureWidth,
                AppImpl.settings.resTextureHeight);
        gemTexture = new TextureRegion(texture,
                AppImpl.settings.gemTextureX,
                AppImpl.settings.gemTextureY,
                AppImpl.settings.resTextureWidth,
                AppImpl.settings.resTextureHeight);
        beastTexture = new TextureRegion(texture,
                AppImpl.settings.beastTextureX,
                AppImpl.settings.beastTextureY,
                AppImpl.settings.resTextureWidth,
                AppImpl.settings.resTextureHeight);

        // Шрифты
        font = new BitmapFont(
                new FileHandle(AppImpl.settings.fontFnt),
                new FileHandle(AppImpl.settings.fontPng),
                false
        );

        //колода
        cardsTextureMap = new HashMap<String, TextureRegion>();
        texture = new Texture(AppImpl.settings.deckTexture);
        cardsTextureArray = TextureRegion.split(texture,
                AppImpl.settings.deckTextureWidth,
                AppImpl.settings.deckTextureHeight
        );

        for (int i = 0; i < AppImpl.settings.deckCountY; ++i) {
            for (int j = 0; j < AppImpl.settings.deckCountX; ++j) {
                String name = i + "_" + j;
                cardsTextureMap.put(name, cardsTextureArray[i][j]);
            }
        }

    }

    public TextureRegion getGameTexture(String name) {
        return cardsTextureMap.get(name);
    }

    public TextureRegion getGameTexture(int y, int x) {
        if (cardsTextureArray.length > y && cardsTextureArray[y].length > x) {
            return cardsTextureArray[y][x];
        } else {
            return deckUndoTexture;
        }
    }
}
