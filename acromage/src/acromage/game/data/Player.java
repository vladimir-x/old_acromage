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

    public void takeCard(int cnt) {

        int taked = 1;
        int index = 0;
        do {
            if ((cards.size() < index && cards.get(index) == null) || cards.size() >= index) {
                if (cards.size() < index) {
                    cards.set(index, AppImpl.cardManager.selectRandomCard());
                    maskCards.set(index, Card.getUndoCard());
                } else {
                    cards.add(AppImpl.cardManager.selectRandomCard());
                    maskCards.add(Card.getUndoCard());
                }
                taked++;
            }
            index++;
        }
        while (taked <= cnt);
        System.out.println("x");    
    }
    // событие - ход передан этому игроку
    public abstract void ding();

    public abstract ArrayList<Card> getCards();

    public boolean playable(Card card) {
        return true;
    }

}
