/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class CardManager {
    
    
    

    private static Random rand = new Random(System.currentTimeMillis());
    private List<Card> cards;

    public CardManager() {
        cards = new ArrayList<Card>();

        cards.add(Card.getCard_0_0());
        cards.add(Card.getCard_0_1());
        cards.add(Card.getCard_0_2());
        cards.add(Card.getCard_0_3());
    }

    public Card selectRandomCard() {
        int index = rand.nextInt(cards.size());
        return cards.get(index);
    }

}
