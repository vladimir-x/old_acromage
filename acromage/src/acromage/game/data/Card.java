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
public abstract class Card {

    boolean switchTurn = true;

    void CardAction() {
        //предписание карты
    }

    public abstract TextureRegion getTexture();

    public abstract void init();

    public abstract void play();

    public boolean isSwitchTurn() {
        return switchTurn;
    }

    public void setSwitchTurn(boolean switchTurn) {
        this.switchTurn = switchTurn;
    }

    public static Card getUndoCard() {
        return new Card() {

            @Override
            public TextureRegion getTexture() {
                return AppImpl.resources.deckUndoTexture;
            }

            @Override
            public void play() {
            }

            @Override
            public void init() {
                setSwitchTurn(true);
            }
        };
    }

    public static Card getCard_0_0() {
        return new Card() {

            @Override
            public TextureRegion getTexture() {
                return AppImpl.resources.getGameTexture(0, 0);
            }

            @Override
            public void play() {
            }

            @Override
            public void init() {
                setSwitchTurn(true);
            }
        };
    }

    public static Card getCard_0_1() {
        return new Card() {

            @Override
            public TextureRegion getTexture() {
                return AppImpl.resources.getGameTexture(0, 1);
            }

            @Override
            public void play() {
            }

            @Override
            public void init() {
                setSwitchTurn(true);
            }
        };
    }

    public static Card getCard_0_2() {
        return new Card() {

            @Override
            public TextureRegion getTexture() {
                return AppImpl.resources.getGameTexture(0, 2);
            }

            @Override
            public void play() {
            }

            @Override
            public void init() {
                setSwitchTurn(true);
            }
        };
    }

    public static Card getCard_0_3() {
        return new Card() {

            @Override
            public TextureRegion getTexture() {
                return AppImpl.resources.getGameTexture(0, 3);
            }

            @Override
            public void play() {
            }

            @Override
            public void init() {
                setSwitchTurn(true);
            }
        };
    }
}
