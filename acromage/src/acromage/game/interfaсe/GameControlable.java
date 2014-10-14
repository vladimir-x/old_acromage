/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage.game.interfaсe;

import acromage.game.data.Card;

/**
 *
 * @author admin
 */
public interface GameControlable {
    
    // передать ход
    void switchTurn();

    public boolean playCard(int r, Card card,boolean drop);
}
