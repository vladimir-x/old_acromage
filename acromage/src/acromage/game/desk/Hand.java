/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.AppImpl;
import acromage.game.Arcomage;
import acromage.game.data.Card;
import acromage.game.data.Player;
import acromage.game.interfaсe.Actionable;
import acromage.game.slot.ActiveSlot;
import acromage.game.slot.FlySlot;
import acromage.game.slot.HandSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone implements Actionable {

    ArrayList<HandSlot> slots;
    FlySlot selectedSlot;
    ActiveSlot activeSlot;
    HandSlot emptySlot;
    Player player;

    public String debugstr;

    public Hand(int zone, ActiveSlot activeSlot, Player player) {
        super(zone);
        this.activeSlot = activeSlot;
        this.player = player;
        slots = new ArrayList<HandSlot>();
        for (int i = 0; i < AppImpl.settings.cardCount; ++i) {
            slots.add(new HandSlot(this, i));
        }
        selectedSlot = null;
    }

    public int getCount() {
        return slots.size();
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

        for (int i = 0; i < slots.size(); ++i) {
            slots.get(i).setPos(i);
            slots.get(i).update();
        }
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        ArrayList<Card> cards = player.getCards();
        for (int i = 0; i < cards.size(); ++i) {
            HandSlot slot = slots.get(i);
            slot.card = cards.get(i);
            slot.render(renderer, spriteBatch);
        }

        if (selectedSlot != null) {
            selectedSlot.render(renderer, spriteBatch);
        }
    }

    @Override
    public void action(float delta) {
        if (selectedSlot != null) {
            selectedSlot.action(delta);
            if (selectedSlot.card == null) {
                selectedSlot = null;
            }
        }
    }

    public boolean promptToSelect(float propX, float propY, boolean drop) {
        for (HandSlot handSlot : slots) {
            if (handSlot.contains(propX, propY) && selectedSlot == null) {
                return playSlot(handSlot, drop);
            }
        }
        return false;
    }

    public boolean promptToSelect(int position, boolean drop) {
        if (position >= 0 && position < slots.size()) {
            return playSlot(slots.get(position), drop);
        } else {
            return false;
        }
    }

    public boolean playSlot(HandSlot handSlot, boolean drop) {
        if (selectedSlot == null) {

            if (player.playable(handSlot.card) || drop) {
                selectedSlot = new FlySlot(handSlot, activeSlot);

                if (drop) {
                    selectedSlot.setDroped();
                }
                emptySlot = handSlot;
                emptySlot.card = null;
                update();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    
    public void takeCard(){
        player.takeCard(1);
    }
}
