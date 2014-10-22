/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.AppImpl;
import acromage.game.data.Card;
import acromage.game.data.Player;
import acromage.game.interfaсe.Actionable;
import acromage.game.slot.ActiveSlot;
import acromage.game.slot.DeckSlot;
import acromage.game.slot.FlySlot;
import acromage.game.slot.HandSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone implements Actionable {

    ArrayList<HandSlot> slots;
    DeckSlot deckSlot;
    FlySlot selectedSlot;
    FlySlot newCardSlot;
    ActiveSlot activeSlot;
    HandSlot emptySlot;
    Player player;

    boolean waitingPlayer;

    public String debugstr;

    public Hand(int zone, ActiveSlot activeSlot, DeckSlot deckSlot, Player player) {
        super(zone);
        this.deckSlot = deckSlot;
        this.activeSlot = activeSlot;
        this.player = player;
        slots = new ArrayList<HandSlot>();

        selectedSlot = null;
        newCardSlot = null;
        waitingPlayer = false;
    }

    public int getCount() {
        return player.getCards().size();
    }

    public Player getPlayer() {
        return player;
    }

    public void selectSlot() {

        //selectedSlot = new FlySlot();
    }

    @Override
    public void update() {
        super.update();
        /*
         slots.clear();
         for (int i = 0; i < player.getCards().size(); ++i) {
         HandSlot slot = new HandSlot(this, i);
         slot.update();
         slot.setCard(player.getCards().get(i));
         slots.add(slot);
         }*/
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        for (int i = 0; i < slots.size(); ++i) {
            HandSlot slot = slots.get(i);
            slot.render(renderer, spriteBatch);
        }
    }

    @Override
    public void action(float delta) {

    }

    public boolean promptToSelect(float propX, float propY, boolean drop) {
        if (waitingPlayer) {
            for (HandSlot handSlot : slots) {
                if (handSlot.contains(propX, propY)) {
                    return playSlot(handSlot, handSlot.getCard(), drop);
                }
            }
        }
        return false;
    }

    public boolean promptToSelect(int position, Card card, boolean drop) {
        if (waitingPlayer && position >= 0 && position < slots.size()) {
            return playSlot(slots.get(position), card, drop);
        } else {
            return false;
        }
    }

    public boolean playSlot(HandSlot handSlot, Card card, boolean drop) {
        if (waitingPlayer) {

            if (player.playable(card) || drop) {
                waitingPlayer = false;
                FlySlot selectedSlot = new FlySlot(handSlot, activeSlot);
                selectedSlot.setPlayedStep(AppImpl.control.getCurrentStepCount());
                selectedSlot.setDroped(drop);
                selectedSlot.setCard(card);

                AppImpl.control.AnimateFlySlot(selectedSlot, null);

                player.removeCard(card);

                emptySlot = handSlot;
                emptySlot.setCard(null);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void takeCard(boolean setWaiting) {
        if (emptySlot == null) {
            for (int i = player.getCards().size(); i < AppImpl.settings.cardCount; ++i) {
                HandSlot slot = new HandSlot(this, i);
                slot.update();
                slots.add(slot);
                takeOneCard(slot, false);
            }
        } else {
            takeOneCard(emptySlot, true);
        }
    }

    private void takeOneCard(HandSlot handSlot, final boolean setWaiting) {
        FlySlot newCardSlot = new FlySlot(deckSlot, handSlot);
        final Card card = AppImpl.cardManager.selectRandomCard();
        newCardSlot.setCard(card);
        AppImpl.control.AnimateFlySlot(newCardSlot, new Runnable() {

            @Override
            public void run() {
                player.takeCard(card);
                if (setWaiting) {
                    setWaitingPlayer() ;
                }
            }
        });
    }

    public void setWaitingPlayer() {
        waitingPlayer = true;
        player.ding();
    }

}
