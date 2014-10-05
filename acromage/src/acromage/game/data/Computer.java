/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.AppImpl;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Computer extends Player {

    @Override
    public void takeCard(int cnt) {
        for (int i = 0; i < cnt; ++i) {
            Card c = AppImpl.cardManager.selectRandomCard();
            cards.add(c);
        }
    }

    @Override
    public void ding() {
        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
                }
                AppImpl.control.switchTurn();
            }
        }.run();
    }

}
