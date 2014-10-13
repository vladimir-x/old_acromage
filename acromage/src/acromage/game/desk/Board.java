/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.data.Player;
import acromage.game.interfaсe.Actionable;
import acromage.game.slot.ActiveSlot;
import acromage.game.slot.DeckSlot;
import acromage.game.slot.PlayedSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Board extends Deskzone implements Actionable {

    private static final int LINE_CNT = 4;
    public static final int CARDS_SPACE_X = 30;
    public static final int CARDS_SPACE_Y = 40;

    DeckSlot deckSlot;
    ActiveSlot activeSlot;

    ArrayList<PlayedSlot> playedSlots;
    PlayedSlot lastSlot;

    public Board(int zone) {
        super(zone);
        deckSlot = new DeckSlot(this);
        activeSlot = new ActiveSlot(this);
        playedSlots = new ArrayList<PlayedSlot>();
        makeEmptySlot();
    }

    @Override
    public void update() {
        super.update();

        deckSlot.update();
        activeSlot.update();

        for (PlayedSlot playedSlot : playedSlots) {
            playedSlot.update();
        }

    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        for (PlayedSlot playedSlot : playedSlots) {
            playedSlot.render(renderer, spriteBatch);
        }
        deckSlot.render(renderer, spriteBatch);
        activeSlot.render(renderer, spriteBatch);

    }

    public void makeEmptySlot() {

        int posX = 1, posY = 0;
        if (lastSlot != null) {
            if (lastSlot.posX != LINE_CNT - 1) {
                posX = lastSlot.posX + 1;
                posY = lastSlot.posY;
            } else {
                posX = 0;
                posY = lastSlot.posY + 1;
            }
        }

        lastSlot = new PlayedSlot(this, posX, posY);

        playedSlots.add(lastSlot);

    }

    public ActiveSlot getActiveSlot() {
        return activeSlot;
    }

    public PlayedSlot getLastPlayedSlot() {
        return lastSlot;
    }

    @Override
    public void action(float delta) {
        activeSlot.action(delta);
    }

    public void passCard(Hand hand) {
        hand.getPlayer().ding();
    }
}
