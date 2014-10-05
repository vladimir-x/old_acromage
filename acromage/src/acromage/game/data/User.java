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
public class User extends Player{

   

    @Override
    public void takeCard(int cnt) {
        for (int i=0;i<cnt;++i){
            Card c = AppImpl.cardManager.selectRandomCard();
            cards.add(c);
        }
    }

    @Override
    public void ding() {
    }
    
    
    
}
