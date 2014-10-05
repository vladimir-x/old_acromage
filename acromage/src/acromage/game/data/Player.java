/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage.game.data;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public abstract class Player {
    
    
    public ArrayList<Card> cards;

    public Player() {
        cards = new ArrayList<Card>();
    }
    
    public abstract void takeCard(int cnt);
    
    // событие - ход передан этому игроку
    public abstract void ding();
}
