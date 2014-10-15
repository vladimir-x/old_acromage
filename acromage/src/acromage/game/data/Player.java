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

    public Card takeCard(int cnt) {

        int taked = 1;
        int index = 0;

        Card lastCard = null;
        do {
            if ((cards.size() < index && cards.get(index) == null) || cards.size() >= index) {
                lastCard = AppImpl.cardManager.selectRandomCard();

                if (cards.size() < index) {
                    cards.set(index, lastCard);
                    maskCards.set(index, Card.getUndoCard());
                } else {
                    cards.add(lastCard);
                    maskCards.add(Card.getUndoCard());
                }
                taked++;
            }
            index++;
        } while (taked <= cnt);
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
        } else {
            System.out.println("not contain (");
        }
    }

}
