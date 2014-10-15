/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.AppImpl;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public abstract class Player {

    public ArrayList<Card> cards;

    public ArrayList<Card> maskCards;//карты обратной стороной

    public Player() {
        cards = new ArrayList<Card>();
        maskCards = new ArrayList<Card>();
    }

    public Card takeCard() {

        Card lastCard = null;
        for (int i = cards.size(); i < AppImpl.settings.cardCount; ++i) {
            lastCard = AppImpl.cardManager.selectRandomCard();
            cards.add(lastCard);
            maskCards.add(Card.getUndoCard());
        }
        return lastCard;
    }

    // событие - ход передан этому игроку
    public abstract void ding();

    public abstract ArrayList<Card> getCards();

    public boolean playable(Card card) {
        return true;
    }

    public void removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
            maskCards.remove(1);
        } else {
            System.out.println("not contain (");
        }
    }

}
