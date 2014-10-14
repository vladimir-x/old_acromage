/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.data;

import acromage.game.AppImpl;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Computer extends Player {

    private static Random randomGen = new Random(System.currentTimeMillis());

    @Override
    public void ding() {
        System.out.println("computerStep st");
        randomStep();
        System.out.println("computerStep en");
    }

    public void randomStep() {
        int r = randomGen.nextInt(cards.size());
        if (!AppImpl.control.playCard(r, cards.get(r), false)) {
            AppImpl.control.playCard(r, cards.get(r), true);
        }
    }

    public void randomStepDrop() {
        int r = randomGen.nextInt(cards.size());
        AppImpl.control.playCard(r, cards.get(r), true);
    }

    @Override
    public ArrayList<Card> getCards() {
        return maskCards;
    }
}
