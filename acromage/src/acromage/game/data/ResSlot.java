/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.ApplicationImpl;
import acromage.game.desk.ResPanel;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author admin
 */
public class ResSlot implements Rendereble {

    Rectangle rect;

    ResPanel owner;
    int pos;
    TextureRegion textureRegion;

    public ResSlot(ResPanel owner, int pos, TextureRegion textureRegion) {
        this.owner = owner;
        this.pos = pos;
        this.textureRegion = textureRegion;
    }

    @Override
    public void update() {
        float centrX = owner.getRectangle().x + owner.getRectangle().width / 2.f;
        float centrY = owner.getRectangle().y + owner.getRectangle().height / 2.f;

        float x = centrX - ApplicationImpl.settings.resTextureWidth / 2.f;
        float y = centrY - ApplicationImpl.settings.resTextureHeight * (1 / 2.f + pos - 1);
        
        rect = new Rectangle(x, y,
                ApplicationImpl.settings.resTextureWidth,
                ApplicationImpl.settings.resTextureHeight);
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(textureRegion, rect.x, rect.y);
        spriteBatch.end();
    }

    public Rectangle getRectangle() {
        return rect;
    }

}
