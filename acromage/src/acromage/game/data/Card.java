/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage.game.data;

import acromage.game.AppImpl;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author elduderino
 */
public class Card {
    
    void CardAction(){
        //предписание карты
    }

    public TextureRegion getTexture() {
        return AppImpl.resources.deckUndoTexture;
    }
}
